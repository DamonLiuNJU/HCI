package businesslogicservice.teacherblservice;

import java.util.ArrayList;

import vo.TeacherVO;

public interface TeacherListService {
	public ArrayList<String> showGeneralInfo(); //显示所有的教师统计信息
	public ArrayList<String> showDetailedInfo(String facultyName); //显示某院系的教师统计信息
	//得到一个院系的所有教师的信息
	public ArrayList<TeacherVO> getTeacherList(String faculty_name);
	public ArrayList<TeacherVO> getFacultyTeacherList(String manager_id);
}
