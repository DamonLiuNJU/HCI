package businesslogicservice.courseselectionblservice;

public interface SelectStudentAlgorithmBLService {
	public boolean processFinalList();//选课，成功返回true，不成功返回false
	public boolean processFacultyCourse(String fTeacherID);//院系选课，选择选修课
	public boolean isProcessed();//判断是否已经完成选课，完成返回true
}
