/**
 * 
 */
package com.brandtology.alert.twitter.datasift;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.filefilter.WildcardFileFilter;

import com.brandtology.entity.Place;
import com.brandtology.entity.Tweet;
import com.brandtology.entity.Voice;
import com.brandtology.io.log.SystemLogger;
import com.brandtology.util.FieldNameConstant;
import com.brandtology.util.FileHandler;
import com.brandtology.util.FormatConstant;

/**
 * @author leah
 *
 */
public class DatasiftParser {
	
	public static final String DATASIFT_DIR = "/opt/datasift";//"/home/leah/workspace/microblog_alert/data";//
    public  static Tweet parseTweet(String value){
        String input = value.replace("}{", "}, {");
        JSONObject obj = JSONObject.fromObject(input);
        return JsonObjectToTweet(obj);
    }

    private static Tweet JsonObjectToTweet(JSONObject obj){


        try{


        if(obj!=null){
            String gender = null;
            if(obj.has("demographic")){
                JSONObject demographic = obj.getJSONObject("demographic");
                gender = demographic.getString("gender");
            }

            int kloutScore = 0;
            if(obj.has("klout")){
                JSONObject klout = obj.getJSONObject("klout");
                kloutScore = klout.getInt("score");
            }
            //SystemLogger.printInfo(i+"\t"+kloutScore);

            String lang = null;
            if(obj.has("language")){
                JSONObject language = obj.getJSONObject("language");
                lang = language.getString("tag");
            }
            //SystemLogger.printInfo(i+"\t"+lang);

            int sentiment = 0;
            if(obj.has("salience")){
                JSONObject salience = obj.getJSONObject("salience");
                JSONObject content = salience.getJSONObject("content");
                if(content!=null){
                    sentiment = content.getInt("sentiment");
                }
            }

            List linkList = null;
            if(obj.has("links")){
                linkList = new ArrayList();
                JSONObject links = obj.getJSONObject("links");
                JSONArray urls = links.getJSONArray("url");
                for(Iterator it=urls.iterator();it.hasNext();){
                    String url = (String)it.next();
                    linkList.add(url);
                }
            }

            JSONObject interaction = obj.getJSONObject("interaction");

            String source = null;
            if(interaction.has("source")){
                source = interaction.getString("source");
            }
            //SystemLogger.printInfo(i+"\t"+lang);

            JSONObject author = interaction.getJSONObject("author");
            String screenname = author.getString("username");
            String name = author.getString("name");
            Long id = author.getLong("id");
            String avatar = author.getString("avatar");
            String voiceURL = author.getString("link");
            long timestamp = FormatConstant.DATASIFT_TWEET_DF().parse(interaction.getString("created_at")).getTime();
            //long timestamp = System.currentTimeMillis();
            String permalink = interaction.getString("link");
            String content = interaction.getString("content");

            JSONObject twitter = obj.getJSONObject("twitter");
            String mid = twitter.getString("id");

            Place geolocation = null;
            if(twitter.has("geo")){
                JSONObject geo = twitter.getJSONObject("geo");
                double latitude = geo.getDouble("latitude");
                double longitude = geo.getDouble("longitude");
                geolocation = new Place(latitude, longitude);
            }
            if(twitter.has("place")){
                JSONObject place = twitter.getJSONObject("place");
                String geoURL = place.getString("url");
                String shortName = place.getString("name");
                String fullName = place.getString("full_name");
                String countryName = place.getString("country");
                String countryCode = place.getString("country_code");
                geolocation.setCountryCode(countryCode);
                geolocation.setCountryName(countryName);
                geolocation.setFullName(fullName);
                geolocation.setName(shortName);
                geolocation.setGeoURL(geoURL);
            }

            int relationshipTypeID = 0;
            String pmid = null;
            if(twitter.containsKey("in_reply_to_status_id")){
                pmid = twitter.getString("in_reply_to_status_id");
                relationshipTypeID=FieldNameConstant.REPLY_RELATION;
            }else if(twitter.containsKey("in_reply_to_screen_name")){
                relationshipTypeID = FieldNameConstant.MENTION_RELATION;
            }

            JSONObject user = null;
            if(twitter.has("user")){
                user = twitter.getJSONObject("user");
            }else if(twitter.has("retweet")){
                JSONObject retweet = twitter.getJSONObject("retweet");
                user = retweet.getJSONObject("user");
                relationshipTypeID = FieldNameConstant.RETWEET_RELATION;

                JSONObject retweeted = twitter.getJSONObject("retweeted");
                pmid = retweeted.getString("id");
            }

            Voice voice = null;
            if(user!=null){
                voice = parseUser(user);
            }else{
                voice = new Voice(screenname);
                voice.setName(name);
                voice.setUid(String.valueOf(id));
                voice.setAvatar(avatar);
                voice.setUrl(voiceURL);
            }
            voice.setGender(gender);

            Tweet tweet = new Tweet(timestamp, content);
            tweet.setMid(mid);
            if(pmid!=null)
                tweet.setParentMid(pmid);
            tweet.setTrackbackPermalink(permalink);
            tweet.setLanguage(lang);
            tweet.setKloutScore(kloutScore);
            tweet.setSentiment(sentiment);
            tweet.setSource(source);
            tweet.setRelationshipTypeID(relationshipTypeID);
            tweet.setAuthor(voice);
            if(linkList!=null && linkList.size()>0)
                tweet.setLinks(linkList);
            if(geolocation!=null)
                tweet.setGeolocation(geolocation);
            return tweet;
             }
            return null;
        }catch(ParseException e){
                e.printStackTrace();
                SystemLogger.printWarning(e.getMessage());
                return null;
        }

    }

