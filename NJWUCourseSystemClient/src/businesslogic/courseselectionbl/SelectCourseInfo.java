package businesslogic.courseselectionbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Vector;

import po.courseselectionpo.SelectCourseRecordPO;
import po.courseselectionpo.TempSelectionPO;
import rmiconnector.RemoteDataFactory;
import businesslogic.coursebl.Course;
import businesslogic.statusbl.Quit_AddCourseStatus;
import businesslogic.statusbl.SelectCourseStatus;
import businesslogic.statusbl.Status;
import businesslogic.studentbl.CourseInfo;
import businesslogic.utilitybl.CourseModule;
import dataservice.courseselectiondataservice.SelectCourseRecordDataService;
import dataservice.courseselectiondataservice.TempSelectionDataService;
public class SelectCourseInfo {
	RemoteDataFactory factory = new RemoteDataFactory();
	TempSelectionDataService tempData ;
	SelectCourseRecordDataService recordData ;
	
	public SelectCourseInfo(){
		tempData = (TempSelectionDataService) factory
				.getData("TempSelection");
		recordData = (SelectCourseRecordDataService) factory
				.getData("SelectCourseRecord");
	}
	
	/********************************************/
	/*查找temp选课表*/
	public Vector<Vector<String>> getSelectedCourse(String student_id,
			String mode) {
		// 获取tempselection中的
		Vector<Vector<String>> vresult = new Vector<Vector<String>>();
		ArrayList<TempSelectionPO> result = null;
		try {
			result = tempData.find(student_id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		for (TempSelectionPO po : result) {
			String course_id = po.getCourse_ID();
			String coursename = new CourseInfo().getCourseName(course_id);
			String module = new Course().getModule(course_id);
			Vector<String> line = new Vector<String>();
			if (module.compareToIgnoreCase(mode) == 0) {
				line.add(course_id);
				line.add(coursename);
				vresult.add(line);
			}
		}
		return vresult;
	}

	public ArrayList<String> getAllMyCoursesInTemp(String student_id) {
		// 从临时表中获取
		ArrayList<TempSelectionPO> result = null;
		try {
			result = tempData.find(student_id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ArrayList<String> course_id = new ArrayList<String>();

		for (TempSelectionPO temppo : result) {
			course_id.add(temppo.getCourse_ID());
		}
		return course_id;
	}
	
	public String getTheGradeWhenISelectedThisCourseForQuit(String student_id,
			String course_id) {
		ArrayList<TempSelectionPO> list = null;
		try {
			list = tempData.find(student_id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		for (TempSelectionPO temppo : list) {
			String grade = temppo.getStuGrade();
			String tempid = temppo.getCourse_ID();
			if (course_id.compareToIgnoreCase(tempid) == 0) {
				return grade;
			}
		}

		return null;
	}
	
	public boolean courseExist(String student_id, String course_id) {
		ArrayList<TempSelectionPO> list = null;
		try {
			list = tempData.find(student_id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		for (TempSelectionPO temppo : list) {
			String course_id_temp = temppo.getCourse_ID();
			if (course_id.compareToIgnoreCase(course_id_temp) == 0) {
				return true;
			}
		}

		return false;
	}

	/********************************************/
	/*查找正式选课表*/
	public ArrayList<String> getAllMyCoursesInSelect(String student_id) {
		SelectCourseRecordPO po = new SelectCourseRecordPO(student_id, "", "","");
		ArrayList<SelectCourseRecordPO> result = null;
		try {
			result = recordData.findCourseList(po);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ArrayList<String> course_id = new ArrayList<String>();

		for (SelectCourseRecordPO temppo : result) {
			course_id.add(temppo.getCourse_ID());
		}

		return course_id;
	}
	
	public ArrayList<SelectCourseRecordPO> getStudentList(String course_id) {
		ArrayList<SelectCourseRecordPO> finalresult = new ArrayList<SelectCourseRecordPO>();
		SelectCourseRecordPO tspo = new SelectCourseRecordPO("", course_id, "",
				"");
		try {
			finalresult = recordData.findStudentList(tspo);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return finalresult;
	}

	public String getScore(String student_id,String course_id){
		SelectCourseRecordPO sp = null;
		try {
			sp=recordData.find(new SelectCourseRecordPO(student_id,course_id,"",""));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		if(sp!=null){
			return sp.getScore();
		}
		return null;
	}

	
	public String getTheGradeWhenISelectedThisCourse(String student_id,
			String course_id) {
		SelectCourseRecordPO po = new SelectCourseRecordPO(student_id,
				course_id, "", "");
		SelectCourseRecordPO list = null;
		try {
			list =recordData.find(po);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return list.getStuGrade();
	}

	public boolean isCurrentTimeValidForSelectionCourse(CourseModule c) {
		Status s = new SelectCourseStatus(c);
		boolean valid = s.current();
		return valid;
	}

	public boolean isCurrentTimeSpecial(CourseModule module) {
		return new Quit_AddCourseStatus(module).current();
	}
}
