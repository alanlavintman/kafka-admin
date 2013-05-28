package models.topics

import scala.collection._

class Topics {
  
  val topics: mutable.ListBuffer[String] = new mutable.ListBuffer()
  
  def getTopics : mutable.ListBuffer[String] = topics
  
  def add(topic: String) = topics+=topic

}