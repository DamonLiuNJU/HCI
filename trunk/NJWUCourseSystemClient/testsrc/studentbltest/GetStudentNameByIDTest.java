package studentbltest;

import org.junit.Before;
import org.junit.Test;

import businesslogic.studentbl.StudentInfo;
import businesslogicservice.studentblservice.StudentInfoBLService;

public class GetStudentNameByIDTest {
	StudentInfoBLService si ;
	@Before
	public void setUp() throws Exception {
		si = new StudentInfo();
	}

	@Test
	public void test() {
		String name = si.getStudentNameByID("121250089");
		assert(name.compareToIgnoreCase("刘威廷")==0);
	}

}
