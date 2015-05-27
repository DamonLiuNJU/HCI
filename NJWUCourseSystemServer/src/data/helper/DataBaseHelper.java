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
		   StringBuilder sb=new StringBuilder();
	       try {  
	           st = (Statement) conn.createStatement();   
	           ResultSet rs = st.executeQuery(sqlInfo); 
	           while (rs.next()) {
	             for(int i=0;i<names.length;i++){
	            	 String s=rs.getString(names[i]);
	            	 if(s.equals("")){
	            		 sb.append(","+"%&%");
	            	 }else{
	            		 sb.append(","+s);  
	            	 }
	             }
	           }
	           if(!sb.toString().equals("")){
	        	   sb.deleteCharAt(0);
	           }
	           conn.close();  	             
	       } catch (SQLException e) {  
	           System.out.println("error");  
	       }
	       return sb.toString();	       
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
	                   String s=rs.getString(names[i]);
	                   if(s.equals("")){
		                	sb.append(","+"%&%");  
		                }else{
		                	sb.append(","+s);  
		                }

	                }
	                if(!sb.toString().equals("")){
	                	sb.deleteCharAt(0);
	                }
	                list.add(sb.toString());
	           }
	           conn.close();  	             
	       } catch (SQLException e) {  
	           System.out.println("error");  
	       }
	       return list;	       
	   }

	   public ResultSet find(String info){
		   conn = getConnection();
		   try {
			st = (Statement) conn.createStatement();
			
			ResultSet result = st.executeQuery(info);
			
			return result;
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
			}
	   }

	   public Connection getConnection() {  
	       Connection con = null;  
	       try {  
	           Class.forName("com.mysql.jdbc.Driver");
	             
	           con = DriverManager.getConnection(  
	                   "jdbc:mysql://localhost:3306/coursesystem", "root", "");
	       } catch (Exception e) {  
	           System.out.println("鏁版嵁搴撹繛鎺ュけ璐�" + e.getMessage());  
	       }  
	       return con; 
	   }  

}
