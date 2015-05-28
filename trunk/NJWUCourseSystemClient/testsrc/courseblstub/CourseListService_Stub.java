package courseblstub;

import java.util.ArrayList;

import vo.coursevo.CourseListItemVO;
import businesslogicservice.courseblservice.CourseListService;

public class CourseListService_Stub  implements CourseListService{

	@Override
	public ArrayList<CourseListItemVO> getSearchList(String cno, String cName,
			String teacherName, String facultyName, String grade) {
		// TODO Auto-generated method stub
		System.out.println("getSearchList");
		return null;
	}

	@Override
	public ArrayList<CourseListItemVO> getTeachList(String teacherID) {
		// TODO Auto-generated method stub
		System.out.println("getTeachList");
		return null;
	}

	@Override
	public ArrayList<CourseListItemVO> getCourseListByModule(String module) {
		// TODO Auto-generated method stub
		System.out.println("getCourseListByModule");
		return null;
	}

	@Override
	public ArrayList<CourseListItemVO> getSearchList(String campus,
			String grade, String facultyName) {
		// TODO Auto-generated method stub
		System.out.println("getSearchList");
		return null;
	}

	@Override
	public ArrayList<CourseListItemVO> getCourseListByChooseList(
			ArrayList<String> cno) {
		// TODO Auto-generated method stub
		System.out.println("getCourseListByChooseList");
		return null;
	}

	@Override
	public ArrayList<CourseListItemVO> getComplusoryCourseList(
			String facultyName) {
		// TODO Auto-generated method stub
		return null;
	}

}
