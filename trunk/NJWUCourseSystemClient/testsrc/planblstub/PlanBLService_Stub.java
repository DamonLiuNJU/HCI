package planblstub;

import java.util.ArrayList;

import vo.PlanVO;
import businesslogicservice.planblservice.PlanBLService;

public class PlanBLService_Stub  implements PlanBLService{

	@Override
	public void importPlan(String fTeacherID, String conten) {
		// TODO Auto-generated method stub
		System.out.println("importPlan");
	}

	@Override
	public String getPlan(String facultyID) {
		// TODO Auto-generated method stub
		System.out.println("getPlan");
		return null;
	}

	@Override
	public ArrayList<PlanVO> getPlanList() {
		// TODO Auto-generated method stub
		System.out.println("getPlanList");
		return null;
	}

	@Override
	public String getOldPlan(String fTeacherID) {
		// TODO Auto-generated method stub
		System.out.println("getOldPlan");
		return null;
	}

	@Override
	public String getPlanByName(String facultyName) {
		// TODO Auto-generated method stub
		System.out.println("getPlanByName");
		return null;
	}

}
