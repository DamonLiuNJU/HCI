package businesslogicservice;

import businesslogic.utilitybl.ReplyMessage;

public interface ManagerBLService extends ReplyMessage{
	public String sendAdvice(String toID,String content );
	public void changePassword(String newPw);
}
