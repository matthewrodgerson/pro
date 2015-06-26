package controllers

import play.api._
import play.api.mvc._
import dal._

class Application extends Controller {

    def searchProjects(searchTerm: String) = Action {
    val projectDal = new dal.ProjectDal {}
    val projList = projectDal.getProjects(searchTerm)
    Ok(views.html.main("Project list")(views.html.list(projList,searchTerm)))
  }
  
  
  //def index = {Action {
    
    //Ok(views.html.index("Your new application is ready."))
//  }
  //}
}
