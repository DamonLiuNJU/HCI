package businesslogicservice.courseselectionblservice;

import java.util.ArrayList;
import java.util.Vector;

import businesslogic.utilitybl.CourseModule;

import po.courseselectionpo.SelectCourseRecordPO;

public interface SelectCourseInfoBLService {
	public Vector<Vector<String>> getSelectedCourse(String student_id,
			String mode);
	public ArrayList<String> getAllMyCoursesInTemp(String student_id);
	public String getTheGradeWhenISelectedThisCourseForQuit(String student_id,
			String course_id);
	public boolean courseExist(String student_id, String course_id);
	public ArrayList<String> getAllMyCoursesInSelect(String student_id);
	public ArrayList<SelectCourseRecordPO> getStudentList(String course_id);
	public String getScore(String student_id,String course_id);
	public String getTheGradeWhenISelectedThisCourse(String student_id,
			String course_id);
	public boolean isCurrentTimeValidForSelectionCourse(CourseModule c);
	public boolean isCurrentTimeSpecial(CourseModule module);
			
}
