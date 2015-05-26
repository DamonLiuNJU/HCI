package coursebltest;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import vo.coursevo.ApplyVO;
import businesslogic.coursebl.CourseApply;

public class ApplyTest {
	@Test
	public void test() {
	CourseApply ca=new CourseApply();
			
		ArrayList<ApplyVO> caList=ca.getApplyList();
		assertTrue(caList.size()==1);
		
		String content=ca.getContent("游动的光影世界");
		assertTrue(content.equals("计划开30个学时的课程，约占2学分。摄影实践，光与色彩的分析及运用。"));
		
		assertTrue(!ca.isNameUsable("游动的光影世界"));
		assertTrue(ca.isNameUsable(""));
		
		
}
}

