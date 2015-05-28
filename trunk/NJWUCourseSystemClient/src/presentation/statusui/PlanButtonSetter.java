package presentation.statusui;

import javax.swing.JButton;

import businesslogic.statusbl.PlanStatus;

public class PlanButtonSetter extends JButtonSetter{

	@Override
	public boolean setEnable(JButton b) {
		status=new PlanStatus();
		boolean enable=status.current();
		b.setEnabled(enable);
		return enable;
	}

}
