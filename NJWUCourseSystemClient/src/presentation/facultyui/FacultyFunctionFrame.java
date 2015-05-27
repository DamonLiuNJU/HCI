package presentation.facultyui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.BoxLayout;
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
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.AutumnSkin;
import org.jvnet.substance.skin.SubstanceSaharaLookAndFeel;
import org.jvnet.substance.theme.SubstanceOliveTheme;
import org.jvnet.substance.watermark.SubstanceBubblesWatermark;
import presentation.mainui.FacultyMainFrame;
import presentation.mainui.LoginUI;
import businesslogic.coursebl.Course;
import businesslogicservice.courseblservice.CourseBLService;

public class FacultyFunctionFrame implements FacultyUIImage{
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
		SubstanceSaharaLookAndFeel.setSkin(new AutumnSkin());
		SubstanceLookAndFeel
				.setCurrentWatermark(new SubstanceBubblesWatermark());
		// SubstanceLookAndFeel.setCurrentTitlePainter(new
		// Glass3DDecorationPainter());

		// SubstanceLookAndFeel.setCurrentTheme(new SubstanceLightAquaTheme());

		SubstanceLookAndFeel.setCurrentTheme(new SubstanceOliveTheme());

		// SubstanceLookAndFeel.setCurrentTheme(new SubstanceJadeForestTheme());

	};

	public static void main(String args[]) {
		FacultyFunctionFrame fff = new FacultyFunctionFrame("100101");
		fff.courseGui();
	}

	JFrame functionFrame;
	JPanel functionPanel;

	String ID;
	int frameWidth;
	int frameHeight;

	SetGUI sg;
	StudentGUI stuG;
	PlanGUI pg;
	CourseGUI cg;
	PublicComponent pc;

	JPanel setButMenu;
	JPanel planButMenu;
	JPanel studentButMenu;
	JPanel courseButMenu;
	ImageIcon background;

	void addBackground(String path) {

		background = new ImageIcon(path);// 背景图片
		background= new ImageIcon(background.getImage().getScaledInstance(frameWidth,frameHeight ,
				Image.SCALE_SMOOTH));
		JLabel label1 = new JLabel(background);// 把背景图片显示在一个标签里面

		// 把标签的大小位置设置为图片刚好填充整个面板
		label1.setBounds(0, 0, background.getIconWidth(),
				background.getIconHeight());

		// 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
		JPanel imagePanel1 = (JPanel) functionFrame.getContentPane();
		imagePanel1.setOpaque(false);
		functionFrame.getLayeredPane().setLayout(null);

		// 把背景图片添加到分层窗格的最底层作为背景
		functionFrame.getLayeredPane().add(label1,
				new Integer(Integer.MIN_VALUE));
	}

	public FacultyFunctionFrame(String id) {
		ID = id;
		pc = new PublicComponent(id);
		sg = new SetGUI(ID);
		functionFrame = new JFrame("院系教务老师");
		int screenWidth = GUIHelper.getScreenWidth();
		int screenHeight = GUIHelper.getScreenHeight();
		frameWidth = 3 * GUIHelper.getFrameWidth() / 2;
		frameHeight = 3 * GUIHelper.getFrameHeight() / 2;

		functionFrame.setBounds((screenWidth - frameWidth) / 2,
				(screenHeight - frameHeight) / 2, frameWidth, frameHeight);
		functionFrame.setResizable(false);
		functionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addBackground(bg);//./icon/faculty/subBackground.jpg
		functionFrame.setLayout(null);
		functionFrame.add(getGuideLine());
		functionFrame.add(getMenuBar());

		setButMenu = getSetButMenu(50, 45);
		setButMenu.setBackground(new Color(0Xd8dbda));
		planButMenu = getPlanButMenu(50, 45);
		planButMenu.setBackground(new Color(0Xd8dbda));
		studentButMenu = getStudentButMenu(50, 45);
		studentButMenu.setBackground(new Color(0Xd8dbda));
		courseButMenu = getCourseButMenu(50, 45);
		courseButMenu.setBackground(new Color(0Xd8dbda));
	}

	public void planGui() {
		functionFrame.add(planButMenu);
		pg = new PlanGUI(ID);

		Course course = new Course();
		System.out.println(course.testStatus("plan"));
		if (course.testStatus("plan")) {
		functionPanel = pg.getCreatePanel();
		} else {
			functionPanel = new JPanel();
			JLabel warning = new JLabel("不在创建教学计划的时间范围内！");
			functionPanel.setBounds(frameWidth / 4, frameHeight / 5,
					frameWidth * 2 / 3, frameHeight * 2 / 3);
			functionPanel.add(warning);
		}
		// 教学计划默认界面为：创建界面
		functionPanel.setOpaque(false);	
		
		functionFrame.add(functionPanel);

		functionFrame.setVisible(true);
	}

	public void courseGui() {
		functionFrame.add(courseButMenu);
		cg = new CourseGUI(ID);

		// 课程默认界面为发布课程界面
		Course course = new Course();
		if (course.testStatus("course")) {
			functionPanel = cg.getPublishPanel();

		} else {

			functionPanel = new JPanel();
			functionPanel.setBounds(frameWidth / 4, frameHeight / 5,
					frameWidth * 2 / 3, frameHeight * 2 / 3);
			JLabel warning = new JLabel("不在创建课程的时间范围内！");
			functionPanel.add(warning);

		}
		functionPanel.setOpaque(false);	
		functionFrame.add(functionPanel);
		functionFrame.setVisible(true);
	}

	public void studentGui() {
		functionFrame.add(studentButMenu);
		stuG = new StudentGUI(ID);

		// 查看学生默认界面为：查询课程界面

		functionPanel = pc.getSearchPanel(false);
		functionPanel.add(getSearchStuBut());
		functionPanel.setOpaque(false);	
		functionFrame.add(functionPanel);
		functionFrame.setVisible(true);
	}

	public void changePswGui() {
		functionFrame.add(setButMenu);
		functionPanel = sg.getPswPanel();
		functionPanel.setOpaque(false);	
		functionFrame.add(functionPanel);
		functionFrame.setVisible(true);
	}

	public void changeInfoGui() {
		functionFrame.add(setButMenu);
		functionPanel = sg.getInfoPanel();
		functionPanel.setOpaque(false);	
		functionFrame.add(functionPanel);
		functionFrame.setVisible(true);
	}

	
	// 功能区左侧菜单公共组件:返回主界面But
	JButton getBackBut(int a, int b) {
		JButton back = pc.getButton(backIcon, "返回", a, b);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FacultyMainFrame fff = new FacultyMainFrame(ID);
				fff.start();
				functionFrame.dispose();
			}
		});
		return back;
	}

	// 设置功能区 左侧菜单 ：个人信息&密码&返回
	JPanel getSetButMenu(int a, int b) {
		JPanel menu = new JPanel();
		menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
		JButton infoBut = pc.getButton(infoIcon, "个人信息", 60,
				60);
		JButton pswBut = pc.getButton(pswIcon, "密码", 60,
				80);

		infoBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				functionFrame.remove(functionPanel);
				functionPanel = sg.getInfoPanel();
				functionPanel.setOpaque(false);	
				functionFrame.add(functionPanel);
				functionFrame.revalidate();
				functionFrame.repaint();
			}
		});

		pswBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				functionFrame.remove(functionPanel);
				functionPanel = sg.getPswPanel();
				functionPanel.setOpaque(false);	
				functionFrame.add(functionPanel);
				
				functionFrame.revalidate();
				functionFrame.repaint();
			}
		});

		menu.add(infoBut);
		menu.add(pswBut);
		menu.add(getBackBut(a, b));
		menu.setBounds(frameWidth / 30, frameHeight / 5, frameWidth / 7,
				frameHeight * 2 / 3);
		menu.setOpaque(false);
		return menu;
	}

	// 教学计划：左侧菜单
	JPanel getPlanButMenu(int a, int b) {
		JPanel menu = new JPanel();
		menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
		JButton createBut = pc.getButton(createIcon, "创建", a, b);
		JButton reviseBut = pc.getButton(revisePlanIcon, "修改", a,
				b);
		createBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				functionFrame.remove(functionPanel);
				pg = new PlanGUI(ID);

				Course course = new Course();
				System.out.println(course.testStatus("plan"));
				if (course.testStatus("plan")) {
					functionPanel = pg.getCreatePanel();
				} else {
					functionPanel = new JPanel();
					JLabel warning = new JLabel("不在创建教学计划的时间范围内！");
					functionPanel.setBounds(frameWidth / 4, frameHeight / 5,
							frameWidth * 2 / 3, frameHeight * 2 / 3);
					functionPanel.add(warning);
				}
				functionPanel.setOpaque(false);	
				functionFrame.add(functionPanel);
				functionFrame.revalidate();
				functionFrame.repaint();
			}
		});

		reviseBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				functionFrame.remove(functionPanel);
				functionPanel = pg.getRevisePanel();
				functionPanel.setOpaque(false);	
				functionFrame.add(functionPanel);
				functionFrame.revalidate();
				functionFrame.repaint();
			}
		});

		menu.add(createBut);
		menu.add(reviseBut);
		menu.add(getBackBut(a, b));
		menu.setBounds(frameWidth / 30, frameHeight / 5, frameWidth / 7,
				frameHeight * 2 / 3);
		menu.setOpaque(false);
		return menu;
	}

	// 课程：左侧菜单
	JPanel getCourseButMenu(int buttonWidth, int buttonHeight) {
		JPanel menu = new JPanel();
		menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
		JButton publishBut = pc.getButton(publishIcon, "发布",
				buttonWidth, buttonHeight);
		JButton reviseBut = pc.getButton(reviseIcon, "修改",
				buttonWidth, buttonHeight);
		JButton scanBut = pc.getButton(scanIcon, "查看",
				buttonWidth, buttonHeight);
		JButton deleteBut = pc.getButton(deleteIcon, "删除",
				buttonWidth, buttonHeight);

		publishBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				functionFrame.remove(functionPanel);
				Course course = new Course();
				if (course.testStatus("course")) {
					functionPanel = cg.getPublishPanel();

				} else {

					functionPanel = new JPanel();
					functionPanel.setBounds(frameWidth / 4, frameHeight / 5,
							frameWidth * 2 / 3, frameHeight * 2 / 3);
					JLabel warning = new JLabel("不在创建课程的时间范围内！");
					functionPanel.add(warning);

				}
				functionPanel.setOpaque(false);	
				functionFrame.add(functionPanel);
				functionFrame.revalidate();
				functionFrame.repaint();
			}
		});
		reviseBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				functionFrame.remove(functionPanel);
				pc=new PublicComponent(ID);
				functionPanel = pc.getSearchPanel(true);
				functionPanel.add(getReviseCourseBut());
				functionPanel.setOpaque(false);	
				
				functionFrame.add(functionPanel);
				functionPanel.revalidate();
				functionFrame.invalidate();
				functionFrame.repaint();
			}
		});
		scanBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc=new PublicComponent(ID);
				functionFrame.remove(functionPanel);
				functionPanel = pc.getSearchPanel(false);
				functionPanel.setOpaque(false);	
				functionFrame.add(functionPanel);
				functionPanel.revalidate();
				functionFrame.invalidate();
				functionFrame.repaint();
			}
		});
		deleteBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc=new PublicComponent(ID);
				functionFrame.remove(functionPanel);
				functionPanel = pc.getSearchPanel(true);
				functionPanel.add(getDeleteBut());
				functionPanel.setOpaque(false);	
				functionFrame.add(functionPanel);
				functionPanel.revalidate();
				functionFrame.invalidate();
				functionFrame.repaint();
			}
		});

		menu.add(publishBut);
		menu.add(reviseBut);
		menu.add(scanBut);
		menu.add(deleteBut);
		menu.add(getBackBut(buttonWidth, buttonHeight));
		menu.setBounds(frameWidth / 30, frameHeight / 5, frameWidth / 7,
				frameHeight * 2 / 3);
		menu.setOpaque(false);
		return menu;
	}

	// 修改课程按钮
	JButton getReviseCourseBut() {
		JButton but = new JButton("确认");
		but.setFont(new Font("微软雅黑", 0, 13));
		but.setBounds(frameWidth * 6 / 11, frameHeight * 3 / 5,
				frameWidth / 10, frameHeight / 30);
		but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=pc.getSelectedCouID();
				if(id.equals("error")){
					GUIHelper.sendMessage("请选择一项课程");
				}else{
				functionFrame.remove(functionPanel);
				functionPanel = cg.getRevisePanel(id); // courseID
				functionPanel.setOpaque(false);	
				
				functionFrame.add(functionPanel);
				
				functionPanel.revalidate();
				functionFrame.invalidate();
				functionFrame.repaint();
				}
				}
		});

		return but;
	}

	// 删除课程but
	JButton getDeleteBut() {
		JButton but = new JButton("删除");
		but.setFont(new Font("微软雅黑", 0, 13));
		but.setBounds(frameWidth * 6 / 11, frameHeight * 3 / 5,
				frameWidth / 10, frameHeight / 30);
		but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CourseBLService course = new Course();
				String id=pc.getSelectedCouID();
				if(id.equals("error")){
					GUIHelper.sendMessage("请选择一项课程");
				}else{
				course.deleteCourse(id);
				functionFrame.remove(functionPanel);
				functionPanel = pc.getSearchPanel(false); // courseID
				functionPanel.add(getDeleteBut());
				functionPanel.setOpaque(false);	
				functionFrame.add(functionPanel);
				functionFrame.invalidate();
				functionFrame.repaint();
				GUIHelper.sendMessage("删除课程成功！");
				}
				}
		});
		
		return but;
	}

	// 学生：左侧菜单
	JPanel getStudentButMenu(int a, int b) {
		JPanel menu = new JPanel();
		menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
		menu.add(getBackBut(a, b));
		menu.setBounds(frameWidth / 30, frameHeight / 5, frameWidth / 7,
				frameHeight * 2 / 3);
		menu.setOpaque(false);
		return menu;
	}

	// 课程列表界面 查看学生列表按钮
	JButton getSearchStuBut() {
		JButton but = new JButton("查看");
		but.setFont(new Font("微软雅黑", 0, 13));
		but.setBounds(frameWidth * 6 / 11, frameHeight * 3 / 5,
				frameWidth / 10, frameHeight / 30);
		but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String courseID=pc.getSelectedCouID();	
				if(courseID.equals("error")){
					GUIHelper.sendMessage("请选择一项课程");
				}else{
				functionFrame.remove(functionPanel);
				
			
				functionPanel = stuG.getStuListPanel(courseID,
						pc.getSelectedCouName()); // 根据课程号得到学生列表 未实现
				functionPanel.add(getStepBackBut());
				functionPanel.setOpaque(false);	
				functionFrame.add(functionPanel);
				functionFrame.invalidate();
				functionFrame.repaint();
			}
			}
		});
		return but;
	}

	// 学生列表界面 返回button
	JButton getStepBackBut() {
		JButton back = new JButton("返回");
		back.setFont(new Font("微软雅黑", 0, 10));
		back.setBounds(frameWidth / 2, frameHeight / 60, frameWidth / 10,
				frameHeight / 30);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				functionFrame.remove(functionPanel);
				functionPanel = pc.getSearchPanel(false);
				functionPanel.add(getSearchStuBut());
				functionPanel.setOpaque(false);	
				functionFrame.add(functionPanel);
				functionPanel.revalidate();
				functionFrame.invalidate();
				functionFrame.repaint();
			}
		});
		return back;
	}

	// 当前用户:"当前用户:"+ID
	JLabel getGuideLine() {
		JLabel label = new JLabel("当前用户:" + ID);
		label.setFont(new Font("微软雅黑", 0, 15));
		label.setBounds(frameWidth / 25, frameHeight / 20, frameWidth / 4,
				frameHeight / 20);
		return label;
	}

	// 工具栏
	JMenuBar getMenuBar() {
		JMenuBar mb=new JMenuBar();
		mb.setOpaque(false);
		
		ImageIcon icon1=new ImageIcon(setIcon);
		JMenu setMenu=new JMenu();
		setMenu.setIcon(icon1);
		setMenu.setOpaque(false);
		JMenuItem pswMenuItem=pc.getPswMenuItem();
		JMenuItem  infoMenuItem=pc.getInfoMenuItem();
		setMenu.add(infoMenuItem);
		setMenu.add(pswMenuItem);

		ImageIcon icon2=new ImageIcon(helpIcon);	
		JMenu helpMenu =new JMenu();
		helpMenu.setIcon(icon2);
		helpMenu.setOpaque(false);

		JMenuItem aboutMenuItem=pc.getAboutMenuItem();
		JMenuItem replyMenuItem=pc.getReplyMenuItem();

		helpMenu.add(aboutMenuItem);
		helpMenu.add(replyMenuItem);

	
		JMenuItem exitMenuItem=getExitMenuItem();
					
		JMenuItem msgMenuItem=pc.getMsgMenuItem();

		mb.add(setMenu);
		mb.add(helpMenu);
		mb.add(msgMenuItem);
		mb.add(exitMenuItem);
		mb.setBounds(frameWidth*6/8,frameHeight/17,frameWidth*11/60,frameHeight/20);


		//菜单事件
		pswMenuItem.addActionListener(new ActionListener()
		{
						public void actionPerformed(ActionEvent e){	
							FacultyFunctionFrame fff=new FacultyFunctionFrame(ID);
							fff.changePswGui();
							functionFrame.dispose();
						}
		});
		infoMenuItem.addActionListener(new ActionListener()
		{
						public void actionPerformed(ActionEvent e){	
							FacultyFunctionFrame fff=new FacultyFunctionFrame(ID);
							fff.changeInfoGui();
							functionFrame.dispose();
						}

		});

		return mb;
	}

	public JMenuItem getExitMenuItem() {
		JMenuItem exitMenuItem = new JMenuItem("", KeyEvent.VK_E);
		exitMenuItem.setOpaque(false);
		ImageIcon icon=new ImageIcon(exitIcon);	
		exitMenuItem.setIcon(icon);
		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// logout
				functionFrame.dispose();
				new LoginUI();
			}
		});
		return exitMenuItem;
	}
}
