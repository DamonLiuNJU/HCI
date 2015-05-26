package presentation.studentui;

import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class ReplyPanel {

	public JLabel getTimeLabel() {
		final JLabel label = new JLabel();
		new Thread() {
			@SuppressWarnings("deprecation")
			public void run() {
				try {
					while (true) {
						label.setText(new Date().toLocaleString());
						Thread.sleep(1000);
					}
				} catch (Exception e) {

				}
			}
		}.start();
		return label;
	}

    public JFrame getReplyFrame(){
    	
    	JFrame frame=new JFrame("反馈");
    	frame.setResizable(false);
//    	frame.setLocationRelativeTo(null);
    	
    	
    	JPanel p=new JPanel(new MigLayout());
    	
    	JLabel l1=new JLabel("请将您的反馈发送至如下地址:");
    	JLabel l2=new JLabel("邮箱：  swing2012@software.nju.edu.cn.");
    	JLabel l3=new JLabel("您的支持是我们完善工作的最大动力。");
    	
    	p.add(l1,"wrap,gaptop 20");
    	p.add(l2,"wrap,gaptop 15");
    	p.add(l3,"gaptop 15");

    	Container c= frame.getContentPane();
    	c.add(p);
    	frame.setSize(300, 200);
//    	frame = new Tool().setMiddle(frame);
    	int windowwedth=frame.getWidth();
		int windowheight=frame.getHeight();
		int screenwedth=Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenheight=Toolkit.getDefaultToolkit().getScreenSize().height;
		frame.setLocation((screenwedth-windowwedth)/2,  (screenheight-windowheight)/2);
    	frame.repaint();
    	frame.setVisible(true);
    	
    	return frame;
    }
    
    public JFrame getAboutFrame(){
    	JFrame frame=new JFrame("关于");
    	frame.setResizable(false);
//    	frame.setLocationRelativeTo(null);
    	
    	Container c= frame.getContentPane();
    	JPanel p=new JPanel(new MigLayout());
    	JLabel l1=new JLabel("Team");
    	JLabel l2=new JLabel("Swing");
    	l2.setFont(new Font("Forte" ,Font.BOLD ,19));
    	JLabel l3=new JLabel("Member : cbb,ll,lr,lwt.");
    	
    	p.add(l1,"wrap,gapleft 80,gaptop 20");
    	p.add(l2,"gaptop 15,gapleft 100,wrap");
    	p.add(l3,"gaptop 15,gapleft 80");
    	
    	c.add(p);
    	frame.setSize(300, 200);
//    	frame = new Tool().setMiddle(frame);
    	int windowwedth=frame.getWidth();
		int windowheight=frame.getHeight();
		int screenwedth=Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenheight=Toolkit.getDefaultToolkit().getScreenSize().height;
		frame.setLocation((screenwedth-windowwedth)/2,  (screenheight-windowheight)/2);
		
    	frame.repaint();
    	frame.setVisible(true);
    	return frame;
    }
}
