package vo.teacherusedvo;


import po.coursepo.CoursePO;

public class TeacherCourseVO {
	private String courseName;
	private String courseID;
	private String location;
	private String classTime;

	public TeacherCourseVO(CoursePO cp) {
		this.courseID = cp.getID();
		this.courseName = cp.getName();
		this.location = cp.getPlace();
		this.classTime = cp.getTime();
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public String getLocation() {
		return location;
	}

	public String getClassTime() {
		return classTime;
	}
	
	
}
