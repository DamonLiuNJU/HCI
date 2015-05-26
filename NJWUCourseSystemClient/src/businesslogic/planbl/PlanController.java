package businesslogic.planbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.PlanVO;
import businesslogic.managerbl.Faculty;
import businesslogicservice.planblservice.PlanBLService;

		public class PlanController implements PlanBLService{
								Plan plan;	
		@Override
		public void importPlan(String fTeacherID,String content) {
		// TODO Auto-generated method stub
							Faculty faculty=new Faculty(fTeacherID);	
							plan=new Plan(faculty.getFacultyID(),content);
							try {
										plan.importPlan();
							} catch (RemoteException e) {
								// TODO Auto-generated catch block
										e.printStackTrace();
							}
		}
		public String  getPlan(String facultyID) {
							// TODO Auto-generated method stub
							plan  = new Plan();
							return plan.getPlan(facultyID);
		}
		@Override
		public ArrayList<PlanVO> getPlanList() {
							plan=new Plan();
							return plan.getPlanList();
		}
		@Override
		public String getOldPlan(String fTeacherID) {
							Faculty faculty=new Faculty(fTeacherID);
							Plan plan=new Plan();
							return plan.getPlan(faculty.getFacultyID());
		}
		
		public String getPlanByName(String facultyName) {
			// TODO Auto-generated method stub
			Plan plan=new Plan();
			return plan.getPlan(plan.getFacultyID(facultyName));
		}

}
