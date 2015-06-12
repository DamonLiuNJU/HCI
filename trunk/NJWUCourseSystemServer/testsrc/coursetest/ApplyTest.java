package coursetest;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

import org.junit.Test;

import po.coursepo.ApplyPO;
import static org.junit.Assert.*;
import data.coursedata.ApplyData;

public class ApplyTest {
	
//	@Test
//	public void test() throws RemoteException{
//		ApplyData ad=new ApplyData();
//		ArrayList<ApplyPO> apList=ad.finds();
//		assertTrue(apList.size()==1);
//		
//		String  delete=ad.delete("");
//		assertTrue(delete==null);
//		
//	}
	
	 public static Connection getConnection() {  
	       Connection con = null;  
	       try {  
	           Class.forName("com.mysql.jdbc.Driver");
	             
	           con = DriverManager.getConnection(  
	                   "jdbc:mysql://localhost:3306/coursesystem"+"?useUnicode=true&characterEncoding=UTF-8", "root", "root");
	       } catch (Exception e) {  
	           System.out.println("数据库连接失败" + e.getMessage());  
	       }  
	       return con; 
	   }  
	 
	 public static void main(String args[]){
		 Connection con = ApplyTest.getConnection();
		 
	 }
		
}


