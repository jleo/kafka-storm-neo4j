package com.brandtology.db.mongodb;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.brandtology.entity.Place;
import com.brandtology.entity.Tweet;
import com.brandtology.io.log.SystemLogger;
import com.brandtology.util.FieldNameConstant;
import com.brandtology.util.FormatConstant;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import com.mongodb.WriteResult;

public class MongoDBTweetHandler {
	
	private static DB db = null;
	private static Mongo m = null;

	public static Boolean PRINT_DETAILS = false;

	String collectionName;
	public MongoDBTweetHandler(String name){
		this.collectionName = name;
	}

	public boolean createConnection()throws SQLException, UnknownHostException, MongoException{
		//m = new Mongo("192.168.51.241",20000);

		List addrs = new ArrayList();
		addrs.add( new ServerAddress( "192.168.51.241" , 20000 ) );
		addrs.add( new ServerAddress( "192.168.51.242" , 20001 ) );
		addrs.add( new ServerAddress( "192.168.51.243" , 20002 ) );

		m = new Mongo( addrs );
		m.slaveOk();

		db=m.getDB("brandtology");
		boolean auth = db.authenticate("root", "brandtology".toCharArray());

		return auth;
	}	

	/**
	 * 
	 */
	public void insertPosts(long channelID, List tweetList) throws ParseException, SQLException, UnknownHostException, MongoException{
		DBCollection coll = db.getCollection(collectionName);	

		for(int i=0;i<tweetList.size(); i++){
			Tweet tweet = (Tweet)tweetList.get(i);
			insertPost(channelID, tweet);
		}
	}


	/**
	 * 
	 */
	public void insertPost(long channelID, Tweet tweet) throws SQLException, UnknownHostException, MongoException{
		DBCollection coll = db.getCollection(collectionName);	

//		if(queryPostExistence(channelID, tweet.getMid())){
//			SystemLogger.printInfo("MONGODB TWEET EXISTED: "+channelID+"\t"+tweet.writeToLogger());
//			
//		}else{

			BasicDBObject data=new BasicDBObject();
			data.put(FieldNameConstant.MESSAGE_ID, tweet.getMid());
			data.put(FieldNameConstant.CHANNEL_ID, channelID); // Client_Account_ID
			data.put(FieldNameConstant.RELATIONSHIP_TYPE_ID, tweet.getRelationshipTypeID());
			data.put(FieldNameConstant.PARENT_MESSAGE_ID, tweet.getParentMid());
			
			data.put(FieldNameConstant.CONTENT, tweet.getContent()); // Content
			data.put(FieldNameConstant.URL, tweet.getTrackbackPermalink());
			long time = tweet.getTimestamp();
			data.put(FieldNameConstant.DATE_POSTED, FormatConstant.SHORT_DP_DF.format(time).substring(0, 8)); // DateTime_Posted
			data.put(FieldNameConstant.DATETIME_POSTED, new Date(tweet.getTimestamp())); // DateTime_Posted
			data.put(FieldNameConstant.DATETIME_INSERTED, new Date(System.currentTimeMillis())); // DateTime_Inserted
			data.put(FieldNameConstant.DATETIME_UPDATED, new Date(System.currentTimeMillis())); // DateTime_Updated
			data.put(FieldNameConstant.VOICE_ID, tweet.getAuthor().getVoiceID());
			data.put(FieldNameConstant.VOICE_USER_ID, tweet.getAuthor().getUid());
			data.put(FieldNameConstant.SOURCE, tweet.getSource());
			data.put(FieldNameConstant.KLOUT_SCORE, tweet.getKloutScore());
			data.put(FieldNameConstant.SENTIMENT, tweet.getSentiment());

			Place place = tweet.getGeolocation();
			if(place!=null){
				data.put(FieldNameConstant.GEO_LATITUDE, place.getLatitude());
				data.put(FieldNameConstant.GEO_LONGITUDE, place.getLongitude());
				data.put(FieldNameConstant.GEO_URL, place.getUrl());
				data.put(FieldNameConstant.COUNTRY, place.getCountryCode());
				data.put(FieldNameConstant.PLACE, place.getName());
			}
			
			List mulist = tweet.getMediaURLs();
			if(mulist!=null && mulist.size()>0){
				BasicDBObject mediaURLs = new BasicDBObject();
				for(int i=0;i<mulist.size();i++){
					String url = (String)mulist.get(i);
					mediaURLs.put(FieldNameConstant.MEDIA_URL, url);
				}
				data.put(FieldNameConstant.MEDIA_URL_LIST, mediaURLs);
			}
			
			List mtlist = tweet.getMentions();
			if(mtlist!=null && mtlist.size()>0){
				BasicDBObject mentions = new BasicDBObject();
				for(int i=0;i<mtlist.size();i++){
					String at = (String)mtlist.get(i);
					mentions.put(FieldNameConstant.MENTION, at);
				}
				data.put(FieldNameConstant.MENTIONS_LIST, mentions);
			}
			
			List lklist = tweet.getLinks();
			if(lklist!=null && lklist.size()>0){
				BasicDBObject links = new BasicDBObject();
				for(int i=0;i<lklist.size();i++){
					String link = (String)lklist.get(i);
					links.put(FieldNameConstant.LINK, link);
				}
				data.put(FieldNameConstant.LINKS_LIST, links);
			}
			
			coll.insert(data);
			if(PRINT_DETAILS)
				SystemLogger.printInfo("MONGODB FINISH INSERT: "+channelID+"\t"+tweet.writeToLogger());
			//record.clear();
//		}
	}

