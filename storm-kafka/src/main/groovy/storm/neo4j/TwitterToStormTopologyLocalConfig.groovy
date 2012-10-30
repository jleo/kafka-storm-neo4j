package storm.neo4j

import com.brandtology.db.neo4j.TweetToNeo4jRestAPIGraphHandler

class TwitterToStormTopologyLocalConfig {

    public static void main(String[] args) throws Exception {
        def zkHost = "localhost"
        def zkServers = ["localhost"]
        def zkPort =  2181
        def topic = "datasift"
        def root = "/kafkastorm"
        def id = "discovery"
        TweetToNeo4jRestAPIGraphHandler.SERVER_ROOT_URI = "http://localhost:7474/db/data/"
        TwitterToStormTopology.submitTopology(zkHost, zkServers,zkPort,topic,root,id,args)

    }
}
