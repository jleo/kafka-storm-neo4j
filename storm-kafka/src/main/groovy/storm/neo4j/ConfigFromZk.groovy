package storm.neo4j

import com.netflix.curator.framework.CuratorFrameworkFactory
import com.netflix.curator.retry.RetryNTimes

class ConfigFromZk {
    public static def config = [:]
    private static def CONNECTSTRING=  "127.0.0.1:2181"

    static {
        def config_root = "/config"
        def client = CuratorFrameworkFactory.newClient(CONNECTSTRING ,new RetryNTimes(Integer.MAX_VALUE, 1000))
        client.start()
        if(client.checkExists().forPath(config_root)  ){
            config.zkHost = new String(client.getData().forPath(config_root+"/zkHost") )
            config.zkServers = new String(client.getData().forPath(config_root+"/zkServers") ).split(",")    //  the offsets will be stored
            config.zkPort = new String(client.getData().forPath(config_root+"/zkPort") ).toInteger()          //  the offsets will be stored
            config.kafkaTopic = new String(client.getData().forPath(config_root+"/topic") )
            config.stormRoot = new String(client.getData().forPath(config_root+"/root") )
            config.stormId = new String(client.getData().forPath(config_root+"/id") )
            config.neo4jUri = new String(client.getData().forPath(config_root+"/neo4jUri") )

        }
        client.close()
    }
}
