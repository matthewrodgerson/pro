package dal

import sqlest._
import domain.{ClientCode, Project, Release, SITCycleSubmission}

/**
 * @author RodgersonM
 */
class ProjectTable(alias: Option[String]) extends Table("relsubdtl", alias) {
  val releaseNumber = column[String]("relnum")
  val name = column[String]("prjcod")
  val projectDesc = column[String]("desc")
  val sitCycle = column[String]("sit_cycle")
  val releaseDecision = column[String]("release_committee_decision")
  val hyperlink = column[String]("pidlnk")
  val client = column[String]("client")
}

object ProjectTable extends ProjectTable(None)

trait ReleaseDal extends SqlestDb {

  def getProjects(searchTerm: String) : List[Release] = {
      select
      .from(ProjectTable)
        .where(ProjectTable.client === searchTerm || searchTerm == "")
      .orderBy (ProjectTable.releaseNumber)
      .extractAll(releaseExtractor)
  }

  lazy val releaseExtractor = extract[Release](
    name = ProjectTable.releaseNumber,
    children = projectExtractor)//.asList)
    .groupBy(ProjectTable.releaseNumber)

  lazy val projectExtractor = extract[Project](
    releaseNumber = ProjectTable.releaseNumber,
    //releaseNumber = ProjectTable.releaseNumber,
    name = ProjectTable.name,
    projectDesc = ProjectTable.projectDesc,
    children = sitExtractor.asList,
    client=ProjectTable.client)
    .groupBy(ProjectTable.releaseNumber,ProjectTable.name,ProjectTable.projectDesc)
    
    lazy val sitExtractor = extract[SITCycleSubmission] (
    name = ProjectTable.sitCycle,
    releaseDecision = ProjectTable.releaseDecision,
    hyperlink = coalesce(ProjectTable.hyperlink, "none").as("hyperlink"))

    def getClients(): List[ClientCode] = {
    select(ProjectTable.client)
      .from(ProjectTable)
      .extractAll(clientExtractor)
    }

    lazy val clientExtractor = extract[ClientCode] (
    clientCode=ProjectTable.client).groupBy(ProjectTable.client)
    



}


  