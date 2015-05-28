package data;

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
	   public ArrayList<String> query(String sqlInfo,String[] names){
		   conn = getConnection(); 
		   ArrayList<String> list=new ArrayList<String>();
	       try {  
	           st = (Statement) conn.createStatement();   
	             
	           ResultSet rs = st.executeQuery(sqlInfo);    
	           while (rs.next()) { 
	                StringBuilder sb=new StringBuilder();
	                for(int i=0;i<names.length;i++){
	                   sb.append(","+rs.getString(names[i]));  
	                }
	                sb.deleteCharAt(0);
	                list.add(sb.toString());
	           }
	           conn.close();  	           
	             
	       } catch (SQLException e) {  
	           System.out.println("error");  
	       }
	       return list;	       
	   }
	   
	   public String queryOne(String sqlInfo){
		   conn = getConnection(); 
		   String reply=null;
		   try {  
	           st = (Statement) conn.createStatement();   
	             
	           //ResultSet rs = st.executeQuery(sqlInfo);
	           //read one item
	           conn.close();  	           
	             
	       } catch (SQLException e) {  
	           System.out.println("error");  
	       }
		   return reply;
	   }

	   public Connection getConnection() {  
	       Connection con = null;  
	       try {  
	           Class.forName("com.mysql.jdbc.Driver");
	             
	           con = DriverManager.getConnection(  
	                   "jdbc:mysql://localhost:3306/managedatabase", "root", "root");// 
	       } catch (Exception e) {  
	           System.out.println("数据库连接失败" + e.getMessage());  
	       }  
	       return con; 
	   }  

}
