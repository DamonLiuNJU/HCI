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

	@Override
	public ArrayList<CourseStatVO> getMyFacultyStatics(String fTeacherID) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ArrayList<String> getMyFacultyStaticsCredit(String iD) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean testStatus(String commend) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public boolean testIfChoose(String iD, String courseID) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public boolean testTeacher(String id, String teacherID) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public void addCourseforStudent(String iD, String courseID) {
		// TODO 自动生成的方法存根
		
	}

}
