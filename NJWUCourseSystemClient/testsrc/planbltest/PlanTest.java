package planbltest;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import vo.PlanVO;
import businesslogic.planbl.Plan;

public class PlanTest {

	
	
	public static void main(String arg[]){
		Plan plan=new Plan();
		String content=plan.getPlan("001");
		assertTrue(content.equals("软件学院的教学计划"));
		
		ArrayList<PlanVO> ppList=plan.getPlanList();
		assertTrue(ppList.size()==4);
		
		
	}
}
