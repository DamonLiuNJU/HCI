package teachertest.teacherlisttest;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import vo.TeacherVO;
import businesslogic.teacherbl.TeacherList;

public class TeacherListGetTeacherListTest {

	@Test
	public void test() {
//		fail("Not yet implemented");
		TeacherList list = new TeacherList();
		String faculty_name = "软件学院";
		ArrayList<TeacherVO> tvArray = list.getTeacherList(faculty_name);
		
		assertTrue(tvArray.get(0).getId().equals("2001001"));
	}

}
