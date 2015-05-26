package businesslogicservice.managerblservice;

import java.util.ArrayList;

import vo.managervo.MessageVO;

public interface FacultyBLService {
	
	public ArrayList<MessageVO> receiveMessage(String myID);
	
	public String getFacultyID();
	
	public void changePassword(String newPw);
}
