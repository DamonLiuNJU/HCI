package businesslogic.statusbl;

import java.util.Calendar;
import java.util.GregorianCalendar;

import businesslogicservice.statusblservice.StatusBLService;


public abstract class Status implements StatusBLService{
   BoundDate on;
   BoundDate off;
   
   public abstract void init();
   
	@Override
	public boolean current() {
		GregorianCalendar g = new GregorianCalendar();
		int month = g.get(Calendar.MONTH) + 1;
		int day = g.get(Calendar.DAY_OF_MONTH);
		if (off.getMonth() >= on.getMonth()) { //限制日期未跨年，如12-01至 12-30
			if (on.previous(month, day) && off.after(month, day)) {
				return true;
			}
			return false;
		} else {	//限制日期跨年，如12-20至1-10
			if (on.previous(month, day) || off.after(month, day)) {
				return true;
			}
			return false;
		}
	}
   
   @Override
   public void setTime(BoundDate ft1,BoundDate ft2){
		on=ft1;
		off=ft2;	   
   }
   
   @Override
   public abstract Object getTimeList();
   
}
