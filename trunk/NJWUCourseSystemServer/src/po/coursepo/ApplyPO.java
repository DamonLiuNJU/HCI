package po.coursepo;

import java.io.Serializable;

public class ApplyPO implements Serializable {
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private String courseName;
	private String teacherID;
	private String facultyID;
	private String content;

	// 构造组
	public ApplyPO(String name, String tea, String fac, String con) {
		teacherID = tea;
		facultyID = fac;
		content = con;
		courseName = name;
	}

	public ApplyPO() {
		teacherID = null;
		facultyID = null;
		content = null;
		courseName = null;

	}

	// set组
	public void setTeacherID(String teacherID) {
		this.teacherID = teacherID;

	}

	public void setFacultyID(String facultyID) {
		this.facultyID = facultyID;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public void setContent(String content) {
		this.content = content;
	}

	// get组
	public String getTeacherID() {
		return teacherID;
	}

	public String getFacultyID() {
		return facultyID;
	}

	public String getCourseName() {
		return courseName;
	}

	public String getContent() {
		return content;
	}

	// mysql
	public String getAttributeNames() {
		return "`name`,`teacherID`,`facultyID`,`applyinfo`";
	}

	public String getValues() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append(s(courseName) + "," + s(teacherID) + "," + s(facultyID) + ","
				+ s(content));
		sb.append(")");
		return sb.toString();
	}

	public String s(Object o) {
		return "'" + o + "'";
	}
}
