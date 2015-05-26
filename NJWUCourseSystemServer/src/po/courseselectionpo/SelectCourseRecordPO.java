package po.courseselectionpo;

import java.io.Serializable;

public class SelectCourseRecordPO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -932851985625266066L;
	/**
	 * @param args
	 */
	String student_id;
	String course_id;
	String score;
	String stugrade;
	public SelectCourseRecordPO(String student_id,String course_id ,String score,String grade){
		this.student_id = student_id;
		this.course_id = course_id;
		this.score= score;
		this.stugrade = grade;
	}
	public String getStudent_ID(){
		return this.student_id;
	}
	public String getCourse_ID(){
		return this.course_id;
	}
	public String getScore(){
		return this.score;
	}
	public String getStuGrade(){
		return this.stugrade;
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

}
