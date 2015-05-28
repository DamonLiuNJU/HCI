package dataservice.statusdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.DataService;

import po.statuspo.CourseStatusPO;

public interface CourseStatusDataService extends DataService{
	   public void insert(CourseStatusPO mp) throws RemoteException;
	   
	   public void update(CourseStatusPO mp) throws RemoteException;
	   
	   public CourseStatusPO find(String module,String type) throws RemoteException;
	   
	   public ArrayList<CourseStatusPO> finds() throws RemoteException;
	   
	   public void delete(String module,String type) throws RemoteException;
}
