package data.statusdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.statuspo.ManageStatusPO;
import data.helper.DataBaseHelper;
import data.helper.Hint;
import data.helper.InfoFormat;
import dataservice.statusdataservice.ManageStatusDataService;

@SuppressWarnings("serial")
public class ManageStatusDataServiceMySqlImpl extends UnicastRemoteObject implements ManageStatusDataService{
	String tableName="managestatus";
	String[] names={"type","on","off"};
	 
	DataBaseHelper db;
	Hint hint;
	InfoFormat format;

	public ManageStatusDataServiceMySqlImpl() throws RemoteException {
		db=new DataBaseHelper();
		hint=new Hint(tableName);
		format=new InfoFormat();
	}

	
	@Override
	public void insert(ManageStatusPO mp) {
		String info = "INSERT INTO `"+tableName+"` VALUES ('" +mp.getType()+"','"
				+mp.getOnTime()+"','"+mp.getOffTime()+ "')"; 
		db.insert(info);
        hint.hintInsert();
	}

	@Override
	public void update(ManageStatusPO mp) {
		String info="update `"+tableName+"`"+" set `on`='"+mp.getOnTime()+
				"',`off`='"+mp.getOffTime()+"' where `type` = '"+mp.getType()+"'";
		db.update(info);
		hint.hintUpdate();
	}

	@Override
	public ArrayList<ManageStatusPO> finds() {
		ArrayList<ManageStatusPO> list=new ArrayList<ManageStatusPO>();
		String info="select * from `"+tableName+"`";
		ArrayList<String> reply=db.query(info,names);
		for(String s:reply){
			if(!s.equals("")){
				String[] sp=format.decode(s);
				list.add(new ManageStatusPO(sp[0],sp[1],sp[2]));
			}
		}
		hint.hintFind();
		return list;
	}

	@Override
	public void delete(String type) {
		String info="delete from  `"+tableName+"`"+" where `type` = '"+type+"'";
		db.delete(info);
		hint.hintDelete();
	}


	@Override
	public ManageStatusPO find(String type) throws RemoteException {
		String info="select * from  `"+tableName+"`"+" where `type` = '"+type+"'";
		String r=db.queryOne(info, names);
		if(!r.equals("")){
			String[] sp=format.decode(r);
			hint.hintFindOne();
			return new ManageStatusPO(sp[0],sp[1],sp[2]);
		}else{
			return null;
		}
	}
}
