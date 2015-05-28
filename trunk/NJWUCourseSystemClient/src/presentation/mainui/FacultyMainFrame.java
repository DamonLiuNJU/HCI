package presentation.mainui;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.AutumnSkin;
import org.jvnet.substance.theme.SubstanceOliveTheme;
import org.jvnet.substance.watermark.SubstanceBubblesWatermark;

import presentation.courseselectionui.FCourseSelectTriggerButton;
import presentation.facultyui.FacultyFunctionFrame;
import presentation.facultyui.FacultyUIImage;
import presentation.facultyui.GUIHelper;
import presentation.facultyui.PublicComponent;
import presentation.managerui.FacultyLabel;
import presentation.managerui.MessageGUI;
@SuppressWarnings("serial")
public class FacultyMainFrame extends JFrame implements FacultyUIImage {
	static {
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
	};

	String id;
	JFrame mainFrame;
	int frameWidth;
	int frameHeight;
	PublicComponent pc;
	ImageIcon background;
	ImageIcon msgBackground;
	MessageGUI msgGUI;

	public static void main(String args[]) {
		FacultyMainFrame fmf = new FacultyMainFrame("100101");
		fmf.start();
	}

	public FacultyMainFrame(String ID) {
		id = ID;
		pc = new PublicComponent(ID);
		int screenWidth = GUIHelper.getScreenWidth();
		int screenHeight = GUIHelper.getScreenHeight();
		frameWidth = 3 * GUIHelper.getFrameWidth() / 2;
		frameHeight = 3 * GUIHelper.getFrameHeight() / 2;

		mainFrame = new JFrame("院系教务老师");

		mainFrame.setBounds((screenWidth - frameWidth) / 2,
				(screenHeight - frameHeight) / 2, frameWidth, frameHeight);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addBackground(bg);// ./icon/faculty/background.jpg
		msgGUI = new MessageGUI(ID);
	}

	void addBackground(String path) {

		background = new ImageIcon(path);// 背景图片
		background = new ImageIcon(background.getImage().getScaledInstance(
				frameWidth, frameHeight, Image.SCALE_SMOOTH));
		JLabel label1 = new JLabel(background);// 把背景图片显示在一个标签里面

		// 把标签的大小位置设置为图片刚好填充整个面板
		label1.setBounds(0, 0, background.getIconWidth(),
				background.getIconHeight());

		// 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
		JPanel imagePanel1 = (JPanel) mainFrame.getContentPane();
		imagePanel1.setOpaque(false);
		mainFrame.getLayeredPane().setLayout(null);

		// 把背景图片添加到分层窗格的最底层作为背景
		mainFrame.getLayeredPane().add(label1, new Integer(Integer.MIN_VALUE));
	}

	public void start() {
		mainFrame.setLayout(null);
		mainFrame.add(pc.getPortrait());
		mainFrame.add(new FacultyLabel().getWelcome(id));
		mainFrame.add(getPlanBut());
		mainFrame.add(getCourseBut());
		mainFrame.add(getStudentBut());
		if (ifMain()) {
			mainFrame.add(new FCourseSelectTriggerButton(id).getTriggerBut(id));
		}

		mainFrame.add(getMsgPanel());
		mainFrame.add(getToolMenuBar());
		// mainFrame.setIconImage(mainFrame.getToolkit().getImage("./icon/faculty/frameIcon.jpg"));
		mainFrame.setVisible(true);
	}

	// 主菜单按钮
	JButton getPlanBut() {
		JButton planBut = pc.getButton(planIcon, "教学计划", 70, 70);
		planBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FacultyFunctionFrame fff = new FacultyFunctionFrame(id);
				fff.planGui();
				mainFrame.dispose();
			}
		});

		planBut.setBounds(frameWidth * 6 / 24, frameHeight / 5, 70, 95);
		return planBut;
	}

	JButton getCourseBut() {
		JButton courseBut = pc.getButton(courseIcon, "课程", 70, 70);
		courseBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FacultyFunctionFrame fff = new FacultyFunctionFrame(id);
				fff.courseGui();
				mainFrame.dispose();
			}
		});
		courseBut.setBounds(frameWidth * 9 / 24, frameHeight / 5, 70, 95);
		return courseBut;
	}

	JButton getStudentBut() {
		JButton studentBut = pc.getButton(studentIcon, "学生", 70, 70);
		studentBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FacultyFunctionFrame fff = new FacultyFunctionFrame(id);
				fff.studentGui();
				mainFrame.dispose();
			}
		});
		studentBut.setBounds(frameWidth * 12 / 24, frameHeight / 5, 70, 95);
		return studentBut;
	}

	// 左上工具栏：设置&帮助&消息&退出
	JMenuBar getToolMenuBar() {
		JMenuBar mb = new JMenuBar();
		mb.setOpaque(false);

		ImageIcon icon1 = new ImageIcon(setIcon);
		JMenu setMenu = new JMenu();
		setMenu.setIcon(icon1);
		setMenu.setOpaque(false);
		JMenuItem pswMenuItem = pc.getPswMenuItem();
		JMenuItem infoMenuItem = pc.getInfoMenuItem();
		setMenu.add(infoMenuItem);
		setMenu.add(pswMenuItem);

		ImageIcon icon2 = new ImageIcon(helpIcon);
		JMenu helpMenu = new JMenu();
		helpMenu.setIcon(icon2);
		helpMenu.setOpaque(false);

		JMenuItem aboutMenuItem = pc.getAboutMenuItem();
		JMenuItem replyMenuItem = pc.getReplyMenuItem();

		helpMenu.add(aboutMenuItem);
		helpMenu.add(replyMenuItem);

		JMenuItem exitMenuItem = getExitMenuItem();

		JMenuItem msgMenuItem = pc.getMsgMenuItem();

		mb.add(setMenu);
		mb.add(helpMenu);
		mb.add(msgMenuItem);
		mb.add(exitMenuItem);
		mb.setBounds(frameWidth * 6 / 8, frameHeight / 17,
				frameWidth * 11 / 60, frameHeight / 20);

		// 菜单事件
		pswMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FacultyFunctionFrame fff = new FacultyFunctionFrame(id);
				fff.changePswGui();
				mainFrame.dispose();
			}
		});
		infoMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FacultyFunctionFrame fff = new FacultyFunctionFrame(id);
				fff.changeInfoGui();
				mainFrame.dispose();
			}

		});

		return mb;
	}

	public JMenuItem getExitMenuItem() {
		JMenuItem exitMenuItem = new JMenuItem("", KeyEvent.VK_E);
		exitMenuItem.setOpaque(false);
		ImageIcon icon = new ImageIcon(exitIcon);
		exitMenuItem.setIcon(icon);
		exitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// logout
				mainFrame.dispose();
				new LoginUI();
			}
		});
		return exitMenuItem;
	}

	// 消息框
	JPanel getMsgPanel() {
		Color txt = new Color(0X000000);
		Color back = new Color(0Xaba16c);
		JPanel msg = msgGUI.getMsgPanel(txt, back);
		msg.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.GRAY, 2), "新消息",
				TitledBorder.LEFT, TitledBorder.TOP));
		msg.setBounds(frameWidth * 10 / 70, frameHeight * 19 / 40,
				frameWidth * 13 / 20, frameHeight / 4);
		return msg;
	}

	// 检测是否为主教务员
	boolean ifMain() {
		char five = id.charAt(4);
		char six = id.charAt(5);
		if (five == '0' && six == '1') {
			return true;
		} else {
			return false;
		}
	}

}
