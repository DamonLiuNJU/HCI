package businesslogic.managerbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.managerpo.ManagerPO;
import po.managerpo.MessagePO;
import vo.managervo.MessageVO;
import businesslogic.planbl.Plan;
import businesslogicservice.managerblservice.FacultyBLService;

/**
 * 院系教务老师类，继承了manager类
 * @author cbb
 *
 */
public class Faculty extends Manager implements FacultyBLService{
	String facultyID;
	static int ID_LENGTH=6;               //院系教务老师的id长度
	static char lastCharAsMajorFlag='1';  //仅院系主教务老师的id最后一位为1
	
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
	
	/**
	 * 查看发给我的信息
	 */
	@Override
	public ArrayList<MessageVO> receiveMessage(String myID) {
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
			if(id.charAt(id.length()-1)==lastCharAsMajorFlag){
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
}
