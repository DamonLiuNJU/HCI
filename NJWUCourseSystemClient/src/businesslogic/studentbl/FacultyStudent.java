package businesslogic.studentbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.studentpo.StudentPO;
import rmiconnector.RemoteDataFactory;
import businesslogic.planbl.Plan;
import businesslogicservice.studentblservice.FacultyStudentBLService;
import dataservice.studentdataservice.StudentDataService;

public class FacultyStudent implements FacultyStudentBLService {

	/**
	 * @param args
	 */
	
	RemoteDataFactory factory = new RemoteDataFactory();
	StudentDataService data = (StudentDataService)factory.getData("Student");
	
	/* （非 Javadoc）
	 * @see businesslogic.studentbl.FacultyStudentBLService#getAllStudentsByFacID(java.lang.String)
	 */
	@Override
	public ArrayList<StudentPO> getAllStudentsByFacID(String faculty_id){
		StudentPO po = new StudentPO();
		po.setFaculty_ID(faculty_id); //将条件放进去
		ArrayList<StudentPO> result = null;
		try {
			result = data.getStudentListByFacultyID(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return result;
	}
	
	/* （非 Javadoc）
	 * @see businesslogic.studentbl.FacultyStudentBLService#getAllStudentsByFacName(java.lang.String)
	 */
	@Override
	public ArrayList<StudentPO> getAllStudentsByFacName(String facultyName){
		StudentPO po = new StudentPO();
		String faculty_id = new Plan().getFacultyID(facultyName);
		po.setFaculty_ID(faculty_id); //将条件放进去
		ArrayList<StudentPO> result = null;
		try {
			result = data.getStudentListByFacultyID(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return result;
	}

}
