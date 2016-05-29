package domain

/**
 * @author RodgersonM
 */

case class Project(val releaseNumber:String, val projectCode: String, val projectDesc: String) {


}

object Project {

  def formApply(releaseNumber: String, projectCode: String, projectDesc: String) = new Project(releaseNumber,projectCode,projectDesc)

  def formUnapply(project: Project): Option[(String, String, String)] = 
    Some((project.releaseNumber, project.projectCode, project.projectDesc)) 

}