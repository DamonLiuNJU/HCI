package businesslogic.teacherbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import po.TeacherPO;
import po.coursepo.CoursePO;
import po.courseselectionpo.SelectCourseRecordPO;
import po.studentpo.StudentPO;
import rmiconnector.RemoteDataFactory;
import vo.CreditVO;
import vo.teacherusedvo.TeacherCourseVO;
import vo.teacherusedvo.TeacherScoreVO;
import vo.teacherusedvo.TeacherUseStudentVO;
import businesslogic.coursebl.Course;
import businesslogic.coursebl.CourseApply;
import businesslogic.creditbl.Credit;
import businesslogic.statusbl.RecordScoreStatus;
import businesslogic.statusbl.Status;
import businesslogic.studentbl.CourseInfo;
import businesslogic.studentbl.StudentInfo;
import businesslogic.utilitybl.CourseModule;
import businesslogicservice.creditblservice.CreditBLService;
import businesslogicservice.studentblservice.CourseInfoBLService;
import businesslogicservice.teacherblservice.TeacherBLService;
import dataservice.TeacherDataService;

/*
 * Teacher类包含了教师的所有操作，以及其他模块调用teacher数据的操作
 * 类中包含getTeacherCourseList , apply , showStudent , recordScore
 * showAverageCourseScore , isVaid , getTeacherName , getTeacherID , 
 * isExist , isRecordTime , updateContactInfo , isExistFaculty12个提供的public方法
 * 以及setTeacherPO , setMyTeacher2个private方法。
 */
public class Teacher implements TeacherBLService {
	private String name;
	private String ID;
	private String password;
	private String facultyID;
	private String seniority;
	private String contactInfo;
	private ArrayList<CoursePO> courseArray = new ArrayList<CoursePO>();
	private RemoteDataFactory remote = new RemoteDataFactory(); 

	public Teacher(String id, String password) {
		this.ID = id;
		this.password = password;
	}
	
	public Teacher(String id){
		this.ID = id;
	}

