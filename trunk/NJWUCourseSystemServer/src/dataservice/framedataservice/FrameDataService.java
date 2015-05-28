package dataservice.framedataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.DataService;

import po.framepo.FramePO;

public interface FrameDataService extends DataService{
	   public void insert(FramePO fp) throws RemoteException;
	   
	   public void update(ArrayList<FramePO> fp) throws RemoteException;
	   
	   public ArrayList<FramePO> finds() throws RemoteException;
	   
	   public void delete() throws RemoteException;
}
