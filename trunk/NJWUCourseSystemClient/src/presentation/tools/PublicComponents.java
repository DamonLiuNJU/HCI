package presentation.tools;

import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class PublicComponents {

//	public JLabel getTimeLabel() {
//		final JLabel label = new JLabel();
//		new Thread() {
//			@Override
//			@SuppressWarnings("deprecation")
//			public void run() {
//				try {
//					while (true) {
//						label.setText(new Date().toLocaleString());
//						Thread.sleep(1000);
//					}
//				} catch (Exception e) {
//					System.out.print("System error.");
//				}
//			}
//		}.start();
//		return label;
//	}
	
	public static JLabel getTimeLabel(String title,String name) {
		final JLabel timelabel = new JLabel();
		final JLabel usernamelabel = new JLabel();
		final JLabel containerlabel = new JLabel();
		containerlabel.setBounds(0, 0, 400, 40);
		
		usernamelabel.setText("欢迎"+title+((name==null)?"":":"+name));
		usernamelabel.setFont(new Font(UIConstants.FONT_TITLE, Font.BOLD, 18));
		
		new Thread() {
			@Override
			@SuppressWarnings("deprecation")
			public void run() {
				try {
					while (true) {
						timelabel.setText(new Date().toLocaleString());
						Thread.sleep(1000);
					}
				} catch (Exception e) {
					System.out.print("System error.");
				}
			}
		}.start();
		
		containerlabel.add(usernamelabel);
		containerlabel.add(timelabel);
		containerlabel.setLayout(null);
		usernamelabel.setBounds(0, 0, containerlabel.getSize().width, 20);
		timelabel.setBounds(0, containerlabel.getSize().height-usernamelabel.getSize().height, containerlabel.getSize().width, 20);
		return containerlabel;
	}

    public JFrame getReplyFrame(){   	
    	JFrame frame=new JFrame();
    	Image image = new ImageIcon("./icon/letter.png").getImage();
    	frame.setIconImage(image);
    	frame.setResizable(false);
    	frame.setLocationRelativeTo(null);
    	
    	JPanel p=new JPanel(new MigLayout());
    	JLabel l1=new JLabel("请将您对于“NJWU选课系统”的反馈发送至如下地址:");
    	JLabel l2=new JLabel("邮箱：  swing2012@software.nju.edu.cn.");
    	JLabel l3=new JLabel("您的支持是我们完善工作的最大动力！");
    	
    	p.add(l1,"wrap,gaptop 20");
    	p.add(l2,"wrap,gaptop 15");
    	p.add(l3,"gaptop 15");
    	
    	frame.add(p);
    	frame.setSize(350, 250);
    	setLocation(frame);
    	return frame;
    }
    
    public JFrame getAboutFrame(){
    	JFrame frame=new JFrame();
    	Image image = new ImageIcon("./icon/elephant.png").getImage();
    	frame.setIconImage(image);
    	frame.setResizable(false);
    	frame.setLocationRelativeTo(null);
    	
    	JPanel p=new JPanel(new MigLayout());
    	JLabel l0=new JLabel("-------------NJWU选课系统-------------");
    	JLabel l1=new JLabel("Team");
    	JLabel l2=new JLabel("Swing");
    	l2.setFont(new Font("Forte" ,Font.BOLD ,19));
    	JLabel l3=new JLabel("Member : cbb,ll,lr,lwt.");
    	
    	p.add(l0,"gapleft 40,gaptop 20,wrap");
    	p.add(l1,"gapleft 80,gaptop 10,wrap");
    	p.add(l2,"gaptop 10,gapleft 100,wrap");
    	p.add(l3,"gaptop 5,gapleft 80");
    	
    	frame.add(p);
    	frame.setSize(350, 250);
    	setLocation(frame);
    	return frame;
    }
    
    void setLocation(JFrame frame){
    	int windowwedth = frame.getWidth();
		int windowheight = frame.getHeight();
		int screenwedth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenheight = Toolkit.getDefaultToolkit().getScreenSize().height;
		frame.setLocation((screenwedth - windowwedth) / 2,
				(screenheight - windowheight) / 2);
    }
}
