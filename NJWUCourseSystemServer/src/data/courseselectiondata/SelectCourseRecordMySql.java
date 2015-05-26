package data.courseselectiondata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import po.courseselectionpo.SelectCourseRecordPO;
import dataservice.courseselectiondataservice.SelectCourseRecordDataService;

public class SelectCourseRecordMySql extends UnicastRemoteObject implements SelectCourseRecordDataService{

	/**
	 * 
	 */
	private static final long serialVersionUID = -218776017243460075L;

	public SelectCourseRecordMySql() throws RemoteException {
		super();
		// TODO 自动生成的构造函数存根
	}

	/**
	 * @param args
	 */
	
	static Connection conn;
	static Statement st;
	static String databasename = "coursesystem";
	static String tablename = "selectcourserecord";
	
	String student_id;
	String course_id;
	String score;
	String stugrade;
	
	private static Connection getConnection() {
		String driver = "com.mysql.jdbc.Driver";
		String username = "root";
		String password = "root";
		Connection con = null; // 创建用于连接数据库的Connection对象
		try {
			Class.forName(driver);// 加载Mysql数据驱动
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"
					+ databasename, username, password);// 创建数据连接
			System.out.println("Connect to database :<" + databasename
					+ ">Success! ");
		} catch (Exception e) {
			System.out.println("数据库连接失败" + e.getMessage());
		}

		return con; // 返回所建立的数据库连接
	}
	
	@Override
	public void insert(SelectCourseRecordPO po) {
		// TODO 自动生成的方法存根
		//重复问题
		conn  = getConnection();
		student_id = po.getStudent_ID();
		course_id = po.getCourse_ID();
		score = po.getScore();
		stugrade  = po.getStuGrade();
		try{
			String sql = "INSERT INTO " + tablename + " ( " + "student_id,course_id,score,stugrade" + " )"
					+ " VALUES('" + student_id+"','"+course_id+"','"+score+"','"+stugrade+ "')";
			st = (Statement) conn.createStatement();
			st.executeUpdate(sql);
			System.out.println("insert success");
			conn.close();
		} catch (SQLException e) {
			System.out.println("Insert Failed" + e.getMessage());
		}

	}
	@Override
	public void delete(SelectCourseRecordPO po) {
		// TODO 自动生成的方法存根
		conn  = getConnection();
		student_id = po.getStudent_ID();
		course_id = po.getCourse_ID();
		score = po.getScore();
		String condition = "";
		condition = "student_id = '"+student_id +"' and course_id = '"+course_id +"'";
		try {
			String sql = "delete from " + tablename + " where ( " + condition +" )";
			st = (Statement) conn.createStatement();
			int count = st.executeUpdate(sql);
			System.out.println("Delete  : " + count + " Records Success");
			conn.close();
		} catch (SQLException e) {
			System.out.println("Failed Delete " + e.getMessage());
		}
		
	}
	@Override
	public void update(SelectCourseRecordPO po) {
		// TODO 自动生成的方法存根
		conn  = getConnection();
		student_id = po.getStudent_ID();
		course_id = po.getCourse_ID();
		score = po.getScore();
		String condition = "";
		condition = "student_id = '"+student_id +"' and course_id = '"+course_id +"'";
		try {
			String sql = "update " + tablename + " set  score = " +score 
					+ "  where (  " + condition  + " )";
			st = (Statement) conn.createStatement();
			int count = st.executeUpdate(sql);
			System.out.println("stu表中更新 " + count + " 条数据");
			conn.close();
		} catch (SQLException e) {
			System.out.println("更新数据失败" + e.getMessage());
		}
		
	}
	@Override
	public SelectCourseRecordPO find(SelectCourseRecordPO po) {
		//find by student_id & course_id
		// TODO 自动生成的方法存根
		//通过这两个信息就可以定位这个记录了
		conn  = getConnection();
		student_id = po.getStudent_ID();
		course_id = po.getCourse_ID();
		score = po.getScore();
		stugrade = po.getStuGrade();
		String condition = "";
		condition = "student_id = '"+student_id +"' and course_id = '"+course_id +"'";
		try {
			String sql = "select " + "*" + " from " + tablename+ " where (  " + condition + ")";
			st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			int count = 0;
			while(rs.next()){
				score = rs.getString("score");
				stugrade= rs.getString("stugrade");
				count++;
			}
			conn.close();
			System.out.println("find "+count +" info success");

		} catch (SQLException e) {
			System.out.println("查询数据失败 : " +e.getMessage());
		}
		
		po = new SelectCourseRecordPO(student_id, course_id, score,stugrade);
		
		return po;
	}
	
	public  ArrayList<SelectCourseRecordPO> findCourseList(SelectCourseRecordPO po) {
		conn  = getConnection();
		student_id = po.getStudent_ID();
		stugrade= po.getStuGrade();
		String condition = "";
		condition = "student_id = '"+student_id +"' ";
		ArrayList<SelectCourseRecordPO> resultlist = new ArrayList<SelectCourseRecordPO>();
		try {
			String sql = "select " + "*" + " from " + tablename
					+ " where (  " + condition + ")";
			st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			int count = 0;
			while(rs.next()){
				course_id = rs.getString("course_id");
				score = rs.getString("score");
				po = new SelectCourseRecordPO(student_id, course_id, score,stugrade);
				resultlist.add(po);
				count++;
			}
			conn.close();
			System.out.println("find "+count +" info success");

		} catch (SQLException e) {
			System.out.println("查询数据失败 : " +e.getMessage());
		}
	
		return resultlist;
	}
	
	public  ArrayList<SelectCourseRecordPO> findStudentList(SelectCourseRecordPO po) {
		conn  = getConnection();
		course_id = po.getCourse_ID();
		stugrade   = po.getStuGrade();
		String condition = "";
		condition = "course_id = '"+course_id +"' ";
		ArrayList<SelectCourseRecordPO> resultlist = new ArrayList<SelectCourseRecordPO>();
		try {
			String sql = "select " + "*" + " from " + tablename
					+ " where (  " + condition + ")";
			st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			int count = 0;
			while(rs.next()){
				student_id = rs.getString("student_id");
				score = rs.getString("score");
				po = new SelectCourseRecordPO(student_id,course_id,score,stugrade);
				resultlist.add(po);
				count++;
			}
			conn.close();
			System.out.println("find "+count +" info success");

		} catch (SQLException e) {
			System.out.println("查询数据失败 : " +e.getMessage());
		}
	
		return resultlist;
	}
}
