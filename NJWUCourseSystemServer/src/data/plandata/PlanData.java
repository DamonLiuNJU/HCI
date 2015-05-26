package data.plandata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.util.ArrayList;

import po.planpo.FacultyPO;
import data.helper.DataBaseHelper;
import dataservice.plandataservice.PlanDataService;

public class PlanData  extends UnicastRemoteObject implements PlanDataService{
 
	
	public   static void main(String arg[]) throws RemoteException{
	PlanData pd=new PlanData();
	FacultyPO fp=pd.find("001");
//	for(FacultyPO fp:fpList){
	//	System.out.println(fp.getFacultyID());
//	}
	System.out.println(fp.getPlan());
//	pd.update(new FacultyPO("10000005","大软件学院","软院的新教学计划"));
}   

	String tableName="faculty";
    DataBaseHelper db ;
    public PlanData() throws RemoteException {		
		 db=new DataBaseHelper();
	}
   private static final long serialVersionUID = 1L;
	 
	@Override
	public void insert(FacultyPO fp) {	
		String info = "INSERT INTO `"+tableName+"`("+fp.getAttributeNames()+ ") VALUES "+fp.getValues(); 
		  
		db.insert(info);
		System.out.println("insert "+tableName+" successfully！");
	}

	@Override
	public void update(FacultyPO fp) {
		// TODO Auto-generated method stub
		 String info="update `"+tableName+"`"+" set `plan`='"+fp.getPlan()+"' where `id` = '"+fp.getFacultyID()+"'";
	     db.update(info);
	     info="update `"+tableName+"`"+" set `name`='"+fp.getFacultyName()+"' where `id` = '"+fp.getFacultyID()+"'";
	     db.update(info);
	     System.out.println("update "+tableName+" successfully！");
		
	     
	}

	@Override
	public FacultyPO find(String facultyID) {
		//System.out.println(facultyID);
		String info="select * from  `"+tableName+"`"+" where `id`='"+facultyID+"'";
		String[] names={"id","name","plan"};
		String r=db.queryOne(info, names);
		String[] sp=r.split(",");
		for(int i=0;i<sp.length;i++){
			if(sp[i].equals("%&%"))
				sp[i]="";
		}
		return new FacultyPO(sp[0],sp[1],sp[2]);
	}

	@Override
	public ArrayList<FacultyPO> finds() {
		ArrayList<FacultyPO> list=new ArrayList<FacultyPO>();
		String info="select * from `"+tableName+"`";
		String attributeNames=new FacultyPO().getAttributeNames().replaceAll("`", "");
		String[] split=attributeNames.split(",");
		ArrayList<String> reply=db.query(info,split);
		for(String s:reply){
			String[] sp=s.split(",");
			list.add(new FacultyPO(sp[0],sp[1],sp[2]));
		}
		return list;
	}

	@Override
	public void delete(String index) {
		// TODO Auto-generated method stub
		String info="delete from  `"+tableName+"`"+" where `id` = '"+index+"'";
		db.delete(info);
		System.out.println("delete "+tableName+" successfully");
	}

}
