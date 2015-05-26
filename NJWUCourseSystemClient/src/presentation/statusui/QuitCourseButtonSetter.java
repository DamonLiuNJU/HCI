package presentation.statusui;

import javax.swing.JButton;

import businesslogic.statusbl.Quit_AddCourseStatus;
import businesslogic.utilitybl.CourseModule;

public class QuitCourseButtonSetter extends JButtonSetter{

	public QuitCourseButtonSetter(CourseModule m) {
		status=new Quit_AddCourseStatus(m);
	}

	@Override
	public boolean setEnable(JButton b) {
		boolean enable=status.current();
		b.setEnabled(enable);
		return enable;
	}

}
