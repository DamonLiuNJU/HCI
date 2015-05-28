package teachertest;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Test;

import data.TeacherData;

import po.TeacherPO;

public class TeacherUpdateTest {

	@Test
	/*
	 * 该方法测试teacherData类中的update方法
	 * 测试报告：
	 * 		使用第一个PO，id“050001”已经存在
	 * 		测试结果：正常。
	 * 		使用第二个PO，id“050008”不存在
	 * 		测试结果：提示update成功，数据库插入了一行null数据的记录
	 * 		（优化方案：DataBaseHelper中的insert，delete，update有boolean返回值）
	 */
	public void test() {
//		fail("Not yet implemented");
		TeacherPO tp = new TeacherPO();
		tp.setId("050001");
		tp.setName("Name");
		tp.setPassword("password");
		tp.setFacultyID("001");
		tp.setSeniority("教授");
		tp.setContactInfo("email:zy@nju.edu.cn");
		
		TeacherPO tp2 = new TeacherPO();
		tp.setId("050005");
		tp.setName("Name");
		tp.setPassword("password");
		tp.setFacultyID("001");
		tp.setSeniority("教授");
		tp.setContactInfo("email:zy@nju.edu.cn");
		
		try {
			TeacherData teacherData = new TeacherData();
			boolean result = teacherData.update(tp2);
			
			assertTrue(result);
			
			result = teacherData.update(tp);
			assertTrue(result);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
