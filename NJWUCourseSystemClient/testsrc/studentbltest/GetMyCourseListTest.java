package studentbltest;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businesslogic.courseselectionbl.SelectCourseInfo;

public class GetMyCourseListTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		SelectCourseInfo mc = new SelectCourseInfo();
		ArrayList<String> courselist = mc.getAllMyCoursesInTemp("121250089");
		 
			boolean result = courselist.get(0).compareToIgnoreCase("2006")==0;
			boolean result2 = courselist.get(1).compareToIgnoreCase("0000")==0;
			boolean result3 = courselist.get(2).compareToIgnoreCase("0003")==0;
			assert(result);
			assert(result2);
			assert(result3);
		 
		
	}

}
