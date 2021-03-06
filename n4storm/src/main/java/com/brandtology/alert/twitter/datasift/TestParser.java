/**
 * 
 */
package com.brandtology.alert.twitter.datasift;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.brandtology.entity.Tweet;
import com.brandtology.io.log.SystemLogger;
import com.brandtology.util.FileHandler;
import com.brandtology.util.FormatConstant;

/**
 * @author leah
 *
 */

public class TestParser {

	/**
	 * 
	 */
	public static List parseJSON(String value){
		List results = new ArrayList();
		if(value==null || value.length()==0)
			return results;

		try{
			JSONObject obj = JSONObject.fromObject(value); 
				if(obj!=null){
					/*int kloutScore = 0;
					JSONObject klout = obj.getJSONObject("klout");
					if(klout!=null){
						kloutScore = klout.getInt("score");
					}*/
					//SystemLogger.printInfo(i+"\t"+kloutScore);
					
					/*String lang = null;
					JSONObject language = obj.getJSONObject("language");
					if(language!=null){
						lang = language.getString("tag");
					}*/
					//SystemLogger.printInfo(i+"\t"+lang);
					
					JSONObject interaction = obj.getJSONObject("interaction");
					System.out.println("INTERACTION");

					JSONObject author = interaction.getJSONObject("author");
					String screenname = author.getString("username");
					String voiceURL = author.getString("link");

					long timestamp = FormatConstant.DATASIFT_TWEET_DF().parse(interaction.getString("created_at")).getTime();
					String permalink = interaction.getString("link");
					String content = interaction.getString("content");

					JSONObject twitter = obj.getJSONObject("twitter");
					long oid = twitter.getLong("id");
					
					JSONObject user = twitter.getJSONObject("user");
					if(user==null){
						JSONObject retweet = twitter.getJSONObject("retweet");
						if(retweet!=null)
							user = retweet.getJSONObject("user");
					}
					
					/*if(user!=null){
						String name = user.getString("name");
						String description = user.getString("description");
						int status = user.getInt("statuses_count");
						int followers = user.getInt("followers_count");
						int friends = user.getInt("friends_count");
					}*/
					
					Tweet tweet = new Tweet(timestamp, content);
					tweet.setTrackbackPermalink(permalink);
					//tweet.setLanguage(lang);
					results.add(tweet);
				}
		}catch(ParseException e){
			SystemLogger.printWarning(e.getMessage());
		}

		return results;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String value = FileHandler.getFileContent(System.getProperty("user.dir")+"/data/json_test.json");
		List results = parseJSON(value);
		for(int i=0;i<results.size();i++){
			Tweet tweet = (Tweet)results.get(i);
			System.out.println(tweet.writeToLogger());
		}

	}

}
