package controllers

import play.api.mvc.Controller
import play.api.mvc.Action
import play.api.libs.json._
import models.brokers.Broker
import services.ConsumerService
import com.codahale.jerkson.Json._
import models.consumer.ConsumerOffsets
import models.consumer.DetailedConsumerTopicOffset
import models.consumer.ConsumerOffset

object ConsumerController extends Controller {
  
   def api_list = Action { implicit request =>
     val consumers = ConsumerService.getConsumerGroups
     val consumersJson = generate(consumers)
     request match {
	  case Accepts.Json() => Ok(consumersJson).as("application/json")
	  case _ => BadRequest
	 }
  }
   
   def api_view(consumerGroup: String) = Action { implicit request =>     
     val consumerTopics = ConsumerService.getConsumerGroupTopics(consumerGroup)
     val consumerTopicsJson = generate(consumerTopics)
     request match {
	  case Accepts.Json() => Ok(consumerTopicsJson).as("application/json")
	  case _ => BadRequest
	 }
  }
   
  def api_view_topic_offset(consumerGroup: String, topic: String) = Action { implicit request =>     
     val topicOffsets = ConsumerService.getConsumerGroupTopicsOffset(consumerGroup, topic)
     val topicOffsetsJson = generate(topicOffsets)
     request match {
	  case Accepts.Json() => Ok(topicOffsetsJson).as("application/json")
	  case _ => BadRequest
	 }
  }
  
  def api_get_broker_offset(consumerGroup: String, topic: String, brokerId: String, partition: String) = Action {
    implicit request =>     
     val detailedBrokerOffset = ConsumerService.getDetailedConsumerGroupAndTopicsOffsetByBrokerId(consumerGroup, topic, brokerId, partition)
     val detailedBrokerOffsetJson = generate(detailedBrokerOffset)
     request match {
	  case Accepts.Json() => Ok(detailedBrokerOffsetJson).as("application/json")
	  case _ => BadRequest
	 }
  }
  
  def api_put_broker_offsets(consumerGroup : String, topic : String, broker : String, partition: String) = Action { 
    implicit request =>
	 val updateBrokerOffset : ConsumerOffset = ConsumerService.updateBrokerOffset(consumerGroup, topic, broker, partition)
	 val updateBrokerOffJson = generate(updateBrokerOffset)
	  request match {
	  	case Accepts.Json() => Ok(updateBrokerOffJson).as("application/json")
	  	case _ => BadRequest
 	   }
  }
  
   def web_consumer_list = Action { implicit request => 
      val consumers = ConsumerService.getConsumerGroups
      Ok(views.html.consumers.list(consumers.getConsumers))
   }
   
   def web_consumer_view(consumerGroup: String) = Action { implicit request => 
      val consumerTopics = ConsumerService.getConsumerGroupTopics(consumerGroup)
      Ok(views.html.consumers.info(consumerTopics.getTopics,consumerGroup))
   }
      
   def web_consumer_offsets(consumerGroup : String, topic : String) = Action { implicit request =>
       val topicOffsets : ConsumerOffsets = ConsumerService.getConsumerGroupTopicsOffset(consumerGroup, topic)
       Ok(views.html.consumers.offsets(topicOffsets.getSortedTopicOffsets,consumerGroup, topic))     
   }

}