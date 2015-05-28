package businesslogicservice.studentblservice;

import java.util.ArrayList;

import po.studentpo.StudentPO;

public interface FacultyStudentBLService {

	public abstract ArrayList<StudentPO> getAllStudentsByFacID(String faculty_id,String grade);

	public abstract ArrayList<StudentPO> getAllStudentsByFacName(
			String facultyName,String grade);
	public abstract ArrayList<StudentPO> getAllStudentByFacName(String facname);

}