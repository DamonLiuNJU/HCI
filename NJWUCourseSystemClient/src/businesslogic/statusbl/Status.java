package businesslogic.statusbl;

import java.util.Calendar;
import java.util.GregorianCalendar;

import businesslogicservice.statusblservice.StatusBLService;


public abstract class Status implements StatusBLService{
   BoundDate on;
   BoundDate off;
   
   public abstract void init();
   
   public boolean current() {
	   GregorianCalendar g=new GregorianCalendar();
       int month = g.get(Calendar.MONTH)+1;
       int day = g.get(Calendar.DAY_OF_MONTH);
       if ((month > on.getMonth() || (month == on.getMonth()&& day>=on.getDay())) && 
    		   (month< off.getMonth() || (month == off.getMonth()&& day<=off.getDay()))) {
           return true;
       }
       return false;
   }
   
   public void setTime(BoundDate ft1,BoundDate ft2){
		on=ft1;
		off=ft2;	   
   }
   
   //public abstract ArrayList<? extends Object> getTimeList();
   public abstract Object getTimeList();
}
