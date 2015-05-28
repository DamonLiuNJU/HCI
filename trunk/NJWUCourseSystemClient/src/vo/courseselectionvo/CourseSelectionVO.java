package vo.courseselectionvo;

public class CourseSelectionVO{	
	String student_id;
	String course_id;
	
	public CourseSelectionVO(String studentnumber) {		 
		this.student_id = studentnumber;
	}
	
	public CourseSelectionVO(String student_id,String course_id) {		 
		this.student_id = student_id;
		this.course_id=course_id;
	}
	
	public void setStudnetID(String student_id){
		this.student_id = student_id;
	}
	public void setCourseID(String course_id){
		this.course_id= course_id;
	}
	
	public String getStudentID(){
		return this.student_id;
	}
	public String getCourseID(){
		return this.course_id;
	}

	public String getStudentNumber() {
		return this.student_id;
	}

	public void setID(String istudent_id) {
		this.student_id = istudent_id;
	}
}
