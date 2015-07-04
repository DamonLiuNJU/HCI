package presentation.courseselectionui;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import presentation.courseui.CourseListTable;
import presentation.studentui.Tool;
import presentation.tools.Setter;
import businesslogic.courseselectionbl.CourseSelection;
import businesslogic.courseselectionbl.SelectCourseInfo;
import businesslogic.studentbl.CourseInfo;
//import javax.swing.JFrame;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.table.DefaultTableModel;
import businesslogicservice.studentblservice.CourseInfoBLService;

public class PECourseFrame {

	/**
	 * @param args
	 */
	final JFrame mainframe ; 
	public PECourseFrame(final String student_id){
		 mainframe = new JFrame();
		mainframe.setSize(800,600);
		final Rectangle selectcoursetablepanesize = new Rectangle(10, 40, 780, 400);
		final JTable table = new CourseListTable().getSelectCourseByModule("体育课");
		table.setOpaque(false);
		JScrollPane jsp  = new JScrollPane(table);
		jsp.setOpaque(false);
		jsp.setBounds(selectcoursetablepanesize);
		Tool.setOpaque(jsp);
		Tool.setOpaque(jsp);
		table.setOpaque(false);
		mainframe.add(jsp);
		mainframe.setLayout(null);
		int windowwedth=mainframe.getWidth();
		int windowheight=mainframe.getHeight();
		int screenwedth=Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenheight=Toolkit.getDefaultToolkit().getScreenSize().height;
		mainframe.setLocation((screenwedth-windowwedth)/2,  (screenheight-windowheight)/2);
		mainframe.setVisible(true);
//		mainframe.setDefaultCloseOperation()
		JButton select = new JButton("选择");
		mainframe.add(select);
		select.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				String course_id = (String) table.getValueAt(table.getSelectedRow(), 0);
				int selectednumber = 0;
				CourseInfoBLService ci = new CourseInfo();
				selectednumber = ci.getSelectedStudentNumber(course_id);
				int courselimitcolumn = 9;
				String limit =  (String) table.getValueAt(table.getSelectedRow(), courselimitcolumn);
				if(selectednumber <Integer.parseInt(limit)){
					CourseSelection s = new CourseSelection();
					s.addPECourseNo(student_id, course_id);		
					JOptionPane.showMessageDialog(null, "选择成功");
					mainframe.dispose();
				}else{
					JOptionPane.showMessageDialog(null, "选择失败，所选课程人数已满");
				}
			}
		});
		
		JButton backButton = new JButton("返回");
		backButton.setBounds(700,450,70,30);
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				mainframe.dispose();
			}
		});
		mainframe.add(backButton);
		
		select.setBounds(10, 450, 100, 25);
		new Setter().addBackground(mainframe, Tool.FrameImagePath);
	}
	
	public boolean selectedThisPELesson(String student_id , String course_id){
//		boolean result = false;
//		Student s = new Student();
//		StudentInfo si = new StudentInfo();
		SelectCourseInfo mc = new SelectCourseInfo();
		ArrayList<String> courselist = mc.getAllMyCoursesInTemp(student_id);
		for(String a : courselist){
			if(a.compareToIgnoreCase(course_id)==0){
				return true;
			}
		}	
		return false;
	}
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

}
