package businesslogicservice.studentblservice;

import vo.CreditVO;
import vo.courseselectionvo.CourseSelectionVO;

public interface StudentBLService {

	public abstract String getScore(String course_id);

	public abstract double getGPA(CreditVO cv);

	public abstract int getAchievedCredits(CourseSelectionVO sv);

	public abstract int getTotalCredit(String studentNo);

	public abstract double getTotalGPA();

}