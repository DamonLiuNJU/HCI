package vo.coursevo;

import businesslogic.teacherbl.Teacher;

public class CourseListItemVO {
	String name;
	String cno;
	String teacherID;
	String place;
	String campus;
	String grade;
	String module;
	String credit;
	String period;
	String facultyName;
	String limit;
	String time;
	String require;
	String info;

	public CourseListItemVO(String name, String cno, String teacherID,
			String place, String campus, String grade, String module,
			String credit, String period, String limit, String time,
			String require, String info, String facultyName) {

		this.name = name;
		this.cno = cno;
		this.teacherID = teacherID;
		this.place = place;
		this.campus = campus;
		this.grade = grade;
		this.module = module;
		this.credit = credit;
		this.period = period;
		this.limit = limit;
		this.time = time;
		this.require = require;
		this.facultyName = facultyName;
		this.info = info;
	}

	// get组

	public String getName() {
		return name;
	}

	public String getCno() {
		return cno;
	}

	public String getTeacherID() {
		return teacherID;
	}
	
	public String getTeacherName(){
		return new Teacher().getTeacherName(teacherID);
	}

	public String getPlace() {
		return place;
	}

	public String getCampus() {
		return campus;
	}

	public String getGrade() {
		return grade;
	}

	public String getModule() {
		return module;
	}

	public String getPeriod() {
		return period;
	}

	public String getLimit() {
		return limit;
	}

	public String getRequire() {
		return require;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public String getInfo() {
		return info;
	}

	public String getCredit() {
		return credit;
	}

	public String getTime() {
		return time;
	}
	
	// set 组
	public void setName(String name) {
		this.name = name;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public void setTeacherID(String teacherID) {
		this.teacherID = teacherID;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public void set(String grade) {
		this.grade = grade;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public void setRequire(String require) {
		this.require = require;
	}

	public void setFTeacherID(String facultyName) {
		this.facultyName= facultyName;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public void setTime(String time) {
		this.time = time;
	}

}