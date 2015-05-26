package businesslogic.managerbl;


import java.rmi.RemoteException;

import po.TeacherPO;
import po.managerpo.ManagerPO;
import po.studentpo.StudentPO;
import rmiconnector.RemoteDataFactory;
import vo.adminvo.UserVO;
import businesslogic.planbl.Plan;
import businesslogic.teacherbl.Teacher;
import businesslogicservice.managerblservice.AdminBLService;
import dataservice.managerdataservice.AdminDataService;

public class Admin extends Manager implements AdminBLService{
	private String name;
	private String ID;
	private String password;
	RemoteDataFactory remote ;
	AdminDataService adminData;
	
	public Admin(String name , String id , String password){
		this.name = name ;
		this.ID = id ;
		this.password = password;
		remote = new RemoteDataFactory();
		adminData = (AdminDataService) remote.getData("Admin");
	}
	
	public Admin(){
		remote = new RemoteDataFactory();
		adminData = (AdminDataService) remote.getData("Admin");
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name ;
	}
	
	public String getID(){
		return ID;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getPassword(){
		return password;
	}

	@Override
	public String deleteUser(String id, String type) {
		// TODO Auto-generated method stub
		try {
			if(adminData.deleUser(id, type)){
				return DELETE_SUCCEED;
			}
			else return DELETE_FAILED;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return CONNECTION_REFUSED;
		}
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see businesslogicservice.adminblservice.AdminBLService#RegisterUser(vo.adminvo.UserVO)
	 * 提取uv的type ， 并按照相应的type形成响应的PO对象，调用服务器的方法进行注册
	 */
	public String RegisterUser(UserVO uv) {
		// TODO Auto-generated method stub
		String type = uv.getType();
		uv.setFaculty_id(new Plan().getFacultyID(uv.getFaculty_name()));//按院系名获取院系id
		
		try {
			if(type.equals("Teacher")){
				TeacherPO tp = this.initTP(uv);
				if(new Teacher().isExist(tp.getId()))
					return ID_EXIST;
				else if(adminData.registerUser(tp, type))
					return REGISTER_SUCCEED;
				else return REGISTER_FAILED;
			}
			else if(type.equals("Student")){//注册学生
				StudentPO sp = this.initSP(uv);
				if(adminData.registerUser(sp, type))
					return REGISTER_SUCCEED;
				else return REGISTER_FAILED;
			}
			else if(type.equals("Manager")){
				Manager manager = new Manager();
				if(manager.isIDExist(uv.getId())){
					return ID_EXIST;
				}
				else {
					ManagerPO mp = this.initManager(uv);
					if(adminData.registerUser(mp, type))
						return REGISTER_SUCCEED;
					else return REGISTER_FAILED;
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return CONNECTION_REFUSED;
		}
		return REGISTER_FAILED;
	}

	@Override
	public UserVO findUser(String id) {
		// TODO Auto-generated method stub
		try {
			char[] temp = id.toCharArray();
			if((temp.length==2)||(temp.length==6)||(temp.length==7)||(temp.length==8)){
				if((temp.length == 2)||(temp.length == 6)){
					if(!new Manager().isIDExist(id)){
						return null;
					}
					Object po = adminData.findUser(id);
					UserVO uv = new UserVO(po);
					return uv;
				}
				Object po = adminData.findUser(id);
				if(po == null){
					return null;
				}
				UserVO uv = new UserVO(po);
				uv.setFaculty_name(new Plan().getFacultyName(uv.getFaculty_id()));
				return uv;
			}
			
			
			return null;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	/*
	 * admin更新一个用户的信息
	 */
	public String updateUser(UserVO uv) {
		// TODO Auto-generated method stub
		String type = uv.getType();
		
		try {
			if(type.equals("Teacher")){
				TeacherPO tp = this.initTP(uv);
					if(adminData.updateUser(tp, type))
						return UPDATE_SUCCEED;
					else return UPDATE_FAILED;
			}
			else if(type.equals("Student")){
				StudentPO sp = this.initSP(uv);
				if(adminData.updateUser(sp, type))
					return UPDATE_SUCCEED;
				else return UPDATE_FAILED;
			}
			else if(type.equals("Manager")){
				ManagerPO mp = this.initManager(uv);
				if(adminData.updateUser(mp, type))
					return UPDATE_SUCCEED;
				else return UPDATE_FAILED;
			}
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//用uservo初始化一个StudentPO
	private StudentPO initSP(UserVO uv){
		StudentPO sp = new StudentPO();
		sp.setStudentID(uv.getId());
		sp.setName(uv.getName());
		sp.setPassword(uv.getPassword());
		sp.setFaculty_ID(uv.getFaculty_id());
		sp.setGrade(uv.getGrade());
		sp.setContactInfo(uv.getContactInfo());
		sp.setPhone(uv.getPhone());
		sp.setHomeAddress(uv.getHomeAddress());
		sp.setMotherName(uv.getMotherName());
		sp.setMotherPhone(uv.getMotherPhone());
		sp.setFatherName(uv.getFatherName());
		sp.setFatherPhone(uv.getFatherPhone());
		
		return sp;
	}
	
	//用uservo初始化一个TeacherPO
	private TeacherPO initTP(UserVO uv){
		TeacherPO tp = new TeacherPO();
		tp.setId(uv.getId());
		tp.setName(uv.getName());
		tp.setPassword(uv.getPassword());
		tp.setFacultyID(uv.getFaculty_id());
		tp.setContactInfo(uv.getContactInfo());
		tp.setSeniority(uv.getSeniority());
		
		return tp;
	}
	
	//用uservo初始化一个managerPO
	private ManagerPO initManager(UserVO uv){
		ManagerPO mp= new ManagerPO();
		mp.setID(uv.getId());
		mp.setFacultyID(uv.getFaculty_id());
		mp.setName(uv.getName());
		mp.setPassword(uv.getPassword());
		mp.setContactInfo(uv.getContactInfo());
		
		return mp;
	}

//	@Override
//	public boolean isValid(String id, String password) {
//		// TODO Auto-generated method stub
//		char[] temp = id.toCharArray();
//		if(!((temp.length == 2)&&(temp[0] == '0'))){
//			return false;
//		}
//		if(!new Manager().isIDExist(id)){
//			return false ;
//		}
//		
//		else {
//			try {
//				ManagerPO mp = (ManagerPO) adminData.findUser(id);
//				if(password.equals(mp.getPassword())){
//					return true;
//				}
//				return false;
//			} catch (RemoteException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}
//		return false;
//	}
	
	
	
}
