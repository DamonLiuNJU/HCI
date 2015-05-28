package dataservice.courseselectiondataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.DataService;
import po.courseselectionpo.TempSelectionPO;

public interface TempSelectionDataService extends DataService {

	public void insert(TempSelectionPO po) throws RemoteException;

	public void delete(String student_id, String course_id)
			throws RemoteException;

	public void deletes() throws RemoteException;

	public void update(TempSelectionPO po) throws RemoteException;

	public ArrayList<TempSelectionPO> find(String student_id)
			throws RemoteException;

	public ArrayList<TempSelectionPO> findStudentList(String course_id)
			throws RemoteException;

	public TempSelectionPO findOne(String student_id, String course_id)
			throws RemoteException;

	public ArrayList<TempSelectionPO> finds() throws RemoteException;
}
