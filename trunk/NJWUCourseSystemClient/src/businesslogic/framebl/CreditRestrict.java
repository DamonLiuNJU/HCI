package businesslogic.framebl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.framepo.CreditRestrictPO;

import rmiconnector.RemoteDataFactory;
import vo.framevo.CreditRestrictVO;
import dataservice.framedataservice.CreditRestrictDataService;

/**
 * 框架策略中学分限制管理的实现类
 * @author cbb
 */
public class CreditRestrict {
	CreditRestrictDataService data;
	
	public CreditRestrict(){
		data=(CreditRestrictDataService) new RemoteDataFactory().getData("CreditRestrict");
	}
	
	/**
	 * 得到学分限制内容的vo值对象
	 * @return  ArrayList<CreditRestrictVO>
	 */
	ArrayList<CreditRestrictVO> getRestricts(){
		ArrayList<CreditRestrictPO> cpList=new ArrayList<CreditRestrictPO>();
		try {
			cpList = data.finds();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ArrayList<CreditRestrictVO> cvList=new ArrayList<CreditRestrictVO>();
		for(CreditRestrictPO cp:cpList){
			cvList.add(new CreditRestrictVO(cp));
		}
		return cvList;
	}
	
	/**
	 * 添加限制项
	 * @param cv
	 */
	void add(CreditRestrictVO cv){
		CreditRestrictPO cp=new CreditRestrictPO(cv.getModule(),cv.getLow(),cv.getHigh());
		try {
			data.insert(cp);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改限制项
	 * @param cvList
	 */
	void modify(ArrayList<CreditRestrictVO> cvList){
		ArrayList<CreditRestrictPO> cpList=new ArrayList<CreditRestrictPO>();
		for(CreditRestrictVO cv:cvList){
			CreditRestrictPO cp=new CreditRestrictPO(cv.getModule(),cv.getLow(),cv.getHigh());
			cpList.add(cp);
		}
		try {
			data.update(cpList);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
