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
import businesslogic.utilitybl.CourseModule;
import businesslogicservice.courseblservice.CourseBLService;
import dataservice.coursedataservice.CourseDataService;


public class Course implements CourseBLService{
	CourseDataService cds ;
	public Course(){
		cds = (CourseDataService)  new RemoteDataFactory().getData("Course");
	}

	/**
	 * 添加课程记录
	 */
	public void addCourse(CourseListItemVO cpv){
		CoursePO cp=Transform.listItemVOToPO(cpv);
		RemoteDataFactory factory = new RemoteDataFactory();
		CourseDataService courseData = (CourseDataService)factory.getData("Course");
		try {
			courseData.insert(cp);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		//若为必修课直接添加到选课列表
		if (cp.getModule().equals("必修课")){
			new CourseSelection().addCompulsoryCourse( cp.getID(), cp.getFacultyID(), cp.getGrade());
		}						
	}
	
	/**
	 * 删除课程
	 */
	public void deleteCourse(String courseID) {
		try {
			cds.delete(courseID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改课程信息
	 */
	public void modifyInfo(CourseListItemVO cpv){
		CoursePO cp=Transform.listItemVOToPO(cpv);
		RemoteDataFactory factory = new RemoteDataFactory();
		CourseDataService courseData = (CourseDataService)factory.getData("Course");
		try {
			courseData.update(cp);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param String courseID
	 * @return CourseListItemVO
	 */
	public CourseListItemVO  getCourseInfo(String courseID){
		CoursePO cp=null;
		ArrayList<CoursePO> cpList=new ArrayList<CoursePO>();
		try {
			cpList = cds.finds();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < cpList.size(); i++) {
			if (courseID.equals(cpList.get(i).getID()))
				cp=cpList.get(i);
		}
		if(cp==null){
			System.out.print("Exception:not found!\r\n");
			return null;
		}else{
			return Transform.POToListItemVO(cp);
		}
	}
	
	/**
	 * 得到课程的详细信息
	 * @param String courseID
	 * @return String
	 */
	public String getSpecificInfo(String courseID) {
		ArrayList<CoursePO> cpList=new ArrayList<CoursePO>();
		try {
			cpList = cds.finds();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < cpList.size(); i++) {
			if (courseID.equals(cpList.get(i).getID()))
				return cpList.get(i).getInfo();
		}
		return  "error";
	}
	
	/**
	 * 各模块课程统计信息
	 */
	public ArrayList<CourseStatVO> getModuleStatics(String facultyName) {
		ArrayList<CoursePO> cpList = new ArrayList<CoursePO>();
		try {
			cpList = cds.finds();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String facultyID = "";
		if (facultyName != "") {
			facultyID = new Plan().getFacultyID(facultyName);

		}
		
		CourseModule[] mList=CourseModule.values();
		int[] numList=new int[mList.length];
		for (int i = 0; i < cpList.size(); i++) {
			CoursePO cp = cpList.get(i);
			CourseModule m=CourseModule.valueOf(cp.getModule());
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
			
		ArrayList<CourseStatVO> statList=new ArrayList<CourseStatVO>();
		for(int i=0;i<mList.length;i++){
			statList.add(new CourseStatVO(mList[i].toString(),numList[i]));
		}

		return statList;
	}

	public ArrayList<CourseStatVO> getMyFacultyStatics(String fTeacherID){
		String facultyID=new Faculty(fTeacherID).getFacultyID();
		String facultyName=new Plan().getFacultyName(facultyID);
		return 		getModuleStatics(facultyName);
	
	}

	@Override
	public String getModule(String courseID) {
		ArrayList<CoursePO> cpList = new ArrayList<CoursePO>();
		try {
			cpList = cds.finds();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < cpList.size(); i++) {
			if (cpList.get(i).getID().equals(courseID)) {
				return cpList.get(i).getModule();
			}
		}
		return "error";
	}
	
	/**
	 * 测试所发布的课程id是否可用
	 */
	public boolean isCourseIDUsable(String id) {
		ArrayList<CoursePO> cpList=new ArrayList<CoursePO>();
		try {
			cpList = cds.finds();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < cpList.size(); i++) {
			if (id.equals(cpList.get(i).getID())) {
				return true;
			}
		}
		return false;
	}

	//为什么和plan的写一起？
	public boolean testStatus(String commend) {
		if (commend.equals("course")) {
			CourseStatus status = new PublishCourseStatus(CourseModule.体育课);
			return status.current();
		} else if (commend.equals("plan")) {
			PlanStatus status = new PlanStatus();
			return status.current();
		}
		return false;
	}
}
