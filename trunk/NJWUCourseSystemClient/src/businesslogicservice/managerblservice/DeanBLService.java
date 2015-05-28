package businesslogicservice.managerblservice;

import businesslogic.utilitybl.ReplyMessage;

public interface DeanBLService extends ReplyMessage{
	/**
	 * 发送信息
	 * @param toID
	 * @param content
	 * @return
	 */
	public String sendMessage(String toID,String content);
	
	/**
	 * 修改密码
	 * @param newPw
	 */
	public void changePassword(String newPw);
}
