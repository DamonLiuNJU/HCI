package data.statusdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.statuspo.CourseStatusPO;
import data.helper.DataBaseHelper;
import dataservice.statusdataservice.CourseStatusDataService;

@SuppressWarnings("serial")
public class CourseStatusDataServiceMySqlImpl extends UnicastRemoteObject implements CourseStatusDataService{
	 String tableName="coursestatus";
	 
	 DataBaseHelper db;
	 
	 public CourseStatusDataServiceMySqlImpl() throws RemoteException {
			super();
			db=new DataBaseHelper();
		}

	 @Override
	 public void insert(CourseStatusPO csp) {
		String info = "INSERT INTO `"+tableName+"` VALUES ('" +csp.getModule()+"','"
				+csp.getType()+"','"+csp.getOnTime()+"','"+csp.getOffTime()+ "')"; 
		db.insert(info);
        System.out.println("成功在"+tableName+"表中插入");
	 }

	 @Override
	 public void update(CourseStatusPO csp) {
		String info="update `"+tableName+"`"+" set `on`='"+csp.getOnTime()+
				"',`off`='"+csp.getOffTime()+"' where `module` = '"+csp.getModule()
				+"' and `type` = '"+csp.getType()+"'";
		db.update(info);
		System.out.println("成功在"+tableName+"表中更新");
	 }

	 @Override
	 public ArrayList<CourseStatusPO> finds() {
		ArrayList<CourseStatusPO> list=new ArrayList<CourseStatusPO>();
		String info="select * from `"+tableName+"`";
		String[] names={"module","type","on","off"};
		ArrayList<String> reply=db.query(info,names);
		for(String s:reply){
			if(!s.equals("")){
				String[] sp=s.split(",");
				list.add(new CourseStatusPO(sp[0],sp[1],sp[2],sp[3]));
			}
		}
		return list;
	 }

	 @Override
	 public void delete(String module,String type) {
		String info="delete from  `"+tableName+"`"+" where `module` = '"+module
				+"' and `type` = '"+type+"'";
		db.delete(info);
		System.out.println("成功从"+tableName+"表中删除");
	 }
	 
	 @Override
	 public CourseStatusPO find(String module,String type) throws RemoteException {
		 String info="select * from  `"+tableName+"`"+" where `module`='"+module
				 +"' and `type` = '"+type+"'";
		 String[] names={"module","type","on","off"};
		 String r=db.queryOne(info, names);
		 if(!r.equals("")){
			 String[] sp=r.split(",");
			 return new CourseStatusPO(sp[0],sp[1],sp[2],sp[3]);
		 }else{
			 return null;
		 }
	 }
	
}
