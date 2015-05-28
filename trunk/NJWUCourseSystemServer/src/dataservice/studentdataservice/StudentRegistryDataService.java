package dataservice.studentdataservice;

import java.rmi.RemoteException;

import dataservice.DataService;
import po.studentpo.StudentRegistryPO;

public interface StudentRegistryDataService extends DataService{

	/**
	 * @param args
	 */
	public void insert(StudentRegistryPO po)throws RemoteException;
	public void delete(StudentRegistryPO po)throws RemoteException;
	public void update(StudentRegistryPO po)throws RemoteException;
	public StudentRegistryPO find(StudentRegistryPO po)throws RemoteException;

}
