package businesslogic.creditbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Vector;

import po.courseselectionpo.SelectCourseRecordPO;
import rmiconnector.RemoteDataFactory;
import vo.CreditVO;
import vo.coursevo.CourseListItemVO;
import businesslogic.coursebl.Course;
import businesslogic.courseselectionbl.SelectCourseInfo;
import businesslogic.studentbl.CourseInfo;
import businesslogic.studentbl.Tool;
import businesslogicservice.creditblservice.CreditBLService;
import businesslogicservice.studentblservice.CourseInfoBLService;
import dataservice.courseselectiondataservice.SelectCourseRecordDataService;
//finished
public class Credit implements CreditBLService {

	RemoteDataFactory factory = new RemoteDataFactory();
	 
	SelectCourseRecordDataService data = (dataservice.courseselectiondataservice.SelectCourseRecordDataService) factory.getData("SelectCourseRecord");
	
	/*
	 * 获取课程对应的学分数。
	 */
	private int getCredit(String course_id) {

		CourseListItemVO vo = new Course().getCourseInfo(course_id);
		
		return  Integer.parseInt(vo.getCredit()) ;  //waiting for cbb;
	}

	@Override
	public String recordScore(CreditVO cv) {
		
		String student_id = cv.getStudent_ID();
		String course_id = cv.getCourse_ID();
		int score = cv.getScore();
		SelectCourseRecordPO po = new SelectCourseRecordPO(student_id, course_id, score+"", "");
		try {
			data.update(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return "success";
	}

	@Override
	public int getTotalCredit(String student_id) {
		// 获取学生的总学分

		int totalcredit = 0 ;
		SelectCourseRecordPO po = new SelectCourseRecordPO(student_id, "", "", "");
		ArrayList<SelectCourseRecordPO> result = null;
		try {
			result = data.findCourseList(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		for(SelectCourseRecordPO temppo : result){
			String score = temppo.getScore();
			String course_id = temppo.getCourse_ID();
			int scoreint = Integer.parseInt(score);
			int credit = new CourseInfo().getCredit(course_id);
			if(scoreint >= 60){
				totalcredit+= credit;
			}
		}

		return totalcredit;
	}

	@Override
	public double getGPA(String student_id, int semesterindex) {
		
		int totalcredit = this.getTotalCredit(student_id);;
//		CreditPO cp = new CreditPO();
		double GPA = 0;
		String grade =  "";
		switch(semesterindex){
		case 0 : 
			grade = "大一上";
			break;
		case 1: 
			grade = "大一下";
			break;
		case 2 : 
			grade = "大二上";
			break;
		case 3 : 
			grade = "大二下";
			break;
		case 4 : 
			grade = "大三上";
			break;
		case 5 : 
			grade = "大三下";
			break;
		case 6 : 
			grade = "大四上";
			break;
		case 7 : 
			grade = "大四下";
			break;
		default :
			return -1;
				
		}
		SelectCourseRecordPO po = new SelectCourseRecordPO(student_id, "", "", "");
		
		ArrayList<SelectCourseRecordPO> result = null;
		try {
			result = data.findCourseList(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		for(SelectCourseRecordPO temppo : result){
			String score = temppo.getScore();
			String course_id = temppo.getCourse_ID();
			int scoreint = Integer.parseInt(score);
			int credit = new CourseInfo().getCredit(course_id);
			String thegradewheniselectedthiscourse = temppo.getStuGrade();
			if( (scoreint >= 60)&&thegradewheniselectedthiscourse.compareToIgnoreCase(grade)==0){
				GPA += scoreint * credit;
				
			}
		}
		if(totalcredit == 0 ){
			return  0;
		}
		
		GPA /= totalcredit*20;
		double test = Math.round(GPA*10)/10.0;
		return test;
	}

	@Override
	public double getAverageScore(String course_id) {
		
		double average = 0 ;
		SelectCourseRecordPO po = new SelectCourseRecordPO("", course_id, "", "");
		ArrayList<SelectCourseRecordPO> result = null;
		try {
			result = data.findStudentList(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		for(SelectCourseRecordPO temppo  : result ){
			String score = temppo.getScore();
			int  scoreint  = Integer.parseInt(score);
			average += scoreint;
		}
		if(result.size()==0){
			return 0;
		}
		average = average/result.size();
		double test = Math.round(average*10)/10.0;
		return test;
	}

	@Override
	public int getScore(String student_id, String course_id) {
		
		SelectCourseRecordPO po = new SelectCourseRecordPO(student_id, course_id, "", "");
		try {
			po = data.find(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return Integer.parseInt(po.getScore());
	}

	public Vector<Vector<String>> getScoreSheet(String student_id,int semester){
		
		CourseInfoBLService ci = new CourseInfo();
		Credit  c= new Credit();
		SelectCourseInfo mc = new SelectCourseInfo();
		ArrayList<String> mycourselist =  mc.getAllMyCoursesInSelect(student_id);
		
		Vector<Vector<String>> result =  new Vector<Vector<String>>(mycourselist.size());

		for(String course_id: mycourselist){
			Vector<String> courseinfo = new Vector<String>();
			String coursename = ci.getCourseName(course_id);
			int  score = c.getScore(student_id, course_id);
			int credit = c.getCredit(course_id);
			
			courseinfo.add(course_id);
			courseinfo.add(coursename);
			courseinfo.add(score+"");
			courseinfo.add(credit+"");
			
			result.add(courseinfo);
			
			
		}

		return result;
	}
//	public static void main(String args[]){
//		Credit c = new Credit();
//		double avg = c.getAverageScore("c2007");
//		System.out.println(avg);
//	}

	
}

