package vo.teacherusedvo;

/*
 * 显示一个教师的每门课程的学生的平均成绩
 * courseID,courseName,avgScore
 * cbb revise
 */
public class TeacherScoreVO {
	private String courseID;
	private String courseName;
	private double credit;
	
	public TeacherScoreVO(String courseID , String courseName , double credit){
		this.courseID = courseID;
		this.courseName = courseName;
		this.credit = credit;
	}
	
	public String getCourseID(){
		return courseID;
	}
	
	public String getCourseName(){
		return courseName;
	}
	
	public double getCredit(){
		return credit;
	}
	
}
