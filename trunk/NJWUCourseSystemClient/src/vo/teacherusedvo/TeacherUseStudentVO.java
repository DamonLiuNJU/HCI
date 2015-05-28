package vo.teacherusedvo;

import po.courseselectionpo.SelectCourseRecordPO;
import po.studentpo.StudentPO;
import businesslogic.planbl.Plan;
import businesslogic.studentbl.StudentInfo;

public class TeacherUseStudentVO {
	private String id;
	private String name;
	private String grade;
	private String score;
	private String course_id;
	private String contactInfo;
	private String faculty_name;
	private String phone;
	private String mother_name;
	private String mother_phone;
	private String father_name;
	private String father_phone;
	
	public TeacherUseStudentVO(SelectCourseRecordPO scPO , StudentPO sp){
		this.id = scPO.getStudent_ID();
		this.score = scPO.getScore();
		this.name = new StudentInfo().getStudentNameByID(scPO.getStudent_ID());
		this.grade = sp.getGrade();
		this.contactInfo = sp.getContactInfo();
		this.course_id = scPO.getCourse_ID();
		this.faculty_name = new Plan().getFacultyName(sp.getFacultyID());
		this.setPhone(sp.getPhone());
		this.setMother_name(sp.getMotherName());
		this.setMother_phone(sp.getMotherPhone());
		this.setFather_name(sp.getFatherName());
		this.setFather_phone(sp.getFatherPhone());
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
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getCourse_id() {
		return course_id;
	}

	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	public String getFaculty_name() {
		return faculty_name;
	}

	public void setFaculty_name(String faculty_name) {
		this.faculty_name = faculty_name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMother_name() {
		return mother_name;
	}

	public void setMother_name(String mother_name) {
		this.mother_name = mother_name;
	}

	public String getFather_name() {
		return father_name;
	}

	public void setFather_name(String father_name) {
		this.father_name = father_name;
	}

	public String getMother_phone() {
		return mother_phone;
	}

	public void setMother_phone(String mother_phone) {
		this.mother_phone = mother_phone;
	}

	public String getFather_phone() {
		return father_phone;
	}

	public void setFather_phone(String father_phone) {
		this.father_phone = father_phone;
	}
}
