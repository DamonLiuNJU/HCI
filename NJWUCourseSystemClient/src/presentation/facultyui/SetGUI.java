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

import net.miginfocom.swing.MigLayout;
import presentation.planui.PlanList;
import vo.managervo.ManagerVO;
import businesslogic.managerbl.Manager;

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
		infoPanel = new JPanel();

		JLabel portrait = getPortrait();
		// JButton changePorBut=getChangePorBut();

		JLabel name = new JLabel("    姓名:    ");
		name.setFont(new Font("微软雅黑", 0, 14));
		name.setBounds(frameWidth / 6, frameHeight / 10, frameWidth / 10,
				frameHeight / 20);
		nameTxt = new JTextField(15);
		nameTxt.setBounds(frameWidth * 12 / 40, frameHeight / 10,
				frameWidth / 5, frameHeight / 20);
		nameTxt.setEditable(false);
		JLabel facultyID = new JLabel("所属院系:");
		facultyID.setFont(new Font("微软雅黑", 0, 14));
		facultyID.setBounds(frameWidth / 6, frameHeight / 5, frameWidth / 10,
				frameHeight / 20);
		facultyBox = new PlanList().getFacultyComboBox();
		facultyBox.setBounds(frameWidth * 12 / 40, frameHeight / 5,
				frameWidth / 5, frameHeight / 20);
		facultyBox.setEnabled(false);
		JLabel contactInfo = new JLabel("联系方式:");
		contactInfo.setFont(new Font("微软雅黑", 0, 14));
		contactInfo.setBounds(frameWidth / 6, frameHeight * 15 / 50,
				frameWidth / 10, frameHeight / 20);
		contactInfoTxt = new JTextField(15);
		contactInfoTxt.setBounds(frameWidth * 12 / 40, frameHeight * 15 / 50,
				frameWidth / 5, frameHeight / 20);

		JButton confirmBut = getConfirmBut();
		JButton cancelBut = getCancelBut();
		infoPanel.setBounds(frameWidth / 4, frameHeight / 5,
				frameWidth * 2 / 3, frameHeight * 2 / 3);
		infoPanel.setLayout(null);
		infoPanel.add(portrait);
		// infoPanel.add(changePorBut);
		infoPanel.add(name);
		infoPanel.add(facultyID);
		infoPanel.add(contactInfo);

		infoPanel.add(confirmBut);
		infoPanel.add(cancelBut);
	}

	void initialPswPanel() {
		pswPanel = new JPanel();
		JLabel old = new JLabel("        旧密码：       ");
		old.setFont(new Font("微软雅黑", 0, 17));
		JLabel newPsw = new JLabel("        新密码：       ");
		newPsw.setFont(new Font("微软雅黑", 0, 17));
		JLabel again = new JLabel("再次输入新密码：");
		again.setFont(new Font("微软雅黑", 0, 17));
		oldContent = new JPasswordField(10);
		newContent = new JPasswordField(10);
		againContent = new JPasswordField(10);

		JButton comfirmBut = new JButton("     确认     ");
		comfirmBut.setFont(new Font("微软雅黑", 0, 13));
		JButton cancelBut = new JButton("     取消     ");
		cancelBut.setFont(new Font("微软雅黑", 0, 13));
		comfirmBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newPsw = new String(newContent.getPassword());
				String againPsw = new String(againContent.getPassword());
				String oldPsw = new String(oldContent.getPassword());
				if (!newPsw.equals(againPsw)) {
					GUIHelper.sendMessage("两次输入的新密码不一致！");
					newContent.setText("");
					againContent.setText("");
				} else {
					Manager manager = new Manager(ID);
					if (oldPsw.equals(manager.getPassword())) {
						manager.changePassword(oldPsw);
						GUIHelper.sendMessage("修改密码成功！");
						clear();
					} else {
						clear();
						GUIHelper.sendMessage("原密码不正确！");

					}
				}

			}
		});
		cancelBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		pswPanel.setLayout(new MigLayout("wrap 2"));

		pswPanel.add(old, "gapleft 90");
		pswPanel.add(oldContent);
		pswPanel.add(newPsw, "gapleft 90");
		pswPanel.add(newContent);
		pswPanel.add(again, "gapleft 90");
		pswPanel.add(againContent);
		pswPanel.add(comfirmBut, "gapleft 120");
		pswPanel.add(cancelBut, "gapleft 20");
		pswPanel.setBounds(frameWidth / 4, frameHeight / 5, frameWidth * 2 / 3,
				frameHeight * 2 / 3);

	}

	void updateInfoPanel() {
		Manager manager = new Manager(ID);
		ManagerVO mv = manager.getManagerInfo();
		nameTxt.setText(mv.getName());
		System.out.println(mv.getFacultyName());
		facultyBox.setSelectedItem(mv.getFacultyName());
		contactInfoTxt.setText(mv.getContactInfo());

	}

	JPanel getPswPanel() {
		clear();
		pswPanel.setOpaque(false);	
		return pswPanel;
	}

	JPanel getInfoPanel() {
		updateInfoPanel();
		infoPanel.add(facultyBox);
		infoPanel.add(contactInfoTxt);
		infoPanel.add(nameTxt);
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
			public void actionPerformed(ActionEvent e) {

			}
		});
		return changePortrait;
	}

	JButton getConfirmBut() {
		JButton confirm = new JButton("确认");
		confirm.setFont(new Font("微软雅黑", 0, 12));
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Manager(ID).setContactInfo(contactInfoTxt.getText());
				GUIHelper.sendMessage("个人信息修改成功！");
			}
		});
		confirm.setBounds(frameWidth / 5, frameHeight * 8 / 20, frameWidth / 8,
				frameHeight / 20);
		return confirm;
	}

	JButton getCancelBut() {
		JButton cancel = new JButton("取消");
		cancel.setFont(new Font("微软雅黑", 0, 10));
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateInfoPanel();
				facultyBox.updateUI();
				infoPanel.revalidate();
				infoPanel.repaint();
			}
		});
		cancel.setBounds(frameWidth * 2 / 5, frameHeight * 8 / 20,
				frameWidth / 8, frameHeight / 20);
		return cancel;
	}

	// 密码界面 ：清空输入TxtField
	void clear() {
		oldContent.setText("");
		newContent.setText("");
		againContent.setText("");
	}

}
