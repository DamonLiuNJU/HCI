package businesslogic.teacherbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.TeacherPO;
import po.coursepo.CoursePO;
import po.courseselectionpo.SelectCourseRecordPO;
import po.studentpo.StudentPO;
import rmiconnector.RemoteDataFactory;
import vo.CreditVO;
import vo.teacherusedvo.TeacherCourseVO;
import vo.teacherusedvo.TeacherScoreVO;
import vo.teacherusedvo.TeacherUseStudentVO;
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
	
	public ArrayList<TeacherCourseVO> getTeacherCourseList(){
		ArrayList<TeacherCourseVO> tcArray = new ArrayList<TeacherCourseVO>();
		
		for(int i=0 ; i<courseArray.size() ; i++)
			tcArray.add(new TeacherCourseVO(courseArray.get(i)));
		
		return tcArray ;
	}

	@Override
	public String apply(String content, String courseName) {
		CourseApply apply = new CourseApply();
		
		if(!apply.isNameUsable(courseName)){
			return "课程名冲突";
		}
		apply.apply(courseName, ID,facultyID, content);
		
		return APPLY_SUCCEED;
		

	}

	@Override
	public ArrayList<TeacherUseStudentVO> showStudent(TeacherCourseVO courseVO) {
		// TODO Auto-generated method stub
		ArrayList<TeacherUseStudentVO> stuArray = new ArrayList<TeacherUseStudentVO>();
		
		CourseInfoBLService courseInfo= new CourseInfo();
		ArrayList<SelectCourseRecordPO> temp = courseInfo.getStudentList(courseVO.getCourseID());
		for(int i=0; i<temp.size();i++){
			String id = temp.get(i).getStudent_ID();
			StudentPO sp = new StudentInfo(id).getStudentInfo();
			stuArray.add(new TeacherUseStudentVO(temp.get(i) , sp ));
		}
		
		return stuArray;
	}

	@Override
	public String recordScore(ArrayList<String> scoreArray, String courseID) {
		// TODO Auto-generated method stub
		CreditBLService creditbl = new Credit();
		CreditVO credit;
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
			average = credit.getAverageScore(courseArray.get(i).getID());
			tsv = new TeacherScoreVO(courseArray.get(i).getID(),courseArray.get(i).getName(),average);
			creditArray.add(tsv);
		}
		return creditArray;
	}
	
	@Override
	public String changePassword(String oldPassword , String newPassword) {
		// TODO Auto-generated method stub
		if(this.password.equals(oldPassword)){
			//待续
			TeacherPO tp = this.setTeacherPO();
			tp.setPassword(newPassword);
			
			TeacherDataService data = (TeacherDataService) remote.getData("Teacher");
			try {
				if(data.update(tp)){
					this.password = newPassword;
					return "修改成功";
				}
				return "修改失败";
				
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "修改失败";
			}
			
		}
		else {
			return "原始密码错误";
		}
	}
	
	private TeacherPO setTeacherPO(){
		TeacherPO tp= new TeacherPO();
		tp.setId(this.ID);
		tp.setName(this.name);
		tp.setFacultyID(this.facultyID);
		tp.setSeniority(this.seniority);
		tp.setContactInfo(this.contactInfo);
		
		return tp;
	}
	
	private void setMyTeacher(TeacherPO tp){
		this.name = tp.getName();
		this.facultyID = tp.getFacultyID();
		this.contactInfo = tp.getContactInfo();
		this.courseArray = tp.getCourseArray();
		this.seniority = tp.getSeniority();
		this.password = tp.getPassword();
	}

	@Override
	public boolean isVaild() {
		// TODO Auto-generated method stub
		TeacherDataService teacherData = (TeacherDataService) remote.getData("Teacher");
		try {
			boolean result = false ;
			TeacherPO tp = teacherData.find(ID);
			if(tp == null){
				result = false;
			}
			else if(tp.getPassword().equals(password)){
				result = true ;
			}
			else return false;
			
			return result;
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}

	@Override
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
	public boolean isRecordTime() {
		// TODO Auto-generated method stub
		Status recordStatus = new RecordScoreStatus(CourseModule.必修课);
		if(recordStatus.current()){
			return true;
		}
		return false;
	}

	

}
