package businesslogicservice.courseblservice;

import java.util.ArrayList;

import vo.coursevo.CourseListItemVO;

public interface CourseListService {
	// cno cName tn facultyID  grade——》"编号","课程名","所属院系","年级","所属模块"
		public ArrayList<CourseListItemVO> getSearchList(String cno, String cName,
																									String teacherName, String facultyName,
																									String grade);
		//teacher:得到任课列表	
		public ArrayList<CourseListItemVO> getTeachList(String teacherID);
		//student:根据模块得到课程列表 module为“”时代表无限制
		public ArrayList<CourseListItemVO> getCourseListByModule(String module) ;
		public ArrayList<CourseListItemVO> getComplusoryCourseList(String facultyName);
		//student:campus&grsde&facultyName（无条件应为 ""）---------->课程列表
		public ArrayList<CourseListItemVO> getSearchList(String campus,
																									String grade, 
																									String facultyName);
		//student :通过学生选课的列表得到学生所选课的信息列表
		public ArrayList<CourseListItemVO> getCourseListByChooseList(ArrayList<String> cno);
}
