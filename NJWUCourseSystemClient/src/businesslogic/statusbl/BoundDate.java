package businesslogic.statusbl;

public class BoundDate {
   int month;
   int day;
   
   public BoundDate(int m,int d){
	   month=m;
	   day=d;
   }
   
   public BoundDate(String s){
	   String[] sp=s.split("-");
	   month=Integer.parseInt(sp[0]);
	   day=Integer.parseInt(sp[1]);
   }
   public BoundDate(){
	   
   }
   
   public int getMonth(){
	   return month;
   }
   
   public int getDay(){
	   return day;
   }
   public String toString(){
	   return month+"-"+day;
   }
}
