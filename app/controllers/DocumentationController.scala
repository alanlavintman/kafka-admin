package controllers

import play.api._
import play.api.mvc._

object DocumentationController extends Controller {
  
  def web_rest_api = Action {
      Ok(views.html.documentation.rest())
  }
  
  def web_web_admin = Action {
      Ok(views.html.documentation.web())
  }
  
  def web_best_practices = Action {
      Ok(views.html.documentation.best_practices())
  }
  
}