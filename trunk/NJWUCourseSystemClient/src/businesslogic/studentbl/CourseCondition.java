package businesslogic.studentbl;

import java.util.ArrayList;
import java.util.Vector;

import vo.studentvo.CourseConditionPanelVO;
import businesslogic.courseselectionbl.SelectCourseInfo;
import businesslogic.creditbl.Credit;
import businesslogic.planbl.Plan;
import businesslogicservice.studentblservice.CourseConditionBLService;
import businesslogicservice.studentblservice.CourseInfoBLService;
/**
 * 
 * @author LiuWT-ASUS
 * 获取学生的课程状态，某一个院系的专业核心课是否已经学习完毕，能否准入准出信息。
 *
 */
public class CourseCondition implements CourseConditionBLService {

	/**
	 * @param args
	 */
	public CourseConditionPanelVO getCourseCondition(String student_id,String facultyID){
		//返回课程准出的情况 
		//以及准入情况
		if(student_id==null||facultyID==null){
			//异常处理
			return new CourseConditionPanelVO();
		}
		SelectCourseInfo si = new SelectCourseInfo();
		ArrayList<String> courselist = si.getAllMyCoursesInSelect(student_id);
		CourseInfoBLService ci   = new CourseInfo();
//		StudentInfo studentinfo =  new StudentInfo();
		Credit c = new Credit();
		Vector<Vector<String>>  content = new Vector<Vector<String>>();
//		String studentfaculty = studentinfo.getFacultyID(student_id);
		for(String course_id : courselist){
			
			String course_type = ci.getModule(course_id);
			String course_facultyID = ci.getFacultyIDByCourseID(course_id);  // 等CBB  //变成了根据课程号获取课程名.
			if(course_type.compareToIgnoreCase("必修课")==0 && (course_facultyID.compareToIgnoreCase(facultyID)==0)){
				//该学生自己所属院系的必修课类型.
				Vector<String> line =  new Vector<String>();
				int  score = c.getScore(student_id, course_id);
				int credit =  ci.getCredit(course_id);
				String coursename = ci.getCourseName(course_id);
				line.add(course_id);
				line.add(coursename);
				line.add(course_type);
				line.add(credit+"");
				line.add(score+"");	
				content.add(line);
			}

		}
		CourseConditionPanelVO vo = new CourseConditionPanelVO ();
		vo.setTableContent(content);
		return vo;
	}
	
	/* （非 Javadoc）
	 * @see businesslogic.studentbl.CourseConditionBLService#getCourseConditionByModule(java.lang.String, java.lang.String)
	 */
	@Override
	public CourseConditionPanelVO getCourseConditionByModule(String student_id,String modulename){
		//返回课程准出的情况 
		//以及准入情况
		SelectCourseInfo si = new SelectCourseInfo();
		ArrayList<String> courselist = si.getAllMyCoursesInSelect(student_id);
		CourseInfoBLService ci   = new CourseInfo();
//		StudentInfo studentinfo =  new StudentInfo();
		Credit c = new Credit();
		Vector<Vector<String>>  content = new Vector<Vector<String>>();
//		String studentfaculty = studentinfo.getFacultyID(student_id);
		for(String course_id : courselist){
//			String facultyID = studentinfo.getFacultyID(student_id);
			String course_type = ci.getModule(course_id);
//			String course_facultyID = ci.getFacultyIDByCourseID(course_id);  // 等CBB  //变成了根据课程号获取课程名.
			if(course_type.compareToIgnoreCase(modulename)==0){
				//该学生自己所属院系的必修课类型.
				Vector<String> line =  new Vector<String>();
				int  score = c.getScore(student_id, course_id);
				int credit =  ci.getCredit(course_id);
				String coursename = ci.getCourseName(course_id);
				line.add(course_id);
				line.add(coursename);
				line.add(course_type);
				line.add(credit+"");
				line.add(score+"");	
				content.add(line);
			}

		}
		CourseConditionPanelVO vo = new CourseConditionPanelVO ();
		vo.setTableContent(content);
		return vo;
	}
	/*
	 * 一个简单的与别的模块的交互，根据院系名取得院系的ID
	 */
	public String getFacultyID(String facName){
		return new Plan().getFacultyID(facName);
	}
	/* （非 Javadoc）
	 * @see businesslogic.studentbl.CourseConditionBLService#isCurrentCreditsEnough(java.lang.String, java.lang.String)
	 */
	@Override
	public CourseConditionPanelVO isCurrentCreditsEnough(String student_id,String faculty_id){
		if(student_id==null||faculty_id==null){
			//异常处理
			return null;
		}
		CourseConditionPanelVO vo = this.getCourseCondition(student_id, faculty_id);
		Vector<Vector<String>> content = vo.getTableContent();
		int totalcredit = 0;
		for(Vector<String> line: content){
			String credits = line.get(3);
			String score = line.get(4);
			int creditsint = Integer.parseInt(credits);
			int scoreint = Integer.parseInt(score);
			if(scoreint >=60){
				totalcredit+=creditsint;
			}
		}

		if(totalcredit>=30){
			vo.setPermission(true);
		}else{
			vo.setPermission(false);
		}
		return vo;
	}
	
	/* （非 Javadoc）
	 * @see businesslogic.studentbl.CourseConditionBLService#canGraduate(java.lang.String)
	 * 是否准许毕业
	 */
	@Override
	public boolean canGraduate(String student_id){
		Student s = new Student();
		int credit = s.getTotalCredit(student_id);
		if(credit>=150){
			return true;
		}else{
			return false;
		}
	}
	
}
