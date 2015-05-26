package businesslogic.coursebl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.coursepo.ApplyPO;
import rmiconnector.RemoteDataFactory;
import vo.coursevo.ApplyVO;
import businesslogic.planbl.Plan;
import businesslogic.teacherbl.Teacher;
import businesslogicservice.courseblservice.CourseApplyService;
import dataservice.coursedataservice.ApplyDataService;
public class CourseApply implements CourseApplyService{

	
	ApplyDataService ads;

	public static void main(String[] arg) {
		CourseApply ca = new CourseApply();
		System.out.println(ca.isNameUsable("sdfsdf"));
	}
 
	public CourseApply() {
		ads = (ApplyDataService) new RemoteDataFactory().getData("Apply");

	}

	@Override
	public void delete(String name) {
		try {
			ads.delete(name);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void apply(String name, String teacherID, String facultyID,
			String applyinfo) {
		// TODO Auto-generated method stub
		ApplyPO ap = new ApplyPO(name, teacherID, facultyID, applyinfo);
		try {
			ads.insert(ap);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<ApplyVO> getApplyList() {
		// TODO Auto-generated method stub
		ArrayList<ApplyPO> apList = new ArrayList<ApplyPO>();
		try {
			apList = ads.finds();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<ApplyVO> avList = new ArrayList<ApplyVO>();
		for (ApplyPO ap : apList) {
			avList.add(POToVO(ap));
		}
		return avList;
	}

	@Override
	public String getContent(String courseName) {
		// TODO Auto-generated method stub
		ArrayList<ApplyPO> apList = new ArrayList<ApplyPO>();
		try {
			apList = ads.finds();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (ApplyPO ap : apList) {
			if (ap.getCourseName().equals(courseName)) {
				return ap.getContent();
			}

		}
		return "";
	}

	@Override
	public boolean isNameUsable(String name) {
		ArrayList<ApplyPO> apList = new ArrayList<ApplyPO>();
		try {
			apList = ads.finds();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < apList.size(); i++) {
			if (apList.get(i).getCourseName().equals(name))
				return false;
		}
		return true;
	}

	ApplyVO POToVO(ApplyPO ap) {
		Plan plan = new Plan();
		Teacher teacher = new Teacher();
		return new ApplyVO(ap.getCourseName(), teacher.getTeacherName(ap
				.getTeacherID()), plan.getFacultyName(ap.getFacultyID()));
	}

}
