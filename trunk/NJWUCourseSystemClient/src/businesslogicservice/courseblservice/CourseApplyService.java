package businesslogicservice.courseblservice;
import java.util.ArrayList;
import vo.coursevo.ApplyVO;
public interface CourseApplyService {
//得到申请课程的列表	
	public ArrayList<ApplyVO> getApplyList();
//得到申请课程的内容
	public String getContent(String courseName);
//审核后删除课程信息
	public void delete(String name);	
//教师申请课程	
	public void apply(String name ,String teacherID,String facultyID,String apply);
// 检测课程名是否可用
	public boolean isNameUsable(String name);
}
