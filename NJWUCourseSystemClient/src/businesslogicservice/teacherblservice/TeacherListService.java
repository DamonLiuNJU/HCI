package businesslogicservice.teacherblservice;

import java.util.ArrayList;

import vo.TeacherVO;

public interface TeacherListService {
	public ArrayList<String> showGeneralInfo(); //显示所有的教师列表
	public ArrayList<String> showDetailedInfo(String facultyName); //显示某院系的教师列表
	public ArrayList<TeacherVO> getTeacherList(String faculty_name);
	
}
