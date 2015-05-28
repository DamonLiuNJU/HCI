package admintest;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Test;

import data.managerdata.AdminData;

import po.TeacherPO;

public class AdminDataRegisterTest {

	@Test
	/*
	 * 该类用于测试是否能成功注册一个用户
	 */
	public void test() {
//		fail("Not yet implemented");
		TeacherPO tp = new TeacherPO();
		tp.setName("刘钦");
		tp.setId("12580");
		tp.setPassword("123456");
		tp.setFacultyID("250");
		tp.setSeniority("讲师");
		tp.setContactInfo("liuqin@software.nju.edu.cn");
		
		try {
			AdminData adminData = new AdminData();
			
			boolean result = adminData.registerUser(tp, "Teacher");
			
			assertTrue(result);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
