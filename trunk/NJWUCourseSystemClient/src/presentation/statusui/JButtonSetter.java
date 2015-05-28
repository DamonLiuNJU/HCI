package presentation.statusui;

import javax.swing.JButton;

import businesslogic.statusbl.Status;

public abstract class JButtonSetter {
	Status status;

	public abstract boolean setEnable(JButton b);
}
