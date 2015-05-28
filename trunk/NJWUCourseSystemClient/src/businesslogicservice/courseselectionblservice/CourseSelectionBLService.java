package businesslogicservice.courseselectionblservice;

import vo.courseselectionvo.CourseSelectionVO;
import vo.coursevo.CourseListItemVO;

public interface CourseSelectionBLService {
	public void addCourseSelection(String student_id, String course_id);
	public void transferFromTempSelection();
	public boolean addCourseNo(String student_id, String coursenumber);
	public boolean removeCourse(String student_id, String coursenumber);
	public void addPECourseNo(String student_id, String course_id);
	public boolean seletedPE(String student_id);
	public String selectCourse(CourseSelectionVO sv);
	public boolean isSelected(String faculty_id ,CourseListItemVO cliv );
}
