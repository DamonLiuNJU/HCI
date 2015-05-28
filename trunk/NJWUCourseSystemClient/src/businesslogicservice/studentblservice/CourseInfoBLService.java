package businesslogicservice.studentblservice;

import java.util.ArrayList;

import po.courseselectionpo.SelectCourseRecordPO;
import vo.studentvo.CourseInfoVO;

public interface CourseInfoBLService {

	public abstract String getTeacherNameByTeacherID(String teacher_id);

	public abstract String getTeacherNameByCourseID(String course_id);

	public abstract String getCourseName(String course_id);

	public abstract String getModule(String course_id);

	public abstract ArrayList<SelectCourseRecordPO> getStudentList(String course_id);

	public abstract CourseInfoVO getStudentList(CourseInfoVO vo);

	public abstract int getSelectedStudentNumber(String course_id);

	public abstract String getCourseGrade(String course_id);

	public abstract int getCredit(String course_id);

	//根据课程号获取
	public abstract String getFacultyIDByCourseID(String course_id);

	public abstract String getFacultyName(String facultyID);

}