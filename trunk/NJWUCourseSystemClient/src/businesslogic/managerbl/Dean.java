package businesslogic.managerbl;

import java.util.ArrayList;

import po.managerpo.ManagerPO;

import vo.managervo.SentMessageVO;
import businesslogic.coursebl.CourseApply;
import businesslogicservice.managerblservice.DeanBLService;

/**
 * 教务处老师类，继承了manager类
 * @author cbb
 *
 */
public class Dean extends Manager implements DeanBLService{  
	static int ID_LENGTH=2;
	static char firstCharAsFlag='1';
   
   public Dean(String id){
	   super(id);
   }
   public Dean(){
	   
   }
   
   /**
    * 发送信息必须创建一个有身份的教务处老师
    */
   @Override
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
   
   /**
    * 查看我的已发信息
    * @return ArrayList<SentMessageVO>
    */
   public ArrayList<SentMessageVO> showMyMessage(){   
	   return new Message().getMessageByFrom(id);
   }
   
   /**
    * 返回教务处老师总数，for forming registerID automatically
    * @return deanCount
    */
   public int getDeanCount(){
	   int count=0;
	   ArrayList<ManagerPO> mpList=getAllManagers();
	   
	   for(ManagerPO mp:mpList){
			String id=mp.getID();
			if(id.length()==ID_LENGTH && id.charAt(0)==firstCharAsFlag){
				count++;
			}
		}
	   return count;
   }
}
