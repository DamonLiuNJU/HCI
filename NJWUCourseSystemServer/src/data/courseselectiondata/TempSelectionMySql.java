package data.courseselectiondata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.courseselectionpo.TempSelectionPO;
import data.helper.DataBaseHelper;
import dataservice.courseselectiondataservice.TempSelectionDataService;

@SuppressWarnings("serial")
public class TempSelectionMySql extends UnicastRemoteObject implements TempSelectionDataService{
	String tableName="tempselection";
	String[] names={"student_id", "course_id", "score", "stugrade" };
	
	DataBaseHelper db;
	
	public TempSelectionMySql() throws RemoteException {
		db=new DataBaseHelper();
	}
	@Override
	public void insert(TempSelectionPO po) {
		String sql =  "INSERT INTO `"+tableName+"` VALUES ('" +po.getStudent_ID()+"','"
				+po.getCourse_ID()+"','"+po.getScore()+"','"+po.getStuGrade()+"')"; 
		db.insert(sql);	
		System.out.println("Insert in "+tableName+" succeed!");
	}
	
	@Override
	public void delete(String student_id,String course_id) {
		String sql="delete from  `"+tableName+"`"+" where `student_id` = '"+student_id
				+"' and `course_id` = '"+course_id+"'";
		db.delete(sql);
		System.out.println("成功从"+tableName+"表中删除");
	}
	
	
	@Override
	public void deletes(){
		String sql="delete from `"+tableName+"`";
		db.delete(sql);
		System.out.println("成功删除"+tableName+"表中所有记录");
	}
	
	@Override
	public void update(TempSelectionPO po) {
		//不知道是否会用到，暂时不写		
	}
	
	@Override
	public ArrayList<TempSelectionPO> find(String student_id) throws RemoteException {
		ArrayList<TempSelectionPO> result = new ArrayList<TempSelectionPO>();
		String condition = "`student_id` ='"+student_id+"'";
		String sql = "select " + "*" + " from `"+tableName+"`"+ " where (  "
				+ condition + ")";
		ArrayList<String> list=db.query(sql, names);
		for(String s:list){
			if(!s.equals("")){
				String[] sp=s.split(",");
				result.add(new TempSelectionPO(sp[0],sp[1],sp[2],sp[3]));
			}
		}
		System.out.println("Find course list of a student in "+tableName+" succeed!");
		return result;
	}

	public ArrayList<TempSelectionPO> findStudentList(String course_id)throws RemoteException {
		ArrayList<TempSelectionPO> result = new ArrayList<TempSelectionPO>();
		String condition = "`course_id` ='" +course_id +"'";
		String sql = "select " + "*" + " from `"+tableName+"`"+ " where (  " + condition + ")";
		ArrayList<String> list=db.query(sql, names);
		for(String s:list){
			if(!s.equals("")){
				String[] sp=s.split(",");
				result.add(new TempSelectionPO(sp[0],sp[1],sp[2],sp[3]));
			}
		}
		System.out.println("Find student list of a course in "+tableName+" succeed!");
		return result;
	}
	
	
	public ArrayList<TempSelectionPO> finds() throws RemoteException {
		ArrayList<TempSelectionPO> result = new ArrayList<TempSelectionPO>();
		String sql="select * from `"+tableName+"`";
		ArrayList<String> reply=db.query(sql,names);
		for(String s:reply){
			if(!s.equals("")){
				String[] sp=s.split(",");
				result.add(new TempSelectionPO(sp[0],sp[1],sp[2],sp[3]));
			}
		}
		System.out.println("Finds in "+tableName+" succeed!");
		return result;
	}
	
	@Override
	public TempSelectionPO findOne(String student_id,
			String course_id) throws RemoteException {
		String sql = "select " + "*" + " from `"+tableName+"`"+ " where " +
				"`student_id` = '"+student_id
				+"' and `course_id` = '"+course_id+"'";
		String r=db.queryOne(sql, names);
		if(!r.equals("")){
			String[] sp=r.split(",");
			return new TempSelectionPO(sp[0],sp[1],sp[2],sp[3]);
		}
		return null;
	}
}

