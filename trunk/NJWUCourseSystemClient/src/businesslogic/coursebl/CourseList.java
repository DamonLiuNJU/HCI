package businesslogic.coursebl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;

import po.coursepo.CoursePO;
import rmiconnector.RemoteDataFactory;
import vo.coursevo.CourseListItemVO;
import businesslogic.planbl.Plan;
import businesslogic.teacherbl.Teacher;
import businesslogic.utilitybl.CourseModule;
import businesslogicservice.courseblservice.CourseListService;
import dataservice.coursedataservice.CourseDataService;

/**
 * 
 * CourseList 主要负责ui层发来的需要查看课程列表信息的请求
 * @author vlery
 * 
 */
public class CourseList implements CourseListService {
	CourseDataService cds;

	public CourseList() {
		cds = (CourseDataService) new RemoteDataFactory().getData("Course");
	}
	/**
	 * cno &cName& tn& facultyID &grade(无条件应为 "")---------->课程列表
	 * @param  String cno,String courseName,String teacherName,
	 * 						String facultyID,String grade
	 * @return  ArrayList<CourseListItemVO>
	 * 
	 * 
	 */
	@Override
	public ArrayList<CourseListItemVO> getSearchList(String cno, String cName,
			String teacherName, String facultyName, String grade) {
		ArrayList<CourseListItemVO> list = new ArrayList<CourseListItemVO>();
		ArrayList<CoursePO> cpList =getAllCourses();
		ArrayList<String> teacherID = new ArrayList<String>();

		if (!teacherName.equals("")) {
			teacherID = new Teacher().getTeacherID(teacherName);
		}
		String facultyID = "";
		if (facultyName != "") {
			facultyID = new Plan().getFacultyID(facultyName);
		}
		for (int i = 0; i < cpList.size(); i++) {
			CoursePO cp = cpList.get(i);
			if ((cno.equals("") || cno.equals(cp.getID()))
					&& (cName.equals("") || cp.getName().equals(cName))
					&& (teacherName.equals("") || teacherID.contains(cp
							.getTeacherID()))
					&& (facultyName.equals("") || cp.getFacultyID().equals(
							facultyID))
					&& (grade.equals("") || cp.getGrade().equals(grade)))
				list.add(Transform.POToListItemVO(cpList.get(i)));
		}

		return list;
	}

	
	/**
	 * student:campus&grade&facultyName（无条件应为 ""）---------->课程列表
	 * @param  String campus,String grade,String facultyName
	 * @return  ArrayList<CourseListItemVO>
	 * 
	 */
	@Override
	public ArrayList<CourseListItemVO> getSearchList(String campus,
			String grade, String facultyName) {
		ArrayList<CoursePO> cpList =getAllCourses();
		ArrayList<CourseListItemVO> list = new ArrayList<CourseListItemVO>();
		Plan plan = new Plan();
		String facultyID = "";
		if (facultyName != "") {
			facultyID = plan.getFacultyID(facultyName);
		}
		for (int i = 0; i < cpList.size(); i++) {
			CoursePO cp = cpList.get(i);
			if (campus.equals(cp.getCampus())
					&& (grade.equals(cp.getGrade())
							|| cp.getGrade().equals("上") && grade.contains("上") || cp
							.getGrade().equals("下") && grade.contains("下"))
					&& facultyID.equals(cp.getFacultyID())) {
				list.add(Transform.POToListItemVO(cpList.get(i)));
			}
		}
		return list;
	}

