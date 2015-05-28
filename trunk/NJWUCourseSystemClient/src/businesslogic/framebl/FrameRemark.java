package businesslogic.framebl;

import java.rmi.RemoteException;

import rmiconnector.RemoteDataFactory;
import dataservice.framedataservice.FrameRemarkDataService;

/**
 * 框架策略备注信息的实现类
 * @author cbb
 *
 */
public class FrameRemark {
	private String content;
	
	FrameRemarkDataService data;
	
	public FrameRemark(){
		data=(FrameRemarkDataService) new RemoteDataFactory().getData("FrameRemark");
		//data=(TxtDataFactory)new RemoteDataFactory().getData("TxtDataFactory");
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
