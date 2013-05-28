package controllers

import play.api.mvc.Controller
import play.api.mvc.Action
import services.BrokerService
import play.api.libs.json._
import com.codahale.jerkson.Json._
import models.brokers.Brokers

object BrokersController extends Controller {
  
   def api_list = Action { implicit request =>     
     val brokers = BrokerService.getBrokers
     val brokersJson = generate(brokers)
     request match {
	  case Accepts.Json() => Ok(brokersJson).as("application/json")
	  case _ => BadRequest
	 }
  }
   
   def web_list = Action { implicit request => 
     val brokerList = BrokerService.getBrokers
     Ok(views.html.brokers.information(brokerList.getBrokers))
   }
   
}