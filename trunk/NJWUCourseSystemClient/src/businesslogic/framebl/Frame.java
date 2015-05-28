package businesslogic.framebl;

import java.util.ArrayList;

import po.framepo.FramePO;
import vo.framevo.CreditRestrictVO;
import vo.framevo.FrameVO;
import businesslogic.statusbl.BoundDate;
import businesslogic.statusbl.FrameStatus;
import businesslogic.statusbl.Status;
import businesslogicservice.frameblservice.FrameBLService;

/**
 * 框架策略主类，负责框架条目及学分限制的增删改查
 * @author cbb
 *
 */
public class Frame implements FrameBLService{

    Status status;
    FrameLineItem fl;
    CreditRestrict cp;

    public Frame(){
    	status = new FrameStatus();
    	fl=new FrameLineItem();
    	cp=new CreditRestrict();
    }
    
    /**
     * 创建新的框架条目，旧的会全被覆盖
     */
    @Override
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
    
    /**
     * 修改已有的框架条目
     */
    @Override
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
    
    /**
     * 修改学分限制
     * @param CreditRestrictVO cv
     */
    @Override
	public String modify(CreditRestrictVO cv){
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
    		return MODIFY_SUCCEED;
    	}
    	return MODIFY_FAIL;
    }
       
    /**
     * 显示框架条目
     */
    @Override
	public FrameVO showFrame(){
        return new FrameVO(fl.getItems());
    }
    
    /**
     * 显示学分限制
     */
    @Override
	public ArrayList<CreditRestrictVO> showCreditRestricts(){
        return cp.getRestricts();
    }
    
    /**
     * 设置框架策略状态（是否可被修改..）
     */
    @Override
	public void setStatus(int m1,int d1,int m2,int d2){
    	status.setTime(new BoundDate(m1,d1), new BoundDate(m2,d2));
    }
    
}
