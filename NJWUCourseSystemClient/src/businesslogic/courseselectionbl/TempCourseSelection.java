package businesslogic.courseselectionbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.courseselectionpo.TempSelectionPO;
import rmiconnector.RemoteDataFactory;
import vo.courseselectionvo.CourseSelectionVO;
import businesslogic.studentbl.StudentInfo;
import businesslogicservice.courseselectionblservice.TempCourseSelectionBLService;
import dataservice.courseselectiondataservice.TempSelectionDataService;

public class TempCourseSelection implements TempCourseSelectionBLService{
	TempSelectionDataService data;
	
	public TempCourseSelection(){
		data = (TempSelectionDataService) new RemoteDataFactory().getData("TempSelection");
	}
	
	public void addTempCourseSelection(CourseSelectionVO sv){
		String studentid =sv.getStudentID();
		String courseid =sv.getCourseID();
		String score = "0";
		String grade = new StudentInfo(studentid).getStuGradeForSelection();
		TempSelectionPO po = new TempSelectionPO(studentid, courseid, score,grade);
		try {
			data.insert(po);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<TempSelectionPO> getAllTempSelection(){
		ArrayList<TempSelectionPO> tspList=null;
		try {
			data.finds();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return tspList;
	}
	
	public void removeTempCourseSelection(String student_id,String coursenumber){
		try {
			data.delete(student_id,coursenumber);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void removeAll(){
		try {
			data.deletes();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
