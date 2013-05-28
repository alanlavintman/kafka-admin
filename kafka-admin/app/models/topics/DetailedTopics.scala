package models.topics

import scala.collection._

class DetailedTopics {
  
  val topics: mutable.ListBuffer[Topic] = new mutable.ListBuffer()
  
  def getTopics : mutable.ListBuffer[Topic] = topics
  
  def add(topic: Topic) = topics+=topic
  
}