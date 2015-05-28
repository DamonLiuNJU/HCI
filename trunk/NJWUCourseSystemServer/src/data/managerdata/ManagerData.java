package data.managerdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.managerpo.ManagerPO;
import data.helper.DataBaseHelper;
import data.helper.Hint;
import data.helper.InfoFormat;
import dataservice.managerdataservice.ManagerDataService;

@SuppressWarnings("serial")
public class ManagerData  extends UnicastRemoteObject  implements ManagerDataService{	
	String tableName="manager";
	
	DataBaseHelper db ;
	Hint hint;
	public ManagerData() throws RemoteException {				
		db=new DataBaseHelper();
		hint=new Hint(tableName);
	}
	@Override
	public void insert(ManagerPO mp){					
		String info = "INSERT INTO `"+tableName+"` VALUES "+mp.getValues(); 				  
		db.insert(info);
		hint.hintInsert();
	}

	@Override
	public void update(ManagerPO mp) throws RemoteException {
		delete(mp.getID());
		insert(mp);
		hint.hintUpdate();
	}
	
	public void updatePw(ManagerPO mp) throws RemoteException{
		String info="update `"+tableName+"`"+" set `password`='"+mp.getPassword()
				+"' where `id` = '"+mp.getID()+"'";
		db.update(info);
		hint.hintUpdate();
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
		InfoFormat format=new InfoFormat();
		for(String s:reply){
			if(!s.equals("")){
				String[] sp=format.decode(s);
				list.add(new ManagerPO(sp[0],sp[1],sp[2],sp[3],sp[4]));
			}
		}
		hint.hintFind();
		return list;
	}

	@Override
	public ManagerPO find(String id) throws RemoteException {
		 String info="select * from  `"+tableName+"`"+" where `id`='"+id+"'";
		 String[] names={"id","password","name","facultyID","contactInfo"};
		 System.out.println(1);
		 String r=db.queryOne(info, names);
		System.out.println(2);
		InfoFormat format=new InfoFormat();
		if(!r.equals("")){
			 String[] sp=format.decode(r);
			 hint.hintFindOne();
			
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
		hint.hintDelete();				
	}
}
