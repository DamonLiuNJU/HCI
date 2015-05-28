package businesslogicservice.managerblservice;

import java.util.ArrayList;

import vo.managervo.MessageVO;

public interface FacultyBLService {
	/**
	 * 
	 * @param myID
	 * @return
	 */
	public ArrayList<MessageVO> receiveMessage(String myID);
	
	
	public void changePassword(String newPw);
}
