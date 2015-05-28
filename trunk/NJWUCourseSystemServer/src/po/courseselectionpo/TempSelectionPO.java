package po.courseselectionpo;

import java.io.Serializable;

public class TempSelectionPO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1191925168216659029L;
	/**
	 * @param args
	 */

	String student_id;
	String course_id;
	String score;
	String stugrade ; 
	
	public TempSelectionPO(String student_id,String course_id ,String score,String grade){
		this.student_id = student_id;
		this.course_id = course_id;
		this.score= score;
		this.stugrade = grade ; 
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
	
	public String toString(){
		return student_id+" "+course_id+" "+score+" "+stugrade;
	}
}
