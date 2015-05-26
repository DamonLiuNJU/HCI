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


}
