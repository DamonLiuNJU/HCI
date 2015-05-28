package coursebltest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import vo.coursevo.CourseListItemVO;
import businesslogic.coursebl.Course;
public class CourseTest {
	@Test
	public void test() {	
	Course course=new Course();
	
		assertTrue(course.isCourseIDUsable("c000101"));
		assertTrue(!course.isCourseIDUsable(""));

		CourseListItemVO cpv=course.getCourseInfo("c000101");
		assertTrue(cpv.getName().equals("软件工程与计算"));
		
		String specificInfo=course.getSpecificInfo("c000101");
		assertTrue(specificInfo.equals("软件工程与计算的详细信息"));
		
		assertTrue(course.getModule("c000101").equals("必修课"));		
	}
}

