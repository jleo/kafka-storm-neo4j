/**
 * 
 */
package com.brandtology.db.neo4j;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.index.IndexHits;
import org.neo4j.graphdb.index.IndexManager;
import org.neo4j.index.impl.lucene.LuceneIndexImplementation;
import org.neo4j.rest.graphdb.RestAPI;
import org.neo4j.rest.graphdb.RestGraphDatabase;

import com.brandtology.alert.twitter.datasift.DatasiftParser;
import com.brandtology.entity.Tweet;
import com.brandtology.entity.Voice;
import com.brandtology.io.log.SystemLogger;
import com.brandtology.util.FileHandler;
import org.neo4j.rest.graphdb.entity.RestNode;
import org.neo4j.rest.graphdb.index.RestIndex;

import static org.neo4j.helpers.collection.MapUtil.map;

/**
 * @author leah
 *
 */
public class TweetToNeo4jRestAPIGraphHandler {
	public static  String SERVER_ROOT_URI = "http://localhost:7474/db/data/";//"http://192.168.50.212:7474/";
    
    // START SNIPPET: vars
    GraphDatabaseService graphDb;
    RestAPI restAPI;
    IndexManager index;

    RestIndex<Node> voices;
    RestIndex<Node> tweets;
   // RelationshipIndex publish;
    RestIndex<Relationship> relationshipRestIndex;
    // END SNIPPET: vars
    
	private static void registerShutdownHook( final GraphDatabaseService graphDb )
	{
		// Registers a shutdown hook for the Neo4j instance so that it
		// shuts down nicely when the VM exits (even if you "Ctrl-C" the
		// running example before it's completed)
		Runtime.getRuntime().addShutdownHook( new Thread()
		{
			@Override
			public void run()
			{
				graphDb.shutdown();
			}
		} );
	}

    void createDb()
    {
        // START SNIPPET: startDb
        graphDb = new RestGraphDatabase(SERVER_ROOT_URI);
        registerShutdownHook( graphDb );
        // END SNIPPET: startDb


        restAPI = ((RestGraphDatabase)graphDb).getRestAPI();
       // restAPI = new RestAPIFacade(SERVER_ROOT_URI);
        index = graphDb.index();

//        voices = index.forNodes( "voices" );
//        tweets = index.forNodes( "tweets" );
//        publish = index.forRelationships( "publish" );
        voices = restAPI.createIndex(Node.class, "voices", LuceneIndexImplementation.EXACT_CONFIG);
        tweets = restAPI.createIndex(Node.class, "tweets", LuceneIndexImplementation.EXACT_CONFIG);
        relationshipRestIndex = restAPI.createIndex(Relationship.class, "relationship", LuceneIndexImplementation.EXACT_CONFIG);


    }
    
    Node createVoiceNode(Voice voice){
    	Node node = null;
        // START SNIPPET: transaction

            // Mutating operations go here
            // END SNIPPET: transaction
            // START SNIPPET: addData
//            node = graphDb.createNode();
//            node.setProperty("uid", voice.getUid());
//            node.setProperty("screen_name", voice.getScreenName());


        node = restAPI.getOrCreateNode(voices, "uid", voice.getUid(),map("uid", voice.getUid(),"screen_name",voice.getScreenName()));

            // END SNIPPET: addData

//            voices.add( node, "uid", node.getProperty("uid") );
//            voices.add( node, "screen_name", node.getProperty("screen_name") );
            
            // START SNIPPET: transaction

        // END SNIPPET: transaction
        return node;
    }


    
    Node createTweetNode(Tweet tweet){
    	if(tweet==null)
    		return null;
    	
    	Node node = null;
        node = restAPI.getOrCreateNode(tweets, "mid", tweet.getMid(),map("mid", tweet.getMid()));


        return node;
    }



    /**
     * 
     */
    Relationship createRelationship(Node startNode, Node endNode, MicroblogRelationshipType relType){
    	Relationship relationship = null;

        String v1 = startNode.hasProperty("mid")?"mid:"+startNode.getProperty("mid"):"uid:"+startNode.getProperty("uid",null) ;
        String v2 = endNode.hasProperty("mid")?"mid:"+endNode.getProperty("mid"):"uid:"+endNode.getProperty("uid",null) ;
        String value = v1+","+relType.name()+","+v2;
        relationship = restAPI.getOrCreateRelationship(relationshipRestIndex, "key",value,(RestNode)startNode,(RestNode)endNode,relType.name(),null);

        return relationship;
    }


    
    /**
     * 
     */
    Node getVoiceNodeByUid(String uid){
    	Node node = null;

    	IndexHits<Node> hits = voices.get( "uid", uid );
        if(hits.size() != 0)
    	    node = hits.getSingle();

    	return node;
    }

    /**
     * 
     */
    Node getVoiceNodeByScreenName(String screenName){
    	Node node = null;

    	IndexHits<Node> hits = voices.get( "screen_name", screenName );
        if(hits.size() != 0)
    	    node = hits.getSingle();

    	return node;
    }
    
    
    /**
     * 
     */
    List queryVoiceNodesByScreenName(String screenName){
    	List results = new ArrayList();
    	
    	for ( Node node : voices.query( "screen_name", screenName ) )
    	{
    	    results.add(node);
    	}
    	
    	return results;
    }

    /**
     * 
     */
    Node getTweetNodeByMid(String mid){
    	Node node = null;

    	IndexHits<Node> hits = tweets.get( "mid", mid );
        if(hits.size() != 0)
    	    node = hits.getSingle();

    	return node;
    }
    

    



    void shutDown()
    {
        SystemLogger.printInfo( "SHUTTING DOWN Neo4j DATABASE ..." );
        // START SNIPPET: shutdownServer
        graphDb.shutdown();
        // END SNIPPET: shutdownServer
    }
        
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final TweetToNeo4jRestAPIGraphHandler tweetToNeo4j = new TweetToNeo4jRestAPIGraphHandler();
		tweetToNeo4j.createDb();
        System.out.println(System.getProperty("user.dir"));
		String fileName = new String(System.getProperty("user.dir")+"/data/sample_datasift_file_SG_20120610_001501_done.json");
		String value = FileHandler.getFileContent(fileName);
        final List results = DatasiftParser.parseJSON(value);
        List<Thread> tlist = new ArrayList<Thread>();
        for (int i=0;i<100;i++){
            Thread tr = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("thread start..");
                    for(int i=0;i<results.size();i++){
                        Tweet tweet = (Tweet)results.get(i);
                        tweetToNeo4j.createTweetNode(tweet);
                    }
                }
            });
            tlist.add(tr)   ;

        }
        for (int i=0;i<100;i++){
             tlist.get(i).start();
        }
		tweetToNeo4j.shutDown();
	}
	
}
