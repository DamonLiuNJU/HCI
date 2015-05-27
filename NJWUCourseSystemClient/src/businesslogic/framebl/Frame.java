package businesslogic.framebl;

import java.util.ArrayList;

import po.framepo.FramePO;
import vo.framevo.CreditRestrictVO;
import vo.framevo.FrameVO;
import businesslogic.statusbl.BoundDate;
import businesslogic.statusbl.FrameStatus;
import businesslogic.statusbl.Status;
import businesslogicservice.frameblservice.FrameBLService;

public class Frame implements FrameBLService{

    Status status;
    FrameLineItem fl;
    CreditRestrict cp;

    public Frame(){
    	status = new FrameStatus();
    	fl=new FrameLineItem();
    	cp=new CreditRestrict();
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
    
    public void modify(CreditRestrictVO cv){
    	if (status.current()){
    		ArrayList<CreditRestrictVO> cvList=showCreditRestricts();
    		boolean isExist=false;
			for(CreditRestrictVO crv:cvList){
				if(crv.getModule().equals(cv.getModule())){
					isExist=true;
					crv.setLow(cv.getLow());
					crv.setHigh(cv.getHigh());
					break;
				}
			}
			if(!isExist){
				cvList.add(cv);
			}
    		cp.modify(cvList);
    	}
    	//此处本来可设置为超出时间便抛出异常的，如此一来就自然地完成了与状态类的交互，而无需到了ui层再麻烦。
    	//sigh
    }
       
    public FrameVO showFrame(){
        return new FrameVO(fl.find());
    }
    
    public ArrayList<CreditRestrictVO> showCreditRestricts(){
        return cp.getRestricts();
    }
    
    public void setStatus(int m1,int d1,int m2,int d2){
    	status.setTime(new BoundDate(m1,d1), new BoundDate(m2,d2));
    }
    
}
