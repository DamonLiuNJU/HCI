package presentation.mainui;

import java.awt.Toolkit;
import java.awt.event.*;

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
import org.jvnet.substance.theme.SubstanceOliveTheme;
import org.jvnet.substance.watermark.SubstanceBubblesWatermark;

import presentation.adminui.SystemConditionPane;
import presentation.adminui.UserManagePane;
import presentation.tools.PublicComponents;
import presentation.tools.UIConstants;
import businesslogic.managerbl.Admin;

/*
 * Admin主界面，主要由一个tabbedPane构成
 * tabbedPane包含2个标签，一个是用户管理，一个是系统状态
 */
public class AdminMainUI extends JFrame implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Admin admin ;
	JPanel northPane , centerPane;
	JTabbedPane jtp ;
	JLabel timeLabel;
	JMenuBar mb;
	JMenu helpMenu ;
	JMenuItem aboutMenuItem , replyMenuItem , exitMenuItem ;
	
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
		SubstanceLookAndFeel.setCurrentWatermark(new SubstanceBubblesWatermark());
		SubstanceLookAndFeel.setCurrentTheme(new SubstanceOliveTheme());  
	
	};
	
	public AdminMainUI(Admin admin){
		this.admin = admin;
		this.draw();
	}
	
	public void draw(){
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		this.setLocation(width/2-500,height/2-350);
		this.setSize(1000,600);
		this.setTitle("NJWU教务系统");
		this.setLayout(null);
		
		this.initTabbedPane();
		this.initNorthPane();
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void initNorthPane(){
		northPane = new JPanel();
		northPane.setLayout(null);
		northPane.setBounds(0 , 0 , 1000 ,100 );
		
//		MyComponent mc = new MyComponent();
		timeLabel = PublicComponents.getTimeLabel(UIConstants.ADMIN, null);
		timeLabel.setBounds(5, 0, 200, 40);
		this.add(timeLabel);
		this.setMyMenuBar();
		northPane.add(mb);
		
		this.add(northPane);
		
	}
	
	private void initTabbedPane(){
		jtp = new JTabbedPane();
		jtp.setBounds(0,75,1000,450);
		
		UserManagePane ump = new UserManagePane(admin);
		SystemConditionPane scp = new SystemConditionPane(admin);
		
		jtp.addTab("用户管理" , ump);
		jtp.addTab("系统状态" , scp);
		
		this.add(jtp);
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
		helpMenu.add(aboutMenuItem);
		helpMenu.add(replyMenuItem);

		exitMenuItem = new JMenuItem("", KeyEvent.VK_E);
		exitMenuItem.setOpaque(false);
		ImageIcon icon3 = new ImageIcon("./icon/exit.png");
		exitMenuItem.setIcon(icon3);
		
		aboutMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){	
				JFrame aboutFrame=new PublicComponents().getAboutFrame();
				aboutFrame.setVisible(true);
			}			
		});
		
		replyMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){	
				JFrame replyFrame=new PublicComponents().getReplyFrame();
				replyFrame.setVisible(true);
			}				
		});
		
		exitMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					AdminMainUI.this.dispose();
					new LoginUI();
			}
		});

		mb.add(helpMenu);
		mb.add(exitMenuItem);

	}
	
	public static void main(String[] args){
		new AdminMainUI(new Admin("name" , "id" , "password"));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
