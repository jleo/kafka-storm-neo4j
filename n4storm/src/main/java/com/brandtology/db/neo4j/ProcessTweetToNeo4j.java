/**
 * 
 */
package com.brandtology.db.neo4j;

import java.util.List;

import com.brandtology.alert.twitter.datasift.DatasiftParser;
import com.brandtology.util.FileHandler;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

import com.brandtology.entity.Tweet;
import com.brandtology.entity.Voice;
import com.brandtology.io.log.SystemLogger;
import com.brandtology.twitter.TwitterStatusLookup;
import com.brandtology.twitter.TwitterUserLookup;

/**
 * @author leah
 *
 */
public class ProcessTweetToNeo4j {
	
	TweetToNeo4jRestAPIGraphHandler tweetToNeo4j;

	/**
	 * 
	 */
	public void setup(){
		this.tweetToNeo4j = new TweetToNeo4jRestAPIGraphHandler();
		this.tweetToNeo4j.createDb();
	}
	
	/**
	 * 
	 */
	public void closeup(){
		this.tweetToNeo4j.shutDown();
	}
	
	/**
	 * 
	 */
	private Node handleTweet(Tweet tweet){

		    Node tweetNode = tweetToNeo4j.getTweetNodeByMid(tweet.getMid());
		// insert the tweet only when it does not exist
		    if(tweetNode==null){
			    tweetNode = tweetToNeo4j.createTweetNode(tweet);
			//tweetToNeo4j.indexTweetNode(tweetNode);
			SystemLogger.printInfo("CREATING NEW TWEET NODE:\t"+tweetNode.getProperty("mid"));
            }
            return tweetNode;


	}
	
	/**
	 * 
	 */
	private Node handleVoice(Voice voice){

		Node voiceNode = null;
		if(voice.getUid()!=null)
			voiceNode = tweetToNeo4j.getVoiceNodeByUid(voice.getUid());
		else
			voiceNode = tweetToNeo4j.getVoiceNodeByScreenName(voice.getScreenName());

		// insert the voice only when it does not exist
		if(voiceNode==null){
			voiceNode = tweetToNeo4j.createVoiceNode(voice);
//			tweetToNeo4j.indexVoiceNode(voiceNode);
			SystemLogger.printInfo("CREATING NEW VOICE NODE:\t"+voiceNode.getProperty("screen_name"));
		}
		return voiceNode;
	}
	
	/**
	 * 
	 */
	private Relationship handlePublishRelationship(Node voiceNode, Node tweetNode){
		 // link the tweet and its voice using publish relatinship type			
		Relationship publish = tweetToNeo4j.createRelationship(voiceNode, tweetNode, MicroblogRelationshipType.PUBLISH);
        SystemLogger.printInfo("RELATIONSHIP CREATED:\t("+voiceNode.getProperty("screen_name")+" -- "+publish.getType().name()+" -- "+tweetNode.getProperty("mid")+")");
	
        return publish;
	}
	
	/**
	 * 
	 */
	private Relationship handleMentionRelationship(Node tweetNode, Node voiceNode){

    	// link the tweet to its mentioned voice. 
		Relationship mention = tweetToNeo4j.createRelationship(tweetNode, voiceNode, MicroblogRelationshipType.MENTION);
        SystemLogger.printInfo("RELATIONSHIP CREATED:\t("+tweetNode.getProperty("mid")+" -- "+mention.getType().name()+" -- "+voiceNode.getProperty("screen_name")+")");
		return mention;
	}
	
	/**
	 * 
	 */
	private Relationship handleReplyRelationship(Node tweetNode, Node parentNode){
		Relationship reply = tweetToNeo4j.createRelationship(tweetNode, parentNode, MicroblogRelationshipType.REPLY);
		SystemLogger.printInfo("RELATIONSHIP CREATED:\t("+tweetNode.getProperty("mid")+" -- "+MicroblogRelationshipType.REPLY.name()+" -- "+parentNode.getProperty("mid")+")");
		return reply;
	}
	
	/**
	 * 
	 */
	private Relationship handleRetweetRelationship(Node tweetNode, Node parentNode){
		Relationship retweet = tweetToNeo4j.createRelationship(tweetNode, parentNode, MicroblogRelationshipType.RETWEET);
		SystemLogger.printInfo("RELATIONSHIP CREATED:\t("+tweetNode.getProperty("mid")+" -- "+MicroblogRelationshipType.RETWEET.name()+" -- "+parentNode.getProperty("mid")+")");
		return retweet;
	}
	
	/**
	 * 
	 */
	public void processTweet(Tweet tweet){
		Node tweetNode = handleTweet(tweet);

		Voice user = tweet.getAuthor();
		Node voiceNode = handleVoice(user);		
		
		Relationship publish = handlePublishRelationship(voiceNode, tweetNode);
       
        // link the tweet to each voice it mentions.
        List mentions = tweet.getMentions();
        for(int m=0;m<mentions.size();m++){
        	String screenName = (String)mentions.get(m);
        	Node mentioned = tweetToNeo4j.getVoiceNodeByScreenName(screenName);
        	
        	// insert the voice if it does not exist. 
        	if(mentioned==null){
        		Voice voice = TwitterUserLookup.getTwitterUser(screenName);
        		mentioned = handleVoice(voice);
        	}

        	Relationship mention = handleMentionRelationship(tweetNode, mentioned);
        }
        
        String pmid = tweet.getParentMid();
        if(pmid!=null && !pmid.equals("null")){
        	Node parentNode = tweetToNeo4j.getTweetNodeByMid(pmid);
        	
        	// insert a tweet node if parent tweet does not exist.
        	if(parentNode==null){
        		Tweet retweeted = TwitterStatusLookup.getStatusDetail(pmid);
        		if(retweeted!=null){
        			parentNode = handleTweet(retweeted);

        			Voice voice = retweeted.getAuthor();
        			if(voice!=null){
        				Node vNode = handleVoice(voice);
                        if (parentNode != null)  {
        				   // Relationship published2 = handlePublishRelationship(vNode, parentNode);
                            handlePublishRelationship(vNode, parentNode);
                        }
        			}
        		}
        	}
        	
        	// Link tweet and its parent accroding to their relationship.
        	int relatinshipTypeID = tweet.getRelationshipTypeID();
        	if(relatinshipTypeID==2){
                if(parentNode!=null)
        		    handleReplyRelationship(tweetNode, parentNode);
        	}else if(relatinshipTypeID==3){

                if(parentNode!=null)
        		    handleRetweetRelationship(tweetNode, parentNode);
        	}
        }
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        String fileName = new String(System.getProperty("user.dir")+"/data/sample_datasift_file_SG_20120610_001501_done.json");
        System.out.println("######: "+fileName);
        ProcessTweetToNeo4j processTweetToNeo4j = new ProcessTweetToNeo4j();

        String value = FileHandler.getFileContent(fileName);

        List results = DatasiftParser.parseJSON(value);
        processTweetToNeo4j.setup();

        for(int i=0;i<results.size();i++){
            Tweet tweet = (Tweet)results.get(i);
            System.out.println(tweet.getMid());
            //tweetToNeo4j.createTweetNode(tweet);
            processTweetToNeo4j.processTweet(tweet);


        }
        processTweetToNeo4j.closeup();

	}

}
