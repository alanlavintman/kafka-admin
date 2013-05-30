package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {
  
  def index = Action {
      Redirect("/web/")
  }
  
  def webIndex = Action {
      Ok(views.html.index())
  }

  
}