package vo.managervo;

import po.managerpo.ManagerPO;
import businesslogic.planbl.Plan;

public class ManagerVO {
	String id;
	String name;
	String facultyName;
	String contactInfo;
	
	public ManagerVO(ManagerPO mp){
		id=mp.getID();
		name=mp.getName();
		String fid=mp.getFacultyID();
		if(!fid.equals("-1")){
			facultyName=new Plan().getFacultyName(fid);
		}
		contactInfo=mp.getContactInfo();
	}
	
	public String getID(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public String getFacultyName(){
		return facultyName;
	}
	
	public String getContactInfo(){
		return contactInfo;
	}
}
