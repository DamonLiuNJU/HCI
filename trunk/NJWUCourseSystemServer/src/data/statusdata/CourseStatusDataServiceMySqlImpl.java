package data.statusdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.statuspo.CourseStatusPO;
import data.helper.DataBaseHelper;
import data.helper.Hint;
import data.helper.InfoFormat;
import dataservice.statusdataservice.CourseStatusDataService;

@SuppressWarnings("serial")
public class CourseStatusDataServiceMySqlImpl extends UnicastRemoteObject implements CourseStatusDataService{
	 String tableName="coursestatus";
	 String[] names={"module","type","on","off"};
	 
	 DataBaseHelper db;
	 Hint hint;
	 InfoFormat format;
	 
	 public CourseStatusDataServiceMySqlImpl() throws RemoteException {
		db=new DataBaseHelper();
		hint=new Hint(tableName);
		format=new InfoFormat();
	 }

	 @Override
	 public void insert(CourseStatusPO csp) {
		String info = "INSERT INTO `"+tableName+"` VALUES ('" +csp.getModule()+"','"
				+csp.getType()+"','"+csp.getOnTime()+"','"+csp.getOffTime()+ "')"; 
		db.insert(info);
        hint.hintInsert();
	 }

	 @Override
	 public void update(CourseStatusPO csp) {
		String info="update `"+tableName+"`"+" set `on`='"+csp.getOnTime()+
				"',`off`='"+csp.getOffTime()+"' where `module` = '"+csp.getModule()
				+"' and `type` = '"+csp.getType()+"'";
		db.update(info);
		hint.hintUpdate();
	 }

	 @Override
	 public ArrayList<CourseStatusPO> finds() {
		ArrayList<CourseStatusPO> list=new ArrayList<CourseStatusPO>();
		String info="select * from `"+tableName+"`";
		ArrayList<String> reply=db.query(info,names);
		for(String s:reply){
			if(!s.equals("")){
				String[] sp=format.decode(s);
				list.add(new CourseStatusPO(sp[0],sp[1],sp[2],sp[3]));
			}
		}
		hint.hintFind();
		return list;
	 }

	 @Override
	 public void delete(String module,String type) {
		String info="delete from  `"+tableName+"`"+" where `module` = '"+module
				+"' and `type` = '"+type+"'";
		db.delete(info);
		hint.hintDelete();
	 }
	 
	 @Override
	 public CourseStatusPO find(String module,String type) throws RemoteException {
		 String info="select * from  `"+tableName+"`"+" where `module`='"+module
				 +"' and `type` = '"+type+"'";
		 String r=db.queryOne(info, names);
		 if(!r.equals("")){
			 String[] sp=format.decode(r);
			 hint.hintFindOne();
			 return new CourseStatusPO(sp[0],sp[1],sp[2],sp[3]);
		 }else{
			 return null;
		 }
	 }
	
}
