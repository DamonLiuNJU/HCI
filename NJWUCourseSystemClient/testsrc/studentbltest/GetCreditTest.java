package studentbltest;

import org.junit.Test;

import businesslogic.studentbl.Student;
import businesslogicservice.studentblservice.StudentBLService;

public class GetCreditTest {

	@Test
	public void test() {
		StudentBLService s = new Student("121250089");
		int result = s.getTotalCredit("121250089");
		assert(result == 3);
	}

}
