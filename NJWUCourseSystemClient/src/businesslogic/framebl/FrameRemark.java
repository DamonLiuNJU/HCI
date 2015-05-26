package businesslogic.framebl;

import java.rmi.RemoteException;

import rmiconnector.RemoteDataFactory;
import dataservice.framedataservice.FrameRemarkDataService;

public class FrameRemark {
	private String content;
	
	FrameRemarkDataService data;
	
	public FrameRemark(){
		data=(FrameRemarkDataService) new RemoteDataFactory().getData("FrameRemark");
		try {
			content=data.finds();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public String getContent(){		
		return content;
	}
	public void setContent(String c){
		content=c;
		try {
			data.update(content);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
