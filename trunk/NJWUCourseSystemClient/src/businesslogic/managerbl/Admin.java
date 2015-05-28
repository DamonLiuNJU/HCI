package businesslogic.managerbl;


import java.rmi.RemoteException;
import java.util.Calendar;

import po.TeacherPO;
import po.managerpo.ManagerPO;
import po.studentpo.StudentPO;
import rmiconnector.RemoteDataFactory;
import vo.adminvo.UserVO;
import businesslogic.planbl.Plan;
import businesslogic.studentbl.FacultyStudent;
import businesslogic.teacherbl.Teacher;
import businesslogic.teacherbl.TeacherList;
import businesslogicservice.managerblservice.AdminBLService;
import dataservice.managerdataservice.AdminDataService;

/*
 * Admin类，继承Manager类，继承得到id，name，password属性。
 * 该类负责完成管理员的所有操作，有RegisterUser,
 * deleteUser,findUser,updateUser,idAuotomate5个public方法
 * initSP,initTP,initManager,getTail，4个private方法
 */
public class Admin extends Manager implements AdminBLService{
	RemoteDataFactory remote ;
	AdminDataService adminData;
	
	public Admin(String name , String id , String password){
		this.name = name ;
		this.id = id ;
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
	
	public void setPassword(String password){
		this.password = password;
	}
	

	@Override
	/*
	 * (non-Javadoc)
	 * @see businesslogicservice.managerblservice.AdminBLService#deleteUser(java.lang.String, java.lang.String)
	 * 删除一个用户，id为用户id，type为用户类型
	 */
	public String deleteUser(String id, String type) {
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
	/*
	 * (non-Javadoc)
	 * @see businesslogicservice.managerblservice.AdminBLService#findUser(java.lang.String)
	 * 查找一个用户，将其信息放入UserVO中
	 */
	public UserVO findUser(String id) {
		// TODO Auto-generated method stub
		try {
			char[] temp = id.toCharArray();
			if((temp.length==2)||(temp.length==6)||(temp.length==7)||(temp.length==8)){
				if((temp.length == 2)||(temp.length == 6)){//用户为管理员
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

	@Override
	/*
	 * (non-Javadoc)
	 * @see businesslogicservice.managerblservice.AdminBLService#idAutomate(java.lang.String, java.lang.String)
	 * 自动生成id
	 */
	public String idAutomate(String type ,String faculty_name) {
		// TODO Auto-generated method stub
		//id格式为：head + faculty + tail （head是头标识，区分用户类型，faculty是院系id，管理员和教务处老师无
		//tail是尾标识，为人数标识）
		String faculty_id = new Plan().getFacultyID(faculty_name);
		String head = "";//id的前缀
		int count = 0;//由count来生成id的后缀
		String tail = "";
		int length = 0;
		switch (type) {
		case "Admin":
			head = "0";
			faculty_id = "";
			break;
		case "Dean":
			head = "1";
			faculty_id = "";
			count = new Dean().getDeanCount() + 1;
			length = 1;
			break;
		case "Faculty":
			head = "1";
			count = new Faculty().getFacultyCount(faculty_name) + 1;
			length = 2;
			break;
		case "Teacher":
			head = "2";
			count = new TeacherList().getTeacherList(faculty_name).size() + 1;
			length = 3;
			break;
		case "Student":
			Calendar cal = Calendar.getInstance();
			head = cal.get(Calendar.YEAR) + "";
			head = head.substring(2);
			count = new FacultyStudent().getAllStudentByFacID(faculty_id).size() +1;
			length = 3;
			break;
		}
		tail = this.getTail(count, length);
		
		return head + faculty_id + tail;
	}
	
	//获取末端的编号
	private String getTail(int count , int length){
		String tail=String.valueOf(count);
		String [] ss = {"","0","00","000"};
		tail = ss[length - tail.length()] + tail;
		
		return tail;
	}
//	
//	public static void main(String[] args){
//		Admin admin = new Admin();
//		String id = admin.idAutomate("Faculty", "软件学院");
//		System.out.println(id);
//	}

}
