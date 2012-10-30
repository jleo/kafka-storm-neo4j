package com.brandtology.db.mongodb;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.brandtology.entity.Place;
import com.brandtology.entity.Tweet;
import com.brandtology.entity.Voice;
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

public class MongoDBVoiceHandler {
	
	private static DB db = null;
	private static Mongo m = null;

	public static Boolean PRINT_DETAILS = false;

	String collectionName;
	public MongoDBVoiceHandler(String name){
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
	public void insertVoice(long channelID, Voice voice) throws SQLException, UnknownHostException, MongoException{
		DBCollection coll = db.getCollection(collectionName);	
		
//		if(voice.getUid()!=null && queryVoiceExistenceByUid(channelID, voice.getUid())){
//			SystemLogger.printInfo("MONGODB VOICE EXISTED: "+channelID+"\t"+voice.writeToLogger());
//			
//		}else if(voice.getScreenName()!=null && queryVoiceExistenceByScreenName(channelID, voice.getScreenName())){
//			SystemLogger.printInfo("MONGODB VOICE EXISTED: "+channelID+"\t"+voice.writeToLogger());
//			
//		}else{

			BasicDBObject data=new BasicDBObject();
			data.put(FieldNameConstant.USER_ID, voice.getUid());
			data.put(FieldNameConstant.SCREEN_NAME, voice.getScreenName());
			data.put(FieldNameConstant.NAME, voice.getName());
			data.put(FieldNameConstant.DESCRIPTION, voice.getDescription());
			data.put(FieldNameConstant.GENDER, voice.getGender());
			data.put(FieldNameConstant.PROFILE_PICTURE_URL, voice.getAvatar());
			data.put(FieldNameConstant.URL, voice.getUrl());
			data.put(FieldNameConstant.LANGUAGE, voice.getLanguage());
			data.put(FieldNameConstant.LOCATION, voice.getLocation());
			data.put(FieldNameConstant.TIME_ZONE, voice.getTimeZone());
			data.put(FieldNameConstant.DATETIME_USER_CREATED, voice.getCreatedAtTimeStr());
			data.put(FieldNameConstant.STATUS_COUNT, voice.getStatusCount());
			data.put(FieldNameConstant.FOLLOWERS_COUNT, voice.getFollowersCount());
			data.put(FieldNameConstant.FRIENDS_COUNT, voice.getFriendsCount());
			data.put(FieldNameConstant.LISTED_COUNT, voice.getListedCount());
			data.put(FieldNameConstant.UTC_OFFSET, voice.getUTCOffset());
			data.put(FieldNameConstant.DATE_OF_BIRTH, FormatConstant.SIMPLE_DF.format(voice.getDateOfBirth()));
			data.put(FieldNameConstant.COUNTRY, voice.getCountry());
			data.put(FieldNameConstant.PROVINCE, voice.getProvince());
			data.put(FieldNameConstant.CITY, voice.getCity());
			
			coll.insert(data);
			if(PRINT_DETAILS)
				SystemLogger.printInfo("MONGODB VOICE FINISH INSERT: "+channelID+"\t"+voice.writeToLogger());
			//record.clear();
//		}
	}

	/**
	 * 
	 */
	private boolean queryVoiceExistenceByUid(long channelID, String uid) throws SQLException, UnknownHostException, MongoException{
		DBCollection coll = db.getCollection(collectionName);

		BasicDBObject queryPost = new BasicDBObject();
		queryPost.put(FieldNameConstant.CHANNEL_ID, channelID);
		queryPost.put(FieldNameConstant.USER_ID, uid);

		DBObject obj = coll.findOne(queryPost);
		if(obj!=null) {
			if(PRINT_DETAILS)
				SystemLogger.printInfo("MONGODB VOICE EXIST: "+channelID+"\t"+uid);
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 
	 */
	public void removeVoiceByUid(long channelID, String uid) throws SQLException, UnknownHostException, MongoException{
		DBCollection coll = db.getCollection(collectionName);

		BasicDBObject queryPost = new BasicDBObject();
		queryPost.put(FieldNameConstant.CHANNEL_ID, channelID);
		queryPost.put(FieldNameConstant.USER_ID, uid);

		coll.remove(queryPost);
		if(PRINT_DETAILS)
			SystemLogger.printInfo("MONGODB VOICE REMOVED: "+channelID+"\t"+uid);
	}

	/**
	 * 
	 */
	private boolean queryVoiceExistenceByScreenName(long channelID, String screenName) throws SQLException, UnknownHostException, MongoException{
		DBCollection coll = db.getCollection(collectionName);

		BasicDBObject queryPost = new BasicDBObject();
		queryPost.put(FieldNameConstant.CHANNEL_ID, channelID);
		queryPost.put(FieldNameConstant.SCREEN_NAME, screenName);

		DBObject obj = coll.findOne(queryPost);
		if(obj!=null) {
			if(PRINT_DETAILS)
				SystemLogger.printInfo("MONGODB VOICE EXIST: "+channelID+"\t"+screenName);
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 
	 */
	public void removeVoiceByScreenName(long channelID, String screenName) throws SQLException, UnknownHostException, MongoException{
		DBCollection coll = db.getCollection(collectionName);

		BasicDBObject queryPost = new BasicDBObject();
		queryPost.put(FieldNameConstant.CHANNEL_ID, channelID);
		queryPost.put(FieldNameConstant.SCREEN_NAME, screenName);

		coll.remove(queryPost);
		if(PRINT_DETAILS)
			SystemLogger.printInfo("MONGODB VOICE REMOVED: "+channelID+"\t"+screenName);
	}



	/**
	 * 
	 */
	public static void main(String[] arg) throws Exception{
		
	}
}