package businesslogic.managerbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.managerpo.ManagerPO;
import po.managerpo.MessagePO;
import vo.managervo.MessageVO;
import businesslogic.planbl.Plan;
import businesslogicservice.managerblservice.FacultyBLService;

/**
 * 院系教务老师类
 */
public class Faculty extends Manager implements FacultyBLService{
	String facultyID;
	static int ID_LENGTH=6;
	
	public Faculty(String id){
		   super(id);
		   ManagerPO mp=new ManagerPO();
		   try {
			   mp=data.find(id);
		   } catch (RemoteException e) {
			   e.printStackTrace();
		   }
		   facultyID=mp.getFacultyID();
	}
	public Faculty(){
		   
	}
	
	public ArrayList<MessageVO> receiveMessage(String myID) {
		//未查到信息，返回会不会有异常？
		ArrayList<MessageVO> message=new ArrayList<MessageVO>();
		ArrayList<MessagePO> mpList=new ArrayList<MessagePO>();
		try {
			mpList = messageData.finds(myID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		for(MessagePO mp: mpList){
			String from_name="";
			try {
				from_name = data.find(mp.getFromID()).getName();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			message.add(new MessageVO(from_name,mp.getContent()));
		}
		return message;
	}
	
	/**
	 * 根据院系名得到该院系教务长的id
	 * @param facultyName
	 * @return
	 */
	public String getMajorFacultyID(String facultyName){
		String majorFacultyID="";
		ArrayList<ManagerPO> mpList=getAllFacultys(facultyName);
				
		for(ManagerPO mp:mpList){
			String id=mp.getID();
			//院系教务长的id最后一位为1
			if(id.charAt(id.length()-1)=='1'){
				majorFacultyID=id;
			}
		}
		return majorFacultyID;
	}
	
	public String getFacultyID(){
		return facultyID;
	}
	
	public String getFacultyName(){
		Plan facList=new Plan();
		return facList.getFacultyName(facultyID);
	}
	
	/**
	 * 得到某个院系的教务老师总数
	 * @param facultyName
	 * @return
	 */
	public int getFacultyCount(String facultyName){
		int count=0;
		ArrayList<ManagerPO> mpList=getAllFacultys(facultyName);
				
		for(ManagerPO mp:mpList){
			String id=mp.getID();
			if(id.length()==ID_LENGTH){
				count++;
			}
		}
		return count;
	}
	
	/**
	 * 得到某个院系所有教务老师po列表
	 * @param facultyName
	 * @return
	 */
	ArrayList<ManagerPO> getAllFacultys(String facultyName){
		String facultyID=new Plan().getFacultyID(facultyName);
		ArrayList<ManagerPO> mpList=new ArrayList<ManagerPO>();
		try {
			mpList=data.finds(facultyID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return mpList;
	}
	
	public static void main(String[] args){
		System.out.print(new Faculty().getFacultyCount("软件学院"));
	}
}
