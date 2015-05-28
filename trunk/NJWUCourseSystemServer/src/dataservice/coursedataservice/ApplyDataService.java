package dataservice.coursedataservice;


import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.DataService;

import po.coursepo.ApplyPO;


public interface ApplyDataService  extends DataService{
		public void insert(ApplyPO ap) throws RemoteException;
		//update  content by courseName
		public void update(ApplyPO ap) throws RemoteException;
	//	public ApplyPO find(String name) throws RemoteException;
		public ArrayList<ApplyPO> finds() throws RemoteException;
		public String delete(String name) throws RemoteException;
	//	public String deleteByAp(ApplyPO ap) throws RemoteException;
		 
}
