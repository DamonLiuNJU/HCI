package data.studentdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import po.studentpo.StudentPO;
import data.helper.DataBaseHelper;
import dataservice.studentdataservice.StudentDataService;

public class StudentDataMySql extends UnicastRemoteObject implements StudentDataService{

	
	
	 DataBaseHelper db;
	
	static Connection conn;
	static Statement st;
	static String databasename = "coursesystem";
	static String tablename = "student";
	
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
	public String insert(StudentPO  sp) {
		String  id = sp.getStudentID();
		String password = sp.getPassWord();
		String 	name = sp.getName();
		String faculty = sp.getFacultyID();
//		String grade = sp.getGrade();
		String contactinfo = sp.getContactInfo();
		String phone = sp.getPhone();
		String homeaddress= sp.getHomeAddress();
		String mothername = sp.getMotherName();
		String motherphone = sp.getMotherPhone();
		String fathername = sp.getFatherName();
		String fatherphone = sp.getFatherPhone();
		conn = getConnection();
		try{
			String sql = "INSERT INTO " + tablename + " ( " + "id,password,name,facultyID,grade,contactInfo,phone,homeaddress,mothername,motherphone,fathername,fatherphone" + " )"
					+ " VALUES('" + id+"','"+password+"'"+name+"','"+faculty+"','"+"entranceyear"+"','"+contactinfo+"','"+phone+"','"+homeaddress+"','"+mothername+"','"+motherphone+"','"+fathername+"','"+fatherphone + "')";
			st = (Statement) conn.createStatement();
			st.executeUpdate(sql);
			System.out.println("insert success");
			conn.close();

		} catch (SQLException e) {
			System.out.println("Insert Failed" + e.getMessage());
		}
        return "Insert Success";
	}
	public String update(StudentPO sp) {
		conn = getConnection();
		String  id = sp.getStudentID(); //不变.
		String password = sp.getPassWord();
//		String 	name = sp.getName();
		String faculty = sp.getFacultyID();
		String entranceyear = sp.getGrade();
		String contactinfo = sp.getContactInfo();
		String phone = sp.getPhone();
		String homeaddress= sp.getHomeAddress();
		String mothername = sp.getMotherName();
		String motherphone = sp.getMotherPhone();
		String fathername = sp.getFatherName();
		String fatherphone = sp.getFatherPhone();
		
		String info="update `"+tablename+"`"+" set `password`='"+password+"' where `id` = '"+id+"'";
		try {
			st = (Statement) conn.createStatement();
			st.executeUpdate(info);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		info="update `"+tablename+"`"+" set `facultyID`='"+faculty+"' where `id` = '"+id+"'";
		try {
			st = (Statement) conn.createStatement();
			st.executeUpdate(info);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		info="update `"+tablename+"`"+" set `entranceyear`='"+entranceyear+"' where `id` = '"+id+"'";
		try {
			st = (Statement) conn.createStatement();
			st.executeUpdate(info);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		info="update `"+tablename+"`"+" set `contactinfo`='"+contactinfo+"' where `id` = '"+id+"'";
		try {
			st = (Statement) conn.createStatement();
			st.executeUpdate(info);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		info="update `"+tablename+"`"+" set `phone`='"+phone+"' where `id` = '"+id+"'";
		try {
			st = (Statement) conn.createStatement();
			st.executeUpdate(info);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		info="update `"+tablename+"`"+" set `homeaddress`='"+homeaddress+"' where `id` = '"+id+"'";
		try {
			st = (Statement) conn.createStatement();
			st.executeUpdate(info);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		info="update `"+tablename+"`"+" set `mothername`='"+mothername+"' where `id` = '"+id+"'";
		try {
			st = (Statement) conn.createStatement();
			st.executeUpdate(info);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		info="update `"+tablename+"`"+" set `motherphone`='"+motherphone+"' where `id` = '"+id+"'";
		try {
			st = (Statement) conn.createStatement();
			st.executeUpdate(info);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		info="update `"+tablename+"`"+" set `fathername`='"+fathername+"' where `id` = '"+id+"'";
		try {
			st = (Statement) conn.createStatement();
			st.executeUpdate(info);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		info="update `"+tablename+"`"+" set `fatherphone`='"+fatherphone+"' where `id` = '"+id+"'";
		try {
			st = (Statement) conn.createStatement();
			st.executeUpdate(info);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return "Update Success";
	}
	
	
	
	
	@Override
	public String delete(StudentPO sp) throws RemoteException {
		// TODO 自动生成的方法存根
		String student_id = sp.getStudentID();
		conn = getConnection();
		try {
			String sql = "delete from " + tablename + " where ( "
					+ "id = '"+student_id+ " ')";
			st = (Statement) conn.createStatement();
			int count = st.executeUpdate(sql);
			System.out.println("Delete  : " + count + " Records Success");
			conn.close();
		} catch (SQLException e) {
			System.out.println("Failed Delete " + e.getMessage());
		}
		return "DeleteSuccess";
	}
	public StudentPO find(StudentPO sp){
		
		StudentPO resultsp = new StudentPO();
		String student_id = sp.getStudentID();
		conn = getConnection();
		
		try {
			String sql = "select " + "*" + " from " + tablename
					+ " where ( id = '" + student_id + "' )";
			st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()){
				resultsp.setStudentID(rs.getString("id"));
				resultsp.setPassword(rs.getString("password"));
				resultsp.setName(rs.getString("name"));
				resultsp.setFaculty_ID(rs.getString("facultyID"));
				resultsp.setGrade(rs.getString("entranceyear"));
				resultsp.setContactInfo(rs.getString("contactInfo"));
				resultsp.setPhone(rs.getString("phone"));
				resultsp.setHomeAddress(rs.getString("homeaddress"));
				resultsp.setMotherName(rs.getString("mothername"));
				resultsp.setMotherPhone(rs.getString("motherphone"));
				resultsp.setFatherName(rs.getString("fathername"));
				resultsp.setFatherPhone(rs.getString("fatherphone"));
			}
			conn.close();
			System.out.println("find success");

		} catch (SQLException e) {
			System.out.println("查询数据失败 : " +e.getMessage());
		}
		return resultsp;
	}
	public StudentDataMySql() throws RemoteException {
		super();
	}
 
	private static final long serialVersionUID = 1L;

 
	public ArrayList<String> getCourseList(StudentPO studentnumber) {
		// TODO 自动生成的方法存根
		return null;
	}

 
	public boolean deleteCourse(StudentPO studentnumber) {
		// TODO 自动生成的方法存根
		return false;
	}

 
	public boolean setPersonalInfo(StudentPO po) {
		// TODO 自动生成的方法存根
		return false;
	}

 
	public boolean addCourseNo(StudentPO coursenumber$studentnumber) {
		// TODO 自动生成的方法存根
		return false;
	}

	public static void main(String args[]){
		StudentPO sp = new StudentPO();
		sp.setStudentID("121250089");
		sp.setName("刘威廷");
		sp.setFaculty_ID("001");
		sp.setGrade("2012");
		sp.setContactInfo("lwt@software.nju.edu.cn");
		sp.setPhone("15950562797");
		sp.setHomeAddress("5B518");
		sp.setMotherName("mothername");
		sp.setMotherPhone("12312313213");
		sp.setFatherName("fathernae");
		sp.setFatherPhone("1111111111");
		StudentDataMySql sdm = null;
		try {
			sdm = new StudentDataMySql();
			sp = sdm.find(sp);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		
	}
	@Override
	public ArrayList<StudentPO> getStudentListByFacultyID(StudentPO po) throws RemoteException {
		// TODO 自动生成的方法存根
		conn  = getConnection();
		ArrayList<StudentPO >  result = new ArrayList<StudentPO>();
		String facultyID = po.getFacultyID();
		String grade = po.getGrade();
		String condition = "";
		condition = "facultyID = '"+facultyID +"' and entranceyear = '" +grade +"' ";
		try {
			String sql = "select " + "*" + " from " + tablename+ " where ( "+condition+" )";
			st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()){
				String studentnumber = rs.getString("id");
				StudentPO sp  = new StudentPO(studentnumber);
				result.add(sp);
			}
			conn.close();
			System.out.println("find success");

		} catch (SQLException e) {
			System.out.println("查询数据失败 : " +e.getMessage());
		}
		
		
		return result;
	}

}
