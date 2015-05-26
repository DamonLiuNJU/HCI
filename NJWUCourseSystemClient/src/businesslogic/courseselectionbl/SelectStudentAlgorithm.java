package businesslogic.courseselectionbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.courseselectionpo.SelectCourseRecordPO;
import po.courseselectionpo.TempSelectionPO;
import rmiconnector.RemoteDataFactory;
import vo.coursevo.CourseListItemVO;
import vo.studentvo.CourseConditionPanelVO;
import businesslogic.coursebl.Course;
import businesslogic.creditbl.Credit;
import businesslogic.studentbl.CourseCondition;
import dataservice.courseselectiondataservice.SelectCourseRecordDataService;
import dataservice.courseselectiondataservice.TempSelectionDataService;

/*
 * 该类为选择学生的算法。
 * 根据学生的年级和在该模块已修学分判定其选择优先级
 * 选课方法将根据课程依次选择。
 * 每一门课程选择完成后，将最终选课结果写入SelectCourseRecord表中，在temp表中删除该课程的所有选课记录
 */
public class SelectStudentAlgorithm {

	// 选择学生的算法
	ArrayList<TempSelectionPO> courseList;
	String module;
	private ArrayList<String> courseArray;// 所有课程的id列表
	private ArrayList<MySelection> selectionArray;// 该门课程选择学生时所需要用到的信息的列表
	private RemoteDataFactory remote;
	private TempSelectionDataService data;

