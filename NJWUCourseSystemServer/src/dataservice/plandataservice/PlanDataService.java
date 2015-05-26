package dataservice.plandataservice;
import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.DataService;

import po.planpo.FacultyPO;

public interface PlanDataService extends DataService{

	public void insert(FacultyPO fp)throws RemoteException;
	public void update(FacultyPO fp)throws RemoteException;
	public FacultyPO  find(String facultyID)throws RemoteException;
	public ArrayList<FacultyPO> finds()throws RemoteException;
	public void delete(String facultyID)throws RemoteException;
}
