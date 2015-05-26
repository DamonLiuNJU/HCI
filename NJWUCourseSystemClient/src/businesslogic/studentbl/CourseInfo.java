package businesslogic.studentbl;

import java.util.ArrayList;
import java.util.Vector;

import po.courseselectionpo.SelectCourseRecordPO;
import rmiconnector.RemoteDataFactory;
import vo.coursevo.CourseListItemVO;
import vo.studentvo.CourseInfoVO;
import businesslogic.coursebl.Course;
import businesslogic.courseselectionbl.SelectCourseInfo;
import businesslogic.planbl.Plan;
import businesslogic.teacherbl.Teacher;
import businesslogicservice.studentblservice.CourseInfoBLService;
//import dataservice.courseselectiondataservice.SelectCourseRecordDataService;
import businesslogicservice.studentblservice.StudentInfoBLService;

public class CourseInfo implements CourseInfoBLService {
	String name;
	String teacherid;
	String campus;
	String grade;
	String palce;
	String time;
	String period;
	String specialinfo;
	String faculty;
	String credit;
	String module;
	String restrict;
	String teachername;

	/* （非 Javadoc）
	 * @see businesslogic.studentbl.CourseInfoBLService#setName(java.lang.String)
	 */
//	@Override
	public void setName(String name) {
		this.name = name;
	}

	/* （非 Javadoc）
	 * @see businesslogic.studentbl.CourseInfoBLService#setTeacherID(java.lang.String)
	 */
//	@Override
	public void setTeacherID(String teacherid) {
		this.teacherid = teacherid;
	}

	/* （非 Javadoc）
	 * @see businesslogic.studentbl.CourseInfoBLService#setTeacherName(java.lang.String)
	 */
//	@Override
	public void setTeacherName(String teachername) {
		this.teachername = teachername;
	}

	/* （非 Javadoc）
	 * @see businesslogic.studentbl.CourseInfoBLService#getTeacherName()
	 */
//	@Override
	public String getTeacherName() {
		return this.teachername;
	}

	/* （非 Javadoc）
	 * @see businesslogic.studentbl.CourseInfoBLService#getName()
	 */
//	@Override
	public String getName() {
		return this.name;
	}

	/* （非 Javadoc）
	 * @see businesslogic.studentbl.CourseInfoBLService#getTeacherID()
	 */
//	@Override
	public String getTeacherID() {
		return this.teacherid;
	}


	/* （非 Javadoc）
	 * @see businesslogic.studentbl.CourseInfoBLService#getTeacherNameByTeacherID(java.lang.String)
	 */
	@Override
	public String getTeacherNameByTeacherID(String teacher_id) {
		Teacher t = new Teacher();
		String result = t.getTeacherName(teacher_id);
		return result;
	}

	/* （非 Javadoc）
	 * @see businesslogic.studentbl.CourseInfoBLService#getTeacherNameByCourseID(java.lang.String)
	 */
	@Override
	public String getTeacherNameByCourseID(String course_id) {
		CourseListItemVO vo = new Course().getCourseInfo(course_id);
		String result = vo.getTeacherName();
		return result;
	}

	/* （非 Javadoc）
	 * @see businesslogic.studentbl.CourseInfoBLService#getCourseName(java.lang.String)
	 */
	@Override
	public String getCourseName(String course_id) {
		//waiting for cbb
		CourseListItemVO vo = new Course().getCourseInfo(course_id);
		return vo.getName();
	}

	/* （非 Javadoc）
	 * @see businesslogic.studentbl.CourseInfoBLService#getModule(java.lang.String)
	 */
	@Override
	public String getModule(String course_id){
		//waiting for cbb;
		CourseListItemVO vo = new Course().getCourseInfo(course_id);
		return vo.getModule();
	}

	RemoteDataFactory factory = new RemoteDataFactory();

