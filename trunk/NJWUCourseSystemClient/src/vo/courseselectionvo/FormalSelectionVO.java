package vo.courseselectionvo;

public class FormalSelectionVO {
	String student_id;
	String course_id;
	int score;
	String stugrade;
	
	
	public FormalSelectionVO(String student_id,String course_id,int score,String stugrade) {		 
		this.student_id = student_id;
		this.course_id=course_id;
		this.score=score;
		this.stugrade=stugrade;
	}
	
	public String getStudentID(){
		return student_id;
	}
	public String getCourseID(){
		return course_id;
	}

	public String getStudentNumber() {
		return student_id;
	}

	public int getScore(){
		return score;
	}
	
	public String getStugrade(){
		return stugrade;
	}
	
	public void setID(String istudent_id) {
		this.student_id = istudent_id;
	}
	
	public void setStudnetID(String student_id){
		this.student_id = student_id;
	}
	public void setCourseID(String course_id){
		this.course_id= course_id;
	}
	
}
