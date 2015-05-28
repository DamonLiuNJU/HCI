package presentation.statusui;

import javax.swing.JButton;

import businesslogic.statusbl.SelectCourseStatus;
import businesslogic.utilitybl.CourseModule;

public class SelectCourseButtonSetter extends JButtonSetter{
	public SelectCourseButtonSetter(CourseModule m) {
		status=new SelectCourseStatus(m);
	}

	@Override
	public boolean setEnable(JButton b) {
		boolean enable=status.current();
		b.setEnabled(enable);
		return enable;
	}

}
