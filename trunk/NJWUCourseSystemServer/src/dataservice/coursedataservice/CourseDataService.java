package dataservice.coursedataservice;

	import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.DataService;


import po.coursepo.CoursePO;


	public interface CourseDataService  extends DataService{
	public void insert(CoursePO cp) throws RemoteException;
public void update(CoursePO cp) throws RemoteException;
//public CoursePO find(int index) throws RemoteException;
public ArrayList<CoursePO> finds() throws RemoteException;
public void delete(String index) throws RemoteException;
//public String deleteByCp(CoursePO cp) throws RemoteException;
		
	}

