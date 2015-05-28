package businesslogicservice.teacherblservice;

import java.util.ArrayList;

import vo.teacherusedvo.TeacherCourseVO;
import vo.teacherusedvo.TeacherScoreVO;
import vo.teacherusedvo.TeacherUseStudentVO;
import businesslogic.utilitybl.ReplyMessage;

public interface TeacherBLService extends ReplyMessage{
	
	//教师申报课程
	public String apply(String content , String courseName);
	//查看某一门课程的学生列表
	public ArrayList<TeacherUseStudentVO> showStudent(TeacherCourseVO courseVO);
	//登记一门课程的成绩
	public String recordScore(ArrayList<String> scoreArray , String courseID);
	//查看教师所授的所有课程的学生的平均成绩
	public ArrayList<TeacherScoreVO> showAverageCourseScore();
	//修改教师密码
	public String changePassword(String oldPassword , String newPassword);
	public int isVaild();//0代表成功，1代表帐号错误，2代表密码错误，-1代表与服务器连接出错
	public String getTeacherName(String teacherID);
	public ArrayList<String> getTeacherID(String teacherName);
	public boolean isExist(String id);
	public boolean isExistInFaculty(String id , String faculty_id);//判断某个教师是否存在于某个院中，是返回true不是返回false
	public boolean isRecordTime(String course_id);
	public String updateContactInfo(String info);
}
