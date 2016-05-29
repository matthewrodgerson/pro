package controllers
import domain._

object test {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(158); 
val projList = List(("F163","AAAAA","project A"),("F163","BBBBB","project B"),("F162","CCCCC","project C"));System.out.println("""projList  : List[(String, String, String)] = """ + $show(projList ));$skip(55); 

def q(inString:String):String = {
 '"'+inString+'"'
};System.out.println("""q: (inString: String)String""");$skip(223); 

def c(start:String,typeList:List[String],valList:List[String]):String = {
  if (typeList.isEmpty) start.substring(0,start.length-1)+"}" else
  c(start+q(typeList.head)+":"+q(valList.head)+",",typeList.tail,valList.tail)
};System.out.println("""c: (start: String, typeList: List[String], valList: List[String])String""");$skip(58); val res$0 = 
 
c("{",List("name","desc"),List("Matthew","programmer"));System.out.println("""res0: String = """ + $show(res$0));$skip(36); 

val nameList = List("name","desc");System.out.println("""nameList  : List[String] = """ + $show(nameList ));$skip(884); 

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
};System.out.println("""toJSON: (inList: List[(String, String, String)], outputString: String, currentLevel: String)String""");$skip(68); 
val starter = "{"+q("name")+":"+q("Releases")+","+q("children")+":";System.out.println("""starter  : String = """ + $show(starter ));$skip(48); 
val endJSON = toJSON (projList,starter,"")+"]}";System.out.println("""endJSON  : String = """ + $show(endJSON ))}
}
