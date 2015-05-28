package teachertest;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Test;

import data.TeacherData;

import po.TeacherPO;

public class TeacherInsertTest {

	@Test
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
			TeacherData teacherData = new TeacherData();
			boolean result = teacherData.insert(tp);
			
			assertTrue(result);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
