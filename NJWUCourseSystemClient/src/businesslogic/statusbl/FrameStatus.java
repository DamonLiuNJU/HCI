package businesslogic.statusbl;

import java.rmi.RemoteException;

import po.statuspo.ManageStatusPO;

public class FrameStatus extends ManageStatus{
    
	//建立dataService对象用于读入状态表/文件中frame的值数据
	
	public FrameStatus(){
	}
	
	@Override
	public void init() {
		//将读入值赋给on,off成员
		super.init();
		ManageStatusPO mp;
		try {
			mp = data.find("frame");
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
			data.update(new ManageStatusPO("framestatus",on.toString(),off.toString()));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