	/**
	 * 
	 */
	private boolean queryPostExistence(long channelID, String mid) throws SQLException, UnknownHostException, MongoException{
		DBCollection coll = db.getCollection(collectionName);

		BasicDBObject queryPost = new BasicDBObject();
		queryPost.put(FieldNameConstant.CHANNEL_ID, channelID);
		queryPost.put(FieldNameConstant.MESSAGE_ID, mid);

		DBObject obj = coll.findOne(queryPost);
		if(obj!=null) {
			if(PRINT_DETAILS)
				SystemLogger.printInfo("MONGODB POST EXIST: "+channelID+"\t"+mid);
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 
	 */
	public void removePost(long channelID, String mid) throws SQLException, UnknownHostException, MongoException{
		DBCollection coll = db.getCollection(collectionName);

		BasicDBObject queryPost = new BasicDBObject();
		queryPost.put(FieldNameConstant.CHANNEL_ID, channelID);
		queryPost.put(FieldNameConstant.MESSAGE_ID, mid);

		coll.remove(queryPost);
		if(PRINT_DETAILS)
			SystemLogger.printInfo("MONGODB POST REMOVED: "+channelID+"\t"+mid);
	}

	/**
	 * 
	 */
	public void removePosts(long channelID, long startTime, long endTime) throws SQLException, UnknownHostException, MongoException{
		DBCollection coll = db.getCollection(collectionName);

		BasicDBObject queryPosts = new BasicDBObject();
		queryPosts.put(FieldNameConstant.CHANNEL_ID, channelID);
		Date start = new Date(startTime);
		Date end = new Date(endTime);
		queryPosts.put(FieldNameConstant.DATETIME_POSTED, new BasicDBObject("$gte", start).append("$lt", end));		

		coll.remove(queryPosts);
		if(PRINT_DETAILS)
			SystemLogger.printInfo("MONGODB POST REMOVED: "+channelID+"\t"+FormatConstant.MONGODB_DF.format(startTime)+"\t"+FormatConstant.MONGODB_DF.format(endTime));
	}

	/**
	 * 
	 */
	public List getPosts(long channelID, int eid, long startTime, long endTime) throws ParseException, SQLException, UnknownHostException, MongoException{
		List result = new ArrayList();
		DBCollection coll = db.getCollection(collectionName);

		BasicDBObject queryPosts = new BasicDBObject();
		queryPosts.put(FieldNameConstant.CHANNEL_ID, channelID);
		queryPosts.put(FieldNameConstant.EVENT_ID, eid);
		Date start = new Date(startTime);
		Date end = new Date(endTime);
		queryPosts.put(FieldNameConstant.DATETIME_POSTED, new BasicDBObject("$gte", start).append("$lt", end));	
		if(PRINT_DETAILS)
			SystemLogger.printInfo("MONGODB RETRIEVE POSTS: "+channelID+"\t"+FormatConstant.MONGODB_DF.format(startTime)+"\t"+FormatConstant.MONGODB_DF.format(endTime));

		DBCursor cur = coll.find(queryPosts);
		while(cur.hasNext()) {

			DBObject obj = (DBObject)cur.next();
			//SystemLogger.printInfo("Find a tweet: "+obj);

			Date datetime = (Date)obj.get(FieldNameConstant.DATETIME_POSTED);
			String voiceUid = (String)obj.get(FieldNameConstant.VOICE_USER_ID);
			String content = (String)obj.get(FieldNameConstant.CONTENT);
			String permalink = (String)obj.get(FieldNameConstant.URL);

			Tweet tweet = new Tweet(datetime.getTime(), content);
			tweet.setTrackbackPermalink(permalink);
			result.add(tweet);
		}
		return result;
	}
	
	/**
	 * 
	 */
	public List getPosts(long channelID, long startTime, long endTime) throws ParseException, SQLException, UnknownHostException, MongoException{
		List results = new ArrayList();
		DBCollection coll = db.getCollection(collectionName);

		BasicDBObject queryPosts = new BasicDBObject();
		queryPosts.put(FieldNameConstant.CHANNEL_ID, channelID);
		Date start = new Date(startTime);
		Date end = new Date(endTime);
		queryPosts.put(FieldNameConstant.DATETIME_POSTED, new BasicDBObject("$gte", start).append("$lt", end));	
		if(PRINT_DETAILS)
			SystemLogger.printInfo("MONGODB RETRIEVE POSTS: "+channelID+"\t"+FormatConstant.MONGODB_DF.format(startTime)+"\t"+FormatConstant.MONGODB_DF.format(endTime));

		DBCursor cur = coll.find(queryPosts);
		while(cur.hasNext()) {

			DBObject obj = (DBObject)cur.next();
			//SystemLogger.printInfo("Find a tweet: "+obj);

			Date datetime = (Date)obj.get(FieldNameConstant.DATETIME_POSTED);
			String voiceUid = (String)obj.get(FieldNameConstant.VOICE_USER_ID);
			String content = (String)obj.get(FieldNameConstant.CONTENT);
			String permalink = (String)obj.get(FieldNameConstant.URL);

			Tweet tweet = new Tweet(datetime.getTime(), content);
			tweet.setTrackbackPermalink(permalink);
			results.add(tweet);
		}
		return results;
	}

	/**
	 * 
	 */
	private boolean updateParentReplyList(long channelID, String mid, String pmid) throws SQLException, UnknownHostException, MongoException{
		DBCollection coll = db.getCollection(collectionName);

		BasicDBObject queryPost = new BasicDBObject();
		queryPost.put(FieldNameConstant.CHANNEL_ID, channelID);
		queryPost.put(FieldNameConstant.MESSAGE_ID, pmid);

		DBObject obj = coll.findOne(queryPost);
		if(obj!=null){
			BasicDBObject replyMid=new BasicDBObject();
			replyMid.put(FieldNameConstant.REPLY_CHILD_MID, mid);

			BasicDBObject updateCommand = new BasicDBObject();
			updateCommand.put("$push", new BasicDBObject(FieldNameConstant.REPLY_CHILD_MID_LIST, replyMid ) );
			
			WriteResult result = coll.update(queryPost, updateCommand);
			SystemLogger.printInfo("UPDATE PARENT REPLY LIST: "+result);
			return true;
		}else
			return false;
	}

	/**
	 * 
	 */
	private boolean updateParentRetweetList(long channelID, String mid, String pmid) throws SQLException, UnknownHostException, MongoException{
		DBCollection coll = db.getCollection(collectionName);

		BasicDBObject queryPost = new BasicDBObject();
		queryPost.put(FieldNameConstant.CHANNEL_ID, channelID);
		queryPost.put(FieldNameConstant.MESSAGE_ID, pmid);

		DBObject obj = coll.findOne(queryPost);
		if(obj!=null){
			BasicDBObject replyMid=new BasicDBObject();
			replyMid.put(FieldNameConstant.RETWEET_CHILD_MID, mid);

			BasicDBObject updateCommand = new BasicDBObject();
			updateCommand.put("$push", new BasicDBObject(FieldNameConstant.RETWEET_CHILD_MID_LIST, replyMid ) );
			
			WriteResult result = coll.update(queryPost, updateCommand);
			SystemLogger.printInfo("UPDATE PARENT REPLY LIST: "+result);
			return true;
		}else
			return false;
	}

	/**
	 * 
	 */
	public static void main(String[] arg) throws Exception{
		
	}
}