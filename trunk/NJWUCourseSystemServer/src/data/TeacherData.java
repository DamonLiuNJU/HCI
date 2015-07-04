package data;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import po.*;
import po.coursepo.CoursePO;
import data.helper.DataBaseHelper;
import dataservice.TeacherDataService;

/*
 * TeacherData类，提供对数据库中Teacher表的操作
 * 包含insert，find，finds，delete，findAll，initalTeacher，
 * findByName，和update8个方法
 */
public class TeacherData extends UnicastRemoteObject implements TeacherDataService{
	
	private final String tableName = "teacher";
	private final String courseTable = "course";
	DataBaseHelper dbHelper ;
	static Connection conn ;
	static Statement st;

	public TeacherData() throws RemoteException {
		super();
		dbHelper = new DataBaseHelper();
		
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//插入一个teacherPO对象
	public boolean insert(TeacherPO tp) {
		// TODO Auto-generated method stub
		String info = "INSERT INTO `"+tableName+"` VALUES ('" + tp.getId() +"','"
				+tp.getPassword()+"','"+tp.getName()+"','"+tp.getFacultyID()
				+"','"+tp.getSeniority()+"','" +tp.getContactInfo() +"')";
		
		dbHelper.insert(info);
		
		return true;
	}

	//查找一个教师PO
	public TeacherPO find(String teacherID) {
		// TODO Auto-generated method stub
		conn = new DataBaseHelper().getConnection(); 
		
		try {
			String info = "select "+"*"+" from "+tableName+" where ("+"id"+"="+teacherID+")";
			st = conn.createStatement();
			
			TeacherPO tp = new TeacherPO();
			ResultSet rs = st.executeQuery(info);
			if(rs.next()){
				tp.setId(teacherID);
				tp.setPassword(rs.getString(2));
				tp.setName(rs.getString(3));
				tp.setFacultyID(rs.getString(4));
				tp.setSeniority(rs.getString(5));
				tp.setContactInfo(rs.getString(6));
			}
			else {
				tp =null;
			}
			conn.close();
			
			return tp;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
		
	}

	//按照院系id查找一个院系的教师列表，返回一个ArrayList
	public ArrayList<TeacherPO> finds(String faculty_id) {
		// TODO Auto-generated method stub
		conn = new DataBaseHelper().getConnection();

		try {
			st = conn.createStatement();
			ArrayList<TeacherPO> teacherArray = new ArrayList<TeacherPO>();
			String info = "select "+"*"+" from "+tableName+" where ("+"facultyID"+"="+faculty_id+")";
			
			ResultSet rs = st.executeQuery(info);
			
			while(rs.next()){
				TeacherPO tp = new TeacherPO();
				tp.setId(rs.getString(1));
				tp.setPassword(rs.getString(2));
				tp.setName(rs.getString(3));
				tp.setFacultyID(rs.getString(4));
				tp.setSeniority(rs.getString(5));
				tp.setContactInfo(rs.getString(6));
				teacherArray.add(tp);
			}
			conn.close();
			return teacherArray;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	//在表中删除一个teacherPO
	public boolean delete(String teacherID) {
		// TODO Auto-generated method stub
		conn = new DataBaseHelper().getConnection();
		
		try {
			st = conn.createStatement();
			
			String info="delete from  `"+tableName+"`"+" where (id = '"+teacherID+"')";
			
			st.executeUpdate(info);
			conn.close();
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

 	
	public ArrayList<TeacherPO> findAll() {
		// TODO Auto-generated method stub
		conn = new DataBaseHelper().getConnection();

		try {
			st = conn.createStatement();
			ArrayList<TeacherPO> teacherArray = new ArrayList<TeacherPO>();
			String info = "select "+"*"+" from "+tableName;
			
			ResultSet rs = st.executeQuery(info);
			
			while(rs.next()){
				TeacherPO tp = new TeacherPO();
				tp.setId(rs.getString(1));
				tp.setPassword(rs.getString(2));
				tp.setName(rs.getString(3));
				tp.setFacultyID(rs.getString(4));
				tp.setSeniority(rs.getString(5));
				tp.setContactInfo(rs.getString(6));
				teacherArray.add(tp);
			}
			conn.close();
			return teacherArray;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	//方法初始化一个登录之后的teacher对象，获取他个人的信息，以及他所授的课程。
	public TeacherPO initialTeacher(String id) throws RemoteException {
		// TODO Auto-generated method stub
		conn = new DataBaseHelper().getConnection();
		
		try {
			st =  conn.createStatement();
			
			String teacherInfo = "select "+"*"+" from "+tableName+" where ("+"id"+"="+id+")";
			st = conn.createStatement();
			
			TeacherPO tp = new TeacherPO();
			ResultSet rs1 = st.executeQuery(teacherInfo);
			if(rs1.next()){
				tp.setId(id);
				tp.setPassword(rs1.getString(2));
				tp.setName(rs1.getString(3));
				tp.setFacultyID(rs1.getString(4));
				tp.setSeniority(rs1.getString(5));
				tp.setContactInfo(rs1.getString(6));
			}
			
			ArrayList<CoursePO> courseArray = new ArrayList<CoursePO>();
			String courseInfo = "select "+"*"+" from "+courseTable+" where ("+"teacherID"+"="+id+")";
			ResultSet rs2= st.executeQuery(courseInfo);
			
			while(rs2.next()){
				CoursePO cp= new CoursePO();
				cp.setID(rs2.getString(1));
				cp.setName(rs2.getString(2));
				cp.setTeacherID(rs2.getString(3));
				cp.setCampus(rs2.getString(4));
				cp.setGrade(rs2.getString(5));
				cp.setPlace(rs2.getString(6));
				cp.setTime(rs2.getString(7));
				cp.setPeriod(rs2.getString(8));
				cp.setCredit(rs2.getString(11));
				courseArray.add(cp);
			}
			tp.setCourseArray(courseArray);
			
			return tp;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see dataservice.TeacherDataService#findByName(java.lang.String)
	 * 根据教师的姓名查找教师的信息
	 */
	public ArrayList<TeacherPO> findByName(String teacherName) throws RemoteException {
		// TODO Auto-generated method stub
		conn = new DataBaseHelper().getConnection(); 
		
		try {
			String info = "select "+"*"+" from "+tableName+" where ("+"name"+"='"+teacherName+"')";
			ArrayList<TeacherPO> teacherArray = new ArrayList<TeacherPO>();
			st = conn.createStatement();
			
			TeacherPO tp = new TeacherPO();
			ResultSet rs = st.executeQuery(info);
			while(rs.next()){
				tp.setId(rs.getString(1));
				tp.setPassword(rs.getString(2));
				tp.setName(rs.getString(3));
				tp.setFacultyID(rs.getString(4));
				tp.setSeniority(rs.getString(5));
				tp.setContactInfo(rs.getString(6));
				teacherArray.add(tp);
			}
			
			conn.close();
			
			return teacherArray;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
		
	}

	//该方法先删去原有的表项，再新增一条记录
	public boolean update(TeacherPO tp) throws RemoteException {
		// TODO Auto-generated method stub
		String id = tp.getId();
		if(this.delete(id)){//删除用户成功
			if(this.insert(tp)){//添加用户成功
				return true;
			}
		}
		return false;
	}
	
	
}
