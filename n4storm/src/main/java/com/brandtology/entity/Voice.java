/**
 * 
 */
package com.brandtology.entity;

import java.text.ParseException;

import org.json.me.JSONException;
import org.json.me.JSONObject;

import com.brandtology.util.FieldNameConstant;
import com.brandtology.util.FormatConstant;

/**
 * @author leah
 *
 */
public class Voice {

	String uid;
	String name;
	String description;
	String avatar;
	String url;
	String location;
	String language;
	String timeZone;
	long UTCOffset;
	long createdAt;
	
	String gender;
	
	int statusCount;
	int followersCount;
	int friendsCount;	
	int listedCount;
	
	int kloutScore;
	
	String country;
	String province;
	String city;
	long dateOfBirth;
	
	long voiceID;
	String screenName;

	public Voice(){
		this.screenName = null;
		this.voiceID = 0;
		
		this.uid = null;
		this.name = null;
		this.description = null;
		this.location = null;
		this.language = null;
		this.timeZone = null;
		this.UTCOffset = 0;
		this.createdAt = 0;
		
		this.gender = null;
		
		this.statusCount = 0;
		this.followersCount = 0;
		this.friendsCount = 0;
		this.listedCount = 0;
		
		this.kloutScore = 0;
		
		this.country = null;
		this.province = null;
		this.city = null;
		this.dateOfBirth = 0;
	}
	
	public Voice(String screenName){
		this.screenName = screenName;
		this.voiceID = 0;
		
		this.uid = null;
		this.name = null;
		this.description = null;
		this.location = null;
		this.language = null;
		this.timeZone = null;
		this.UTCOffset = 0;
		this.createdAt = 0;
		
		this.gender = null;
		
		this.statusCount = 0;
		this.followersCount = 0;
		this.friendsCount = 0;
		this.listedCount = 0;
		
		this.kloutScore = 0;
		
		this.country = null;
		this.province = null;
		this.city = null;
		this.dateOfBirth = 0;
	}
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String lang) {
		this.language = lang;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public long getUTCOffset() {
		return UTCOffset;
	}

	public void setUTCOffset(long uTCOffset) {
		UTCOffset = uTCOffset;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedAtTimeStr() {
		return FormatConstant.DATASIFT_TWEET_DF().format(this.createdAt);
	}

	public void setCreatedAt(String timeStr) throws ParseException {
		this.createdAt = FormatConstant.DATASIFT_TWEET_DF().parse(timeStr).getTime();
	}

	public int getStatusCount() {
		return statusCount;
	}

	public void setStatusCount(int statusCount) {
		this.statusCount = statusCount;
	}

	public int getFollowersCount() {
		return followersCount;
	}

	public void setFollowersCount(int followersCount) {
		this.followersCount = followersCount;
	}

	public int getFriendsCount() {
		return friendsCount;
	}

	public void setFriendsCount(int friendsCount) {
		this.friendsCount = friendsCount;
	}

	public int getListedCount() {
		return listedCount;
	}

	public void setListedCount(int listedCount) {
		this.listedCount = listedCount;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public long getVoiceID() {
		return voiceID;
	}

	public void setVoiceID(long voiceID) {
		this.voiceID = voiceID;
	}

	public String getVoiceName() {
		return screenName;
	}

	public void setVoiceName(String voiceName) {
		this.screenName = voiceName;
	}

	public int getKloutScore() {
		return kloutScore;
	}

	public void setKloutScore(int kloutScore) {
		this.kloutScore = kloutScore;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public long getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(long dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String writeToLogger(){
		StringBuffer res = new StringBuffer();
		res.append(screenName+" ("+name+")\t"+uid+"\t["+statusCount+", "+followersCount+", "+friendsCount+"]\t"+url);
		return res.toString();
	}

	public String writeToJSON(){
		JSONObject json = new JSONObject();
		try{
			json.put(FieldNameConstant.NAME, name);
			json.put(FieldNameConstant.DESCRIPTION, description);
			json.put(FieldNameConstant.URL, url);
			json.put(FieldNameConstant.PROFILE_PICTURE_URL, avatar);
			json.put(FieldNameConstant.LANGUAGE, language);
			json.put(FieldNameConstant.LOCATION, location);
			json.put(FieldNameConstant.TIME_ZONE, timeZone);
			json.put(FieldNameConstant.UTC_OFFSET, UTCOffset);
			json.put(FieldNameConstant.DATETIME_USER_CREATED, FormatConstant.MONGODB_DF.format(createdAt));
			json.put(FieldNameConstant.GENDER, gender);
			json.put(FieldNameConstant.STATUS_COUNT, statusCount);
			json.put(FieldNameConstant.FOLLOWERS_COUNT, followersCount);
			json.put(FieldNameConstant.FRIENDS_COUNT, friendsCount);
			json.put(FieldNameConstant.KLOUT_SCORE, kloutScore);
			json.put(FieldNameConstant.SCREEN_NAME, screenName);
			json.put(FieldNameConstant.VOICE_ID, voiceID);
		}catch(JSONException e){
			e.printStackTrace();
		}
		return json.toString();
	}
	
	public void parseFromJSON(String jsonStr){
		this.name = new String();
		this.description = new String();
		this.url = new String();
		this.avatar = new String();
		this.language = new String();
		this.location = new String();
		this.timeZone = new String();
		this.UTCOffset = 0;
		this.createdAt = 0;
		this.gender = new String();
		this.statusCount = 0;
		this.followersCount = 0;
		this.friendsCount = 0;
		this.kloutScore = 0;
		this.screenName = new String();
		this.voiceID = 0;
		
		JSONObject json = null;
		try{
			json = new JSONObject(jsonStr);
			if(json!=null){
				this.name = json.getString(FieldNameConstant.NAME);
				this.description = json.getString(FieldNameConstant.DESCRIPTION);
				this.url = json.getString(FieldNameConstant.URL);
				this.avatar = json.getString(FieldNameConstant.PROFILE_PICTURE_URL);
				this.language = json.getString(FieldNameConstant.LANGUAGE);
				this.location = json.getString(FieldNameConstant.LOCATION);
				this.timeZone = json.getString(FieldNameConstant.TIME_ZONE);
				this.UTCOffset = json.getLong(FieldNameConstant.UTC_OFFSET);
				this.createdAt = FormatConstant.MONGODB_DF.parse(json.getString(FieldNameConstant.DATETIME_POSTED)).getTime();
				this.gender = json.getString(FieldNameConstant.GENDER);
				this.statusCount = json.getInt(FieldNameConstant.STATUS_COUNT);
				this.followersCount = json.getInt(FieldNameConstant.FOLLOWERS_COUNT);
				this.friendsCount = json.getInt(FieldNameConstant.FRIENDS_COUNT);
				this.kloutScore = json.getInt(FieldNameConstant.KLOUT_SCORE);
				this.screenName = json.getString(FieldNameConstant.SCREEN_NAME);
				this.voiceID = json.getLong(FieldNameConstant.VOICE_ID);
			}
		}catch(JSONException e){
			e.printStackTrace();
		}catch(ParseException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