	/**
	 * 
	 */
	public static List parseJSON(String value){
		List results = new ArrayList();
		if(value==null || value.length()==0)
			return results;
        String input = value.replace("}{", "}, {");
        JSONObject json = JSONObject.fromObject(input);
        JSONArray arr = json.getJSONArray("list");
        for(int i=0; i<arr.size(); i++){
            JSONObject obj = (JSONObject)arr.get(i);
            Tweet tweet = JsonObjectToTweet(obj) ;
            if(tweet != null)
                results.add(tweet);
        }



		return results;
	}

	/**
	 * 
	 */
	public static Voice parseUser(JSONObject user){
		Voice voice = new Voice();
		
		String name = user.getString("name");
		//String url = user.getString("url");
		String description = null;
		if(user.has("description"))
			description = user.getString("description");
		String location = null;
		if(user.has("location"))
			location = user.getString("location");
		int statusCount =user.containsKey("statuses_count")? user.getInt("statuses_count"):0;
		int followersCount = user.containsKey("followers_count")?user.getInt("followers_count"):0;

		int friendsCount =user.containsValue("friends_count")? user.getInt("friends_count"):0;

        String screenName = user.getString("screen_name");
		String language = user.getString("lang");
		String timeZone = null;
		if(user.has("time_zone"))
			timeZone = user.getString("time_zone");
		long utcOffset = 0;
		if(user.has("utc_offset"))
			utcOffset = user.getLong("utc_offset");
		String listedCount = null;
		if(user.has("listed_count"))
			listedCount = user.getString("listed_count");
		String uid = user.getString("id_str");
		String createdAt = user.getString("created_at");	
		
		voice.setName(name);
		//voice.setUrl(url);
		voice.setDescription(description);
		voice.setLocation(location);
		voice.setStatusCount(statusCount);
		voice.setFollowersCount(followersCount);
		voice.setFriendsCount(friendsCount);
		voice.setScreenName(screenName);
		voice.setLanguage(language);
		voice.setTimeZone(timeZone);
		voice.setUTCOffset(utcOffset);
		if(listedCount!=null && !listedCount.equals("null"))
			voice.setListedCount(Integer.parseInt(listedCount));
		voice.setUid(uid);
		try{
			voice.setCreatedAt(FormatConstant.DATASIFT_TWEET_DF().parse(createdAt).getTime());
		}catch(ParseException e){
			SystemLogger.printWarning(e.getMessage());
		}
		
		return voice;
	}
	
	/**
	 * 
	 */
	public static String readJSONFiles(String countryCode, String date, String time){
		StringBuffer res = new StringBuffer("{\"list\":[");
		BufferedReader br = null;
		

		try {
			File dir = new File(DATASIFT_DIR+"/"+countryCode+"/archive/");
			FileFilter fileFilter = new WildcardFileFilter("datasift_file_"+countryCode+"_"+date+"_"+time+"*_done.json");
			File[] files = dir.listFiles(fileFilter);
			for (int i = 0; i < files.length; i++) {
			   //SystemLogger.printInfo(files[i].getName());
			
			   String currentLine;

			   br = new BufferedReader(new FileReader(files[i]));

			   while ((currentLine = br.readLine()) != null) {
				   res.append(currentLine);
			   }

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		res.append("]}");
		return res.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fileName = new String(System.getProperty("user.dir")+"/data/sample_datasift_file_SG_20120610_001501_done.json");
		String value = FileHandler.getFileContent(fileName);
		
		List results = parseJSON(value);
		for(int i=0;i<results.size();i++){
			Tweet tweet = (Tweet)results.get(i);
			Voice user = tweet.getAuthor();
			//System.out.println(user.writeToLogger());
			System.out.println(tweet.writeToLogger());
			
			/*List links = tweet.getLinks();
			if(links!=null && links.size()>0){
				System.out.println(tweet.writeToLogger());
				for(int j=0;j<links.size();j++){
					System.out.println((String)links.get(j));
				}
			}*/
			
			/*Place geolocation = tweet.getGeolocation();
			if(geolocation!=null){
				System.out.println(geolocation.writeToLogger());
			}*/
		}
	}

}
