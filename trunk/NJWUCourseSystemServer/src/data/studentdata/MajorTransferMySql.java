package data.studentdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import po.studentpo.MajorTransferPO;
import dataservice.studentdataservice.MajorTransferDataService;

public class MajorTransferMySql extends UnicastRemoteObject implements MajorTransferDataService{

	public MajorTransferMySql() throws RemoteException {
		super();
		// TODO 自动生成的构造函数存根
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8989792864624645038L;

	/**
	 * @param args
	 */
	
	static Connection conn;
	static Statement st;
	static String databasename = "coursesystem";
	static String tablename = "majortransfer";
	
	
 
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
	public void insert(MajorTransferPO po) {
		// TODO 自动生成的方法存根
		String student_id = po.getStudent_ID();
		String preschool = po.getPreSchool();
		String toschool = po.getToSchool();
		String status = po.getStatus();
		String applydate = po.getapplydate();
		conn = getConnection();
		
		try{
			String sql = "INSERT INTO " + tablename + " ( " + "student_id,preschool,toschool,status,applydate" + " )"
					+ " VALUES( '" + student_id+"','"+preschool+"','"+toschool+"','"+status+"','"+applydate + "')";
			st = (Statement) conn.createStatement();
			st.executeUpdate(sql);
			System.out.println("insert success");
			conn.close();

		} catch (SQLException e) {
			System.out.println("Insert Failed" + e.getMessage());
		}
		
		
	}
	@Override
	public void delete(MajorTransferPO po) {
		// TODO 自动生成的方法存根
		
	}
	@Override
	public void update(MajorTransferPO po) {
		// TODO 自动生成的方法存根
		
	}
	@Override
	public MajorTransferPO find(MajorTransferPO po) {
		// TODO 自动生成的方法存根
		//通过学号来查找 如果有的话反回  没有的时候直接反悔null
		conn = getConnection();
		String student_id = po.getStudent_ID();
		String preschool = po.getPreSchool();
		String toschool = po.getToSchool();
		String status = po.getStatus();
		String applydate = po.getapplydate();
		String condition = "student_id = '"+student_id+"'";
		int count = 0;
		try {
			String sql = "select " + "*" + " from " + tablename+ " where (  " + condition + ")";
			st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				preschool = rs.getString("preschool");
				toschool = rs.getString("toschool");
				status = rs.getString("status");
				applydate = rs.getString("applydate");
				count++;
			}
			conn.close();
			System.out.println("find "+count +" info success");

		} catch (SQLException e) {
			System.out.println("查询数据失败 : " +e.getMessage());
		}
		
		if(count == 0){
			return null;
		}else{
			return new MajorTransferPO(student_id, preschool, toschool, status, applydate);
		}
		 
	}
	@SuppressWarnings("null")
	public ArrayList<MajorTransferPO> getAllContent(){
		ArrayList<MajorTransferPO> result  = new ArrayList<MajorTransferPO>();
		conn = getConnection();
		String student_id ;
		String preschool ;
		String toschool ;
		String status;
		String applydate;
		int count = 0 ;
		try {
			String sql = "select " + "*" + " from " + tablename;
			st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				student_id = rs.getString("student_id");
				preschool = rs.getString("preschool");
				toschool = rs.getString("toschool");
				status = rs.getString("status");
				applydate = rs.getString("applydate");
				count++;
				MajorTransferPO po = new MajorTransferPO(student_id, preschool, toschool, status, applydate);
				result.add(po);
			}
			conn.close();
			System.out.println("find "+count +" info success");

		} catch (SQLException e) {
			System.out.println("查询数据失败 : " +e.getMessage());
		}
		
		return result;
	}
	
	public static void main(String args[]){
		
	}

}
