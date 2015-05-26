package presentation.mainui;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.AutumnSkin;
import org.jvnet.substance.skin.SubstanceSaharaLookAndFeel;
import org.jvnet.substance.theme.SubstanceOliveTheme;
import org.jvnet.substance.watermark.SubstanceBubblesWatermark;

import presentation.teacherui.ApplyPane;
import presentation.teacherui.TeacherInfoPane;
import presentation.teacherui.TeachingInfo;
import presentation.tools.MyComponent;
import businesslogic.teacherbl.Teacher;

/*
 * 该类为教师主界面， 继承JFrame类 ， 在用户以教师身份登录成功后， 显示该界面
 * 界面分为northJP 和 centerJP
 * northJP处于上部 ， 内中放置欢迎信息，提供“退出” ， “选项” ， “帮助”等监听
 * centerJP处于中部 ， 完全由一个JTabbedPane构成。
 * JTabbedPane中，初始化了InnerJP1 和InnerJP2 ， 2个标签
 * 在开始时，界面显示该教师信息 ， 选择标签进行响应操作， 在标签中点击返回和回到教师信息设置界面
 */
public class TeacherMainUI extends JFrame implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static JTabbedPane jtp;

	JPanel northJP, centerJP;
	TeachingInfo innerJP1;
	ApplyPane innerJP2;
	TeacherInfoPane tip;
	JPanel scorePane, stuInfoPane;
	JLabel welcomeLabel, exit, option, helper, timeLabel;
	Teacher teacher;
	JMenuBar mb;
	JMenu helpMenu ;
	JMenuItem aboutMenuItem , replyMenuItem , exitMenuItem ;

	// 该段代码选择了界面的风格
//	static {
//		try {
//			try {
//				UIManager.setLookAndFeel(new SubstanceLookAndFeel());
//
//			} catch (UnsupportedLookAndFeelException ex) {
//				System.out.println(ex.getMessage());
//			}
//		} catch (Exception et) {
//			System.out.println(et.getMessage());
//		}
//		SubstanceSaharaLookAndFeel.setSkin(new AutumnSkin());
//		SubstanceLookAndFeel
//				.setCurrentWatermark(new SubstanceBubblesWatermark());
//
//		SubstanceLookAndFeel.setCurrentTheme(new SubstanceOliveTheme());
//
//	};

	public TeacherMainUI(Teacher teacher) {
		try{
			try{
				UIManager.setLookAndFeel(new SubstanceLookAndFeel());										
			}catch(UnsupportedLookAndFeelException  ex){
				System.out.println(ex.getMessage());
			}
		}catch(Exception et){
				System.out.println(et.getMessage());
		}
		SubstanceSaharaLookAndFeel.setSkin(new AutumnSkin());
		SubstanceLookAndFeel.setCurrentWatermark(new SubstanceBubblesWatermark());
		SubstanceLookAndFeel.setCurrentTheme(new SubstanceOliveTheme());
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		this.teacher = teacher;

		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		this.setLocation(width / 2 - 500, height / 2 - 350);
		this.setSize(1000, 600);
		this.setTitle("NJWU教务系统");
		this.setLayout(null);

		String path = "./icon/teacher/back1.jpg";
		ImageIcon ii = this.getImageIcon(path, 1000, 600);
		JLabel backLabel = new JLabel(ii);
		backLabel.setBounds(0, 0, 1000, 600);

		JPanel imagePanel1 = (JPanel) this.getContentPane();
		imagePanel1.setOpaque(false);
		this.getLayeredPane().setLayout(null);
		this.getLayeredPane().add(backLabel, new Integer(Integer.MIN_VALUE));

		northJP = new JPanel();
		northJP.setLayout(null);
		northJP.setSize(1000, 100);
		northJP.setOpaque(false);

		welcomeLabel = new JLabel("欢迎 ：" + teacher.getName());
		welcomeLabel.setBounds(50, 30, 200, 40);

		MyComponent mc = new MyComponent();
		timeLabel = mc.getTimeLabel();
		timeLabel.setBounds(750, 30, 200, 40);
		
		this.setMyMenuBar();

		northJP.add(welcomeLabel);
		northJP.add(timeLabel);
		northJP.add(mb);

		centerJP = new JPanel();
		centerJP.setLayout(null);
		centerJP.setBounds(0, 80, 1000, 450);
		centerJP.setOpaque(false);

		innerJP1 = new TeachingInfo(teacher);
		innerJP2 = new ApplyPane(teacher);
		tip = new TeacherInfoPane(teacher);

		jtp = new JTabbedPane();
		jtp.setBounds(0, 0, 980, 450);

		jtp.addTab("个人信息", tip);
		jtp.addTab("课程信息", innerJP1);
		jtp.addTab("申报课程", innerJP2);

		centerJP.add(jtp);
		this.add(centerJP);
		this.add(northJP);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);

	}

	private void setMyMenuBar() {
		mb = new JMenuBar();
		mb.setOpaque(false);
		mb.setBounds(900 , 10 , 100 , 30);

		helpMenu = new JMenu();
		helpMenu.setOpaque(false);
		helpMenu.addMouseListener(this);
		ImageIcon icon2 = new ImageIcon("./icon/help.png");
		helpMenu.setIcon(icon2);
		aboutMenuItem = new JMenuItem("关于");
		replyMenuItem = new JMenuItem("反馈", KeyEvent.VK_R);
		aboutMenuItem.addMouseListener(this);
		replyMenuItem.addMouseListener(this);
		helpMenu.add(aboutMenuItem);
		helpMenu.add(replyMenuItem);

		exitMenuItem = new JMenuItem("", KeyEvent.VK_E);
		exitMenuItem.setOpaque(false);
		ImageIcon icon3 = new ImageIcon("./icon/exit.png");
		exitMenuItem.setIcon(icon3);
		exitMenuItem.addMouseListener(this);
		
		aboutMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){	
				JFrame aboutFrame=new MyComponent().getAboutFrame();
				aboutFrame.setVisible(true);
			}			
		});
		
		replyMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){	
				JFrame replyFrame=new MyComponent().getReplyFrame();
				replyFrame.setVisible(true);
			}				
		});

		mb.add(helpMenu);
		mb.add(exitMenuItem);

	}

	private ImageIcon getImageIcon(String path, int width, int height) {
		ImageIcon icon = new ImageIcon(path);
		icon.getImage();
		Image temp = icon.getImage().getScaledInstance(this.getWidth(),
					this.getHeight(), Image.SCALE_DEFAULT);
		icon = new ImageIcon(temp);

		return icon;
	}

	// 该Main方法为测试方法
	public static void main(String[] args) {
		Teacher teacher = new Teacher("2001001", "password");
		teacher.initTeacher();
		new TeacherMainUI(teacher);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == exitMenuItem){
			this.dispose();
			new LoginUI1();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
