package data.datafactoryimpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import data.framedata.CreditRestrictDataTxtImpl;
import data.framedata.FrameDataServiceTxtImpl;
import data.framedata.FrameRemarkDataServiceTxtImpl;
import dataservice.framedataservice.CreditRestrictDataService;
import dataservice.framedataservice.FrameDataService;
import dataservice.framedataservice.FrameRemarkDataService;
import dataservice.mydatafactory.TxtDataFactory;

@SuppressWarnings("serial")
public class DataFactoryTxtImpl extends UnicastRemoteObject implements TxtDataFactory{
	FrameDataService frameData;
    CreditRestrictDataService restrictData;
    FrameRemarkDataService remarkData;
	
	public DataFactoryTxtImpl() throws RemoteException {
		frameData=new FrameDataServiceTxtImpl();
		restrictData=new CreditRestrictDataTxtImpl();
		remarkData=new FrameRemarkDataServiceTxtImpl();
	}
    
	public FrameDataService getFrameData() {		
		return frameData;
	}
	
	public CreditRestrictDataService getCreditRestrictData(){
		return restrictData;
	}

	public FrameRemarkDataService getFrameRemarkData() {		
		return remarkData;
	}
}
