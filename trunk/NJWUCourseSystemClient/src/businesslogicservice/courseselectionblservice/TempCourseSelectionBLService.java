package businesslogicservice.courseselectionblservice;

import java.util.ArrayList;

import po.courseselectionpo.TempSelectionPO;
import vo.courseselectionvo.CourseSelectionVO;

public interface TempCourseSelectionBLService {
	public void addTempCourseSelection(CourseSelectionVO sv);
	public ArrayList<TempSelectionPO> getAllTempSelection();
	public void removeTempCourseSelection(String student_id,String coursenumber);
	public void removeAll();
	public boolean isTransfered();//判断课程是否已经转移至正式选课表中
	//将tempSelection表中的所有选课记录都转入SelectCourseRecord表中
//	public boolean transToFormal(String module);
}
