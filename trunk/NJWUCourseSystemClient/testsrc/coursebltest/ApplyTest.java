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
		assertTrue(caList.size()==3);
		
		String content=ca.getContent("宋词");
		assertTrue(content.equals("深入研究20首具有代表意义的宋词作品，提高大学生基本的传统诗词修养。"));
		
		assertTrue(!ca.isNameUsable("宋词"));
		assertTrue(ca.isNameUsable(""));
		
		
}
}

