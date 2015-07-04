package presentation.courseselectionui;

import java.util.Vector;

import presentation.tools.UIConstants;
import businesslogic.courseselectionbl.SelectCourseInfo;
import businesslogic.studentbl.Student;

public class CommonKnowledgeCourseSelectionFrame extends CourseSelectionFrame{

	public CommonKnowledgeCourseSelectionFrame(Student s) {
		super(s);
		// TODO Auto-generated constructor stub
		final String modelname = UIConstants.CommonKnowledgeCourseModule;
		Vector<Vector<String>> mycourselist =new SelectCourseInfo().getSelectedCourse(s.getStudentID(), modelname); //store the lessons he has chosen;
		this.showFrame(modelname,mycourselist);
	}

}
