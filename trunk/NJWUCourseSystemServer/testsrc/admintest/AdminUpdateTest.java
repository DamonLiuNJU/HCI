package admintest;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Test;

import data.managerdata.AdminData;
import dataservice.managerdataservice.AdminDataService;

import po.TeacherPO;

public class AdminUpdateTest {

	@Test
	/*
	 * 该方法测试adminData中的update方法
	 * （只能update已存在的id对应的对象）
	 * 测试报告：
	 * 		teacher测试正常。
	 * 
	 */
	public void test() {
//		fail("Not yet implemented");
		TeacherPO tp = new TeacherPO();
		tp.setId("2005001");
		tp.setName("刘钦");
		tp.setPassword("password");
		tp.setSeniority("教授");
		tp.setFacultyID("005");
		tp.setContactInfo("liuqin@software.nju.edu.cn");
		
		try {
			AdminDataService adminData = new AdminData();
			
			boolean result =false ;
			result = adminData.updateUser(tp, "Teacher");
			
			assertTrue(result);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
