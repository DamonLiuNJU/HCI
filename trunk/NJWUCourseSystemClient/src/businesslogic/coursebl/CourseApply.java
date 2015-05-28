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
	
	
	/**
	 * 审核后删除课程信息
	 * 	@param String courseName
	 * @return void
	 * 	@Override
	 */
	@Override
	public void delete(String name) {
		try {
			ads.delete(name);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 *教师申请课程
	 * 	@param String courseName,String teacherID,String facultyID,String applyInfo
	 * @return void
	 * 	@Override
	 */
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

	
	/**
	 * 得到申请课程的列表	
	 * 	@param 
	 * @return ArrayList<ApplyVO>
	 * 	@Override
	 */
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

	
	/**
	 * 得到申请课程的内容
	 * 	@param String courseName
	 * @return String
	 * 	@Override
	 */
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

	/**
	 * 检测课程名是否可用
	 * 	@param String courseName
	 * @return boolean
	 * 	@Override
	 */
	@Override
	public boolean isNameUsable(String name) {
		ArrayList<ApplyPO> apList = new ArrayList<ApplyPO>();
		try {
			apList = ads.finds();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//若申请的课程名有冲突返回false
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
