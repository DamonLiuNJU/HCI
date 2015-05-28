package teachertest.teachertest;

import static org.junit.Assert.*;

import org.junit.Test;

import businesslogic.teacherbl.Teacher;

public class IsInFacultyTest {

	@Test
	public void test() {
//		fail("Not yet implemented");
		String id = "2001001";
		String faculty_id = "001";
		Teacher teacher = new Teacher();
		boolean result = teacher.isExistInFaculty(id, faculty_id);
		
		assertTrue(result);
	}

}
