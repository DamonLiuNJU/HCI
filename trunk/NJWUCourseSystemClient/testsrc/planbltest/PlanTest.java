package planbltest;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import vo.PlanVO;
import businesslogic.planbl.Plan;

public class PlanTest {

	
	@Test
	public void test() {
		Plan plan=new Plan();
		String content=plan.getPlan("001");
		assertTrue(content.equals("软件学院的教学计划"));
		
		ArrayList<PlanVO> ppList=plan.getPlanList();
		assertTrue(ppList.size()==20);
		
		
	}
}
