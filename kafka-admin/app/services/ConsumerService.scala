package services

import utils.Zookeeper
import kafka.utils.ZkUtils
import scala.collection._
import models.consumer.ConsumerGroups
import models.consumer.ConsumerTopicOffset
import models.topics.Topics
import models.consumer.ConsumerOffsets

object ConsumerService {
  
  val consumersZPath = "/consumers"   
  
  def getConsumerGroups = {
    val zkClient = Zookeeper.getInstance()
    val zkConsumerGroups = ZkUtils.getChildrenParentMayNotExist(zkClient, consumersZPath)
    val consumerGroups = new ConsumerGroups
    for(consumers <- zkConsumerGroups){
      consumerGroups.add(consumers)
    }
    consumerGroups
  }
  
  def getConsumerGroupTopics(consumerGroup: String) = {
    val zkClient = Zookeeper.getInstance()
    val zkTopics = ZkUtils.getChildrenParentMayNotExist(zkClient, consumersZPath + "/" + consumerGroup + "/offsets")
    val topics = new Topics()
    for (topic : String <- zkTopics){
      topics.add(topic)
    }
    topics
  } 
  
  def getConsumerGroupTopicsOffset(consumerGroup: String, topic: String) = {
    val zkClient = Zookeeper.getInstance()
    val brokerPartitions = ZkUtils.getChildrenParentMayNotExist(zkClient, consumersZPath + "/" + consumerGroup + "/offsets/" + topic)
    val consumerGroups = new ConsumerOffsets()
    for (brokerPartition <- brokerPartitions){
      val offset = getConsumerGroupOffset(consumerGroup, topic, brokerPartition)
      val brokerPartitionSplit = brokerPartition.split("-")
      val consumer = ConsumerTopicOffset(topic, brokerPartitionSplit(0), brokerPartitionSplit(1),offset)
      consumerGroups.add(consumer)
    }
    consumerGroups
  }
  
  def getConsumerGroupOffset(consumerGroup: String, topic: String, brokerPartition: String) : String = {
    val zkClient = Zookeeper.getInstance()
    ZkUtils.readData(zkClient, consumersZPath + "/" + consumerGroup + "/offsets/" + topic + "/" + brokerPartition)	
  }
  
}