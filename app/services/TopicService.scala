package services

import utils.Zookeeper
import kafka.utils.ZkUtils
import scala.collection._
import models.topics.Topics
import models.topics.Topic
import models.topics.DetailedTopics
import models.topics.BrokerTopicItem

object TopicService {
  
  val topicsZPath = "/brokers/topics"   
  
  /**
   * Gets all the topic names registered in zookeeper.
   */
  def getTopics = {
    val zkClient = Zookeeper.getInstance()
    val topicNames = ZkUtils.getChildrenParentMayNotExist(zkClient, topicsZPath)
    val topics = new Topics
    for(topicName <- topicNames){
      topics.add(topicName)
    }
    topics
  }
  
  /**
   * Gets all the topics with its details.
   * It will query zookeeper for the following path: /brokers/topics
   */
  def getTopicsWithDetails = {
    val topics = getTopics
    val detailedTopics = new DetailedTopics
    val zkClient = Zookeeper.getInstance()
    val topicNames = topics.getTopics
    
    // Fill in the topics, broker and num of partitions.
    for(topicName <- topicNames){
      val topic = getTopicDetails(topicName)
      detailedTopics.add(topic)
    }
    detailedTopics
  }
  
  /**
   * Gets the topic information with all the details according to the topic name
   * passed by parameter.
   * It will query zookeeper for the following path: /brokers/topics/{topicName parameter}
   * @param topicName: The topic name we are searching for.
   */
  def getTopicDetails(topicName: String):Topic ={
	val topicInfo = Topic(topicName)
	val zkClient = Zookeeper.getInstance()
    val brokerIds = ZkUtils.getChildrenParentMayNotExist(zkClient, topicsZPath + "/" + topicName)  
    for(brokerId <- brokerIds){
    	val numOfPartitions = ZkUtils.readData(zkClient, topicsZPath + "/" + topicName + "/" + brokerId)
    	val brokerTopicItem = BrokerTopicItem(brokerId, numOfPartitions)
    	topicInfo.add(brokerTopicItem)
    }
    topicInfo
  } 
  
}