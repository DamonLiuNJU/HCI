package teachertest.teacherlisttest;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import businesslogic.teacherbl.TeacherList;

public class TeacherListShowDetailInfoTest {

	@Test
	public void test() {
//		fail("Not yet implemented");
		TeacherList list = new TeacherList();
		String faculty_name = "软件学院";
		ArrayList<String> info = list.showDetailedInfo(faculty_name);
		
		assertTrue(info.get(0).equals("全院教师人数： 2"));
	}

}
