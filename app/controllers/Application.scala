package controllers

import play.api._
import play.api.mvc._
import dal._
import domain._
import play.api.libs.json._
import org.json4s._
import org.json4s.native.Serialization
import org.json4s.native.Serialization.{read, write}


object projectDal {
  val releaseDal = new dal.ReleaseDal {}
  val releaseList = releaseDal.getProjects("")
}

class Application extends Controller {

  def searchProjects(searchTerm: String, perPage:Int, pageNo:Int) = 
    {displayProjList(perPage,pageNo,searchTerm)}
 
  def q(inString:String):String = {
     '"'+inString+'"'}

  def displayProjList(perPage:Int,pageNo:Int,searchTerm:String) = Action {
    val releaseDal = new dal.ReleaseDal {}
    val releaseList = releaseDal.getProjects(searchTerm)
    implicit val formats = Serialization.formats(NoTypeHints)
    val starter = "{"+q("name")+":"+q("Releases")+","+q("children")+":" 
    val endJSON = (starter+write(releaseList)+"}")
    Ok(views.html.main("Project Detail")(views.html.releasesViewer3(endJSON)))
  }
    
}
