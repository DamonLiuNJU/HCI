package presentation.courseui;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import businesslogic.coursebl.Course;
import businesslogicservice.courseblservice.CourseBLService;

	public class CourseInfoFrame  {
			JFrame f;
			public static void main(String arg[]){
					new CourseInfoFrame("c0001");
			}

	public 	CourseInfoFrame(String courseID){
			f=new JFrame("详细信息");
			CourseBLService course=new Course();	
			String s=course.getSpecificInfo(courseID);
			//System.out.println(s);
			
			JTextArea info=new JTextArea(s,15,20);
			info.setSize(400,300);
			info.setLineWrap(true);
			info.setWrapStyleWord(true);
			
			JScrollPane sp=new JScrollPane(info);
			sp.setSize(450,350);
			
			f.setResizable(false);	
			f.setBounds(500,200,450,350);
			f.add(sp);
			f.setVisible(true);
			f.repaint();
		}
		
}
