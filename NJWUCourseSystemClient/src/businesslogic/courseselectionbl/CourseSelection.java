package businesslogic.courseselectionbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.courseselectionpo.SelectCourseRecordPO;
import po.courseselectionpo.TempSelectionPO;
import po.studentpo.StudentPO;
import rmiconnector.RemoteDataFactory;
import vo.courseselectionvo.CourseSelectionVO;
import businesslogic.coursebl.Course;
import businesslogic.studentbl.FacultyStudent;
import businesslogic.studentbl.StudentInfo;
import businesslogicservice.courseselectionblservice.CourseSelectionBLService;
import dataservice.courseselectiondataservice.SelectCourseRecordDataService;

public class CourseSelection implements CourseSelectionBLService{
	SelectCourseRecordDataService data;
	
	public CourseSelection(){
		data= (SelectCourseRecordDataService)new RemoteDataFactory()
		.getData("SelectCourseRecord");
	}
	
	/**
	 * 为某院系某年级所有学生添加相应必修课记录
	 * @param course_id
	 * @param faculty_id
	 * @param grade
	 */
	public void addCompulsoryCourse(String course_id, String faculty_id, String grade) {
		// 把一个院系所有符合grade的学生添加一个课程 这里的grade应该是形式为 2012 这种的
		// 但是课程的grade是大一上大一下这种的。
//		RemoteDataFactory factory = new RemoteDataFactory();
		StudentPO sp = new StudentPO();
		sp.setFaculty_ID(faculty_id);
		sp.setGrade(grade);
		ArrayList<StudentPO> list = new ArrayList<StudentPO>();
		list = new FacultyStudent().getAllStudentsByFacID(faculty_id);
		for (StudentPO po : list) {
			String student_id = po.getStudentID();
			new CourseSelection().addCourseSelection(student_id, course_id);
		}

	}
	
	public void addCourseSelection(String student_id, String course_id){
		SelectCourseRecordPO po = new SelectCourseRecordPO(student_id, course_id, 
				"0",new StudentInfo(student_id).getStuGradeForSelection());
		try {
			data.insert(po);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void transferFromTempSelection(){
		ArrayList<TempSelectionPO> tspList=new TempCourseSelection().getAllTempSelection();
		for(TempSelectionPO tsp:tspList){
			try {
				data.insert(new SelectCourseRecordPO(tsp.getStudent_ID(),tsp.getCourse_ID(),
						tsp.getScore(),tsp.getStuGrade()));
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean addCourseNo(String student_id, String coursenumber) {
		// 添加到暂时的表中
//		Student s = new Student();
		CourseSelectionVO sv = new CourseSelectionVO(student_id);
		sv.setCourseID(coursenumber);
		this.selectCourse(sv);
		return true;
	}


	public boolean removeCourse(String student_id, String coursenumber) {


		new TempCourseSelection().removeTempCourseSelection(student_id,
				coursenumber);

		return true;
	}
	
	//from studentinfo
		public void addPECourseNo(String student_id, String course_id) {
			// TODO 自动生成的方法存根
			// 用于体育选课的时候直接添加到正表中。
			new CourseSelection().addCourseSelection(student_id, course_id);
		}

		public boolean seletedPE(String student_id) {
			// boolean result = true;
			ArrayList<String> courselist = new SelectCourseInfo()
					.getAllMyCoursesInTemp(student_id);
			ArrayList<String> zhengshicourselist = new SelectCourseInfo()
					.getAllMyCoursesInSelect(student_id);
			courselist.addAll(zhengshicourselist);
			for (String course_id : courselist) {
				String module = new Course().getModule(course_id);
				if (module.compareToIgnoreCase("体育课") == 0) {
					return true;
				}
			}
			return false;
		}
		
		public String selectCourse(CourseSelectionVO sv) {
			// TODO 自动生成的方法存根
			//学生的数据库方面负责将这些东西写入到数据库中
			new TempCourseSelection().addTempCourseSelection(sv);
			return "Select Success";
		}
}
