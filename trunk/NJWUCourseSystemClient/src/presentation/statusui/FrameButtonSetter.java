package presentation.statusui;

import javax.swing.JButton;

import businesslogic.statusbl.FrameStatus;

public class FrameButtonSetter extends JButtonSetter{

	@Override
	public boolean setEnable(JButton b) {
		status=new FrameStatus();
		boolean enable=status.current();
		b.setEnabled(enable);
		return enable;
	}

}
