package data.managerdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.managerpo.ManagerPO;
import data.helper.DataBaseHelper;
import dataservice.managerdataservice.ManagerDataService;

@SuppressWarnings("serial")
public class ManagerData  extends UnicastRemoteObject  implements ManagerDataService{
	
	String tableName="manager";
	DataBaseHelper db ;
	public ManagerData() throws RemoteException {				
		db=new DataBaseHelper();
	}
	@Override
	public void insert(ManagerPO mp){					
		String info = "INSERT INTO `"+tableName+"` VALUES "+mp.getValues(); 				  
		db.insert(info);
		System.out.println("insert success "+tableName);
	}

	@Override
	public void update(ManagerPO mp) throws RemoteException {
		delete(mp.getID());
		insert(mp);
		System.out.println("update success in"+tableName);
	}
	
	public void updatePw(ManagerPO mp) throws RemoteException{
		String info="update `"+tableName+"`"+" set `password`='"+mp.getPassword()
				+"' where `id` = '"+mp.getID()+"'";
		db.update(info);
		System.out.println("update manager's password success");
	}
	
	@Override
	public void updateCi(ManagerPO mp) throws RemoteException {
		String info="update `"+tableName+"`"+" set `contactInfo`='"+mp.getContactInfo()
				+"' where `id` = '"+mp.getID()+"'";
		db.update(info);
		System.out.println("update manager's contactInfo success");
	}
	
	@Override
	public ArrayList<ManagerPO> finds() {
		ArrayList<ManagerPO> list=new ArrayList<ManagerPO>();
		String info="select * from `"+tableName+"`";
		String[] names={"id","password","name","facultyID","contactInfo"};
		ArrayList<String> reply=db.query(info,names);
		for(String s:reply){
			if(!s.equals("")){
				String[] sp=s.split(",");
				list.add(new ManagerPO(sp[0],sp[1],sp[2],sp[3],sp[4]));
			}
		}
		return list;
	}

	@Override
	public ManagerPO find(String id) throws RemoteException {
		 String info="select * from  `"+tableName+"`"+" where `id`='"+id+"'";
		 String[] names={"id","password","name","facultyID","contactInfo"};
		 String r=db.queryOne(info, names);
		 if(!r.equals("")){
			 String[] sp=r.split(",");
			 return new ManagerPO(sp[0],sp[1],sp[2],sp[3],sp[4]);
		 }else{
			 return null;
		 }
	}
	
	@Override
	public ArrayList<ManagerPO> finds(String facultyID) throws RemoteException{
		ArrayList<ManagerPO> list=new ArrayList<ManagerPO>();
		String info="select * from `"+tableName+"`"+" where `facultyId`='"+facultyID+"'";
		String[] names={"id","password","name","facultyID","contactInfo"};
		ArrayList<String> reply=db.query(info,names);
		for(String s:reply){
			String[] sp=s.split(",");
			list.add(new ManagerPO(sp[0],sp[1],sp[2],sp[3],sp[4]));
		}
		return list;
	}
	
	@Override
	public void delete(String id) throws RemoteException {
		String info="delete from  `"+tableName+"`"+" where `id` = '"+id+"'";
		db.delete(info);
		System.out.println("delete success in "+tableName);				
	}
}
