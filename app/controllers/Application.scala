package controllers

import play.api._
import play.api.mvc._
import dal._
import domain._

object projectDal {
  val projectDal = new dal.ProjectDal {}
  val projList = projectDal.getProjects("")
}

class Application extends Controller {

    def searchProjects(searchTerm: String, perPage:Int, pageNo:Int) = //Action {
    {
      //val projectDal = new dal.ProjectDal {}
      //val projList = projectDal.getProjects(searchTerm)
      //val subList = projectDal.projList.take(perPage)
    //Ok(views.html.main("Project list")(views.html.list(projList,searchTerm)))
    displayProjList(perPage,pageNo,searchTerm)
  }
  
    def displayProjList(perPage:Int,pageNo:Int,searchTerm:String) = Action {
      //val projectDal = new dal.ProjectDal {}
      //val projList = projectDal.getProjects(searchTerm)
      val subList = (projectDal.projList.drop((pageNo-1)*perPage)).take(perPage)
    Ok(views.html.main("Project list")(views.html.list(subList,searchTerm,perPage,pageNo)))
  }
  //def index = {Action {
    
    //Ok(views.html.index("Your new application is ready."))
//  }
  //}
}
