package services

import kafka.utils.ZkUtils
import utils.Zookeeper
import scala.collection._
import models.{Brokers, Broker}

object BrokerService {
  
  val brokerTopicsZPath = "/brokers/ids"
    
  def getBrokers = {
    
    val zkClient = Zookeeper.getInstance()
    val brokerIds = ZkUtils.getChildrenParentMayNotExist(zkClient, brokerTopicsZPath)
    val brokers = new Brokers
    
    val brokerSet: mutable.ListBuffer[Broker] = new mutable.ListBuffer()
    
    // Get all the broker identifiers and bring their personal information.
    for(brokerId <- brokerIds){
    	val brokerInformation = ZkUtils.readData(zkClient, brokerTopicsZPath + "/" + brokerId)
    	val brokenBrokerInformation = brokerInformation.split(":")
    	val broker: Broker = Broker(brokerId, brokenBrokerInformation(1),brokenBrokerInformation(2))
    	brokers.add(broker)
    }
    brokers
  }
  
}