package selectcoursetest;

import static org.junit.Assert.*;

import org.junit.Test;

import businesslogic.courseselectionbl.SelectStudentAlgorithm;

public class IsProcessedTest {

	@Test
	public void test() {
//		fail("Not yet implemented");
		String module = this.setup();
		
		SelectStudentAlgorithm test = new SelectStudentAlgorithm(module);
		boolean result = test.isProcessed();
		
		assertTrue(!result);
		
	}
	
	private String setup(){
		String courseModule = "选修课";
		return courseModule;
	}

}
