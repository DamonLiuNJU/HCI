package dataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.TeacherPO;

public interface TeacherDataService extends DataService{
	public boolean insert(TeacherPO tp) throws RemoteException;
	public TeacherPO find(String teacherID) throws RemoteException;
	public ArrayList<TeacherPO> finds(String faculty) throws RemoteException;
	public ArrayList<TeacherPO> findByName(String teacherName) throws RemoteException;
	public ArrayList<TeacherPO> findAll() throws RemoteException;
	public boolean delete(String teacherID) throws RemoteException;
	public boolean update(TeacherPO tp ) throws RemoteException;
	public TeacherPO initialTeacher(String id) throws RemoteException;//初始化一个登录后的teacher对象
}
