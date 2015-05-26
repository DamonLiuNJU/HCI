package studentbltest;

import org.junit.Test;

import businesslogic.courseselectionbl.CourseSelection;

public class RemoveCourseTest {

	@Test
	public void test() {
		CourseSelection si = new CourseSelection();
		boolean removeresult = si.removeCourse("121250089", "");
		assert(removeresult);
	}

}
