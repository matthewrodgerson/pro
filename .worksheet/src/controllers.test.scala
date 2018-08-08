package controllers
import domain._
//import org.json4s._

object test {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(238); 
val projList = List(("F163","AAAAA","project A","SIT1"),("F163","AAAAA","project A","SIT2"),("F163","BBBBB","project B","SIT2"),("F162","CCCCC","project C","SIT3"));System.out.println("""projList  : List[(String, String, String, String)] = """ + $show(projList ));$skip(56); 

def q(inString:String):String = {
 '"'+inString+'"'
};System.out.println("""q: (inString: String)String""");$skip(224); 

def c(start:String,typeList:List[String],valList:List[String]):String = {
  if (typeList.isEmpty) start.substring(0,start.length-1)+"}" else
  c(start+q(typeList.head)+":"+q(valList.head)+",",typeList.tail,valList.tail)
};System.out.println("""c: (start: String, typeList: List[String], valList: List[String])String""");$skip(47); 
val nameList = List("name","desc","SIT cycle");System.out.println("""nameList  : List[String] = """ + $show(nameList ));$skip(41); 
val nameList2 = List("desc","SIT cycle");System.out.println("""nameList2  : List[String] = """ + $show(nameList2 ));$skip(1893); 

def toJSON (inList:List[(String,String,String,String)],outputString:String,currentReleaseLevel:String,currentProjectLevel:String):String = {
 if (inList.isEmpty) return outputString+"]}" else {
   

   // Same release and project
   if (inList.head._1 == currentReleaseLevel & inList.head._2 == currentProjectLevel) {
   val extraBit = ","+c("{",nameList2,List(inList.head._3,inList.head._4))
   val newString = outputString + extraBit
   toJSON (inList.tail,newString,currentReleaseLevel,currentProjectLevel) }
  
   // Same release, different project
   else if (inList.head._1 == currentReleaseLevel & inList.head._2 !=currentProjectLevel) {
   val extraBit = "]},[{"+q("name")+":"+q(inList.head._2)+ ","+q("children")+ ":[" +
     //c("{",nameList,List(inList.head._2,inList.head._3,inList.head._4))
     //"{"+ q("name")+":"+q(inList.head._2)+ ","+q("children")+ ":["+
     c("{",nameList2,List(inList.head._3,inList.head._4))
   val newString = outputString + extraBit
   toJSON (inList.tail,newString,currentReleaseLevel,inList.head._2) }
      
   // New Release level, not first time
   else if (currentReleaseLevel != "") {
   val extraBit = "]},{"+ q("name")+":"+q(inList.head._1)+ ","+q("children")+ ":[" +
     "{"+ q("name")+":"+q(inList.head._2)+ ","+q("children")+ ":["+
     c("{",nameList2,List(inList.head._3,inList.head._4))
   val newString = outputString + extraBit
   toJSON (inList.tail,newString,inList.head._1,inList.head._2) }
 
   // First time through
   else {
   val extraBit = "[{"+q("name")+":"+q(inList.head._1)+ ","+q("children")+ ":[" +
     //c("{",nameList,List(inList.head._2,inList.head._3,inList.head._4))
     "{"+ q("name")+":"+q(inList.head._2)+ ","+q("children")+ ":["+
     c("{",nameList2,List(inList.head._3,inList.head._4))
   val newString = outputString + extraBit
   toJSON (inList.tail,newString,inList.head._1,inList.head._2) }
  
 }
};System.out.println("""toJSON: (inList: List[(String, String, String, String)], outputString: String, currentReleaseLevel: String, currentProjectLevel: String)String""");$skip(68); 
val starter = "{"+q("name")+":"+q("Releases")+","+q("children")+":";System.out.println("""starter  : String = """ + $show(starter ));$skip(51); 
val endJSON = toJSON (projList,starter,"","")+"]}";System.out.println("""endJSON  : String = """ + $show(endJSON ))}
}
