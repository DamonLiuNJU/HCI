package dataservice.mydatafactory;

import java.rmi.RemoteException;

import dataservice.DataService;
import dataservice.framedataservice.CreditRestrictDataService;
import dataservice.framedataservice.FrameDataService;
import dataservice.framedataservice.FrameRemarkDataService;

public interface TxtDataFactory extends DataService{
	public FrameDataService getFrameData() throws RemoteException;

	public CreditRestrictDataService getCreditRestrictData()
			throws RemoteException;

	public FrameRemarkDataService getFrameRemarkData() throws RemoteException;
}
																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																					