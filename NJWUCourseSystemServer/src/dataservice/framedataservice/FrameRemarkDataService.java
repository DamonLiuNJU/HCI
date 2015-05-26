package dataservice.framedataservice;

import java.rmi.RemoteException;

import dataservice.DataService;

public interface FrameRemarkDataService extends DataService{
	public void update(String remark) throws RemoteException;
	
	public String finds() throws RemoteException;
}
