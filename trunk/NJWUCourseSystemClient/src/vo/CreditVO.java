package vo;

public class CreditVO {
	;
	int semesterindex;

	String course_id;
	String student_id;
	int score ;
	
	public CreditVO( ) {
		// TODO Auto-generated constructor stub
		 
	}

	public CreditVO(int semester, String studentnumber) {
		// TODO 自动生成的构造函数存根
		this.semesterindex =semester;
		this.student_id = studentnumber;
	}

	public void setScore(int score){
		this.score = score;
	}
	public void setStudent_ID(String student_id){
		this.student_id = student_id;
	}
	public void setCourse_ID(String course_id){
		this.course_id =course_id;
	}
	public String getStudentNumber(){
		return this.student_id;
	}
	public int getScore(){
		return this.score;
	}
	public int getSemesterIndex(){
		return this.semesterindex;
	}
	public String getCourse_ID(){
		return this.course_id;
	}
	public String getStudent_ID(){
		return this.student_id;
	}
}
