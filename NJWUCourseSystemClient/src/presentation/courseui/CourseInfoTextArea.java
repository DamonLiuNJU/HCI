package presentation.courseui;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import businesslogic.coursebl.Course;
import businesslogicservice.courseblservice.CourseBLService;
public class CourseInfoTextArea  {
		
		//得到课程的详细信息:info
		public   JScrollPane  getCourseInfo(String index ,int row, int column ){
					
				CourseBLService 	course=new Course();
					JTextArea   ta=new JTextArea(course.getSpecificInfo(index),row,column);
					JScrollPane sp= new JScrollPane(ta);  
					sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					return sp;
		} 		
	}
