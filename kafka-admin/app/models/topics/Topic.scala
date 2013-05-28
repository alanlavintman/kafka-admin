package models.topics

import scala.collection._

case class Topic (name: String) {
  
  val brokersTopicInfo: mutable.ListBuffer[BrokerTopicItem] = new mutable.ListBuffer()
  
  def add(brokerInfo: BrokerTopicItem) = {brokersTopicInfo+=brokerInfo}

}