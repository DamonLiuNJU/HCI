package businesslogicservice.studentblservice;

import vo.studentvo.CourseConditionPanelVO;

public interface CourseConditionBLService {

	public abstract CourseConditionPanelVO getCourseConditionByModule(
			String student_id, String modulename);

	public abstract CourseConditionPanelVO isCurrentCreditsEnough(
			String student_id, String faculty_id);

	public abstract boolean canGraduate(String student_id);

}