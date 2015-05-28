package data.managerdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import po.TeacherPO;
import po.managerpo.ManagerPO;
import po.studentpo.StudentPO;

import data.TeacherData;
import data.studentdata.StudentDataMySql;
import dataservice.TeacherDataService;
import dataservice.managerdataservice.AdminDataService;
import dataservice.managerdataservice.ManagerDataService;
import dataservice.studentdataservice.StudentDataService;

/*
 * AdminData类，不直接提供对数据库中的表的操作，但是调用其他data的方法进行
 * 增删改查。
 * 包含deleteUser,registerUser,findUser,updataUser，4个方法
 */
public class AdminData extends UnicastRemoteObject implements AdminDataService {

	public AdminData() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 是否要对学工号作规定，例如：某一位表示该用户的身份，某几位是该用户的院系编号。
	@Override
	/*
	 * (non-Javadoc)
	 * @see dataservice.managerdataservice.AdminDataService#deleUser(java.lang.String, java.lang.String)
	 * 按id和类型删除一个用户
	 */
	public boolean deleUser(String id, String type) throws RemoteException {
		// TODO Auto-generated method stub
		if(type.equals("Manager")){
			ManagerDataService managerData = new ManagerData();
			managerData.delete(id);
			return true;
		}
		else if(type.endsWith("Teacher")){
			TeacherDataService teacherData = new TeacherData();
			if(teacherData.delete(id))
				return true;
			else return false;
		}
		else if(type.equals("Student")){
			StudentDataService studentData = new StudentDataMySql();
			StudentPO sp = new StudentPO();
			sp.setStudentID(id);
			studentData.delete(sp);
			return true;
		}
		
		return false;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see dataservice.AdminDataService#registerUser(java.lang.Object, java.lang.String)
	 * 注册一个新用户，用户类型为type，用户信息在PO中
	 */
	public boolean registerUser(Object po, String type) throws RemoteException {
		// TODO Auto-generated method stub
		if (type.equals("Teacher")) {
			TeacherPO tp = (TeacherPO) po;
			TeacherDataService teacherData = new TeacherData();
			if (teacherData.insert(tp))
				return true;
			else
				return false;
		} else if (type.equals("Student")) {
			StudentPO sp = (StudentPO) po;
			StudentDataService stuData = new StudentDataMySql();
			if(!(stuData.find(sp) == null)){
				return false;
			}
			stuData.insert(sp);
			return true;
		} else if (type.equals("Manager")) {
			ManagerPO mp = (ManagerPO) po;
			ManagerData managerData = new ManagerData();
			managerData.insert(mp);
			return true;
		}
		return false;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see dataservice.managerdataservice.AdminDataService#findUser(java.lang.String)
	 * 按照id查找一个用户
	 */
	public Object findUser(String id) throws RemoteException {
		// TODO Auto-generated method stub
		char[] temp = id.toCharArray();
		if((temp.length ==2)||(temp.length == 6)){//该用户可能为管理员或教务处老师或院系教务老师
			ManagerDataService managerData = new ManagerData();
			ManagerPO mp = managerData.find(id);
			if(mp.getID().equals("")){
				mp = null;
			}
			return mp;
		}
		else if(temp.length == 8){//用户为学生
			StudentDataService studentData = new StudentDataMySql();
			StudentPO sp = new StudentPO();
			sp.setStudentID(id);
			sp = studentData.find(sp);
			if(sp.student_id == null){
				sp = null;
			}
			return sp;
		}
		else if(temp.length == 7){//用户为教师
			TeacherDataService teacherData = new TeacherData();
			TeacherPO tp = teacherData.find(id);
			return tp;
		}
		
		return null;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see dataservice.managerdataservice.AdminDataService#updateUser(java.lang.Object, java.lang.String)
	 * 更新一个用户的信息，type为用户类型
	 */
	public boolean updateUser(Object po, String type) throws RemoteException {
		// TODO Auto-generated method stub
		if (type.equals("Teacher")) {
			TeacherPO tp = (TeacherPO) po;
			TeacherDataService teacherData = new TeacherData();
			// 如果更新成功，返回true，否则返回false
			if (teacherData.update(tp))
				return true;
			else
				return false;
		} else if (type.equals("Student")) {
			StudentPO sp = (StudentPO) po;
			StudentDataService studentData = new StudentDataMySql();
			studentData.update(sp);
			return true;
		} else if (type.equals("Manager")) {
			ManagerPO mp = (ManagerPO) po;
			ManagerDataService managerData = new ManagerData();
			managerData.update(mp);
			return true;
		}
		return false;
	}

}
