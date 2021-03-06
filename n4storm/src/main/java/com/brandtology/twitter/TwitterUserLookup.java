package com.brandtology.twitter;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import net.sf.json.JSONObject;


import org.apache.http.HttpHost;

import com.brandtology.entity.Voice;
import com.brandtology.io.log.SystemLogger;
import com.brandtology.util.FormatConstant;

public class TwitterUserLookup {

	private static SimpleDateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
	private static String punct = " !\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";

	//private static HttpHost proxy = new HttpHost("192.168.50.121", 3128);
	private static HttpClientManager manager = new HttpClientManager();
	private static HttpHost proxy;

	private static String apiUrl = "https://api.twitter.com/1/users/show.json";
	private static String screen = "?screen_name=";

	public static void setup(){

		String ip = new String("192.168.50.121");
		proxy = new HttpHost(ip, 3128);

	}

	/**
	 * 
	 */
	public static int getFollowersCount(String screenName){

		int count = 0;
		try{
			String queryURL = apiUrl+screen+screenName;

			HttpClientManager manager = new HttpClientManager();
			String value = manager.getResponse(queryURL, "UTF-8", proxy);
			if(value != null){
				JSONObject json = JSONObject.fromObject(value); 	
				//					SystemLogger.printInfo("**********************************************************************");	
				//					SystemLogger.printInfo(value);	
				//					SystemLogger.printInfo("**********************************************************************");

				count = json.getInt("followers_count");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * 
	 */
	public static Voice getTwitterUser(String screenName){

		Voice voice = null;
		try{
			String queryURL = apiUrl+screen+screenName;

			HttpClientManager manager = new HttpClientManager();
			String value = manager.getResponse(queryURL, "UTF-8", proxy);
			if(value != null){				
				JSONObject json = JSONObject.fromObject(value); 	
				//					SystemLogger.printInfo("**********************************************************************");	
				//					SystemLogger.printInfo(value);	
				//					SystemLogger.printInfo("**********************************************************************");

				int listedCount = json.getInt("listed_count");
				String utcOffset = json.getString("utc_offset");
				String avatar = json.getString("profile_image_url");
				String name = json.getString("name");
				String uid = json.getString("id_str");
				String createdAt = json.getString("created_at");
				String language = json.getString("lang");
				String description = json.getString("description");
				String location = json.getString("location");
				String timeZone = json.getString("time_zone");
				int statusCount = json.getInt("statuses_count");
				int followersCount = json.getInt("followers_count");
				int friendsCount = json.getInt("friends_count");
				
				voice = new Voice(screenName);
				voice.setName(name);
				voice.setUid(uid);
				voice.setAvatar(avatar);
				voice.setDescription(description);
				voice.setFriendsCount(friendsCount);
				voice.setFollowersCount(followersCount);
				voice.setStatusCount(statusCount);
				voice.setListedCount(listedCount);
				voice.setLanguage(language);
				voice.setLocation(location);
				voice.setTimeZone(timeZone);
				if(utcOffset!=null && !utcOffset.equals("null"))
					voice.setUTCOffset(Long.parseLong(utcOffset));
				try{
					voice.setCreatedAt(FormatConstant.TWITTER_CA_DF().parse(createdAt).getTime());
				}catch(ParseException e){
					SystemLogger.printWarning(e.getMessage());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return voice;
	}
	
	/**
	 * 
	 */
	public static void main(String[] args){
		String screenName = "leahsuperb";
		setup();
		 
		Voice user = getTwitterUser(screenName);
		System.out.println(user.writeToLogger());
	}
}
