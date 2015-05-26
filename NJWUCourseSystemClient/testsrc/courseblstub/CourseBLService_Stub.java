package courseblstub;

import java.util.ArrayList;

import vo.coursevo.CourseListItemVO;
import vo.coursevo.CourseStatVO;
import businesslogicservice.courseblservice.CourseBLService;

public class CourseBLService_Stub  implements CourseBLService{

	@Override
	public void addCourse(CourseListItemVO cpv) {
		// TODO Auto-generated method stub
		System.out.println("addCourse");
	}

	@Override
	public boolean isCourseIDUsable(String id) {
		// TODO Auto-generated method stub
		System.out.println("testCourseID");
		return true;
	}

	@Override
	public void modifyInfo(CourseListItemVO cpv) {
		// TODO Auto-generated method stub
		System.out.println("update");
		
	}

	@Override
	public CourseListItemVO getCourseInfo(String courseID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSpecificInfo(String courseID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CourseStatVO> getModuleStatics(String facultyName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCourse(String courseID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getModule(String courseID) {
		// TODO Auto-generated method stub
		return null;
	}

}
