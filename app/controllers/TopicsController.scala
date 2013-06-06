package controllers

import play.api.mvc.Controller
import play.api.mvc.Action
import play.api.libs.json._
import com.codahale.jerkson.Json._
import services.TopicService

object TopicsController extends Controller {
  
   def api_view = Action { implicit request =>     
     val topics = TopicService.getTopics
     request match {
      case Accepts.Json() => Ok(generate(topics)).as("application/json")
	  case _ => BadRequest
	 }
  }
  
   def api_view_with_details = Action { implicit request =>     
     val topics = TopicService.getTopicsWithDetails
     request match {
      case Accepts.Json() => Ok(generate(topics)).as("application/json")
	  case _ => BadRequest
	 }
  }
   
   def api_view_detail(topicName: String) = Action { implicit request =>     
     val topicDetails = TopicService.getTopicDetails(topicName)
     request match {
      case Accepts.Json() => Ok(generate(topicDetails)).as("application/json")
	  case _ => BadRequest
	 }
  }
   
   def web_topic_list = Action { implicit request => 
     val topics = TopicService.getTopics
     Ok(views.html.topics.list(topics.getTopics))
   }
   
   def web_topic_detail(topicName : String) = Action { implicit request => 
     val topicDetails = TopicService.getTopicDetails(topicName)
     Ok(views.html.topics.info(topicDetails.getTopicInfo, topicName))
   }

}