	/* （非 Javadoc）
	 * @see businesslogic.studentbl.CourseInfoBLService#getStudentList(java.lang.String)
	 */
	@Override
	public ArrayList<SelectCourseRecordPO> getStudentList(String course_id) {
		// ArrayList<TempSelectionPO> result = new ArrayList<TempSelectionPO>();
		ArrayList<SelectCourseRecordPO> finalresult = new ArrayList<SelectCourseRecordPO>();

		finalresult =  new SelectCourseInfo().getStudentList(course_id);

		return finalresult;
	}
	
	/* （非 Javadoc）
	 * @see businesslogic.studentbl.CourseInfoBLService#getStudentList(vo.studentvo.CourseInfoVO)
	 */
	@Override
	public CourseInfoVO getStudentList(CourseInfoVO vo){
		//根据课程号获取学生的学号姓名以及所属院系.
		String course_id = vo.getCourseID();
		ArrayList<SelectCourseRecordPO> list = this.getStudentList(course_id);
		Vector<Vector<String>> content = new Vector<Vector<String>>();
		StudentInfoBLService si = new StudentInfo();
		Plan  p = new Plan();
		for(SelectCourseRecordPO po : list){
			String student_id = po.getStudent_ID();
			String student_name =si.getStudentNameByID(student_id);
			String faculty_id = si.getFacultyID(student_id);
			String faculty_name = p.getFacultyName(faculty_id);
			Vector<String> line = new Vector<String>();
			line.add(student_id);
			line.add(student_name);
			line.add(faculty_name);
			content.add(line);
		}
		vo.setContent(content);
		return	vo;
	}

	/* （非 Javadoc）
	 * @see businesslogic.studentbl.CourseInfoBLService#getSelectedStudentNumber(java.lang.String)
	 */
	@Override
	public int getSelectedStudentNumber(String course_id) {
		// 这个是正表中查找确定了已经选过某一门课程的人数
		int result = this.getStudentList(course_id).size();
		return result;
	}

	/* （非 Javadoc）
	 * @see businesslogic.studentbl.CourseInfoBLService#getCourseGrade(java.lang.String)
	 */
	@Override
	public String getCourseGrade(String course_id) {
		//根据课程号获取该课程是针对哪一个年级的；
		CourseListItemVO cv = new Course().getCourseInfo(course_id);
		String grade = cv.getGrade();
		return grade;
	}

	/* （非 Javadoc）
	 * @see businesslogic.studentbl.CourseInfoBLService#getCredit(java.lang.String)
	 */
	@Override
	public int getCredit(String course_id) {
		//
		CourseListItemVO cv = new Course().getCourseInfo(course_id);
		return Integer.parseInt(cv.getCredit());
	}


	//根据课程号获取
	/* （非 Javadoc）
	 * @see businesslogic.studentbl.CourseInfoBLService#getFacultyIDByCourseID(java.lang.String)
	 */
	@Override
	public String getFacultyIDByCourseID(String course_id){
		//出问题了 等待CBB
		CourseListItemVO cv = new Course().getCourseInfo(course_id);
		String facultyname = cv.getFacultyName();
		String facultyID = new Plan().getFacultyID(facultyname);
		return facultyID;
	}
	
	/* （非 Javadoc）
	 * @see businesslogic.studentbl.CourseInfoBLService#getFacultyName(java.lang.String)
	 */
	@Override
	public String getFacultyName(String facultyID){
		 
		String name = new Plan().getFacultyName(facultyID);
		return name;
	}
	
	public static void main(String args[]){
		CourseInfoBLService  ci = new CourseInfo();
		String course_id = "c2007";
		String facutlyID= ci.getFacultyIDByCourseID(course_id);
		System.out.println("ID : " + facutlyID);
//		CourseListItemVO cv = new Course().getCourseInfo("");
//		String facultyname = cv.getFacultyName();
//		System.out.println("name : "+facultyname);
		
	}
	
	
}
