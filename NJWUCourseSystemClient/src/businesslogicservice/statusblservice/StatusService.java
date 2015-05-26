package businesslogicservice.statusblservice;

import businesslogic.statusbl.BoundDate;

public interface StatusService {
	public boolean current();
	
	public void setTime(BoundDate ft1,BoundDate ft2);
	
	public Object getTimeList();
	
	
}
