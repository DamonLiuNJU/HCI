package dataservice.studentdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.DataService;

import po.studentpo.StudentPO;

public interface StudentDataService extends DataService {
	public ArrayList<String> getCourseList(StudentPO studentnumber)
			throws RemoteException;

	public boolean deleteCourse(StudentPO studentnumber) throws RemoteException;

	public boolean setPersonalInfo(StudentPO po) throws RemoteException;

	public boolean addCourseNo(StudentPO coursenumber$studentnumber)
			throws RemoteException;

	public String insert(StudentPO sp) throws RemoteException;

	public String delete(StudentPO sp) throws RemoteException;

	public StudentPO find(StudentPO sp) throws RemoteException;

	public String update(StudentPO sp) throws RemoteException;

	public ArrayList<StudentPO> getStudentListByFacultyID(StudentPO po)
			throws RemoteException;
}
