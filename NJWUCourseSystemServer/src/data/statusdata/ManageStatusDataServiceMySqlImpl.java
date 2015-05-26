package data.statusdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.statuspo.ManageStatusPO;
import data.helper.DataBaseHelper;
import dataservice.statusdataservice.ManageStatusDataService;

@SuppressWarnings("serial")
public class ManageStatusDataServiceMySqlImpl extends UnicastRemoteObject implements ManageStatusDataService{
	String tableName="managestatus";
	 
	DataBaseHelper db;

	public ManageStatusDataServiceMySqlImpl() throws RemoteException {
		super();
		db=new DataBaseHelper();
	}

	
	@Override
	public void insert(ManageStatusPO mp) {
		String info = "INSERT INTO `"+tableName+"` VALUES ('" +mp.getType()+"','"
				+mp.getOnTime()+"','"+mp.getOffTime()+ "')"; 
		db.insert(info);
        System.out.println("成功在"+tableName+"表中插入");
	}

	@Override
	public void update(ManageStatusPO mp) {
		String info="update `"+tableName+"`"+" set `on`='"+mp.getOnTime()+
				"',`off`='"+mp.getOffTime()+"' where `type` = '"+mp.getType()+"'";
		db.update(info);
		System.out.println("成功在"+tableName+"表中更新");
	}

	@Override
	public ArrayList<ManageStatusPO> finds() {
		ArrayList<ManageStatusPO> list=new ArrayList<ManageStatusPO>();
		String info="select * from `"+tableName+"`";
		String[] names={"type","on","off"};
		ArrayList<String> reply=db.query(info,names);
		for(String s:reply){
			if(!s.equals("")){
				String[] sp=s.split(",");
				list.add(new ManageStatusPO(sp[0],sp[1],sp[2]));
			}
		}
		return list;
	}

	@Override
	public void delete(String type) {
		String info="delete from  `"+tableName+"`"+" where `type` = '"+type+"'";
		db.delete(info);
		System.out.println("成功从"+tableName+"表中删除");
	}


	@Override
	public ManageStatusPO find(String type) throws RemoteException {
		String info="select * from  `"+tableName+"`"+" where `type` = '"+type+"'";
		String[] names={"type","on","off"};
		String r=db.queryOne(info, names);
		if(!r.equals("")){
			String[] sp=r.split(",");
			return new ManageStatusPO(sp[0],sp[1],sp[2]);
		}else{
			return null;
		}
	}
}
