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
	public int isVaild();//0代表成功，1代表帐号错误，2代表密码错误，-1代表与服务器连接出错
	public String getTeacherName(String teacherID);
	public ArrayList<String> getTeacherID(String teacherName);
	public boolean isExist(String id);
	public boolean isRecordTime(String course_id);
	public String updateContactInfo(String info);
}
