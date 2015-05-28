package vo.studentvo;

import java.util.Vector;

public class CourseInfoVO {

	/**
	 * @param args
	 */
	
	String student_id;
	Vector<Vector<String>> content ;
	String course_id;
	public void setContent(Vector<Vector<String>> content){
		this.content = content;
	}
	public void setStudentID(String id){
		this.student_id = id;
	}
	public Vector<Vector<String>>  getContent( ){
		return this.content;
	}
	public String getStudentID( ){
		return this.student_id;
	}
	public void setCourseID(String id){
		this.course_id= id;
	}
	public String getCourseID(){
		return this.course_id;
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

}
