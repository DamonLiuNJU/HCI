package dataservice.managerdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.DataService;

import po.managerpo.MessagePO;

public interface MessageDataService extends DataService{
	public void insert(MessagePO mp) throws RemoteException;

	public ArrayList<MessagePO> finds(String to_id) throws RemoteException;
	
	public ArrayList<MessagePO> findByFrom(String from_id)throws RemoteException;
	
	public void delete(String from_id,String to_id) throws RemoteException;
 
}
