package businesslogic.coursebl;


import java.rmi.RemoteException;
import java.util.ArrayList;

import po.coursepo.CoursePO;
import rmiconnector.RemoteDataFactory;
import vo.coursevo.CourseListItemVO;
import vo.coursevo.CourseStatVO;
import businesslogic.courseselectionbl.CourseSelection;
import businesslogic.managerbl.Faculty;
import businesslogic.planbl.Plan;
import businesslogic.statusbl.CourseStatus;
import businesslogic.statusbl.PlanStatus;
import businesslogic.statusbl.PublishCourseStatus;
import businesslogic.teacherbl.Teacher;
import businesslogic.utilitybl.CourseModule;
import businesslogicservice.courseblservice.CourseBLService;
import dataservice.coursedataservice.CourseDataService;

/**
 * Course 的职责是负责院系教务老师课程处理界面（CourseGUI、PublicComponent、FacultyFunctionFrame）
 * 及courseui模块发来的请求
 * Course的职责还有完成bl层间同其他模块的间的协作
 * 
 * @author vlery
 *@see presentation.facultyui.CourseGUI
 *@see presentation.facultyui.PublicComponent
 *@see presentation.facultyui.FacultyFunctionFrame
 *@see presentation.courseui
 */

public class Course implements CourseBLService {
	CourseDataService cds;
	
	public Course() {
		cds = (CourseDataService) new RemoteDataFactory().getData("Course");
	}

