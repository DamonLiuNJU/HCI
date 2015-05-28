package dataservice.framedataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.DataService;

import po.framepo.CreditRestrictPO;

public interface CreditRestrictDataService extends DataService{
	public void insert(CreditRestrictPO cp) throws RemoteException;
	
	public void update(ArrayList<CreditRestrictPO> cpList) throws RemoteException;
	
	public ArrayList<CreditRestrictPO> finds() throws RemoteException;
	
	public void delete(String module) throws RemoteException;
}
