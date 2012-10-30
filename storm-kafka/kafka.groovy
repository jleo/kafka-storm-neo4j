import groovy.json.JsonBuilder
neo4j{
    uri = "http://localhost:7474/db/data/"
}
zk {
    host = "localhost"
    zkServers = ["localhost"];
    zkPort = 2181
}

kafka{
    topic = "datasift"
}

storm{
    root="/kafkastorm"
    id="discovery"
}
//solr{
//    baseURL = "http://localhost:8983/solr"
//    collection = "c4"
//}

threadNumber = 10

def ruleEngineWorker = new JsonBuilder()

//neo4jWorker {
//    name("neo4j-consumer")
//    topic("datasift")
//    consumerGroup("neo4j-group")
//    messageHandlerName("com.brandtology.kafka.Neo4jConsumer")
//    zkConnect("localhost:2181")
//    maxWorkers(1)
//    active(true)
//}

ruleEngineWorker {
    name("rule-engine-consumer")
    topic("olay-topic")
    consumerGroup("group1")
    messageHandlerName("com.jointhegrid.ironcount.eventtofile.MessageToFileHandler")
    zkConnect("localhost:2181")
    maxWorkers(1)
    active(true)
}

//def solrWorker = new JsonBuilder()
//
//solrWorker {
//    name("solr-consumer")
//    topic("datasift")
//    consumerGroup("solr-group")
//    messageHandlerName("com.brandtology.kafka.SolrConsumer")
//    zkConnect("localhost:2181")
//    maxWorkers(4)
//    active(true)
//}

workers = [
//        solrWorker.toString(),
        ruleEngineWorker.toString()
]