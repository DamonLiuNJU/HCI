package data.coursedata;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.util.ArrayList;

import po.coursepo.ApplyPO;
import data.helper.DataBaseHelper;
import dataservice.coursedataservice.ApplyDataService;
@SuppressWarnings("serial")
public class ApplyData extends UnicastRemoteObject  implements ApplyDataService{
     String tableName;
	 
	 DataBaseHelper db;
     public ApplyData()  throws RemoteException{
		 tableName="courseapplication";
		 db=new DataBaseHelper();
	  }
  
     
     @Override
	public void insert(ApplyPO ap) {
    	 String info = "INSERT INTO `"+tableName+"`("+ap.getAttributeNames()+ ") VALUES "+ap.getValues(); 
	     db.insert(info);
         System.out.println( "insert  "+tableName+" succefully!");
	}
   @Override
	public void update(ApplyPO ap) {
		// TODO Auto-generated method stub
		 String info="update `"+tableName+"`"+" set `applyinfo`='"+ap.getContent()+
				 "' where `name` = '"+ap.getCourseName()+"'";
	     db.update(info);
	     System.out.println("update  "+tableName+"  successfully!");
	}
	
	@Override
	public ArrayList<ApplyPO> finds() {
		// TODO Auto-generated method stub
		 ArrayList<ApplyPO> list=new ArrayList<ApplyPO>(); 
		 
		 String info="select * from `"+tableName+"`";
		 String attributeNames=new ApplyPO().getAttributeNames().replaceAll("`","");
		 String[] split=attributeNames.split(",");
		 ArrayList<String> reply=db.query(info,split);
		 for(String s:reply){
			 String[] sp=s.split(",");
			 //System.out.println(s);
			 list.add(new ApplyPO( sp[0], sp[1], sp[2], sp[3]));
		 }
		 return list;
	}
	@Override
	public String delete(String name) {
		// TODO Auto-generated method stub
		String info="delete from  `"+tableName+"`"+" where `name` = '"+name+"'";
		db.delete(info);
		System.out.println("delete"+tableName+"successfully");
		return null;
	}
	
     
}