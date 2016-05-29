package controllers

import play.api._
import play.api.mvc._
import dal._
import domain._
import play.api.libs.json._

class Application extends Controller {

    def searchProjects(searchTerm: String, perPage:Int, pageNo:Int) = 
    {
      val projectDal = new dal.ProjectDal {}
      displayProjList(perPage,pageNo,searchTerm)
    }
  
    def q(inString:String):String = {
     '"'+inString+'"'
    }
    
    def c(start:String,typeList:List[String],valList:List[String]):String = {
      if (typeList.isEmpty) start.substring(0,start.length-1)+"}" else
      c(start+q(typeList.head)+":"+q(valList.head)+",",typeList.tail,valList.tail)
    }  
    def toJSON (inList:List[Project],outputString:String,currentLevel:String):String = {
      val nameList = List("name","desc")
      if (inList.isEmpty) return outputString+"]}" else {
      if (inList.head.releaseNumber == currentLevel) {
       val extraBit = ","+c("{",nameList,List(inList.head.projectCode,inList.head.projectDesc))
       val newString = outputString + extraBit
       toJSON (inList.tail,newString,currentLevel) }
      else if (currentLevel != "") {
       val extraBit = "]},{"+ q("name")+":"+q(inList.head.releaseNumber)+ ","+q("children")+ ":[" +
       c("{",nameList,List(inList.head.projectCode,inList.head.projectDesc))
       val newString = outputString + extraBit
       toJSON (inList.tail,newString,inList.head.releaseNumber) }
      else {
       val extraBit = "[{"+q("name")+":"+q(inList.head.releaseNumber)+ ","+q("children")+ ":[" +
       c("{",nameList,List(inList.head.projectCode,inList.head.projectDesc))
       val newString = outputString + extraBit
       toJSON (inList.tail,newString,inList.head.releaseNumber) }
     }
} 
    
    def displayProjList(perPage:Int,pageNo:Int,searchTerm:String) = Action {
      val projectDal = new dal.ProjectDal {}
      val projList = projectDal.getProjects(searchTerm).sortWith (_.releaseNumber < _.releaseNumber)
      val subList = (projList.drop((pageNo-1)*perPage)).take(perPage)
    //Ok(views.html.main("Project list")(views.html.list(subList,searchTerm,perPage,pageNo)))
    val starter = "{"+q("name")+":"+q("Releases")+","+q("children")+":"
    val endJSON = toJSON (projList,starter,"")+"]}"
    //val json:JsValue = Json.parse(endJSON)
      Ok(views.html.main("Project Detail")(views.html.releasesViewer3(endJSON)))
  }
    
    def displayProjDets(projCode:String) = Action {
      val projectDal = new dal.ProjectDal {}
      val projList = projectDal.getProjects(projCode).sortWith (_.releaseNumber < _.releaseNumber)
    Ok(views.html.main("Project Detail")(views.html.details(projList)))
    //Ok(views.html.main("Project Detail")(views.html.releasesViewer3))
  }
  //def index = {Action {
    
    //Ok(views.html.index("Your new application is ready."))
//  }
  //}
}
