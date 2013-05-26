package controllers

import play.api.mvc._
import play.api.libs.json.Json._

object TopicsController extends Controller {
  
  def api_list = Action { implicit request =>
  
    val topics = List("asd")
    
    request match {
	  case Accepts.Json() => Ok(toJson(topics))
	  case _ => BadRequest
	}
  }

}