	/**
	 * 添加课程记录
	 * 
	 * @param cliv
	 *            CourseListItemVO型，界面传来的包含所需发布课程的所有信息的vo对象
	 * @return void
	 * @throws RemoteException
	 *             数据库连接失败
	 * @see presentation.facultyui.CourseGUI
	 * 
	 */
	@Override
	public void addCourse(CourseListItemVO cliv) {
		// 转换为po对象
		CoursePO cp = Transform.listItemVOToPO(cliv);
		// 连接数据库
		RemoteDataFactory factory = new RemoteDataFactory();
		CourseDataService courseData = (CourseDataService) factory
				.getData("Course");
		try {
			courseData.insert(cp);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		// 若为必修课直接添加到选课列表
		if (cp.getModule().equals("必修课")) {
			new CourseSelection().addC_Course(cp.getID(),
					cp.getFacultyID(), cp.getGrade());
		}
	}

	/**
	 * 删除课程
	 * 
	 * @param courseID
	 *            String型
	 * @return void
	 * @throws RemoteException
	 *             数据库连接失败
	 * @see presentation.facultyui.FacultyFunctionFrame
	 * 
	 */
	@Override
	public void deleteCourse(String courseID) {
		// 连接database
		try {
			cds.delete(courseID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 修改课程信息
	 * 
	 * @param CourseListItemVO
	 *            cliv
	 * @return void
	 * @throws RemoteException
	 *             数据库连接失败
	 * @see presentation.facultyui.FacultyFunctionFrame
	 * 
	 */
	@Override
	public void modifyInfo(CourseListItemVO cliv) {
		// 转换为po对象
		CoursePO cp = Transform.listItemVOToPO(cliv);
		// 连接数据库
		RemoteDataFactory factory = new RemoteDataFactory();
		CourseDataService courseData = (CourseDataService) factory
				.getData("Course");
		try {
			courseData.update(cp);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 得到某门课程的所有信息
	 * 
	 * @param String
	 *            courseID
	 * @return CourseListItemVO
	 * @throws RemoteException
	 *             数据库连接失败
	 * @see presentation.facultyui.CourseGUI
	 */
	@Override
	public CourseListItemVO getCourseInfo(String courseID) {
		CoursePO cp = null;
		ArrayList<CoursePO> cpList = new ArrayList<CoursePO>();
		// 连接数据库
		try {
			cpList = cds.finds();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		// 遍历找到课程id相同的对象
		for (int i = 0; i < cpList.size(); i++) {
			if (courseID.equals(cpList.get(i).getID()))
				cp = cpList.get(i);
		}
		if (cp == null) {
			System.out.print("Exception:not found!\r\n");
			return null;
		} else {
			return Transform.POToListItemVO(cp);
		}
	}

	/**
	 * 得到课程的详细信息
	 * 
	 * @param String
	 *            courseID
	 * @return String
	 * @throws RemoteException
	 *             数据库连接失败
	 * @see presentation.courseui
	 * 
	 */
	@Override
	public String getSpecificInfo(String courseID) {
		ArrayList<CoursePO> cpList = new ArrayList<CoursePO>();
		// 连接到database
		try {
			cpList = cds.finds();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		// 遍历找到课程id符合的po，返回其specificInfo
		for (int i = 0; i < cpList.size(); i++) {
			if (courseID.equals(cpList.get(i).getID()))
				return cpList.get(i).getInfo();
		}
		return "error";
	}

	/**
	 * 各模块课程统计信息
	 * 
	 * @param String
	 *            facultyName
	 * @return ArrayList<CourseStatVO>
	 * @throws RemoteException
	 *             数据库连接失败
	 * @see presentation.courseui
	 */
	@Override
	public ArrayList<CourseStatVO> getModuleStatics(String facultyName) {
		ArrayList<CoursePO> cpList = new ArrayList<CoursePO>();
		// 连接数据库
		try {
			cpList = cds.finds();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// facultyName为“”时代表全校，此时facultyID为“”，代表全校（即院系条件无限制）
		String facultyID = "";
		if (facultyName != "") {
			facultyID = new Plan().getFacultyID(facultyName);

		}

		// 遍历
		CourseModule[] mList = CourseModule.values();
		int[] numList = new int[mList.length];
		for (int i = 0; i < cpList.size(); i++) {
			CoursePO cp = cpList.get(i);
			CourseModule m = CourseModule.valueOf(cp.getModule());
			if (facultyName.equals("") || cp.getFacultyID().equals(facultyID)) {
				switch (m) {
				case 体育课:
					numList[0]++;
					break;
				case 选修课:
					numList[1]++;
					break;
				case 必修课:
					numList[2]++;
					break;
				case 公选课:
					numList[3]++;
					break;
				case 通识课:
					numList[4]++;
					break;
				default:
				}
			}
		}
		// 转换为CourseStatVO
		ArrayList<CourseStatVO> statList = new ArrayList<CourseStatVO>();
		for (int i = 0; i < mList.length; i++) {
			statList.add(new CourseStatVO(mList[i].toString(), numList[i]));
		}

		return statList;
	}

	/**
	 * 院系教务员得到本院系的课程统计信息
	 * 
	 * @param String
	 *            fTeacherID
	 * @return ArrayList<CourseStatVO>
	 * @throws RemoteException
	 *             数据库连接失败
	 * @see presentation.courseui
	 * 
	 */
	@Override
	public ArrayList<CourseStatVO> getMyFacultyStatics(String fTeacherID) {
		String facultyID = new Faculty(fTeacherID).getFacultyID();
		String facultyName = new Plan().getFacultyName(facultyID);
		return getModuleStatics(facultyName);

	}

	/**
	 * 得到课程所属的模块
	 * 
	 * @param String
	 *            courseID
	 * @return String
	 * @throws RemoteException
	 *             数据库连接失败
	 * 
	 */
	@Override
	public String getModule(String courseID) {
		ArrayList<CoursePO> cpList = new ArrayList<CoursePO>();
		// 连接database
		try {
			cpList = cds.finds();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		// 遍历
		for (int i = 0; i < cpList.size(); i++) {
			if (cpList.get(i).getID().equals(courseID)) {
				return cpList.get(i).getModule();
			}
		}
		return "error";
	}

	/**
	 * 测试所发布的课程id是否可用
	 * 
	 * @param String
	 *            courseID
	 * @return boolean
	 * @throws RemoteException
	 *             数据库连接失败
	 * @see presentation.facultyui.CourseGUI
	 */
	@Override
	public boolean isCourseIDUsable(String id) {
		ArrayList<CoursePO> cpList = new ArrayList<CoursePO>();
		try {
			cpList = cds.finds();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		// 遍历：若课程号可用，即与已发布课程不冲突，返回true
		for (int i = 0; i < cpList.size(); i++) {
			if (id.equals(cpList.get(i).getID())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 检测发布教学计划和发布课程是否在限制状态之内
	 * 
	 * @param String
	 *            commend
	 * @return boolean 在时间限制内返回true，在时间限制之外返回false
	 * @throws RemoteException
	 *             数据库连接失败
	 * @see presentation.facultyui.FacultyFunctionFrame
	 * 
	 */
	@Override
	public boolean testStatus(String commend) {
		// commend: course为检测发布课程的时间权限，plan为检测发布教学计划的时间权限
		// 在时间范围内返回true
		if (commend.equals("course")) {
			CourseStatus status = new PublishCourseStatus(CourseModule.体育课);
			return status.current();
		} else if (commend.equals("plan")) {
			PlanStatus status = new PlanStatus();
			return status.current();
		}
		return false;
	}

	/**
	 * 检测某课程某院学生是否统一添加
	 * 
	 * @param String
	 *            courseID
	 * @return boolean 已选返回true,未选返回false
	 * @throws RemoteException
	 *             数据库连接失败
	 * @see presentation.facultyui.FacultyFunctionFrame
	 * 
	 */
	@Override
	public  boolean testIfChoose(String iD, String courseID) {
		// TODO Auto-generated method stub
		String facultyID = new Faculty(iD).getFacultyID();
		CourseListItemVO cliv = new Course().getCourseInfo(courseID);
		// 若已添加返回true;
		return new CourseSelection().isSelected(facultyID, cliv);

	}


	
	/**
	 * 院系教务员给本院学生统一添加课程
	 * 
	 * @param String
	 *            fTeacherID,String courseID
	 * @return void
	 * @throws RemoteException
	 *             数据库连接失败
	 * @see presentation.facultyui.FacultyFunctionFrame
	 * 
	 */
	@Override
	public void addCourseforStudent(String iD, String courseID) {
		// TODO Auto-generated method stub
		String facultyID = new Faculty(iD).getFacultyID();
		CoursePO cp = null;
		ArrayList<CoursePO> cpList = new ArrayList<CoursePO>();
		// 连接数据库
		try {
			cpList = cds.finds();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		// 遍历
		for (int i = 0; i < cpList.size(); i++) {
			if (courseID.equals(cpList.get(i).getID()))
				cp = cpList.get(i);
		}
		new CourseSelection().addC_Course(cp.getID(), facultyID,
				cp.getGrade());
	}

	/**
	 * 得到本院系已发布课程的 学分统计信息
	 * 
	 * @param String
	 *            fTeacherID
	 * @return ArrayList<String>
	 * @throws RemoteException
	 *             数据库连接失败
	 * @see presentation.courseui
	 */
	@Override
	public ArrayList<String> getMyFacultyStaticsCredit(String iD) {
		// TODO Auto-generated method stub
		String facultyID = new Faculty(iD).getFacultyID();
		ArrayList<CoursePO> cpList = new ArrayList<CoursePO>();
		// 连接数据库
		try {
			cpList = cds.finds();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CourseModule[] mList = CourseModule.values();
		int[] creditList = new int[mList.length];

		for (int i = 0; i < cpList.size(); i++) {
			CoursePO cp = cpList.get(i);
			CourseModule m = CourseModule.valueOf(cp.getModule());
			if (cp.getFacultyID().equals(facultyID)) {
				switch (m) {
				case 体育课:
					creditList[0] = creditList[0]
							+ Integer.parseInt(cp.getCredit());
					break;
				case 选修课:
					creditList[1] = creditList[1]
							+ Integer.parseInt(cp.getCredit());
					break;
				case 必修课:
					creditList[2] = creditList[2]
							+ Integer.parseInt(cp.getCredit());
					break;
				case 公选课:
					creditList[3] = creditList[3]
							+ Integer.parseInt(cp.getCredit());
					break;
				case 通识课:
					creditList[4] = creditList[4]
							+ Integer.parseInt(cp.getCredit());
					break;
				default:
				}
			}
		}
		ArrayList<String> list = new ArrayList<String>();
		// 遍历
		for (int i = 0; i < creditList.length; i++) {
			list.add(mList[i] + ":" + creditList[i]);
		}
		return list;
	}

	/**
	 * 发布课程时检测教师号是否存在并属于本院
	 * 
	 * @param String
	 *            fTeacherID,String teacherID
	 * @return boolean 存在并属于本院返回true，否则返回false
	 * @throws RemoteException
	 *             数据库连接失败
	 * @see presentation.facultyui.CourseGUI
	 */
	@Override
	public boolean testTeacher(String id, String teacherID) {
		// TODO Auto-generated method stub
		String facultyID = new Faculty(id).getFacultyID();
		// 存在并属于本院返回true
		return new Teacher().isExistInFaculty(teacherID, facultyID);
	}

}
