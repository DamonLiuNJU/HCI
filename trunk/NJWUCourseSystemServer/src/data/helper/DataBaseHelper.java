package data.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataBaseHelper {
		Connection conn;
		Statement st;
	 
	 public void insert(String sqlInfo){
		   conn=getConnection();
		   try {    
	           st = (Statement) conn.createStatement();   
	           st.executeUpdate(sqlInfo); 
	           conn.close();   
	             
	       } catch (SQLException e) {  
	           System.out.println("error" + e.getMessage());  
	       }  
	   }
	 
	 public void update(String sqlInfo){
		   conn = getConnection(); 
	       try {  
	    	   st = (Statement) conn.createStatement();      
	           st.executeUpdate(sqlInfo);
	           conn.close();   
	       } catch (SQLException e) {  
	           System.out.println("error");  
	       }  
	   }
	   public void delete(String sqlInfo){
		   conn = getConnection();  
	       try {  
	           st = (Statement) conn.createStatement();
	             
	           st.executeUpdate(sqlInfo);
	             
	           conn.close(); 
	             
	       } catch (SQLException e) {  
	           System.out.println("error");  
	       }  
	   }
	   
	   public String queryOne(String sqlInfo,String[] names){
		   conn = getConnection(); 
		   String s="";
	       try {  
	           st = (Statement) conn.createStatement();   
	           ResultSet rs = st.executeQuery(sqlInfo); 
	       
	           InfoFormat format=new InfoFormat();
	           String[] field=new String[names.length];
			   while (rs.next()) {
				   	for (int i = 0; i < names.length; i++) {
				   			field[i] = rs.getString(names[i]);
				   	}
				   	s = format.encode(field);
			   }
			   conn.close(); 	             
	       	} catch (SQLException e) {  
	           System.out.println("error");  
	       }
	       return s;	       
	   }
	   public ArrayList<String> query(String sqlInfo,String[] names){
		   conn = getConnection(); 
		   ArrayList<String> list=new ArrayList<String>();
	       try {  
	           st = (Statement) conn.createStatement();   
	           ResultSet rs = st.executeQuery(sqlInfo);    
	           while (rs.next()) {
	        	    //建立InfoFormat为检出信息作简单编码
	        	   	InfoFormat format = new InfoFormat();
					String[] field = new String[names.length];
					for (int i = 0; i < names.length; i++) {
						field[i] = rs.getString(names[i]);
					}
					String s = format.encode(field);
					list.add(s);
				}
	           conn.close();  	             
	       } catch (SQLException e) {  
	           System.out.println("error");  
	       }
	       return list;	       
	   }

	   public Connection getConnection() {  
	       Connection con = null;  
	       try {  
	           Class.forName("com.mysql.jdbc.Driver");
	             
	           con = DriverManager.getConnection(  
	                   "jdbc:mysql://localhost:3306/coursesystem", "root", "root");
	       } catch (Exception e) {  
	           System.out.println("数据库连接失败" + e.getMessage());  
	       }  
	       return con; 
	   }  

}
