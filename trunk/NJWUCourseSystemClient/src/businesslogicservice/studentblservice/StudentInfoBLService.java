package businesslogicservice.studentblservice;

import po.studentpo.StudentPO;
import vo.studentvo.StudentInfoVO;

public interface StudentInfoBLService {

	public abstract String getStudentNo();

	public abstract boolean update();

	public abstract StudentInfoVO getPersonInfo(String studnet_id);

	public abstract String getStudentNameByID(String student_id);

	public abstract String getStuCurrentGrade();

	public abstract String getStuGradeForSelection();

	public abstract StudentPO getStudentInfo();

	public abstract String getFacultyID(String student_id);

	public abstract boolean changePassWord(String student_id, char[] oldpass,
			char[] newpass1);

}