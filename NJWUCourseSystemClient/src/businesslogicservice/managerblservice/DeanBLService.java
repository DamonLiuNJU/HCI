package businesslogicservice.managerblservice;

import businesslogic.utilitybl.ReplyMessage;

public interface DeanBLService extends ReplyMessage{
	public String sendMessage(String toID,String content);
	
	public void changePassword(String newPw);
}
