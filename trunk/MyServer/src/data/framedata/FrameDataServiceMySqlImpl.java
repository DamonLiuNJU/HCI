package data.framedata;

import java.util.ArrayList;

import po.FramePO;
import data.DataBaseHelper;
import dataservice.FrameDataService;

public class FrameDataServiceMySqlImpl implements FrameDataService{    
	 String tableName="frame";
	 
	 DataBaseHelper db=new DataBaseHelper();
	 
	 @Override
	 public void insert(FramePO fp){		   
	     String info = "INSERT INTO `"+tableName+"`("+fp.getAttributeNames()+ ") VALUES "+fp.getValues(); 
	     db.insert(info);
         System.out.println("成功在"+tableName+"表中插入");
	 }

	 @Override
	 public void update(FramePO fp) {
	     String info="update `"+tableName+"`"+" set `content`='"+fp.getContent()+"' where `index` = '"+fp.getIndex()+"'";
	     db.update(info);
	     System.out.println("成功在"+tableName+"表中更新");
	 }

	 @Override
	 public FramePO find(int index) {		
		return null;
	 }

	 @Override
	 public ArrayList<FramePO> finds() {
		 ArrayList<FramePO> list=new ArrayList<FramePO>();
		 String info="select * from `"+tableName+"`";
		 String attributeNames=new FramePO().getAttributeNames().replaceAll("`", "");
		 String[] split=attributeNames.split(",");
		 ArrayList<String> reply=db.query(info,split);
		 for(String s:reply){
			 String[] sp=s.split(",");
			 //System.out.println(s);
			 list.add(new FramePO(Integer.parseInt(sp[0]),sp[1]));
		 }
		 return list;
	 }

	 @Override
	 public void delete(int index) {
		String info="delete from  `"+tableName+"`"+" where `index` = '"+index+"'";
		db.delete(info);
		System.out.println("成功从"+tableName+"表中删除");
	 }
}
