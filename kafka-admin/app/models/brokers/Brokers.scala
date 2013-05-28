package models.brokers

import scala.collection._

class Brokers {
  
  val brokers: mutable.ListBuffer[Broker] = new mutable.ListBuffer()
  
  def getBrokers : mutable.ListBuffer[Broker] = brokers
  
  def add(broker: Broker) = brokers+=broker

}