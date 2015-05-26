package businesslogic.framebl;

import java.util.ArrayList;

import po.framepo.FramePO;
import vo.framevo.FrameVO;
import businesslogic.statusbl.BoundDate;
import businesslogic.statusbl.FrameStatus;
import businesslogic.statusbl.Status;
import businesslogicservice.FrameBLService;

public class Frame implements FrameBLService{

    Status status;
    FrameLineItem fl;

    public Frame(){
    	status = new FrameStatus();
    	fl=new FrameLineItem();
    }
    
    public String create(ArrayList<String> list) {
    	fl.makeEmpty();
        if (status.current()) {
           for (int i = 0; i < list.size(); i++) {
        	   FrameLineItem item=new FrameLineItem(i + 1, list.get(i));
        	   item.add();
           }
           return CREATE_SUCCEED;
        }
        return CREATE_FAIL;
    }
    
    public String modify(ArrayList<String> list){
    	ArrayList<FramePO> fpList=new ArrayList<FramePO>();
    	if (status.current()) {
            for (int i = 0; i < list.size(); i++) {
                fpList.add(new FramePO(i+1,list.get(i)));
            }
            fl.update(fpList);
            return MODIFY_SUCCEED;
         }
         return MODIFY_FAIL;
    }
    public FrameVO showFrame(){
        return new FrameVO(new FrameLineItem().find());
    }
    
    public void setStatus(int m1,int d1,int m2,int d2){
    	status.setTime(new BoundDate(m1,d1), new BoundDate(m2,d2));
    }
    
}