	/**
	 *  student:根据模块得到课程列表 module为“”时 代表无限制
	 * @param  String module
	 * @return  ArrayList<CourseListItemVO>
	 * 
	 */
	@Override
	public ArrayList<CourseListItemVO> getCourseListByModule(String module) {
		// TODO Auto-generated method stub
		ArrayList<CoursePO> cpList =getAllCourses();
		ArrayList<CourseListItemVO> list = new ArrayList<CourseListItemVO>();
		boolean term = testTerm();
		if (term) {
			for (int i = 0; i < cpList.size(); i++) {
				CoursePO cp = cpList.get(i);
				if ((cp.getGrade().equals("大一上") || cp.getGrade().equals("大二上")
						|| cp.getGrade().equals("大三上")
						|| cp.getGrade().equals("大四上") || cp.getGrade().equals(
						"上"))
						&& (module.equals("") || module.equals(cp.getModule()))) {
					list.add(Transform.POToListItemVO(cpList.get(i)));
				}
			}
			return list;
		} else {
			for (int i = 0; i < cpList.size(); i++) {
				CoursePO cp = cpList.get(i);
				if ((cp.getGrade().equals("大一下") || cp.getGrade().equals("大二下")
						|| cp.getGrade().equals("大三下")
						|| cp.getGrade().equals("大四下") || cp.getGrade().equals(
						"下"))
						&& module.equals(cp.getModule())) {
					list.add(Transform.POToListItemVO(cpList.get(i)));
				}
			}
			return list;
		}
	}

	
	/**
	 * student :通过学生选课的列表得到学生所选课的信息列表
	 * @param  ArrayList<String >cno
	 * @return  ArrayList<CourseListItemVO>
	 * 
	 */
	@Override
	public ArrayList<CourseListItemVO> getCourseListByChooseList(
			ArrayList<String> cno) {
		ArrayList<CourseListItemVO> list = new ArrayList<CourseListItemVO>();
		ArrayList<CoursePO> cpList =getAllCourses();
		for (int i = 0; i < cpList.size(); i++) {
			CoursePO cp = cpList.get(i);
			if (cno.contains(cp.getID())) {
				list.add(Transform.POToListItemVO(cpList.get(i)));
			}
		}
		return list;
	}
	
	/**
	 *  得到某个院系的必修课列表
	 * @param  String facultyName
	 * @return  ArrayList<CourseListItemVO>
	 * 
	 */
	@Override
	public ArrayList<CourseListItemVO> getComplusoryCourseList(String facultyName){
		ArrayList<CourseListItemVO> list = new ArrayList<CourseListItemVO>();
		ArrayList<CoursePO> cpList =getAllCourses();
		for (CoursePO cp:cpList) {
			if (cp.getFacultyID().equals(new Plan().getFacultyID(facultyName))&&cp.getModule().equals(CourseModule.必修课.toString())) {
				list.add(Transform.POToListItemVO(cp));
			}
		}
		return list;
	}

	/**
	 *  teacher:得到任课列表
	 * @param  String teacherID
	 * @return  ArrayList<CourseListItemVO>
	 * 
	 */
	@Override
	public ArrayList<CourseListItemVO> getTeachList(String teacherID) {
		ArrayList<CourseListItemVO> list = new ArrayList<CourseListItemVO>();
		ArrayList<CoursePO> cpList =getAllCourses();
		for (int i = 0; i < cpList.size(); i++) {
			if (teacherID.equals(cpList.get(i).getTeacherID())) {
				list.add(Transform.POToListItemVO(cpList.get(i)));
			}
		}
		return list;
	}

	/**
	 *  得到所有课程的po
	 * @param  
	 * @return  ArrayList<CoursePO>
	 * 
	 */
	  ArrayList<CoursePO> getAllCourses(){
		ArrayList<CoursePO> cpList = new ArrayList<CoursePO>();
		try {
			cpList = cds.finds();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return cpList;
	}
	
	  
		/**
		 * 检测当前学期 上学期:true 8.1~12.31&1.1~2.14
		 * 下学期:false 2:15~7.31 直接测试的时候只会返回上学期的课程
		 *  得到所有课程的po
		 * @param  
		 * @return  boolean
		 * 
		 */ 
	boolean testTerm() {
			Calendar cal = Calendar.getInstance();
			int day = cal.get(Calendar.DATE);
			int month = cal.get(Calendar.MONTH) + 1;

			if (8 <= month || month == 1) {
					return true;
			} else if (month == 2 && day <= 14) {
					return true;
			} else {
					return false;
			}
		}

	}
