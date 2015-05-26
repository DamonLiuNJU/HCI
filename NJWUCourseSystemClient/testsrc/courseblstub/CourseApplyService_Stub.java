package courseblstub;

import java.util.ArrayList;

import vo.coursevo.ApplyVO;
import businesslogicservice.courseblservice.CourseApplyService;

public class CourseApplyService_Stub implements CourseApplyService{

	@Override
	public ArrayList<ApplyVO> getApplyList() {
		// TODO Auto-generated method stub
		System.out.println(getApplyList());
		return null;
	}

	@Override
	public String getContent(String courseName) {
		// TODO Auto-generated method stub
		System.out.println("getContent");
		return null;
	}

	@Override
	public void delete(String name) {
		// TODO Auto-generated method stub
		System.out.println("delete");
	}

	@Override
	public void apply(String name, String teacherID, String facultyID,
			String apply) {
		// TODO Auto-generated method stub
		System.out.println("apply");
	}

	@Override
	public boolean isNameUsable(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
