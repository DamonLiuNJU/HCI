package teachertest.teachertest;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import vo.teacherusedvo.TeacherScoreVO;
import businesslogic.teacherbl.Teacher;

public class ShowAverageScoreTest {

	@Test
	public void test() {
//		fail("Not yet implemented");
		Teacher teacher = new Teacher("2001001");
		ArrayList<TeacherScoreVO> tsvArray = new ArrayList<TeacherScoreVO>();
		tsvArray = teacher.showAverageCourseScore();
	
		assertTrue(tsvArray.size() == 1);
	}

}
