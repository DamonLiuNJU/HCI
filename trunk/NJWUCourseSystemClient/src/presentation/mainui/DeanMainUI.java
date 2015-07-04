package presentation.mainui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;
import presentation.courseui.CourseStatInfoPane;
import presentation.deanui.DeanUIImage;
import presentation.deanui.MainAuditPane;
import presentation.deanui.MainFramePane;
import presentation.deanui.PlanPane;
import presentation.managerui.ChangePWPane;
import presentation.managerui.ManagerInfoPane;
import presentation.managerui.MessageInfoFrame;
import presentation.teacherui.TeacherStatInfoPane;
import presentation.tools.PublicComponents;
import presentation.tools.Setter;
import presentation.tools.UIConstants;
import businesslogic.managerbl.Manager;

public class DeanMainUI implements DeanUIImage {
	JFrame deanFrame;
	JPanel pa;// 盛放多标签页的面板及各种设置面板的最底层面板
	JTabbedPane panel; // contains all tab pane
	JPanel home; // 首页
	JTabbedPane framePane; // 框架策略面板
	JPanel planPane; // 院系教学计划面板
	JPanel teacherInfoPane; // 教师统计信息面板
	JPanel courseInfoPane; // 课程统计信息面板
	JTabbedPane auditPane; // 资格审核面板

	PublicComponents comp = new PublicComponents();// 获取一个自定义通用组件对象
	Setter setter = new Setter();

	String id;// 当前用户id

	String item1 = "框架策略";
	String item2 = "院系教学计划";
	String item3 = "教师信息";
	String item4 = "课程信息";
	String item5 = "审核与管理";

	public DeanMainUI(String id) {
		this.id = id;

		deanFrame = new JFrame("NJWUCS-教务处老师");
		deanFrame.setSize(850, 600);
		deanFrame.setLocation(300, 100);
		deanFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		deanFrame.setResizable(false);

		String backPath = background;
		setter.addBackground(deanFrame, backPath);

		JPanel p0 = getUpPane();
		p0.setBounds(0, 0, 850, 90);

		pa = new JPanel();
		pa.setBounds(0, 80, 850, 490);
		pa.setLayout(null);
		pa.setOpaque(false);
		showMainPane();

		deanFrame.setLayout(null);
		deanFrame.add(p0);
		deanFrame.add(pa);

		deanFrame.setVisible(true);
	}

	public void showMainPane() {
		pa.removeAll();
		home = drawHomePage();
		framePane = new MainFramePane();
		planPane = new PlanPane(id);
		teacherInfoPane = new TeacherStatInfoPane();
		courseInfoPane = new CourseStatInfoPane();
		auditPane = new MainAuditPane(id);

		panel = getJTabbedPane();
		panel.setOpaque(false);
		panel.setBounds(0, 0, 850, 500);

		pa.add(panel);
		deanFrame.repaint();
	}

