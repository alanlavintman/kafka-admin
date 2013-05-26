package controllers

import play.api.mvc._
import play.api.libs.json.Json._
import services.BrokerService


object BrokersController extends Controller {
  
   def api_list = Action { implicit request =>
     
     val brokers = BrokerService.getBrokers
     val brokers2 = brokers
     request match {
	  case Accepts.Json() => Ok("")
	  case _ => BadRequest
	 }
  }

}