package businesslogic.studentbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Vector;

import businesslogicservice.studentblservice.MajorApplyBLService;

import po.studentpo.MajorTransferPO;
import rmiconnector.RemoteDataFactory;
import dataservice.studentdataservice.MajorTransferDataService;
/**
 * 
 * @author LiuWT-ASUS
 * 转专业申请
 *
 */

public class MajorApply implements MajorApplyBLService {

	/**
	 * @param args
	 */
	
	RemoteDataFactory factory = new RemoteDataFactory();
	MajorTransferDataService data = (MajorTransferDataService) factory.getData("MajorTransfer");
	/* （非 Javadoc）
	 * @see businesslogic.studentbl.MajorApplyBLService#getMajorApplyStatus(java.lang.String)
	 * 获取学生的转专业申请状态信息。
	 */
	@Override
	public Vector<String> getMajorApplyStatus(String student_id){
	
		Vector<String> result = new Vector<String>();
		MajorTransferPO po = new MajorTransferPO(student_id, "", "", "", "");
		try {
			po = data.find(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		result.add(po.getPreSchool());
		result.add(po.getToSchool());
		result.add(po.getStatus());
		result.add(po.getapplydate());
		return result;
		
	}

	/* （非 Javadoc）
	 * @see businesslogic.studentbl.MajorApplyBLService#hasApplied(java.lang.String)
	 * 查看是否存在该学生的专业申请记录。
	 */
	@Override
	public boolean hasApplied(String student_id) {
		// TODO 自动生成的方法存根
		MajorTransferPO po = new MajorTransferPO(student_id, "", "", "", "");
		MajorTransferDataService data = (MajorTransferDataService) factory.getData("MajorTransfer");
		try {
			po = data.find(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		if(po == null){
			return false;
		}else{
			return true;
		}
		
		
	}
	/* （非 Javadoc）
	 * @see businesslogic.studentbl.MajorApplyBLService#apply(po.studentpo.MajorTransferPO)
	 */
	@Override
	public void apply(MajorTransferPO po){
		MajorTransferDataService data = (MajorTransferDataService) factory.getData("MajorTransfer");
		try {
			data.insert(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	/*
	 * 对外提供的供接口 ： 查看审核信息的时候。
	 */
	public ArrayList<MajorTransferPO> getAllContent(){
		ArrayList<MajorTransferPO> result = null;
		try {
			result = data.getAllContent();
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return result;
	}

}
