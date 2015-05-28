package selectcoursetest;

import static org.junit.Assert.*;

import org.junit.Test;

import vo.coursevo.CourseListItemVO;

import businesslogic.courseselectionbl.CourseSelection;

public class IsSelectedTest {

	String faculty_id;
	CourseListItemVO cliv;
	
	@Test
	public void test() {
//		fail("Not yet implemented");
		
		this.setup();
		
		CourseSelection cs = new CourseSelection();
		boolean result = cs.isSelected(faculty_id, cliv);
		
		assertTrue(!result);
	}

	private void setup(){
		faculty_id = "001";
		cliv = new CourseListItemVO("软件工程与计算", "c000101", "2001001", "仙二304", "仙林", "大一下", "必修课", "3", "1-17", "-1", "", "", "", "软件学院");
	}
	
}
