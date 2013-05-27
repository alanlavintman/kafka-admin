package controllers

import play.api.mvc.Controller
import play.api.mvc.Action
import services.BrokerService
import play.api.libs.json._
import models.Broker
import com.codahale.jerkson.Json._

object BrokersController extends Controller {
  
   def api_list = Action { implicit request =>     
     val brokers = BrokerService.getBrokers
     val brokersJson = generate(brokers)
     request match {
	  case Accepts.Json() => Ok(brokersJson)
	  case _ => BadRequest
	 }
  }

}