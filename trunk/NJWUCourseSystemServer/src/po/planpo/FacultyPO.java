package po.planpo;

import java.io.Serializable;

public class FacultyPO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String facultyID;
	private String facultyName;
	private String plan;

	// 构造组
	public FacultyPO() {
		facultyID = null;
		facultyName = null;
		plan = null;
	}

	public FacultyPO(String facultyID, String facultyName, String plan) {
		this.facultyID = facultyID;
		this.facultyName = facultyName;
		this.plan = plan;
	}

	// set组
	public void setFacultyID(String id) {
		facultyID = id;
	}

	public void setFacultyName(String name) {
		facultyName = name;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	// get组
	public String getFacultyID() {
		return facultyID;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public String getPlan() {
		return plan;
	}

	// mysql

	public String getAttributeNames() {
		return "`id`,`name`,`plan`";
	}

	public String getValues() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append(s(facultyID) + "," + s(facultyName) + "," + s(plan));
		sb.append(")");
		return sb.toString();
	}

	public String s(Object o) {
		return "'" + o + "'";
	}
}
