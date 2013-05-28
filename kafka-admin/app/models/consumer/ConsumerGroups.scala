package models.consumer

import scala.collection._

class ConsumerGroups {
  
  val consumers: mutable.ListBuffer[String] = new mutable.ListBuffer()
  
  def getConsumers : mutable.ListBuffer[String] = consumers
  
  def add(consumer: String) = consumers+=consumer

}