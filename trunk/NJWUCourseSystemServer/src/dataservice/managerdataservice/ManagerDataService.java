package dataservice.managerdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.DataService;

import po.managerpo.ManagerPO;

public interface ManagerDataService extends DataService{
	public ManagerPO find(String id) throws RemoteException ;

    public void insert(ManagerPO mp) throws RemoteException;

    public void delete(String id) throws RemoteException;

    public void update(ManagerPO mp) throws RemoteException;
    
    public void updatePw(ManagerPO mp) throws RemoteException;
    
    public void updateCi(ManagerPO mp) throws RemoteException;

    public ArrayList<ManagerPO> finds() throws RemoteException;
    
    public ArrayList<ManagerPO> finds(String facultyID) throws RemoteException;
}
