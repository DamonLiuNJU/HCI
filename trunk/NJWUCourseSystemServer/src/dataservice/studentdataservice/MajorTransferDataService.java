package dataservice.studentdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.studentpo.MajorTransferPO;
import dataservice.DataService;

public interface MajorTransferDataService extends DataService{
	public void insert(MajorTransferPO po)throws RemoteException;
	public void delete(MajorTransferPO po)throws RemoteException;
	public void update(MajorTransferPO po)throws RemoteException;
	public MajorTransferPO find(MajorTransferPO po)throws RemoteException;
	public ArrayList<MajorTransferPO> getAllContent() throws RemoteException;
}
