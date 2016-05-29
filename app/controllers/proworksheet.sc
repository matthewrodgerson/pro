package controllers
import domain._

object test {
val projList = List(("F163","AAAAA","project A"),("F163","BBBBB","project B"),("F162","CCCCC","project C"))
                                                  //> projList  : List[(String, String, String)] = List((F163,AAAAA,project A), (F
                                                  //| 163,BBBBB,project B), (F162,CCCCC,project C))

def q(inString:String):String = {
 '"'+inString+'"'
}                                                 //> q: (inString: String)String

def c(start:String,typeList:List[String],valList:List[String]):String = {
  if (typeList.isEmpty) start.substring(0,start.length-1)+"}" else
  c(start+q(typeList.head)+":"+q(valList.head)+",",typeList.tail,valList.tail)
}                                                 //> c: (start: String, typeList: List[String], valList: List[String])String
 
c("{",List("name","desc"),List("Matthew","programmer"))
                                                  //> res0: String = {"name":"Matthew","desc":"programmer"}

val nameList = List("name","desc")                //> nameList  : List[String] = List(name, desc)

def toJSON (inList:List[(String,String,String)],outputString:String,currentLevel:String):String = {
 if (inList.isEmpty) return outputString+"]}" else {
   if (inList.head._1 == currentLevel) {
   val extraBit = ","+c("{",nameList,List(inList.head._2,inList.head._3))
   val newString = outputString + extraBit
   toJSON (inList.tail,newString,currentLevel) }
   else if (currentLevel != "") {
   val extraBit = "]},{"+ q("name")+":"+q(inList.head._1)+ ","+q("children")+ ":[" +
     c("{",nameList,List(inList.head._2,inList.head._3))
   val newString = outputString + extraBit
   toJSON (inList.tail,newString,inList.head._1) }
   else {
   val extraBit = "[{"+q("name")+":"+q(inList.head._1)+ ","+q("children")+ ":[" +
     c("{",nameList,List(inList.head._2,inList.head._3))
   val newString = outputString + extraBit
   toJSON (inList.tail,newString,inList.head._1) }
   
 }
}                                                 //> toJSON: (inList: List[(String, String, String)], outputString: String, curr
                                                  //| entLevel: String)String
val starter = "{"+q("name")+":"+q("Releases")+","+q("children")+":"
                                                  //> starter  : String = {"name":"Releases","children":
val endJSON = toJSON (projList,starter,"")+"]}"   //> endJSON  : String = {"name":"Releases","children":[{"name":"F163","children
                                                  //| ":[{"name":"AAAAA","desc":"project A"},{"name":"BBBBB","desc":"project B"}]
                                                  //| },{"name":"F162","children":[{"name":"CCCCC","desc":"project C"}]}]}
}