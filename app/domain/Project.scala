package domain

/**
 * @author RodgersonM
 */

case class Project(val releaseNumber:String, val name: String, val projectDesc: String, val client:String, val children:List[SITCycleSubmission]) {
}

case class SITCycleSubmission(val name:String, val releaseDecision:String, val hyperlink:String) {}

case class Release(val name:String, val children:List[Project]) {}

case class ClientCode(val clientCode:String) {}

