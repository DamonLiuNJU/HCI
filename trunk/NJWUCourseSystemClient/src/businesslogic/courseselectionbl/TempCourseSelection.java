package businesslogic.courseselectionbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.courseselectionpo.TempSelectionPO;
import rmiconnector.RemoteDataFactory;
import vo.courseselectionvo.CourseSelectionVO;
import businesslogic.studentbl.StudentInfo;
import businesslogicservice.courseselectionblservice.TempCourseSelectionBLService;
import dataservice.courseselectiondataservice.TempSelectionDataService;

/**
 * 
 * @author LiuWT-ASUS
 * 向Temp表中添加选课信息的操作。
 *
 */
public class TempCourseSelection implements TempCourseSelectionBLService{
	TempSelectionDataService data;
	
	public TempCourseSelection(){
		data = (TempSelectionDataService) new RemoteDataFactory().getData("TempSelection");
	}
	
	
	@Override
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
	
	@Override
	public ArrayList<TempSelectionPO> getAllTempSelection(){
		ArrayList<TempSelectionPO> tspList=null;
		try {
			tspList = data.finds();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return tspList;
	}
	
	@Override
	public void removeTempCourseSelection(String student_id,String coursenumber){
		try {
			data.delete(student_id,coursenumber);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void removeAll(){
		try {
			data.deletes();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}


	@Override
	public boolean isTransfered() {
		// TODO Auto-generated method stub
		try {
			if(data.finds().size() == 0){
				return true;
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	


//	@Override
//	/*
//	 * (non-Javadoc)
//	 * @see businesslogicservice.courseselectionblservice.TempCourseSelectionBLService#transToFormal(java.lang.String)
//	 * 
//	 */
//	public boolean transToFormal(String module) {
//		try {
//			ArrayList<TempSelectionPO> tempList = data.finds();
//			
//			ArrayList<SelectCourseRecordPO> selectList = new ArrayList<SelectCourseRecordPO>();
//			for(int i=0 ; i < tempList.size() ; i++){//筛选该模块的课程
//				TempSelectionPO temp = tempList.get(i);
//				if(judgeModule(module , temp.getCourse_ID())){//判断课程是不是属于这个课程模块
//					//初始化一个SelectCourseRecordPO
//					SelectCourseRecordPO po = new SelectCourseRecordPO(
//							temp.getStudent_ID(), temp.getCourse_ID(), "0",
//							temp.getStuGrade());
//					selectList.add(po);
//				}
//			}
//			
//			
//			
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return false;
//	}
//	
//	//判断课程是不是属于这个课程模块
//	private boolean judgeModule(String module , String course_id){
//		String myModule = new CourseInfo().getModule(course_id);
//		if(myModule.equals(module)){
//			return true;
//		}
//		
//		return false;
//	}
}
