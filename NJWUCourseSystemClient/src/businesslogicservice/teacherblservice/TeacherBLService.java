package businesslogicservice.teacherblservice;

import java.util.ArrayList;

import vo.teacherusedvo.TeacherCourseVO;
import vo.teacherusedvo.TeacherScoreVO;
import vo.teacherusedvo.TeacherUseStudentVO;
import businesslogic.utilitybl.ReplyMessage;

public interface TeacherBLService extends ReplyMessage{
	
	public String apply(String content , String courseName);
	public ArrayList<TeacherUseStudentVO> showStudent(TeacherCourseVO courseVO);
	public String recordScore(ArrayList<String> scoreArray , String courseID);
	public ArrayList<TeacherScoreVO> showAverageCourseScore();
	public String changePassword(String oldPassword , String newPassword);
	public boolean isVaild();
	public String getTeacherName(String teacherID);
	public ArrayList<String> getTeacherID(String teacherName);
	public boolean isExist(String id);
	public boolean isRecordTime();
	
}
