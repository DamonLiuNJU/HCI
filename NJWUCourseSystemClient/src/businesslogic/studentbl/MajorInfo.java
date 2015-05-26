package businesslogic.studentbl;

import java.util.ArrayList;

import vo.PlanVO;
import businesslogic.planbl.Plan;
import businesslogicservice.studentblservice.MajorInfoBLService;

public class MajorInfo implements MajorInfoBLService {

	/**
	 * @param args
	 */
	public ArrayList<String> getMajorList(){
		ArrayList<String> result = new ArrayList<String>();
		Plan p = new Plan();
		ArrayList<PlanVO> list = p.getPlanList();
		for(PlanVO vo : list){
			String name = vo.getName();
			result.add(name);
		}
		
		return result;
	}


}
