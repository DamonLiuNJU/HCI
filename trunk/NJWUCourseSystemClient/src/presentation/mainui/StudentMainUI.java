package presentation.mainui;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.AutumnSkin;
import org.jvnet.substance.theme.SubstanceOliveTheme;

import presentation.courseselectionui.QuitCoursePanel;
import presentation.courseselectionui.SelectCourseModule;
import presentation.studentui.CourseCommentPanel;
import presentation.studentui.CourseConditionPanel;
import presentation.studentui.ExitButton;
import presentation.studentui.GetCourseInfoPanel;
import presentation.studentui.MajorTransferPanel;
import presentation.studentui.PersonalInfoPanel;
import presentation.studentui.ReplyPanel;
import presentation.studentui.ScorePanel;
import presentation.studentui.SettingButton;
import presentation.studentui.Tool;
import presentation.tools.MyComponent;
import presentation.tools.Setter;
import vo.courseselectionvo.CourseSelectionVO;
import businesslogic.studentbl.StudentInfo;

public class StudentMainUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1995874605973301364L;
	
	
	
	static {
		try{
			try{
				UIManager.setLookAndFeel(new SubstanceLookAndFeel());
				
					
			}catch(UnsupportedLookAndFeelException  ex){
				System.out.println(ex.getMessage());
			}
		}catch(Exception et){
				System.out.println(et.getMessage());
		}
		SubstanceLookAndFeel.setSkin(new AutumnSkin());
//		SubstanceLookAndFeel.setCurrentWatermark(new SubstanceBubblesWatermark());
//		SubstanceLookAndFeel.setCurrentTitlePainter(new Glass3DDecorationPainter());
//		SubstanceLookAndFeel.setCurrentTheme(new SubstanceLightAquaTheme());

		SubstanceLookAndFeel.setCurrentTheme(new SubstanceOliveTheme());  
		  
//	       SubstanceLookAndFeel.setCurrentTheme(new SubstanceJadeForestTheme());  
	  
	
	
	};
	ImageIcon icon=new ImageIcon(Tool.schoolmarkpath) ; 
	String title="Course System for Student";
	Setter setter = new Setter();
	JPanel getcoursepanel,getscorepanel,selectcoursepanel,getcheckinfopanel;
	JTabbedPane pane;
	JButton settingbutton;
	JButton exitbutton;
	JLabel timelabel;
	JLabel welcomelabel;
	JButton replybutton;
	JButton aboutbutton ;
	
	public JFrame createFrame(String student_id,JFrame loginframe){
		
		
		this.setIconImage(icon.getImage());
		
		int windowwedth=800;
		int windowheight=600;
		int screenwedth=Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenheight=Toolkit.getDefaultToolkit().getScreenSize().height;
		
		this.setTitle(title);
		
		setter.addBackground(this,Tool.FrameImagePath);	    //背景设置。    
		Container container=this.getContentPane();
		container.setLayout(null);
		container.setBackground(Color.white);
		this.setBackground(Color.white);
		
		
		getcoursepanel=new GetCourseInfoPanel().getCourseInfoPanel(student_id);
		getscorepanel=new ScorePanel().getScorePanel(student_id);
		//Changing
		selectcoursepanel=new SelectCourseModule().selectModelPanel(student_id);
		getcheckinfopanel=new MajorTransferPanel().getPanel(new CourseSelectionVO(student_id));
		
		JPanel quitcoursepanel=new QuitCoursePanel().getQuitCoursePanel(student_id);
		JPanel personalpanel=new PersonalInfoPanel().getMainPanel(student_id);
		JPanel courseconditionpanel = new CourseConditionPanel(student_id);
		JPanel coursecommentpanel = new CourseCommentPanel(student_id);
		pane=this.getJTabbedPane(getcoursepanel,getscorepanel,selectcoursepanel,quitcoursepanel,getcheckinfopanel ,personalpanel,courseconditionpanel,coursecommentpanel);
		
		container.add(pane);
		this.repaint();
		pane.setSize(windowwedth, windowheight);
		pane.setBackground(Color.white);
		int tabbedpanewedth=800;
		int tabbedpaneheight=550;
		settingbutton=new SettingButton().getSettingButton(student_id);
		exitbutton=this.getExitButtion(loginframe);
//		container.add(settingbutton);
//		container.add(exitbutton);
		
		
		
		
		//time label
		timelabel=new MyComponent().getTimeLabel();
		container.add(timelabel);
		
		int timelabelwidth=200;
		int timelabelheight=40;
		
		timelabel.setBounds(5,10, timelabelwidth, timelabelheight);
		
		//welcome label
		welcomelabel=new JLabel();
		welcomelabel.setText("学生 : "+new StudentInfo().getStudentNameByID(student_id));
		container.add(welcomelabel);
		welcomelabel.setBounds(5, 0, 200, 20);
		
		//反馈信息等
		replybutton = new JButton("");
		replybutton.setSize(50,50);
		Tool.setIcon(Tool.mailimage, replybutton);
	
		replybutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ReplyPanel tools = new ReplyPanel();
				JFrame replyinfo  = tools.getReplyFrame();
				replyinfo.setVisible(true);
				replyinfo.repaint();
			}
		});
		
		aboutbutton=new JButton("");
		aboutbutton.setSize(50, 50);
		Tool.setIcon(Tool.helpimage, aboutbutton);

		
		aboutbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				ReplyPanel tools = new ReplyPanel();
				JFrame replyinfo  = tools.getAboutFrame();
				replyinfo.setVisible(true);
				replyinfo.repaint();
			}
		});
		JPanel componentpanel =  new JPanel();
		componentpanel.setLayout(new FlowLayout());
		componentpanel.add(aboutbutton);
		componentpanel.add(replybutton);
		componentpanel.add(settingbutton);
		componentpanel.add(exitbutton);
		int buttonlength = 100;
		int buttonheight = 100;
		
		settingbutton.setSize(buttonlength, buttonheight);
		exitbutton.setSize(buttonlength, buttonheight);
		aboutbutton.setSize(buttonlength, buttonheight);
		replybutton.setSize(buttonlength, buttonheight);
		componentpanel.setOpaque(false);
		container.add(componentpanel);
		componentpanel.setBounds(550, 0, 300, 110);
		
		pane.setBounds(0,windowheight-tabbedpaneheight,tabbedpanewedth,tabbedpaneheight);
		this.setBounds((screenwedth-windowwedth)/2, (screenheight-windowheight)/2, windowwedth, windowheight); //set position at the center of the screen
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);	
		this.repaint();
		return this;
	}
	
	private JTabbedPane getJTabbedPane(JPanel courseinfopanel,JPanel scorepanel,JPanel selectcoursepanel,JPanel quitcoursepanel, JPanel admissionpanel,JPanel personalInfopanel,JPanel courseconditionpanel,final JPanel coursecommentpanel){
		
		final JTabbedPane tabpane=new JTabbedPane(SwingConstants.TOP, SwingConstants.HORIZONTAL);
		String title3="选择课程";
		String tip3="Select Course or Quit Your Course here";
		tabpane.addTab(title3,null,selectcoursepanel,tip3);
		tabpane.setIconAt(0, new ImageIcon(Tool.books));
		String title4="退选课程";
		String tip4="";
		tabpane.addTab(title4, null, quitcoursepanel, tip4);
		tabpane.setIconAt(1, new ImageIcon(Tool.rubbishcan));
		
		String title1="查看课程信息";
		String tip1="you can get course info here";

		tabpane.addTab(title1, null, courseinfopanel, tip1);
		tabpane.setIconAt(2, new ImageIcon(Tool.find));
		
		String title2="查看成绩";
		String tip2="get your scores and credit info";
		tabpane.addTab(title2, null,scorepanel,tip2);
		tabpane.setIconAt(3, new ImageIcon(Tool.score));
		
		String title5="转院系申请";
		String tip5="See if your application is allowed";
		tabpane.addTab(title5,null,admissionpanel,tip5);
		tabpane.setIconAt(4, new ImageIcon(Tool.change));
		
		String title6="个人信息";
		String tip6="";
		tabpane.addTab(title6,null,personalInfopanel,tip6);
		tabpane.setIconAt(5, new ImageIcon(Tool.personalinfo));
		
		String title7 = "准入准出";
		String tip7 ="Get Course Condition";
		tabpane.addTab(title7, null,courseconditionpanel,tip7);
		tabpane.setIconAt(6, new ImageIcon(Tool.permission));

		//String title8 = "课程评估";
		//String tip8 = "评价教师与课程";
        //tabpane.addTab(title8, null,coursecommentpanel,tip8);
		tabpane.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		return tabpane;
	}
	
	
	
	private JButton getExitButtion(JFrame loginFrame){
		return new ExitButton(loginFrame).getexitbutton(this);
	}	 
}

