package businesslogic.studentbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.studentpo.StudentPO;
import rmiconnector.RemoteDataFactory;
import businesslogic.planbl.Plan;
import businesslogicservice.studentblservice.FacultyStudentBLService;
import dataservice.studentdataservice.StudentDataService;
/**
 * 
 * @author LiuWT-ASUS
 * 在院系的基础上的学生的操作。
 *
 */
public class FacultyStudent implements FacultyStudentBLService {

	/**
	 * @param args
	 */
	
	RemoteDataFactory factory = new RemoteDataFactory();
	StudentDataService data = (StudentDataService)factory.getData("Student");
	
	/* （非 Javadoc）
	 * @see businesslogic.studentbl.FacultyStudentBLService#getAllStudentsByFacID(java.lang.String)
	 */
	
	public ArrayList<StudentPO> getAllStudentsByFacID(String faculty_id,String grade){
		StudentPO po = new StudentPO();
		po.setFaculty_ID(faculty_id); //将条件放进去
		po.setGrade(grade);
		ArrayList<StudentPO> result = null;
		try {
			result = data.getStudentListByFacultyID(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		ArrayList<StudentPO> finalresult =  new ArrayList<StudentPO>();
		for(StudentPO temppo : result){
			String tempgrade = temppo.getGrade();
			if(tempgrade.compareToIgnoreCase(grade)==0){
				finalresult.add(temppo);
			}
		}
		
		return finalresult;
	}
	
	/* （非 Javadoc）
	 * @see businesslogic.studentbl.FacultyStudentBLService#getAllStudentsByFacName(java.lang.String)
	 */
	@Override
	public ArrayList<StudentPO> getAllStudentsByFacName(String facultyName,String grade){
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
		ArrayList<StudentPO> tobedelete =  new ArrayList<StudentPO>();
		for(StudentPO temppo : result){
			String tempgrade = temppo.getGrade();
			if(tempgrade.compareToIgnoreCase(grade)==0){
				tobedelete.add(temppo);
			}
		}
		
		for(StudentPO  temp : tobedelete){
			result.remove(temp);
		}
		return result;
	}

	@Override
	public ArrayList<StudentPO> getAllStudentByFacName(String facname) {
		// TODO Auto-generated method stub
		StudentPO po = new StudentPO();
	 
		String faculty_id = new Plan().getFacultyID(facname);
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
	public ArrayList<StudentPO> getAllStudentByFacID(String id) {
		// TODO Auto-generated method stub
		StudentPO po = new StudentPO(); 
		po.setFaculty_ID(id); //将条件放进去
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
