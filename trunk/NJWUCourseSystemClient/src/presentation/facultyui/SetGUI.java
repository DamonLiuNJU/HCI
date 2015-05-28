package presentation.facultyui;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;

import presentation.managerui.ChangePWPane;
import presentation.managerui.ManagerInfoPane;


public class SetGUI {
	String ID;
	int frameWidth;
	int frameHeight;

	JPasswordField oldContent;
	JPasswordField newContent;
	JPasswordField againContent;

	JTextField nameTxt;
	JComboBox<String> facultyBox;
	JTextField contactInfoTxt;

	JTable infoTable;
	JPanel infoPanel;
JPanel pswPanel;

	public SetGUI(String id) {
		ID = id;
		frameWidth = 3 * GUIHelper.getFrameWidth() / 2;
		frameHeight = 3 * GUIHelper.getFrameHeight() / 2;
		initialInfoPanel();
		initialPswPanel();

	}

	void initialInfoPanel() {
    	
		infoPanel = new ManagerInfoPane(ID).getManagerInfoPane();
		infoPanel.setBounds(frameWidth / 10000, frameHeight / 10, frameWidth ,
				frameHeight );
	
	}

	void initialPswPanel() {
		pswPanel = new ChangePWPane(ID).getChangePWPane();
		pswPanel.setBounds(frameWidth / 10000, frameHeight / 10, frameWidth ,
				frameHeight );
	}

	JPanel getPswPanel() {
		//clear();
		pswPanel.setOpaque(false);	
		return pswPanel;
	}

	JPanel getInfoPanel() {
		infoPanel.setOpaque(false);	
		return infoPanel;
	}

	JLabel getPortrait() {
		ImageIcon portraitIcon = new ImageIcon("./icon/faculty/portrait.png");
		portraitIcon = new ImageIcon(portraitIcon.getImage().getScaledInstance(
				frameWidth / 10, frameHeight / 8, Image.SCALE_SMOOTH));
		JLabel portrait = new JLabel(portraitIcon);
		portrait.setBounds(frameWidth / 30, frameHeight / 20, frameWidth / 10,
				frameHeight / 8);
		return portrait;
	}

	// changeInfo
	JButton getChangePorBut() {
		JButton changePortrait = new JButton("修改头像");
		changePortrait.setFont(new Font("微软雅黑", 0, 10));
		changePortrait.setBounds(frameWidth / 30, frameHeight * 11 / 60,
				frameWidth / 10, frameHeight / 20);
		changePortrait.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		return changePortrait;
	}


	
}
