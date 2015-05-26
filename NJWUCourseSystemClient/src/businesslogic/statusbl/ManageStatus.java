package businesslogic.statusbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.statuspo.ManageStatusPO;
import rmiconnector.RemoteDataFactory;
import vo.statusvo.ManageStatusVO;
import dataservice.statusdataservice.ManageStatusDataService;

public class ManageStatus extends Status{

	ManageStatusDataService data;
	
	public ManageStatus(){
		init();
	}
	
	@Override
	public void init(){
	    //DataFactory factory=(DataFactory) new RemoteDataFactory().getData("ManageStatusMySqlImpl");
		data=(ManageStatusDataService) new RemoteDataFactory().getData("ManageStatus");//factory.getManageStatusDataBase();
	}

	@Override
	public final ManageStatusVO getTimeList() {
		ArrayList<ManageStatusPO> msList=new ArrayList<ManageStatusPO>();
		try {
			msList = data.finds();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return new ManageStatusVO(msList);
	}
	
}
