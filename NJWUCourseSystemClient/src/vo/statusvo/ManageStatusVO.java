package vo.statusvo;

import java.util.ArrayList;

import po.statuspo.ManageStatusPO;

public class ManageStatusVO {
	ArrayList<String> timeList=new ArrayList<String>();
	
	public ManageStatusVO(ArrayList<ManageStatusPO> msList){
		for(ManageStatusPO ms : msList){
			timeList.add(ms.getOnTime()+" "+ms.getOffTime());	
		}
	}
	
	public ArrayList<String> getTimeList(){
		return timeList;
	}
}
