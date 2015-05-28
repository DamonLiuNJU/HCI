package studentbltest;

import org.junit.Test;

import vo.coursevo.CourseListItemVO;
import businesslogic.coursebl.Course;
import businesslogic.planbl.Plan;

public class CourseInfoTest {

	@Test
	public void test() {
		CourseListItemVO cv = new Course().getCourseInfo("c0000");
//		CourseProcessVO cv = new Course().getCourseInfo(course_id);
		String facultyname = cv.getFacultyName();
		String facultyID = new Plan().getFacultyID(facultyname);
		String teachername = cv.getTeacherName();
		String teacherID = cv.getTeacherID();
		assert(facultyname.compareToIgnoreCase("软学院")==0);
		assert(facultyID.compareToIgnoreCase(facultyID)==0);
		assert(teacherID.compareToIgnoreCase("000000")==0);
		assert(teachername .compareToIgnoreCase("测试教师")==0);
	}

}
