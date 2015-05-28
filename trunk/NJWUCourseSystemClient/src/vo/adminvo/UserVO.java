package vo.adminvo;

import po.TeacherPO;
import po.managerpo.ManagerPO;
import po.studentpo.StudentPO;

public class UserVO {
	private String type ;
	private String id;
	private String name;
	private String faculty_name;
	private String faculty_id;
	private String seniority;
	private String grade;
	private String contactInfo;
	private String password;
	private String motherName;
	private String motherPhone;
	private String fatherName;
	private String fatherPhone;
	private String homeAddress;
	private String phone;
	
	public UserVO(String type){
		this.type = type;
	}
	
	public UserVO(Object po){
		if(po instanceof TeacherPO){//用teacherPO初始化一个uservo
			this.type = "Teacher";
			TeacherPO tp = (TeacherPO) po;
			this.id = tp.getId();
			this.name = tp.getName();
			this.faculty_id = tp.getFacultyID();
			this.password = tp.getPassword();
			this.contactInfo = tp.getContactInfo();
			this.seniority = tp.getSeniority();
		}
		else if(po instanceof StudentPO){//用studentPO初始化一个uservo
			this.type = "Student";
			StudentPO sp = (StudentPO) po;
			this.id = sp.getStudentID();
			this.name = sp.getName();
			this.password = sp.getPassWord();
			this.faculty_id = sp.getFacultyID();
			this.grade = sp.getGrade();
			this.contactInfo = sp.getContactInfo();
			this.phone = sp.getPhone();
			this.homeAddress = sp.getHomeAddress();
			this.motherName = sp.getMotherName();
			this.motherPhone = sp.getMotherPhone();
			this.fatherName = sp.getFatherName();
			this.fatherPhone = sp.getFatherPhone();
			
		}
		else if(po instanceof ManagerPO){//用managerPO初始化一个uservo
			this.type = "Manager";
			ManagerPO mp = (ManagerPO) po;
			this.id = mp.getID();
			this.name = mp.getName();
			this.password = mp.getPassword();
			this.faculty_id = mp.getFacultyID();
			this.contactInfo = mp.getContactInfo();
		}
	}

	public String getType(){
		return type;
	}
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
	public String getFaculty_id() {
		return faculty_id;
	}
	public void setFaculty_id(String faculty_id) {
		this.faculty_id = faculty_id;
	}
	public String getFaculty_name() {
		return faculty_name;
	}
	public void setFaculty_name(String faculty_name) {
		this.faculty_name = faculty_name;
	}
	public String getSeniority() {
		return seniority;
	}
	public void setSeniority(String seniority) {
		this.seniority = seniority;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getMotherPhone() {
		return motherPhone;
	}

	public void setMotherPhone(String motherPhone) {
		this.motherPhone = motherPhone;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getFatherPhone() {
		return fatherPhone;
	}

	public void setFatherPhone(String fatherPhone) {
		this.fatherPhone = fatherPhone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	
	
}
