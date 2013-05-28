package controllers

import play.api.mvc.Controller
import play.api.mvc.Action
import play.api.libs.json._
import models.brokers.Broker
import com.codahale.jerkson.Json._
import services.ConsumerService

object ConsumerController extends Controller {
  
   def api_list = Action { implicit request =>
     val brokers = ConsumerService.getConsumerGroups
     val brokersJson = generate(brokers)
     request match {
	  case Accepts.Json() => Ok(brokersJson).as("application/json")
	  case _ => BadRequest
	 }
  }
   
   def api_view(consumerGroup: String) = Action { implicit request =>     
     val brokers = ConsumerService.getConsumerGroupTopics(consumerGroup)
     val brokersJson = generate(brokers)
     request match {
	  case Accepts.Json() => Ok(brokersJson).as("application/json")
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
   
}