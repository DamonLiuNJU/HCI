package presentation.courseselectionui;

import java.util.Vector;

import presentation.tools.UIConstants;
import businesslogic.courseselectionbl.SelectCourseInfo;
import businesslogic.studentbl.Student;

public class ElectiveCourseFrame extends CourseSelectionFrame{

	/**
	 * 
	 */
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = -5338131222748149557L;

	/**
	 * @param args
	 */
	public ElectiveCourseFrame(Student s){

		super(s);
		final String modelname = UIConstants.ElectiveCourseModule;
		Vector<Vector<String>> mycourselist =new SelectCourseInfo().getSelectedCourse(s.getStudentID(), modelname); //store the lessons he has chosen;
		this.showFrame(modelname,mycourselist);

	}

	
	
	
	
	
	

}
