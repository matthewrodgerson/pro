package dal

import sqlest._
import domain.Project

/**
 * @author RodgersonM
 */
class ProjectTable(alias: Option[String]) extends Table("relsubdtl", alias) {
  val releaseNumber = column[String]("relnum")
  val projectCode = column[String]("prjcod")
  val projectDesc = column[String]("desc")
}

object ProjectTable extends ProjectTable(None)

trait ProjectDal extends SqlestDb {

  def getProjects(searchTerm: String) : List[Project] = {
    
    if (searchTerm != "") {
    val wildCardSearch = ("%" + searchTerm + "%")  
    select
      .from(ProjectTable)
      .where((ProjectTable.projectDesc like wildCardSearch) || (ProjectTable.projectCode like wildCardSearch))
      .extractAll(projectExtractor)}
    else
      select
      .from(ProjectTable)
      .extractAll(projectExtractor)    
  }

  

  lazy val projectExtractor = extract[Project](
    releaseNumber = ProjectTable.releaseNumber,
    projectCode = ProjectTable.projectCode,
    projectDesc = ProjectTable.projectDesc

  )

}


  