package presentation.courseui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import presentation.frameui.CreditRestrictPane;


import businesslogic.coursebl.Course;
import businesslogicservice.courseblservice.CourseBLService;

public class CourseStaticsButton {

	public JButton getCourseStaticsButton(String id){
	final String ID=id;
		JButton scan=new JButton("学分限制查询");
		scan.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CourseBLService   course  = new Course();
				ArrayList<	String> list=course.getMyFacultyStaticsCredit(ID);
				String content="";
				for(int i=0;i<list.size();i++){
					content=content+list.get(i)+"分"+"\r\n";
				}
				new JStaticFrame(content).run();
			}
		});
	
	return  scan;
		
	
	}

	class JStaticFrame {
		String content;
		Point origin;
		JFrame infoDialog;

		JStaticFrame(String content) {
			this.content = content;
			origin = new Point();
		}

		void run() {
			infoDialog = new JFrame();
			infoDialog.setUndecorated(true);
			infoDialog.setBounds(500, 300, 300, 200);
			JPanel infoPanel = new JPanel();
			infoPanel.setLayout(null);

			infoPanel.setBounds(0, 0, 300, 200);
			infoPanel.setBackground(new Color(0X8d8a78));
			
			JLabel frameLabel=new JLabel("教学框架参考:");
			JTextArea frameTxt=new CreditRestrictPane().getCreditRestrictsInfo();
			
			
			frameLabel.setBounds(10,10,100,20);
			frameTxt.setBounds(10,30,130,160);
			frameTxt.setEditable(false);
			
			
			JLabel myFacultyLabel=new JLabel("本院课程统计参考:");
			JTextArea facultyTxt = new JTextArea(content, 20, 20);
			facultyTxt.setEditable(false);
		
			myFacultyLabel.setBounds(145,10,100,20);
			facultyTxt.setBounds(145,30, 130,160 );
			
			

			infoPanel.add(frameLabel);
			infoPanel.add(frameTxt);
			infoPanel.add(myFacultyLabel);
			infoPanel.add(facultyTxt);
			infoDialog.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					origin.x = e.getX();
					origin.y = e.getY();
				}
			});
			infoDialog.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					Point p = infoDialog.getLocation();
					infoDialog.setLocation(p.x + e.getX() - origin.x,
							p.y + e.getY() - origin.y);
				}
			});

			JButton close = new JButton("X");
			close.setForeground(Color.BLACK);
			close.setFont(new Font("微软雅黑", 1, 15));

			close.setContentAreaFilled(false);
			close.setBorderPainted(false);
			close.setBounds(280, 0, 25, 25);
			close.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					infoDialog.dispose();
				}

			});
			infoPanel.add(close);
			infoDialog.add(infoPanel);
			infoDialog.setVisible(true);
		}
	}


}
