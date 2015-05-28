package businesslogic.framebl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.framepo.FramePO;
import rmiconnector.RemoteDataFactory;
import dataservice.framedataservice.FrameDataService;

/**
 * 框架策略中框架条目项的实现类
 * @author cbb
 *
 */
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

    /**
     * 增加当前新条目至总条目中
     */
    void add() {
        try {
			data.insert(new FramePO(index,content));
		} catch (RemoteException e) {
			e.printStackTrace();
		} 
    }
    
    /**
     * 更新框架条目信息
     * @param fpList
     */
    void update(ArrayList<FramePO> fpList){
    	try {
			data.update(fpList);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 得到所有框架条目
     * @return
     */
    ArrayList<FramePO> getItems(){
    	try {
    		return data.finds();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    /**
     * 清空所有框架条目
     */
    void makeEmpty(){
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
