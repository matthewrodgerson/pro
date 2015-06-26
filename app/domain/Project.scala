package domain

/**
 * @author RodgersonM
 */

case class Project(val projectCode: String, val projectDesc: String) {


}

object Project {

  def formApply(projectCode: String, projectDesc: String) = new Project(projectCode,projectDesc)

  def formUnapply(project: Project): Option[(String, String)] = 
    Some((project.projectCode, project.projectDesc)) 

}