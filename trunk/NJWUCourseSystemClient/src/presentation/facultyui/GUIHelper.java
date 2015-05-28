package presentation.facultyui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class GUIHelper {
	public static int getScreenWidth() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		return screenWidth;
	}

	public static int getScreenHeight() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		return screenHeight;
	}

	public static int getFrameWidth() {
		int frameWidth;
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		if (screenWidth * 3 > screenHeight * 4) {
			frameWidth = screenHeight * 2 / 3;
		} else {
			frameWidth = screenWidth / 4;
		}
		return frameWidth;
	}

	public static int getFrameHeight() {
		int frameHeight;
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		if (screenWidth * 3 > screenHeight * 4) {
			frameHeight = screenHeight / 2;
		} else {
			frameHeight = screenWidth * 3 / 4;
		}
		return frameHeight;
	}

	public static void sendMessage(String a) {
		JDialog infoDialog = new JDialog();
		infoDialog.setBounds(600, 300, 200, 100);
		infoDialog.setVisible(true);
		JLabel infoLabel = new JLabel(a);
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setVisible(true);
		infoDialog.add(infoLabel);
	}

}
