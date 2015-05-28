package businesslogicservice.courseblservice;

import java.util.ArrayList;

import vo.coursevo.CourseListItemVO;
import vo.coursevo.CourseStatVO;

public interface CourseBLService {
		//faculty:添加课程
		public void addCourse(CourseListItemVO cpv);
		//faculty:测试所发布的课程id是否可用
		public boolean isCourseIDUsable(String id);
		//faculty:修改课程后对po更新
		public void modifyInfo(CourseListItemVO cpv);
		//faculty:修改课程之前得到课程信息
		public CourseListItemVO  getCourseInfo(String courseID);
		//得到课程的详细信息
		public String getSpecificInfo(String courseID);
		//各模块课程统计信息
		public  ArrayList<CourseStatVO> getModuleStatics(String facultyName);
		//删除课程
		public void deleteCourse(String courseID);
		//获得课程模块
		public String getModule(String courseID);
		//得到本院系的课程统计信息
		public ArrayList<CourseStatVO> getMyFacultyStatics(String fTeacherID);
		//得到本院系已发布课程的 学分统计信息
		public ArrayList<String> getMyFacultyStaticsCredit(String iD);
		//检测发布教学计划和发布课程是否在限制状态之内
		boolean testStatus(String commend);
		//检测某课程某院学生是否统一添加
		boolean testIfChoose(String iD, String courseID);
		//发布课程时检测教师号是否存在并属于本院
		boolean testTeacher(String id, String teacherID);
		//院系教务员给本院学生统一添加课程
		void addCourseforStudent(String iD, String courseID);
		

}
