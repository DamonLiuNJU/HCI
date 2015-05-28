package businesslogicservice.managerblservice;

import vo.adminvo.UserVO;
import businesslogic.utilitybl.ReplyMessage;


public interface AdminBLService extends ReplyMessage{
	
	public String deleteUser(String id , String type);//按id删除一个user，type代表user的身份（教师，学生等）
	public String RegisterUser(UserVO uv);//注册一个用户，type在uservo中
	public UserVO findUser(String id);//按照id查找一个user，返回一个uservo
	public String updateUser(UserVO uv);//更新一个user信息，（暂定）学号不能更改。
	public String idAutomate(String type ,String faculty_name);//自动化生成一个id
}

