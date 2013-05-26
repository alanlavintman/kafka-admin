package services

import kafka.utils.ZkUtils

object BrokerService {
  
  def getBrokers = {

    val brokerTopicsZPath = "/brokers/ids"
    val zkClient = Zookeeper.getInstance()
    
    val brokers = ZkUtils.getChildrenParentMayNotExist(zkClient, brokerTopicsZPath)
    for (broker <- brokers){
    	val one = broker
    }
  }

}