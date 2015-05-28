package businesslogic.courseselectionbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;

import po.courseselectionpo.SelectCourseRecordPO;
import po.courseselectionpo.TempSelectionPO;
import po.studentpo.StudentPO;
import rmiconnector.RemoteDataFactory;
import vo.courseselectionvo.CourseSelectionVO;
import vo.coursevo.CourseListItemVO;
import businesslogic.coursebl.Course;
import businesslogic.studentbl.FacultyStudent;
import businesslogic.studentbl.StudentInfo;
import businesslogicservice.courseselectionblservice.CourseSelectionBLService;
import dataservice.courseselectiondataservice.SelectCourseRecordDataService;
/**
 * 
 * @author LiuWT-ASUS
 * 选课相关操作
 *
 */
public class CourseSelection implements CourseSelectionBLService {
	
	SelectCourseRecordDataService data;
	
	public CourseSelection() {
		data = (SelectCourseRecordDataService) new RemoteDataFactory()
				.getData("SelectCourseRecord");
	}
	

	/**
	 * 为某院系某年级所有学生添加相应必修课记录
	 * 
	 * @param course_id
	 * @param faculty_id
	 * @param grade
	 */
	public void addCompulsoryCourse(String course_id, String faculty_id,
			String grade) {
		// 把一个院系所有符合grade的学生添加一个课程 这里的grade应该是形式为 2012 这种的
		// 但是课程的grade是大一上大一下这种的。
		// RemoteDataFactory factory = new RemoteDataFactory();
		StudentPO sp = new StudentPO();
		sp.setFaculty_ID(faculty_id);
		sp.setGrade(grade);
		ArrayList<StudentPO> list = new ArrayList<StudentPO>();
		list = new FacultyStudent().getAllStudentsByFacID(faculty_id,grade);
		for (StudentPO po : list) {
			String student_id = po.getStudentID();
			new CourseSelection().addCourseSelection(student_id, course_id);
		}

	}
	public  void addC_Course(String course_id,String fac_id,String grade)	{
		//using grade like 大一上
		ArrayList<StudentPO> list = new ArrayList<StudentPO>();
		ArrayList<StudentPO> finalist = new ArrayList<StudentPO>();
		list = new FacultyStudent().getAllStudentByFacID(fac_id);
		for(StudentPO po : list){
			String student_id = po.getStudentID();
			String tempgrade = new StudentInfo(student_id).getStuGradeForSelection();
			if(tempgrade.compareToIgnoreCase(grade)==0){
				finalist.add(po);
			}
		}
		
		for (StudentPO po : finalist) {
			String student_id = po.getStudentID();
			new CourseSelection().addCourseSelection(student_id, course_id);
		}
	}
	/*
	 * （非 Javadoc）
	 * @see businesslogicservice.courseselectionblservice.CourseSelectionBLService#addCourseSelection(java.lang.String, java.lang.String)
	 * 在SelectCourseRecord表中添加一条记录；
	 */
	@Override
	public void addCourseSelection(String student_id, String course_id) {
		SelectCourseRecordPO po = new SelectCourseRecordPO(student_id,
				course_id, "-1",
				new StudentInfo(student_id).getStuGradeForSelection());
		try {
			data.insert(po);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/*
	 * （非 Javadoc）
	 * @see businesslogicservice.courseselectionblservice.CourseSelectionBLService#transferFromTempSelection()
	 * 将存放暂时的选课记录的表中的内容转移到正式的表中。
	 * TempSelection -> SelectCourseRecord；
	 */
	@Override
	public void transferFromTempSelection() {
		TempCourseSelection temp= new TempCourseSelection();
		ArrayList<TempSelectionPO> tspList = temp.getAllTempSelection();
		for (TempSelectionPO tsp : tspList) {
			try {
				data.insert(new SelectCourseRecordPO(tsp.getStudent_ID(), tsp
						.getCourse_ID(), tsp.getScore(), tsp.getStuGrade()));
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		temp.removeAll();
	}
	/*
	 * （非 Javadoc）
	 * @see businesslogicservice.courseselectionblservice.CourseSelectionBLService#addCourseNo(java.lang.String, java.lang.String)
	 * 在TempSelection表中添加一条学生选课的记录。
	 */
	@Override
	public boolean addCourseNo(String student_id, String coursenumber) {
		// 添加到暂时的表中
		// Student s = new Student();
		CourseSelectionVO sv = new CourseSelectionVO(student_id);
		sv.setCourseID(coursenumber);
		this.selectCourse(sv);
		return true;
	}
	/*
	 * （非 Javadoc）
	 * @see businesslogicservice.courseselectionblservice.CourseSelectionBLService#removeCourse(java.lang.String, java.lang.String)
	 * 在TempSelection表中删除一条表项（学生选课记录）。
	 */
	@Override
	public boolean removeCourse(String student_id, String coursenumber) {

		new TempCourseSelection().removeTempCourseSelection(student_id,
				coursenumber);

		return true;
	}

	// from studentinfo
	/*
	 * （非 Javadoc）
	 * @see businesslogicservice.courseselectionblservice.CourseSelectionBLService#addPECourseNo(java.lang.String, java.lang.String)
	 * 学生选择体育课，体育课采用先到先得的策略，所以直接添加到正式的表（SelectCoursRecord）中。
	 */
	@Override
	public void addPECourseNo(String student_id, String course_id) {
		// TODO 自动生成的方法存根
		// 用于体育选课的时候直接添加到正表中。
		new CourseSelection().addCourseSelection(student_id, course_id);
	}

	/*
	 * （非 Javadoc）
	 * @see businesslogicservice.courseselectionblservice.CourseSelectionBLService#seletedPE(java.lang.String)
	 * 用于判断该学生在这个学期是否已经选择了一门体育课，在已经选择了的情况下是不能再选择别的课程的。
	 */
	@Override
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
	
	@Override
	public String selectCourse(CourseSelectionVO sv) {
		// TODO 自动生成的方法存根
		// 学生的数据库方面负责将这些东西写入到数据库中
		new TempCourseSelection().addTempCourseSelection(sv);
		return "Select Success";
	}
	
	
	public int getStudentGrade(String enterYear) {
		int grade = 1;
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;

		if (0 < month && month <= 8) {
			grade = year - Integer.parseInt(enterYear);
		} else {

			grade = year - Integer.parseInt(enterYear) + 1;
		}
		return grade;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see businesslogicservice.courseselectionblservice.CourseSelectionBLService#isSelected(java.lang.String, vo.coursevo.CourseListItemVO)
	 * 查看该课程是否被本院的所有学生选择
	 */
	public boolean isSelected(String faculty_id, CourseListItemVO cliv) {
		// TODO Auto-generated method stub
		String course_id = cliv.getCno();
		SelectCourseRecordPO po = new SelectCourseRecordPO("",course_id,"0","");
		
		try {
			ArrayList<SelectCourseRecordPO> list = data.findStudentList(po);
			
			for(int i = 0; i<list.size() ; i++){
				String stu_id = list.get(i).getStudent_ID();
				int temp = this.getMyStudentGrade(stu_id);
				if(this.thisSelected(faculty_id, stu_id, temp , cliv)){
					return true;
				}
			}
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	/*
	 * 获取学生入学年份的数据。
	 */
	private int getMyStudentGrade(String student_id){
		int grade = 0;
		String enterYear = new StudentInfo(student_id).getStudentInfo().grade;
		grade = this.getStudentGrade(enterYear);
		return grade;
	}
	
	//判断属于faculty_id的学生student_id是否在选过这门课
	private boolean thisSelected(String faculty_id, String student_id,
			int stu_grade, CourseListItemVO cliv) {
		if (this.isInThisFaculty(faculty_id, student_id)) {
			// 判断在表中这门课是否已被选择
			String grade = "";
			
			switch (stu_grade){
			case 1:grade = "大一";break;
			case 2:grade = "大二";break;
			case 3:grade = "大三";break;
			case 4:grade = "大四";break;
			}
			
			if(cliv.getGrade().contains(grade)){
				return true;
			}
		}

		return false;
		
	}
	
	//判断一个学生是否在这个院系当中
	private boolean isInThisFaculty(String faculty_id, String student_id){
		String stuFac = new StudentInfo().getFacultyID(student_id);
		if(stuFac.equals(faculty_id)){
			return true;
		}
		else return false;
	}
	
	public static void main(String args[]){
		CourseSelection cs = new CourseSelection();
		cs.addC_Course("c000102", "001", "大二上");
		
//		CourseListItemVO cliv = new CourseListItemVO("", "c000102", "", "", "", "大二上", "", "", "", "", "", "", "", "");
	//	System.out.println(cs.isSelected("001", cliv));
		
	}
	
}
