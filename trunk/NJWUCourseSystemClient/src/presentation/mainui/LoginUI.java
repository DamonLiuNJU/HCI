package presentation.mainui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Enumeration;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.FontUIResource;

import net.miginfocom.swing.MigLayout;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.AutumnSkin;
import org.jvnet.substance.theme.SubstanceOliveTheme;
import org.jvnet.substance.watermark.SubstanceBubblesWatermark;

import presentation.tools.OutputHelper;
import presentation.tools.Setter;
import presentation.tools.UIConstants;
import presentation.tools.UserType;
import presentation.tools.ViewReplyMessage;
import vo.studentvo.StudentInfoVO;
import businesslogic.managerbl.Admin;
import businesslogic.managerbl.Manager;
import businesslogic.studentbl.StudentInfo;
import businesslogic.teacherbl.Teacher;

public class LoginUI implements ViewReplyMessage {
	JFrame frame;
	JPanel p;
	JComboBox<UserType> roleBox;
	JTextField tf;
	JPasswordField pf;

	OutputHelper helper = new OutputHelper();

	public LoginUI() {
		frame = new JFrame("NJWU学生选课系统-登录");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setIconImage(new ImageIcon("./icon/student/mark.png").getImage());
		String backPath = "./icon/login.png";
		new Setter().addBackground(frame, backPath);
		frame.setLayout(new MigLayout());

		p = new JPanel(new MigLayout());
		p.setOpaque(false);
		JLabel roleLabel = new JLabel("用户类型");
		UserType[] users = UserType.values();
		roleBox = new JComboBox<UserType>(users);
		roleBox.setPreferredSize(new Dimension(200, 20));
		JLabel idlabel = new JLabel("用户名");
		tf = new JTextField(25);
		JLabel pwlabel = new JLabel("密码");
		pf = new JPasswordField(25);

		JPanel p1 = new JPanel(new MigLayout());
		p1.setOpaque(false);
		JButton button1 = new JButton("登录");

		String s = "Copyright © 2013 All Rights Reserved.版权所有：Swing.";
		JLabel l = new JLabel(s);

		tf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == '\n') {
					login();
				}
			}
		});

		pf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == '\n') {
					login();
				}
			}
		});
		JButton button2 = new JButton("重置");
		button1.addActionListener(new BListener());
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				roleBox.setSelectedIndex(0);
				tf.setText("");
				pf.setText("");
			}
		});
		p1.add(button1);
		p1.add(button2, "gapleft 70");

		p.add(roleLabel);
		p.add(roleBox, "gapleft 20,wrap");
		p.add(idlabel);
		p.add(tf, "gapleft 20,wrap");
		p.add(pwlabel);
		p.add(pf, "gapleft 20,wrap");

		frame.add(p, "gapleft 110,gapright 100,gaptop 110,wrap");
		frame.add(p1, "gapleft 130,wrap");
		frame.add(l, "gaptop 45,gapleft 105");
		frame.setSize(500, 350);
		int windowwedth = frame.getWidth();
		int windowheight = frame.getHeight();
		int screenwedth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenheight = Toolkit.getDefaultToolkit().getScreenSize().height;
		frame.setLocation((screenwedth - windowwedth) / 2,
				(screenheight - windowheight) / 2);
		frame.setVisible(true);
	}

	class BListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			login();
		}
	}

	void login() {
		try {
			UserType type = change(roleBox.getSelectedItem().toString());
			String userid = tf.getText();
			char[] password = pf.getPassword();
			String pass = "";
			for (int i = 0; i < password.length; i++) {
				pass += password[i];
			}
			boolean valid;
			int login = 0;// 登陆验证类型：0 用户不存在，1 密码错误，2 成功

			IDFormat format = new IDFormat(type);

			if (type == UserType.管理员 || type == UserType.教务处老师
					|| type == UserType.院系教务老师) {
				login = new Manager().login(userid, pass);
				if (login == 0 || !format.isFormatValid(userid)) {
					login = 0;
					helper.outputToDialog(ID_NOT_EXIST);
				} else if (login == 1) {
					helper.outputToDialog(PW_ERROR);
				}
			}

			switch (type) {
			case 管理员:
				if (login == 2) {
					frame.dispose();
					new AdminMainUI(new Admin());
				}
				break;
			case 教务处老师:
				if (login == 2) {
					frame.dispose();
					new DeanMainUI(userid);
				}
				break;
			case 院系教务老师:
				if (login == 2) {
					frame.dispose();
					new FacultyMainFrame(userid).start();
				}
				break;
			case 任课教师:
				Teacher teacher = new Teacher(userid, pass);
				if (teacher.isVaild() == 0) {
					teacher.initTeacher();
					new TeacherMainUI(teacher);
					frame.dispose();
				} else if (teacher.isVaild() == 1) {
					helper.outputToDialog(ID_NOT_EXIST);
				} else if (teacher.isVaild() == 2) {
					helper.outputToDialog(PW_ERROR);
				} else {
					helper.outputToDialog("与服务器连接失败");
				}
				break;
			case 学生:
				StudentInfoVO siv = new StudentInfoVO();
				siv.setID(userid);
				siv.setKey(password);
				valid = new StudentInfo().isKeyValid(siv);
				if (valid) {
					frame.dispose();
					JFrame f = new StudentMainUI().createFrame(userid, frame);
					f.setVisible(true);
				} else {
					helper.outputToDialog("Wrong PassWord !");
				}
				break;

			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "与服务器连接失败");
		}
	}

	public UserType change(String s) {
		UserType type = null;
		switch (s) {
		case "管理员":
			type = UserType.管理员;
			break;
		case "教务处老师":
			type = UserType.教务处老师;
			break;
		case "院系教务老师":
			type = UserType.院系教务老师;
			break;
		case "任课教师":
			type = UserType.任课教师;
			break;
		case "学生":
			type = UserType.学生;
			break;
		}
		return type;
	}

	public static void main(String[] args) {
		try {
			try {
				UIManager.setLookAndFeel(new SubstanceLookAndFeel());
			} catch (UnsupportedLookAndFeelException ex) {
				System.out.println(ex.getMessage());
			}
		} catch (Exception et) {
			System.out.println(et.getMessage());
		}
		SubstanceLookAndFeel.setSkin(new AutumnSkin());
		SubstanceLookAndFeel
				.setCurrentWatermark(new SubstanceBubblesWatermark());
		SubstanceLookAndFeel.setCurrentTheme(new SubstanceOliveTheme());
		InitGlobalFont(UIConstants.DEFAULT_FONT); // 统一设置字体
		new LoginUI();
	}

	private static void InitGlobalFont(Font font) {
		FontUIResource fontRes = new FontUIResource(font);
		for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys
				.hasMoreElements();) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof FontUIResource) {
				UIManager.put(key, fontRes);
			}
		}
	}
}