	public Teacher(){
		
	}
	public ArrayList<CoursePO> getCourseArray(){
		return courseArray;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFaculty() {
		return facultyID;
	}

	public String getFacultyID() {
		return facultyID;
	}
	
	public String getSeniority() {
		return seniority;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	public void seniorityChange(String seniority) {
		this.seniority = seniority;
	}
	
	
	/*
	 * 初始化一个教师，将教师的所有信息放入该对象中
	 */
	public void initTeacher(){
		TeacherDataService data = (TeacherDataService) remote.getData("Teacher");
		try {
			TeacherPO tp = data.initialTeacher(ID);
			this.setMyTeacher(tp);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * 得到教师的课程列表，返回一个TeacherCourseVO的list
	 * TeacherCourseVO中包含courseName,courseID,location,classTime4个属性
	 */
	public ArrayList<TeacherCourseVO> getTeacherCourseList(){
		ArrayList<TeacherCourseVO> tcArray = new ArrayList<TeacherCourseVO>();
		
		for(int i=0 ; i<courseArray.size() ; i++)
			tcArray.add(new TeacherCourseVO(courseArray.get(i)));
		
		return tcArray ;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see businesslogicservice.teacherblservice.TeacherBLService#apply(java.lang.String, java.lang.String)
	 * teacher申报课程的方法，委托CourseApply类的apply方法完成
	 */
	public String apply(String content, String courseName) {
		CourseApply apply = new CourseApply();
		
		if(!apply.isNameUsable(courseName)){
			return "课程名冲突";
		}
		apply.apply(courseName, ID,facultyID, content);
		
		return APPLY_SUCCEED;
		

	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see businesslogicservice.teacherblservice.TeacherBLService#showStudent(vo.teacherusedvo.TeacherCourseVO)
	 * 用于界面显示一门课程的学生列表，返回一个TeacherUserStudentVO的list
	 * TeacherUserStudentVO包含student的name,id,grade,score,course_id,
	 * contactInfo,faculty_name,mother_phone,mother_name,father_phone,
	 * father_name
	 */
	public ArrayList<TeacherUseStudentVO> showStudent(TeacherCourseVO courseVO) {
		// TODO Auto-generated method stub
		ArrayList<TeacherUseStudentVO> stuArray = new ArrayList<TeacherUseStudentVO>();
		
		CourseInfoBLService courseInfo= new CourseInfo();
		//获取选课记录中的所有该课程的选课记录，SelectCourseRecordPO中包含student_id,course_id,score,grade
		ArrayList<SelectCourseRecordPO> temp = courseInfo.getStudentList(courseVO.getCourseID());
		for(int i=0; i<temp.size();i++){
			String course_grade = temp.get(i).getStuGrade();
			String id = temp.get(i).getStudent_ID();
			StudentPO sp = new StudentInfo(id).getStudentInfo();
			
			if(judgeYear(sp.getGrade() , course_grade)){
				stuArray.add(new TeacherUseStudentVO(temp.get(i) , sp ));
			}
			
		}
		
		return stuArray;
	}
	
	
	private boolean judgeYear(String stu_grade , String course_grade){
		int year = Integer.parseInt(stu_grade);
		GregorianCalendar g = new GregorianCalendar();
		int stuGrade = g.get(Calendar.YEAR) - year + 1;
		
		int courseGrade = -1;
		switch (course_grade) {
		case "大一上": courseGrade =1 ; break;
		case "大一下": courseGrade =1 ; break;
		case "大二上": courseGrade =2 ; break;
		case "大二下": courseGrade =2 ; break;
		case "大三上": courseGrade =3 ; break;
		case "大三下": courseGrade =3 ; break;
		case "大四上": courseGrade =4 ; break;
		case "大四下": courseGrade =4 ; break;
		}
		
		if(stuGrade == courseGrade){
			return true;
		}
		
		return false;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see businesslogicservice.teacherblservice.TeacherBLService#recordScore(java.util.ArrayList, java.lang.String)
	 * 教师登记课程的成绩
	 * 委托creditbl类的recordScore方法完成
	 * String的格式为：student_id student_name student_grade score
	 */
	public String recordScore(ArrayList<String> scoreArray, String courseID) {
		// TODO Auto-generated method stub
		CreditBLService creditbl = new Credit();
		CreditVO credit;
		//一次登记一个成绩，需要新建一个creditVO，包含登记成绩的course_id,score,student_id
		for(int i=0 ; i<scoreArray.size() ; i++){
			String id = scoreArray.get(i).split(" ")[0];
			int score = Integer.parseInt(scoreArray.get(i).split(" ")[3]);
			credit = new CreditVO();
			credit.setCourse_ID(courseID);
			credit.setScore(score);
			credit.setStudent_ID(id);
			creditbl.recordScore(credit);
		}
		
		return "登记成功";
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see businesslogicservice.teacherblservice.TeacherBLService#showAverageCourseScore()
	 * 查看一个教师的所有所授课程的学生的平均成绩
	 * TeacherScoreVO包含course_id,course_name,credit(平均分)
	 */
	public ArrayList<TeacherScoreVO> showAverageCourseScore() {
		// TODO Auto-generated method stub --cbb revise
		CreditBLService credit = new Credit();
		ArrayList<TeacherScoreVO> creditArray = new ArrayList<TeacherScoreVO>();
		this.initTeacher();
		if(courseArray.size() == 0){
			return creditArray;
		}
		double average = 0;
		TeacherScoreVO tsv;
		for (int i = 0; i < courseArray.size(); i++) {
			//由credit类得到一门课程的平均成绩
			average = credit.getAverageScore(courseArray.get(i).getID());
			tsv = new TeacherScoreVO(courseArray.get(i).getID(),courseArray.get(i).getName(),average);
			creditArray.add(tsv);
		}
		return creditArray;
	}
	
	@Override
	/*
	 * (non-Javadoc)
	 * @see businesslogicservice.teacherblservice.TeacherBLService#changePassword(java.lang.String, java.lang.String)
	 * 教师修改自己的密码
	 * 前置条件：newPassword有效
	 */
	public String changePassword(String oldPassword , String newPassword) {
		// TODO Auto-generated method stub
		//检测密码是否正确
		if(this.password.equals(oldPassword)){
			TeacherPO tp = this.setTeacherPO();
			tp.setPassword(newPassword);
			
			TeacherDataService data = (TeacherDataService) remote.getData("Teacher");
			try {
				if(data.update(tp)){
					this.password = newPassword;//将密码改为新密码，以便在该次登录中继续进行密码相关操作
					return "修改成功";
				}
				return "修改失败";
				
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "与服务器连接失败";
			}
			
		}
		else {
			return "原始密码错误";
		}
	}
	
	/*
	 * 以该teacher对象生成一个teacherPO
	 */
	private TeacherPO setTeacherPO(){
		TeacherPO tp= new TeacherPO();
		tp.setId(this.ID);
		tp.setPassword(this.password);
		tp.setName(this.name);
		tp.setFacultyID(this.facultyID);
		tp.setSeniority(this.seniority);
		tp.setContactInfo(this.contactInfo);
		
		return tp;
	}
	
	/*
	 * 用一个teacherPO完善该teacher对象的信息
	 */
	private void setMyTeacher(TeacherPO tp){
		this.name = tp.getName();
		this.facultyID = tp.getFacultyID();
		this.contactInfo = tp.getContactInfo();
		this.courseArray = tp.getCourseArray();
		this.seniority = tp.getSeniority();
		this.password = tp.getPassword();
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see businesslogicservice.teacherblservice.TeacherBLService#isVaild()
	 * 在登陆的时候判断输入是否合法，如果不存在这个id返回1
	 * 如果密码错误，返回2，如果输入正确，返回0
	 */
	public int isVaild() {
		// TODO Auto-generated method stub
		TeacherDataService teacherData = (TeacherDataService) remote.getData("Teacher");
		try {
			int result = -1 ;
			TeacherPO tp = teacherData.find(ID);
			if(tp == null){
				result = 1;
			}
			else if(tp.getPassword().equals(password)){
				result = 0 ;
			}
			else return 2;
			
			return result;
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return -1;
		}
		
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see businesslogicservice.teacherblservice.TeacherBLService#getTeacherName(java.lang.String)
	 * 根据教师的id查找教师的姓名
	 */
	public String getTeacherName(String teacherID) {
		// TODO Auto-generated method stub
		TeacherDataService teacherData = (TeacherDataService) remote.getData("Teacher");
		try {
			TeacherPO tp = teacherData.find(teacherID);
			if(tp!= null){
				return tp.getName();
			}
			else {
				return "不存在该工号的教师";
			}
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	
	@Override
	/*
	 * (non-Javadoc)
	 * @see businesslogicservice.teacherblservice.TeacherBLService#getTeacherID(java.lang.String)
	 * 根据教师的姓名查找教师的id
	 * 可能会有同名的教师，所以返回的是一个arrayList，里面存有所有该名字的教师的id
	 */
	public ArrayList<String> getTeacherID(String teacherName) {
		// TODO Auto-generated method stub
		TeacherDataService teacherData = (TeacherDataService) remote.getData("Teacher");
		ArrayList<TeacherPO> info;
		try {
			info = teacherData.findByName(teacherName);
			ArrayList<String> id_Array = new ArrayList<String>();
			for(int i=0 ; i<info.size() ; i++){
				id_Array.add(info.get(i).getId());
			}
			
			return id_Array;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see businesslogicservice.teacherblservice.TeacherBLService#isExist(java.lang.String)
	 * 判断该id的教师是否存在
	 */
	public boolean isExist(String id) {
		// TODO Auto-generated method stub
		TeacherDataService teacherData = (TeacherDataService) remote.getData("Teacher");
		try {
			TeacherPO tp = teacherData.find(id);
			
			if(tp == null){
				return false ;
			}
			else return true;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see businesslogicservice.teacherblservice.TeacherBLService#isRecordTime(java.lang.String)
	 * 判断一个课程是否在登记课程成绩的时间内，如果在，返回true，否则返回false
	 */
	public boolean isRecordTime(String course_id) {
		// TODO Auto-generated method stub
		String temp = new Course().getModule(course_id);
		CourseModule module = CourseModule.必修课;
		switch (temp) {
		case "选修课": module = CourseModule.选修课;break;
		case "公选课": module = CourseModule.公选课;break;
		case "通识课": module = CourseModule.通识课;break;
		case "体育课": module = CourseModule.体育课;break;
		}
		Status recordStatus = new RecordScoreStatus(module);
		if(recordStatus.current()){
			return true;
		}
		return false;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see businesslogicservice.teacherblservice.TeacherBLService#updateContactInfo(java.lang.String)
	 * 更新教师的联系方式
	 */
	public String updateContactInfo(String info) {
		// TODO Auto-generated method stub
		TeacherPO tp = this.setTeacherPO();
		tp.setContactInfo(info);
		
		TeacherDataService teacherData = (TeacherDataService) remote.getData("Teacher");
		
		try {
			if(teacherData.update(tp)){
				return "更新成功";
			}
			return "更新失败";
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "更新失败";
		}
		
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see businesslogicservice.teacherblservice.TeacherBLService#isExistInFaculty(java.lang.String, java.lang.String)
	 * 判断一个教师是否存在于某个院系中
	 */
	public boolean isExistInFaculty(String id, String faculty_id) {
		// TODO Auto-generated method stub
		TeacherDataService teacherData = (TeacherDataService) remote.getData("Teacher");
		try {
			TeacherPO tp = teacherData.find(id);
			if(tp ==null){
				return false;
			}
			if(tp.getFacultyID().equals(faculty_id)){
				return true;
			}
			else {
				return false;
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return true;
		}
		

	}

}
