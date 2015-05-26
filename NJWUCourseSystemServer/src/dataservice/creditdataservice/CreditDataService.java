package dataservice.creditdataservice;

import java.rmi.RemoteException;

import dataservice.DataService;

import po.creditpo.CreditPO;

public interface CreditDataService extends DataService {
	
	 	public CreditPO findString(CreditPO po)throws RemoteException;
	 	public CreditPO findInt(CreditPO cpindex) throws RemoteException;
	    public void insert(CreditPO cp) throws RemoteException;
	    public void delete(CreditPO cp) throws RemoteException;
	    public void update(CreditPO cp) throws RemoteException;
	    
	   
	    
	    
	    public CreditPO findIntOne(CreditPO cp) throws RemoteException;
	    public CreditPO findStringOne(CreditPO cp) throws RemoteException;
	    public CreditPO insertOne(CreditPO cp) throws RemoteException;
	    public CreditPO deleteOne(CreditPO cp) throws RemoteException;
	    public CreditPO updateOne(CreditPO cp) throws RemoteException;
	    
	    
	    
	    
	    
}
