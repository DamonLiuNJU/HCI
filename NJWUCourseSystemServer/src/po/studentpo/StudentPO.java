package po.studentpo;

import java.io.Serializable;

public class StudentPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
 
	public String student_id;
	public String password;
	public String name;
	public String faculty_id;
	public String grade;
	public String contackinfo; //email
	public String phone;
	public String homeaddress;
	public String mothername;
	public String motherphone;
	public String fathername;
	public String fatherphone;
	
	public StudentPO (String studentid){
		this.student_id = studentid;
	}
	
	public StudentPO() {
		// TODO 自动生成的构造函数存根
	}

	public String getStudentID(){
		return this.student_id;
	}
	public String getPassWord(){
		return this.password;
	}
	public String getName(){
		return this.name;
	}
	public String getFacultyID(){
		return this.faculty_id;
	}
	public String getGrade(){
		return this.grade;
	}
	public String getContactInfo(){
		return this.contackinfo;
	}
	public String getPhone(){
		return this.phone;
	}
	public String getHomeAddress(){
		return this.homeaddress;
	}
	public String getMotherName(){
		return this.mothername;
	}
	public String getMotherPhone(){
		return this.motherphone;
	}
	public String getFatherName(){
		return this.fathername;
	}
	public String getFatherPhone(){
		return this.fatherphone;
	}
	
	public void setStudentID(String student_id){
		this.student_id = student_id;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setFaculty_ID(String id){
		this.faculty_id= id;
	}
	public void setGrade(String grade){
		this.grade = grade;
	}
	public void setContactInfo(String contactinfo){
		this.contackinfo = contactinfo;
	}
	public void setPhone(String phone){
		this.phone= phone;
	}
	public void setHomeAddress(String address){
		this.homeaddress = address;
	}
	public void setMotherName(String mothername){
		this.mothername = mothername;
	}
	public void setMotherPhone(String phonenumber){
		this.motherphone = phonenumber;
	}
	public void setFatherName(String name){
		this.fathername = name;
	}
	public void setFatherPhone(String number){
		this.fatherphone= number;
	}
	
}
