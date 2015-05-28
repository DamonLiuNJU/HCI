package presentation.managerui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JLabel;

import businesslogic.managerbl.Manager;

public class FacultyLabel {
	// "  欢迎进入NJWU教务系统！ "+id
	public JLabel getWelcome(String id) {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		int frameWidth;
		int frameHeight;
		if (screenWidth * 3 > screenHeight * 4) {
			frameWidth = screenHeight * 2 / 3;
			frameHeight = screenHeight / 2;

		} else {
			frameWidth = screenWidth / 4;
			frameHeight = screenWidth * 3 / 4;
		}
		String name = new Manager(id).getName();

		JLabel welcome = new JLabel("  欢迎进入NJWU教务系统！ " + name + " 老师");
		welcome.setFont(new Font("微软雅黑", 0, 17));
		welcome.setBounds(frameWidth * 7 / 24, frameHeight / 30,
				frameWidth * 2 / 3, frameHeight / 10);
		return welcome;
	}
}
