package businesslogicservice.frameblservice;

import java.util.ArrayList;

import vo.framevo.CreditRestrictVO;
import vo.framevo.FrameVO;
import businesslogic.utilitybl.ReplyMessage;

public interface FrameBLService extends ReplyMessage{
	/**
	 * 创建框架条目
	 * @param list
	 * @return
	 */
	public String create(ArrayList<String> list);
	
	/**
	 * 修改框架条目
	 * @param list
	 * @return
	 */
	public String modify(ArrayList<String> list);
	
	/**
	 * 修改学分限制
	 * @param cv
	 */
	public String modify(CreditRestrictVO cv);
	
	/**
	 * 查看框架条目
	 * @return
	 */
	public FrameVO showFrame();
	
	/**
	 * 查看学分限制
	 * @return
	 */
	public ArrayList<CreditRestrictVO> showCreditRestricts();
	
	/**
	 * 设置框架策略状态
	 * @param m1
	 * @param d1
	 * @param m2
	 * @param d2
	 */
	public void setStatus(int m1,int d1,int m2,int d2);
}
