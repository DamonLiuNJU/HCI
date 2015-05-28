package vo;

import businesslogic.teacherbl.Teacher;


public class TeacherVO{
	private String id;
	private String name;
	private String seniority;
	private int courseCount;
	
	public TeacherVO(Teacher teacher){
		this.id = teacher.getID();
		this.name = teacher.getName();
		this.seniority = teacher.getSeniority();
		this.courseCount = teacher.getCourseArray().size();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSeniority() {
		return seniority;
	}
	public void setSeniority(String seniority) {
		this.seniority = seniority;
	}
	public int getCourseCount() {
		return courseCount;
	}
	public void setCourseCount(int courseCount) {
		this.courseCount = courseCount;
	}
}
