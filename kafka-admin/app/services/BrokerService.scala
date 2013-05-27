package services

import kafka.utils.ZkUtils
import utils.Zookeeper

object BrokerService {
  
  def getBrokers = {

    val brokerTopicsZPath = "/brokers/ids"
    val zkClient = Zookeeper.getInstance()
    
    ZkUtils.getChildrenParentMayNotExist(zkClient, brokerTopicsZPath)
    
  }

}