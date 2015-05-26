package dataservice.courseselectiondataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.courseselectionpo.SelectCourseRecordPO;
import dataservice.DataService;

public interface SelectCourseRecordDataService extends DataService{
	public void insert(SelectCourseRecordPO po)throws RemoteException;
	public void delete(SelectCourseRecordPO po)throws RemoteException;
	public void update(SelectCourseRecordPO po)throws RemoteException;
	public SelectCourseRecordPO find(SelectCourseRecordPO po)throws RemoteException;
	public  ArrayList<SelectCourseRecordPO> findCourseList(SelectCourseRecordPO po)throws RemoteException;
	public  ArrayList<SelectCourseRecordPO> findStudentList(SelectCourseRecordPO po)throws RemoteException;
}
