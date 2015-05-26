package businesslogicservice.studentblservice;

import java.util.ArrayList;

import po.studentpo.StudentPO;

public interface FacultyStudentBLService {

	public abstract ArrayList<StudentPO> getAllStudentsByFacID(String faculty_id);

	public abstract ArrayList<StudentPO> getAllStudentsByFacName(
			String facultyName);

}