	// 首页
	public JPanel drawHomePage() {
		JPanel home = new JPanel(new MigLayout("insets 40 20 40 20"));
		home.setOpaque(false);
		home.setSize(850, 475);

		// 一组按钮
		JButton[] button = new JButton[5];

		ImageIcon img0 = new ImageIcon(frameItem);
		ImageIcon img1 = new ImageIcon(planItem);
		ImageIcon img2 = new ImageIcon(teachStat);
		ImageIcon img3 = new ImageIcon(courseStat);
		ImageIcon img4 = new ImageIcon(qualifyItem);

		button[0] = new JButton(item1, img0);
		button[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.setSelectedComponent(framePane);
			}
		});
		button[1] = new JButton(item2, img1);
		button[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.setSelectedComponent(planPane);
			}
		});

		button[2] = new JButton(item3, img2);
		button[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.setSelectedComponent(teacherInfoPane);
			}
		});

		button[3] = new JButton(item4, img3);
		button[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.setSelectedComponent(courseInfoPane);
			}
		});

		button[4] = new JButton(item5, img4);
		button[4].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.setSelectedComponent(auditPane);
			}
		});
		for (int i = 0; i < button.length; i++) {
			setter.setButtonWithImage(button[i]);
		}

		home.add(button[0], "gapleft 60,gaptop 25");
		home.add(button[1], "gapleft 40,wrap");
		home.add(button[2], "gaptop 35,gapleft 60");
		home.add(button[3], "gaptop 35,gapleft 40");
		home.add(button[4], "gaptop 35,gapleft 40");
		return home;
	}

	// 切换各标签页的panel
	private JTabbedPane getJTabbedPane() {

		JTabbedPane tabpane = new JTabbedPane(SwingConstants.TOP,
				JTabbedPane.SCROLL_TAB_LAYOUT);
		tabpane.setOpaque(false);//

		String title0 = "首页<<";
		ImageIcon icon0 = new ImageIcon(homeItem);
		tabpane.addTab(title0, icon0, home, "");

		String title1 = item1;
		ImageIcon icon1 = new ImageIcon(frameItem1);
		tabpane.addTab(title1, icon1, framePane, "");

		String title2 = item2;
		ImageIcon icon2 = new ImageIcon(planItem1);
		tabpane.addTab(title2, icon2, planPane, "");

		String title3 = item3;
		ImageIcon icon3 = new ImageIcon(teachStat1);
		tabpane.addTab(title3, icon3, teacherInfoPane, "");

		String title4 = item4;
		ImageIcon icon4 = new ImageIcon(courseStat1);
		tabpane.addTab(title4, icon4, courseInfoPane, "");

		String title5 = item5;
		ImageIcon icon5 = new ImageIcon(qualifyItem1);
		tabpane.addTab(title5, icon5, auditPane, "");

		return tabpane;
	}

	// 教务处老师界面最上层一栏面板
	public JPanel getUpPane() {
		JPanel p0 = new JPanel(null);
		p0.setOpaque(false);
		// JLabel label = new JLabel("欢迎 ");
		// label.setFont(new Font("华文楷体", Font.BOLD, 18));
		// HeadInfoLabel head=new HeadInfoLabel(id);
		// JLabel label0=head.getDeanImageLabel();
		// JLabel label1=head.getNameLabel();

		// p0.add(getMenuBar(),"split, span,gapleft 700,wrap");
		JMenuBar temp = getMenuBar();
		p0.add(temp);
		temp.setBounds(UIConstants.WINDOWWIDTH-temp.getSize().width, 0, temp.getSize().width, temp.getSize().height);
		// p0.add(PublicComponents.getTimeLabel(UIConstants.DEAN, new
		// Manager(id).getName()),"gapleft 700,wrap");
		JLabel timelabel = PublicComponents.getTimeLabel(UIConstants.DEAN,
				new Manager(id).getName());
		p0.add(timelabel);
		timelabel.setBounds(5, 0, 200, 40);
		// p0.add(label, "split");
		// p0.add(label0);
		// p0.add(label1);
		p0.add(new JSeparator(), "growx, wrap");
		return p0;
	}

	// 置于界面右上角的工具栏
	private JMenuBar getMenuBar() {
		JMenuBar mb = new JMenuBar();
		mb.setOpaque(false);
		mb.setSize(200, 40);
		JMenu setMenu = new JMenu();
		setMenu.setOpaque(false);
		ImageIcon icon1 = new ImageIcon(setIcon);
		setMenu.setIcon(icon1);
		JMenuItem infoMenuItem = new JMenuItem("个人信息", KeyEvent.VK_I);
		JMenuItem pwMenuItem = new JMenuItem("修改密码", KeyEvent.VK_D);
		setMenu.add(infoMenuItem);
		setMenu.add(pwMenuItem);

		JMenuItem messageMenuItem = new JMenuItem("", KeyEvent.VK_E);
		messageMenuItem.setOpaque(false);
		ImageIcon icon4 = new ImageIcon(letterIcon);
		messageMenuItem.setIcon(icon4);

		JMenu helpMenu = new JMenu();
		helpMenu.setOpaque(false);
		ImageIcon icon2 = new ImageIcon(helpIcon);
		helpMenu.setIcon(icon2);
		JMenuItem aboutMenuItem = new JMenuItem("关于");
		JMenuItem replyMenuItem = new JMenuItem("反馈", KeyEvent.VK_R);
		helpMenu.add(aboutMenuItem);
		helpMenu.add(replyMenuItem);

		JMenuItem exitMenuItem = new JMenuItem("", KeyEvent.VK_E);
		exitMenuItem.setOpaque(false);
		ImageIcon icon3 = new ImageIcon(exitIcon);
		exitMenuItem.setIcon(icon3);

		mb.add(setMenu);
		mb.add(messageMenuItem);
		mb.add(helpMenu);
		mb.add(exitMenuItem);

		// 菜单事件
		infoMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showDeanInfoPane();
			}
		});

		pwMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showChangePWPane();
			}
		});

		aboutMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame aboutFrame = comp.getAboutFrame();
				aboutFrame.setVisible(true);
			}
		});

		replyMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame replyFrame = comp.getReplyFrame();
				replyFrame.setVisible(true);
			}
		});

		exitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deanFrame.dispose();
				new LoginUI();
			}
		});

		messageMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame messageFrame = new MessageInfoFrame(id);
				messageFrame.setVisible(true);
			}
		});

		return mb;
	}

	private void showDeanInfoPane() {
		pa.removeAll();

		ImageIcon icon1 = new ImageIcon(returnButton);
		JButton return_b = new JButton(icon1);
		return_b.setBounds(382, 350, 60, 20);
		return_b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showMainPane();
			}
		});

		JPanel deanInfoPane = new ManagerInfoPane(id)
				.getManagerInfoPane(return_b);
		deanInfoPane.setBounds(0, 0, 850, 350);
		pa.add(deanInfoPane);

		deanFrame.repaint();
	}

	private void showChangePWPane() {
		pa.removeAll();

		ImageIcon icon1 = new ImageIcon(returnButton);
		JButton return_b = new JButton(icon1);
		return_b.setBounds(382, 350, 60, 20);
		return_b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showMainPane();
			}
		});

		JPanel changePWPane = new ChangePWPane(id).getChangePWPane(return_b);
		changePWPane.setBounds(0, 0, 850, 350);
		pa.add(changePWPane);

		deanFrame.repaint();
	}
}
