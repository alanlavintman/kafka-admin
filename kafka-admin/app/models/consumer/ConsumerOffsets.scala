package models.consumer

import scala.collection._

class ConsumerOffsets {
  
  val topicOffsets: mutable.ListBuffer[ConsumerTopicOffset] = new mutable.ListBuffer()
  
  def getTopicOffsets : mutable.ListBuffer[ConsumerTopicOffset] = topicOffsets
  
  def add(topicOffset: ConsumerTopicOffset) = topicOffsets+=topicOffset

}