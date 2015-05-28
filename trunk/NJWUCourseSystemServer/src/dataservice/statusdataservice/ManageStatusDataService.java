package dataservice.statusdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.DataService;

import po.statuspo.ManageStatusPO;

public interface ManageStatusDataService extends DataService{
	   public void insert(ManageStatusPO mp) throws RemoteException;
	   
	   public void update(ManageStatusPO mp) throws RemoteException;
	   
	   public ManageStatusPO find(String type) throws RemoteException;
	   
	   public ArrayList<ManageStatusPO> finds() throws RemoteException;
	   
	   public void delete(String type) throws RemoteException;
}
