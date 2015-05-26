package businesslogic.framebl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.framepo.FramePO;
import rmiconnector.RemoteDataFactory;
import dataservice.framedataservice.FrameDataService;

public class FrameLineItem {
    private int index;
    private String content;

    FrameDataService data; 

    public FrameLineItem(int i, String c) {
    	data= (FrameDataService) new RemoteDataFactory().getData("Frame"); 
        index = i;
        content = c;
    }
    public FrameLineItem(){
    	data= (FrameDataService) new RemoteDataFactory().getData("Frame"); 
    }

    public void add() {
        try {
			data.insert(new FramePO(index,content));
		} catch (RemoteException e) {
			e.printStackTrace();
		} 
    }
    
    public void update(ArrayList<FramePO> fpList){
    	try {
			data.update(fpList);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }
    
    public ArrayList<FramePO> find(){
    	try {
    		return data.finds();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    public void makeEmpty(){
    	try {
			data.delete();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }
    
    public int getIndex(){
    	return index;
    }
    public String getContent(){
    	return content;
    }
}
