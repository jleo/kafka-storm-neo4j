package storm.neo4j;


import backtype.storm.Config
import backtype.storm.LocalCluster
import backtype.storm.StormSubmitter
import backtype.storm.spout.SpoutOutputCollector
import backtype.storm.task.TopologyContext
import backtype.storm.topology.BasicOutputCollector
import backtype.storm.topology.OutputFieldsDeclarer
import backtype.storm.topology.TopologyBuilder
import backtype.storm.topology.base.BaseBasicBolt
import backtype.storm.topology.base.BaseRichSpout
import backtype.storm.tuple.Fields
import backtype.storm.tuple.Tuple
import backtype.storm.tuple.Values
import backtype.storm.utils.Utils
import com.brandtology.alert.twitter.datasift.DatasiftParser
import com.brandtology.db.neo4j.ProcessTweetToNeo4j
import com.brandtology.entity.Tweet
import com.brandtology.util.FileHandler
import storm.kafka.KafkaConfig
import storm.kafka.KafkaSpout
import storm.kafka.SpoutConfig
import storm.kafka.StringScheme
import com.netflix.curator.framework.CuratorFrameworkFactory
import com.netflix.curator.retry.RetryNTimes
import com.brandtology.db.neo4j.TweetToNeo4jRestAPIGraphHandler

public class TwitterToStormTopology {

    //only for test
    public static class TestSpout extends BaseRichSpout {
        SpoutOutputCollector _collector;
        Random _rand;


        @Override
        public void declareOutputFields(OutputFieldsDeclarer declarer) {
            declarer.declare(new Fields("tweet"));
        }

        @Override
        public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
            _collector = collector;
            _rand = new Random();
        }

        @Override
        public void nextTuple() {
            Utils.sleep(100);
            String fileName = new String(System.getProperty("user.dir") + "/data/sample_datasift_file_SG_20120610_001501_done.json");
            String value = FileHandler.getFileContent(fileName);
            List results = DatasiftParser.parseJSON(value);
            Tweet tweet = (Tweet) results.get(_rand.nextInt(results.size()));

            _collector.emit(new Values(tweet));
        }
    }

    public static class ProcessTweetBolt extends BaseBasicBolt {
        ProcessTweetToNeo4j processTweetToNeo4j;



        @Override
        public void execute(Tuple tuple, BasicOutputCollector collector) {
            if (processTweetToNeo4j == null) {
                processTweetToNeo4j = new ProcessTweetToNeo4j();
                processTweetToNeo4j.setup();
            }

            Tweet tweet = (Tweet) tuple.getValue(0);
            String mid = tweet.getMid();
            collector.emit(new Values(mid));
        }

        @Override
        public void declareOutputFields(OutputFieldsDeclarer declarer) {
            declarer.declare(new Fields("mid"));
        }
    }

    public static class ProcessTweetFromKafKaBolt extends BaseBasicBolt {
        ProcessTweetToNeo4j processTweetToNeo4j;

        @Override
        public void execute(Tuple input, BasicOutputCollector collector) {
            if (processTweetToNeo4j == null) {
                processTweetToNeo4j = new ProcessTweetToNeo4j();
                processTweetToNeo4j.setup();
            }

            Tweet tweet = DatasiftParser.parseTweet(input.getString(0));
            if (tweet != null) {
                String mid = tweet.getMid();
                processTweetToNeo4j.processTweet(tweet);
                collector.emit(new Values(mid));
            }
        }

        @Override
        public void declareOutputFields(OutputFieldsDeclarer declarer) {
            declarer.declare(new Fields("mid"));
        }
    }

    public static void submitTopology(def zkHost,def zkServers,def zkPort,def topic,def root,def id,String[] args ){
        SpoutConfig spoutConfig = new SpoutConfig(
                new KafkaConfig.ZkHosts(zkHost, "/brokers"), // list of Kafka brokers
                topic, // topic to read from
                root, // the root path in Zookeeper for the spout to store the consumer offsets
                id); // an id for this consumer for storing the consumer offsets in Zookeeper

        spoutConfig.scheme = new StringScheme();

        spoutConfig.zkServers = zkServers ;
        spoutConfig.zkPort =zkPort;
        //spoutConfig.forceStartOffsetTime(-2); //It will choose the latest offset written around that timestamp to start consuming. You can force the spout to always start from the latest offset by passing in -1, and you can force it to start from the earliest offset by passing in -2.

        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("ks", new KafkaSpout(spoutConfig), 2);
        builder.setBolt("tweet", new ProcessTweetFromKafKaBolt(), 2)
                .shuffleGrouping("ks");

        Config conf = new Config();
        conf.setDebug(true);

        if (args != null && args.length > 0) {
            conf.setNumWorkers(3);
            StormSubmitter.submitTopology(args[0], conf, builder.createTopology());
        } else {
            conf.setMaxTaskParallelism(3);
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("processTweet-neo4j", conf, builder.createTopology());
        }
    }

    public static void main(String[] args) throws Exception {
        def zkHost = ConfigFromZk.config.zkHost
        def zkServers = ConfigFromZk.config.zkServers      //  the offsets will be stored
        def zkPort =  ConfigFromZk.config.zkPort
        def topic = ConfigFromZk.config.kafkaTopic
        def root =  ConfigFromZk.config.stormRoot
        def id =  ConfigFromZk.config.stormId
        TweetToNeo4jRestAPIGraphHandler.SERVER_ROOT_URI = ConfigFromZk.config.neo4jUri
        submitTopology(zkHost, zkServers,zkPort,topic,root,id,args)


    }
}
