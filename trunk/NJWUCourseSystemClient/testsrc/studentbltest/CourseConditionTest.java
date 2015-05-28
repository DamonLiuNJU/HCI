package studentbltest;

import java.util.Vector;

import org.junit.Test;

import vo.studentvo.CourseConditionPanelVO;
import businesslogic.studentbl.CourseCondition;

public class CourseConditionTest {

	@Test
	public void test() {
		CourseCondition cc = new CourseCondition();
		CourseConditionPanelVO vo = cc.getCourseCondition("121250089", "001");
		Vector<Vector<String>> content = vo.getTableContent();
		assert(content.get(0).get(0).compareToIgnoreCase("c0001")==0);
//		fail("尚未实现");
	}

}
