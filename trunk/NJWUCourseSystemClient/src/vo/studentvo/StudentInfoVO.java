package vo.studentvo;

public class StudentInfoVO {

	/**
	 * @param args
	 */

	//	String id ;
//	char[] key;
	String id;
	String password;
	String name;
	String facultyID;
	String entranceyear;
	String contactinfo;
	String phone;
	String homeaddress;
	String mothername;
	String fathername;
	String motherphone;
	String fatherphone;
	public void setID(String id ){
		this.id = id;
	}
	public void setKey(String key){
		this.password= key;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setFacultyID(String id){
		this.facultyID = id;
	}
	public void setEntranceYear(String grade){
		this.entranceyear =  grade;
	}
	public void setContactInfo(String  info ){
		this.contactinfo = info;
	}
	public void setPhone(String  phone){
		this.phone = phone;
	}
	public void setMotherName(String name){
		this.mothername = name;
	}
	public void setMotherPhone(String number ){
		this.motherphone = number;
	}
	public void setFatherName(String name){
		this.fathername = name;
	}
	public void setFatherPhone(String  number){
		this.fatherphone =  number;
	}
	public void setHomeAddress(String address){
		this.homeaddress = address;
	}
	public String getHomeAddress(){
		return this.homeaddress;
	}
	public String getID(){
		return this.id;
	}
	public String getKey(){
		return this.password;
	}
	public String getName(){
		return this.name;
	}
	public String getFacultyID(){
		return this.facultyID;
	}
	public String getEntranceYear(){
		return this.entranceyear;
	}
	public String getContactInfo(){
		return this.contactinfo;
	}
	public String getPhone(){
		return this.phone;
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
	public void setKey(char[] password2) {
		// TODO 自动生成的方法存根
		this.password =  new String (password2);
	}
	
	

	
}
