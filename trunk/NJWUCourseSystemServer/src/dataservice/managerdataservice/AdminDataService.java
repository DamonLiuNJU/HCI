package dataservice.managerdataservice;

import java.rmi.RemoteException;

import dataservice.DataService;

public interface AdminDataService extends DataService{
//	public String insertStudent(StudentPO sp) throws RemoteException;
//	public boolean existStudent(String studentID) throws RemoteException;
//	public String updateTeacher(TeacherPO tp , String id) throws RemoteException;
//	public String updateStudent(StudentPO sp, String id) throws RemoteException;
	
	/*
	 * 方法中的object都指的是几个可以使用的用户类型，如教师，学生
	 */
	public boolean deleUser(String id , String type) throws RemoteException;
	//注册一个用户，type为用户的类型，po为响应类型的PO，存入注册信息
	public boolean registerUser(Object po , String type) throws RemoteException; 
	//按照id查找一个用户，返回一个PO对象，id的格式参见文档：id格式
	public Object findUser(String id) throws RemoteException;
	//更新一个用户信息，其中id不能改变，按照id查找到原有用户，将其信息修改为当前PO所包含的信息
	public boolean updateUser(Object po , String type) throws RemoteException;
}