	public SelectStudentAlgorithm(String module) {
		remote = new RemoteDataFactory();
		data = (TempSelectionDataService) remote.getData("TempSelection");
		
		try {
			this.module = module;
			ArrayList<TempSelectionPO> tempList = data.finds();
			this.filtrate(tempList);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.sortCourse();
	}
	
	//从列表中选取该模块的课程
	private void filtrate(ArrayList<TempSelectionPO> tempList){
		courseList = new ArrayList<TempSelectionPO>();
		for(int i=0 ; i<tempList.size(); i++){
			TempSelectionPO tempPO = tempList.get(i);
			String myModule = new Course().getModule(tempPO.getCourse_ID());
			if(myModule.equals(module)){
				courseList.add(tempPO);
			}
		}
	}

	public boolean processFinalList() {
		for (int i = 0; i < courseArray.size(); i++) {
			if (!this.processOneCourse(courseArray.get(i))) {
				return false;
			}
		}
		return true;
	}

	// 计算一门课程的选课学生
	private boolean processOneCourse(String course_id) {
		this.sortSelection(course_id);
		return this.select();
	}
	
	//选择课程，按照priority的高低进行选择。
	private boolean select(){
		ArrayList<SelectCourseRecordPO> selectList = new ArrayList<SelectCourseRecordPO>();//selecetList存放有选上该课的学生的记录
		int max_number = selectionArray.get(0).max_number;//该门课程可选的最大人数
		
		@SuppressWarnings("unchecked")
		ArrayList<MySelection> backup = (ArrayList<MySelection>) selectionArray.clone(); 
		
		SelectCourseRecordPO scrPO;
		//选择算法，按照priority的高低，冒泡选择
		for(int i=max_number ; i>0 ; i--){
			if(selectionArray.size() == 0){
				this.insert(selectList);
				this.delete(backup);
				return true;
			}
			MySelection temp = selectionArray.get(0);
			int index = 0;
			for(int j=0 ; j<selectionArray.size();j++){
				if(temp.priority < selectionArray.get(j).priority){
					temp = selectionArray.get(j);
					index = j;
				}
			}
			selectionArray.remove(index);
			scrPO = new SelectCourseRecordPO(temp.student_id, temp.course_id, "0", temp.grade);
			selectList.add(scrPO);
		}
		this.insert(selectList);
		this.delete(backup);
		
		return true;
	}
	
	private void delete(ArrayList<MySelection> temp){
		for(int i=0 ; i<temp.size() ; i++){
			String student_id = temp.get(i).student_id;
			String course_id = temp.get(i).course_id;
			try {
				data.delete(student_id,course_id);
				
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private void insert(ArrayList<SelectCourseRecordPO> list) {
		RemoteDataFactory remote = new RemoteDataFactory();
		SelectCourseRecordDataService data = (SelectCourseRecordDataService) remote
				.getData("SelectCourseRecord");
		for (int i = 0; i < list.size(); i++) {
			try {
				data.insert(list.get(i));

			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// 获取一门课程的学生列表
	private void sortSelection(String course_id) {
		selectionArray = new ArrayList<MySelection>();
		selectionArray.clear();

		MySelection selection;
		for (int i = 0; i < courseList.size(); i++) {
			if (courseList.get(i).getCourse_ID().equals(course_id)) {
				selection = new MySelection(courseList.get(i));
				selectionArray.add(selection);
				this.setPriority(selection);
			}
		}
	}

	// 判断该学生的优先级
	private void setPriority(MySelection selection) {
		int priority = 0;
		priority = this.judgeGradePriority(selection.grade);
		int credit = 0;
		if(module.equals("公选课")){
			credit = selection.student_total_credit;
		}
		else {
			credit = selection.student_credit;
		}
		priority += this.judgeCreditPriority(credit);
		selection.priority = priority;
		
		
	}
	
	//按年级确定优先级
	private int judgeGradePriority(String grade) {
		int priority = 0;
		if(module.equals("选修课")){
			return 0;
		}
		switch (grade){
		case "大一上": priority = 0 ; break;
		case "大一下": priority = 5 ; break;
		case "大二上": priority = 10 ; break;
		case "大二下": priority = 15 ; break;
		case "大三上": priority = 20 ; break;
		case "大三下": priority = 25 ; break;
		case "大四上": priority = 30 ; break;
		case "大四下": priority = 35 ; break;
		}
		if(module.equals("公选课")){
			priority = priority/5;
			priority = priority*25;
		}

		return priority;
	}
	
	//按已修学分确定优先级
	private int judgeCreditPriority(int credit) {
		int priority = 0;
		int requiredCredit = 0;
		switch (module) {
		case "公选课": requiredCredit = 150; break;
		case "通识课": requiredCredit = 14 ; break;
		case "必修课": requiredCredit = 50 ; break;
		}
		priority = requiredCredit - credit;

		return priority;
	}

	// 获取课程的列表
	private void sortCourse() {
		courseArray = new ArrayList<String>();
		for (int i = 0; i < courseList.size(); i++) {
			String course_id = courseList.get(i).getCourse_ID();
			if (!isIDExist(course_id)) {// 如果courseArray中不存在该id，加入该id
				courseArray.add(course_id);
			}
		}
	}

	// 判断该id是否在courseArray里面存在
	private boolean isIDExist(String id) {
		for (int i = 0; i < courseArray.size(); i++) {
			if (id.equals(courseArray.get(i))) {
				return true;
			}
		}

		return false;
	}

	// 需要的属性：student_id ,course_id , course_module , course_credit , stu_credit
	// , stu_grade , priority
	private class MySelection {
		String student_id;
		String course_id;
		String grade;
		int student_credit = 0;
		int student_total_credit = 0;
		int priority = 0;
		int max_number;

		MySelection(TempSelectionPO tsPO) {
			this.student_id = tsPO.getStudent_ID();
			this.course_id = tsPO.getCourse_ID();
			this.grade = tsPO.getStuGrade();
			this.student_total_credit = new Credit().getTotalCredit(student_id);
			CourseListItemVO cliVO = new Course().getCourseInfo(course_id);
			this.max_number = Integer.parseInt(cliVO.getLimit());
			CourseConditionPanelVO vo = new CourseCondition()
					.getCourseConditionByModule(student_id, module);
			
			for (int i = 0; i < vo.getTableContent().size(); i++) {
				int temp = Integer.parseInt(vo.getTableContent().get(i).get(3));
				this.student_credit += temp;
			}
		}

	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		ArrayList<TempSelectionPO> courseList = new ArrayList<TempSelectionPO>();
		TempSelectionPO tempPO1 = new TempSelectionPO("12125089", "c0007", "0", "大一上");
		TempSelectionPO tempPO2 = new TempSelectionPO("12125003", "c0007", "0", "大二上");
//		TempSelectionPO tempPO3 = new TempSelectionPO("12125083", "c2003", "0", "大一上");
//		TempSelectionPO tempPO4 = new TempSelectionPO("12125082", "c2003", "0", "大一上");
//		TempSelectionPO tempPO5 = new TempSelectionPO("12125081", "c2003", "0", "大一上");
		courseList.add(tempPO1);
		courseList.add(tempPO2);
		
		SelectStudentAlgorithm test = new SelectStudentAlgorithm("通识课");
		boolean back = test.processFinalList();
		System.out.println(back);
	}

}
