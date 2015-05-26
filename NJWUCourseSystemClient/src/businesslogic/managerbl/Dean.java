package businesslogic.managerbl;

import java.util.ArrayList;

import vo.managervo.SentMessageVO;
import businesslogic.coursebl.CourseApply;
import businesslogicservice.managerblservice.DeanBLService;

public class Dean extends Manager implements DeanBLService{
   
   public Dean(String id){
	   super(id);
   }
   public Dean(){
	   
   }
   
   /**
    * 发送信息必须创建一个有身份的教务处老师
    */
   public String sendMessage(String toID,String content ){
	   if(toID=="")  return SEND_FAIL1;
	   Message m=new Message(id,toID,content);
	   m.add();
	   return SEND_SUCCEED;
   }
  
   /**
    * 关于通识及公选课的信息发送：通知教务老师申请信息，并删掉apply表中该项申请
    * @param toID
    * @param content
    * @param courseName
    * @return
    */
   public String sendMessageAboutApply(String toID,String content,String courseName){
	   CourseApply ca=new CourseApply();
	   ca.delete(courseName);
	   return sendMessage(toID,content);
   }
   
   public ArrayList<SentMessageVO> showMyMessage(){   
	   return new Message().getMessageByFrom(id);
   }
}
