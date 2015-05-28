package teachertest;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Test;

import po.TeacherPO;
import data.TeacherData;

public class TeacherFindTest {

	@Test
	public void test() {
//		fail("Not yet implemented");
		try {
			TeacherData teacherData = new TeacherData();
			
			TeacherPO tp = teacherData.find("050005");
			
//			assertTrue(tp.getName().equals("章鱼"));
			assertTrue(tp == null);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
