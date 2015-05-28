package po;

import java.io.Serializable;
import java.util.ArrayList;

import po.coursepo.CoursePO;

public class TeacherPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name ;
	private String id ;
	private String password;
	private String facultyID;
	private String seniority;
	private String contactInfo;
	private ArrayList<CoursePO> courseArray;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFacultyID() {
		return facultyID;
	}
	public void setFacultyID(String faculty) {
		this.facultyID = faculty;
	}
	public String getSeniority() {
		return seniority;
	}
	public void setSeniority(String seniority) {
		this.seniority = seniority;
	}
	public String getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}
	public ArrayList<CoursePO> getCourseArray() {
		return courseArray;
	}
	public void setCourseArray(ArrayList<CoursePO> courseArray) {
		this.courseArray = courseArray;
	}
	

}
