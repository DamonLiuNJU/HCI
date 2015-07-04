package presentation.courseselectionui;

import java.util.Vector;

import javax.swing.JFrame;
import businesslogic.courseselectionbl.SelectCourseInfo;
import businesslogic.studentbl.Student;

public class PublicCourseSelection extends CourseSelectionFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8199556140172081692L;
	/**
	 * @param args
	 */


	public PublicCourseSelection(Student s) {
		super(s);
		final String student_id = s.getStudentID();
		final String modelname = "公选课";
		Vector<Vector<String>> mycourselist = new SelectCourseInfo()
				.getSelectedCourse(student_id, modelname); // store the lessons
															// he has chosen;
		super.showFrame(modelname, mycourselist);

	}

}
