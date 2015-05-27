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
import businesslogicservice.creditblservice.CreditBLService;
import businesslogicservice.studentblservice.CourseInfoBLService;
import dataservice.courseselectiondataservice.SelectCourseRecordDataService;
//finished
public class Credit implements CreditBLService {

	RemoteDataFactory factory = new RemoteDataFactory();
	 
	SelectCourseRecordDataService data = (dataservice.courseselectiondataservice.SelectCourseRecordDataService) factory.getData("SelectCourseRecord");
	

	private int getCredit(String course_id) {

		
//		ArrayList<String> keyhead = new ArrayList<String>();
//		ArrayList<ArrayList<String>> keycontent = new ArrayList<ArrayList<String>>();
//		ArrayList<String> keyline1 = new ArrayList<String>();
//		ArrayList<String> resulthead = new ArrayList<String>();
//		ArrayList<ArrayList<String>> resultcontent = new ArrayList<ArrayList<String>>();
//		keyhead.add("id");
//		keyline1.add(course_id);
//		keycontent.add(keyline1);
//		resulthead.add("credit");
//
//		CreditPO cp = new CreditPO();
//		cp.setKeyHeadList(keyhead);
//		cp.setKeyValueList(keycontent);
//		cp.setResultHeadList(resulthead);
//		cp.setTableName("course");
//		try {
//			cp = data.findInt(cp);
//		} catch (RemoteException e) {
//			// TODO 自动生成的 catch 块
//			e.printStackTrace();
//		}
//
//		resultcontent = cp.getResultContentList();
//
//		return Integer.parseInt(resultcontent.get(0).get(0));
		CourseListItemVO vo = new Course().getCourseInfo(course_id);
		
		return  Integer.parseInt(vo.getCredit()) ;  //waiting for cbb;
	}

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
		// 从选课记录中获取学生的所有课程号以及对应成绩。
//		ArrayList<String> keyhead = new ArrayList<String>();
//		ArrayList<ArrayList<String>> keycontent = new ArrayList<ArrayList<String>>();
//		ArrayList<String> keyline1 = new ArrayList<String>();
//		ArrayList<String> resulthead = new ArrayList<String>();
//		ArrayList<ArrayList<String>> resultcontent = new ArrayList<ArrayList<String>>();
//		// ArrayList<String> resultline1 =new ArrayList<String>();
//		keyhead.add("student_id");
//		keyhead.add("semesterindex");
//		keyline1.add(studentnumber);
//		keyline1.add(semesterindex + "");
//		keycontent.add(keyline1);
//		resulthead.add("course_id");
//		resulthead.add("score");
//		cp.setKeyHeadList(keyhead);
//		cp.setKeyValueList(keycontent);
//		cp.setResultHeadList(resulthead);
//		cp.setTableName("selectcourserecord");
//		try {
//
//			
//			cp = data.findString(cp);
//			resultcontent = cp.getResultContentList();
//			for (ArrayList<String> line : resultcontent) {
//				String course_id = line.get(0);
//				// String score = line.get(1);
//				int score = Integer.parseInt(line.get(1));
//				int credit = this.getCredit(course_id);
//				if(score>= 60){
//					GPA += score * credit;
//					totalcredit+= credit;
//				}
//			}
//
//		} catch (RemoteException e1) {
//			// TODO 自动生成的 catch 块
//			e1.printStackTrace();
//		}
		if(totalcredit == 0 ){
			return  0;
		}
		
		GPA /= totalcredit*20;

		return GPA;
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
		
		return average/result.size();
//		ArrayList<String> keyhead = new ArrayList<String>();
//		ArrayList<ArrayList<String>> keyvalue = new ArrayList<ArrayList<String>>();
//		ArrayList<String> keyline1 =new ArrayList<String>();
//		ArrayList<String> resulthead = new ArrayList<String>();
//		ArrayList<ArrayList<String>> resultvalue = new ArrayList<ArrayList<String>>();
//		keyhead.add("course_id");
//		keyline1.add(coursenumber);
//		keyvalue.add(keyline1);
//		resulthead.add("score");
//		String tablename = "selectcourserecord";
//		CreditPO cp= new CreditPO();
//		cp.setTableName(tablename);
//		cp.setKeyHeadList(keyhead);
//		cp.setKeyValueList(keyvalue);
//		cp.setResultHeadList(resulthead);
//		
//		try {
//			cp =data.findString(cp);
//		} catch (RemoteException e) {
//			// TODO 自动生成的 catch 块
//			e.printStackTrace();
//		}
//		
//		resultvalue = cp.getResultContentList();
//		int averagescore=0;
//		for(ArrayList<String> line : resultvalue){
//			String score = line.get(0);
//			int tempscore = Integer.parseInt(score);
//			averagescore+=tempscore;
//		}
//		
//		if(resultvalue.size()==0){
//			return 0;
//		}
//		
//		averagescore/=resultvalue.size();
//		
//		
//		return averagescore;
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
	public static void main(String args[]){
		Credit c = new Credit();
		double avg = c.getAverageScore("c2007");
		System.out.println(avg);
	}

	
}

