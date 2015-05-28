package coursebltest;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import po.coursepo.CoursePO;

import vo.coursevo.CourseListItemVO;

import businesslogic.coursebl.Course;
import businesslogic.coursebl.Transform;

public class Course_unit_test {
	@Test
	public void test(){
		Course course=new Course();
		CoursePO cp1=new CoursePO("c0001" ,"name1","2001001","campus","grade","place","time","period",
				"require","001","credit","module","limit","specificInfo");
		CourseListItemVO cliv=Transform.POToListItemVO(cp1);
		

	}
}
