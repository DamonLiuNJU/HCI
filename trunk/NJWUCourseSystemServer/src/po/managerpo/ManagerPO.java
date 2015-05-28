package po.managerpo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ManagerPO implements Serializable{
	private String id;
	private String password;
	private String name;
	private String facultyID;
	private String contactInfo;

	//构造组
	public ManagerPO(){
	}
	
	//info not complete,for password updating
	public ManagerPO(String id,String password){
		this.id=id;
		this.password=password;
	}
	
	//info not complete,for contactInfo updating
	public ManagerPO(String id,String contactInfo,String differ){
		this.id=id;
		this.contactInfo=contactInfo;
	}
	
	public ManagerPO(String id, String password,String name,String facultyID,String contactInfo){
		this.id=id;
		this.password=password;
		this.name=name;
		this.facultyID=facultyID;
		this.contactInfo=contactInfo;
	}

	public void setID(String id){
		this.id=id;
	}
	public	void setPassword(String password){
		this.password=password;
	}
	public	void setName(String name){
		this.name=name;
	}
	
	public	void setFacultyID(String facultyID){
		this.facultyID=facultyID;
	}
	public	void setContactInfo(String contactInfo){
		this.contactInfo=contactInfo;
	}

	public String getID(){
		return id;
	}

	public String getPassword(){
		return password;
	}

	public String getName(){
		return name;
	}
	public String getFacultyID(){
		return facultyID;
	}
	public String getContactInfo(){
		return contactInfo;
	}


	//mysql
	public String getValues() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append(s(id) + "," + s(password)+","+s(name)+","+s(facultyID)+","+s(contactInfo));
		sb.append(")");
		return sb.toString();
	}

	public String s(Object o) {
		return "'" + o + "'";
	}
	
	public String toString(){
		return id+" "+password+" "+name+" "+facultyID+" "+contactInfo;
	}
}