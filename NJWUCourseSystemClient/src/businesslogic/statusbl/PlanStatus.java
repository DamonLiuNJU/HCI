package businesslogic.statusbl;

import java.rmi.RemoteException;

import po.statuspo.ManageStatusPO;

public class PlanStatus extends ManageStatus{

	
	public PlanStatus(){
	}
	
	@Override
	public void init() {
		super.init();
		ManageStatusPO mp;
		try {
			mp = data.find("plan");
			on=new BoundDate(mp.getOnTime());
			off=new BoundDate(mp.getOffTime());		
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setTime(BoundDate ft1,BoundDate ft2){
		super.setTime(ft1, ft2);
		try {
			data.update(new ManageStatusPO("planstatus",on.toString(),off.toString()));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
