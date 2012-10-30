package com.brandtology.entity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.json.me.JSONException;
import org.json.me.JSONObject;

import com.brandtology.util.FieldNameConstant;
import com.brandtology.util.FormatConstant;


public class Tweet implements Comparable<Tweet>{
	
	String mid;
	long timestamp;
	String content;
	String trackbackPermalink;
	
	String language;
	int kloutScore;
	int sentiment;	
	String source;
	
	int relationshipTypeID;
	String parentMid;
	String mentionUid;
	
	List mediaURLs;
	List mentions;
	List links;

	Voice author;
	Place geolocation;
		
	public Tweet(long time, String text){
		this.mid = new String();
		this.timestamp = time;
		this.content = text;
		this.trackbackPermalink = new String();
		this.language = new String();
		this.kloutScore = 0;
		this.sentiment = 0;
		this.source = null;
		this.relationshipTypeID = 0;
		this.parentMid = null;
		this.author = null;
		this.geolocation = null;
		this.mediaURLs = new ArrayList();
		this.mentions = new ArrayList();
		this.links = new ArrayList();
	}

	public Tweet(){
		this.mid = new String();
		this.timestamp = 0;
		this.content = new String();
		this.trackbackPermalink = new String();
		this.language = new String();
		this.kloutScore = 0;
		this.sentiment = 0;
		this.source = null;
		this.relationshipTypeID = 0;
		this.parentMid = null;
		this.author = null;
		this.geolocation = null;
		this.mediaURLs = new ArrayList();
		this.mentions = new ArrayList();
		this.links = new ArrayList();
	}

