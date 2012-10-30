

import com.netflix.curator.framework.CuratorFrameworkFactory
import com.netflix.curator.retry.RetryNTimes


        def client = CuratorFrameworkFactory.newClient("127.0.0.1:2181",new RetryNTimes(Integer.MAX_VALUE, 1000))
        client.start()
        String config_root ="/config"


        if(!client.checkExists().forPath(config_root)  ){
            client.create().forPath(config_root,new byte[0])
            client.create().forPath(config_root+"/zkHost","localhost".getBytes())
            client.create().forPath(config_root+"/zkServers","localhost".getBytes())
            client.create().forPath(config_root+"/zkPort","2181".getBytes())
            client.create().forPath(config_root+"/topic","datasift".getBytes())
            client.create().forPath(config_root+"/root","/kafkastorm".getBytes())
            client.create().forPath(config_root+"/id","discovery".getBytes())


        }
        if(client.checkExists().forPath(config_root)  ){
            println "#exists..."
            println  new String(client.getData().forPath(config_root+"/zkHost") )

            println  new String(client.getData().forPath(config_root+"/zkServers") )
            println  new String(client.getData().forPath(config_root+"/zkPort") )
            println  new String(client.getData().forPath(config_root+"/topic") )

        }
        client.close()


    }

