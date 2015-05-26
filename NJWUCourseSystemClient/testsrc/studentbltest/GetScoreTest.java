package studentbltest;

import org.junit.Test;

import businesslogic.studentbl.Student;
import businesslogicservice.studentblservice.StudentBLService;

public class GetScoreTest {

	@Test
	public void test() {
		StudentBLService s = new Student("121250089");
		String score = s.getScore("c2007");
		
		assert(score.compareToIgnoreCase("100")==0);
		
		
	}

}