	public void setTimestamp(String timeStr) {
		long time = 0;
		try{
			time = FormatConstant.SIMPLE_DF.parse(timeStr).getTime();
		}catch(Exception e){
			e.printStackTrace();
		}
		this.timestamp = time;
	}

	
	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}


	public int getKloutScore() {
		return kloutScore;
	}

	public void setKloutScore(int kloutScore) {
		this.kloutScore = kloutScore;
	}

	public int getSentiment() {
		return sentiment;
	}

	public void setSentiment(int sentiment) {
		this.sentiment = sentiment;
	}

	public Voice getAuthor() {
		return author;
	}

	public void setAuthor(Voice author) {
		this.author = author;
	}

	public Place getGeolocation() {
		return geolocation;
	}

	public void setGeolocation(Place location) {
		this.geolocation = location;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long time) {
		this.timestamp = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTrackbackPermalink() {
		return trackbackPermalink;
	}

	public void setTrackbackPermalink(String permalink) {
		this.trackbackPermalink = permalink;
	}

	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getRelationshipTypeID() {
		return relationshipTypeID;
	}

	public void setRelationshipTypeID(int relationshipTypeID) {
		this.relationshipTypeID = relationshipTypeID;
	}

	public String getParentMid() {
		return parentMid;
	}

	public void setParentMid(String parentMid) {
		this.parentMid = parentMid;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public List getMediaURLs() {
		return mediaURLs;
	}

	public void setMediaURLs(List mediaURLs) {
		this.mediaURLs = mediaURLs;
	}

	public List getMentions() {
		return mentions;
	}

	public void setMentions(List mentions) {
		this.mentions = mentions;
	}

	public List getLinks() {
		return links;
	}

	public void setLinks(List links) {
		this.links = links;
	}

	public void addMediaURL(String url){
		this.mediaURLs.add(url);
	}
	
	public void addMention(String mention){
		this.mentions.add(mention);
	}
	
	public void addLink(String link){
		this.links.add(link);
	}
	
	public static String getRelationshipName(Tweet tweet){
		String relationship = "OG";
		if(tweet.relationshipTypeID==FieldNameConstant.MENTION_RELATION)
			relationship = "MT {"+getMentions(tweet)+"}";
		else if(tweet.relationshipTypeID==FieldNameConstant.REPLY_RELATION)
			relationship = "RP ->"+tweet.getParentMid()+"";
		else if(tweet.relationshipTypeID==FieldNameConstant.RETWEET_RELATION)
			relationship = "RT <"+tweet.getParentMid()+">";
		return relationship;
	}
	
	public static String getMentions(Tweet tweet){
		if(tweet.mentions==null || tweet.mentions.size()==0)
			return "";
		StringBuffer res = new StringBuffer();
		res.append(tweet.mentions.get(0));
		for(int i=1;i<tweet.mentions.size();i++){
			res.append(", "+(String)tweet.mentions.get(i));
		}
		return res.toString();
	}
	
	public String writeToLogger(){
		return new String(getRelationshipName(this)+"\t"+FormatConstant.SIMPLE_DF.format(timestamp)+"\t["+author.getVoiceName()+"]\t"+language+"\t"+content.replace("\n", " "));
	}
	
	public String writeToJSON(){
		JSONObject json = new JSONObject();
		try{
			json.put(FieldNameConstant.MESSAGE_ID, mid);
			json.put(FieldNameConstant.DATETIME_POSTED, FormatConstant.SIMPLE_DF.format(this.timestamp));
			json.put(FieldNameConstant.CONTENT, this.content);
			json.put(FieldNameConstant.URL, this.trackbackPermalink);
			json.put(FieldNameConstant.LANGUAGE, language);
			json.put(FieldNameConstant.KLOUT_SCORE, kloutScore);
			json.put(FieldNameConstant.SENTIMENT, sentiment);
			json.put(FieldNameConstant.VOICE_USER_ID, author.getUid());
			json.put(FieldNameConstant.SCREEN_NAME, author.getVoiceName());
		}catch(JSONException e){
			e.printStackTrace();
		}
		return json.toString();
	}
	
	public void parseFromJSON(String jsonStr){
		this.mid = new String();
		this.timestamp = 0;
		this.content = new String();
		this.trackbackPermalink = new String();
		this.language = new String();
		this.kloutScore = 0;
		this.sentiment = 0;
		this.author = new Voice();
		
		JSONObject json = null;
		try{
			json = new JSONObject(jsonStr);
			if(json!=null){
				this.timestamp = FormatConstant.SIMPLE_DF.parse(json.getString(FieldNameConstant.DATETIME_POSTED)).getTime();
				this.content = json.getString(FieldNameConstant.CONTENT);
				this.trackbackPermalink = json.getString(FieldNameConstant.URL);
				this.language = json.getString(FieldNameConstant.LANGUAGE);
				this.kloutScore = json.getInt(FieldNameConstant.KLOUT_SCORE);
				this.sentiment = json.getInt(FieldNameConstant.SENTIMENT);
				String voiceName = json.getString(FieldNameConstant.SCREEN_NAME);
				String vId = json.getString(FieldNameConstant.VOICE_USER_ID);
				this.author.setVoiceName(voiceName);
				this.author.setUid(vId);
			}
		}catch(JSONException e){
			e.printStackTrace();
		}catch(ParseException e){
			e.printStackTrace();
		}
	}

	public int compareTo(Tweet obj){
		int result = 0;
		
		Tweet a = null;
		if(obj instanceof Tweet)
			a = (Tweet)obj;

		result = (int)(a.getTimestamp()-this.timestamp)/1000;
		
		return result;
	}
	
	public boolean equals(Tweet obj){
		boolean result = false;
		
		Tweet a = null;
		if(obj instanceof Tweet)
			a = (Tweet)obj;
		
		if(a.getTrackbackPermalink()!=null && a.getTrackbackPermalink()==this.trackbackPermalink){
			result = true;
		}else if(a.getMid()!=null && a.getMid()==this.mid){
			result = true;
		}else if(a.getAuthor().getUid()==this.author.getUid()){
			if(a.getTimestamp()==this.timestamp){
				if(a.getContent()!=null && a.getContent()==this.content)
					result = true;
			}
		}
		
		return result;
	}
	
}
