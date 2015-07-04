package presentation.courseselectionui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import presentation.statusui.QuitCourseButtonSetter;
import presentation.studentui.CourseModuleBox;
import presentation.studentui.Tool;
import businesslogic.courseselectionbl.CourseSelection;
import businesslogic.courseselectionbl.SelectCourseInfo;
import businesslogic.studentbl.CourseInfo;
import businesslogic.studentbl.StudentInfo;
import businesslogic.utilitybl.CourseModule;
import businesslogicservice.studentblservice.CourseInfoBLService;
import businesslogicservice.studentblservice.StudentInfoBLService;

public class QuitCoursePanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2283757043707620064L;

	public JPanel getQuitCoursePanel(final String student_id){
		
		
		
//		final JComboBox<String> grade = new SemesterSelectionBox();
		final JComboBox<String> module = new CourseModuleBox();
		module.removeItemAt(2);
//		module.remove
		
		JPanel compopanel =new JPanel();
		compopanel.add(module);
		compopanel.repaint();
		compopanel.updateUI();
		compopanel.setOpaque(false);
//		compopanel.add(grade);
		
		final JPanel mainpanel=new JPanel();
		mainpanel.setLayout(new BorderLayout());
		mainpanel.setOpaque(false);
		final Vector<String> head =  new Vector<String>();
		head.add("课程号");
		head.add("课程名");
		head.add("教师姓名");
		head.add("类型");
		head.add("退选");
		final Vector<Vector<String>> content = new Vector<Vector<String>>();
		final DefaultTableModel dtm =new DefaultTableModel(content,head);
		final JTable table = new JTable(dtm);
		final JScrollPane jsp=new JScrollPane(table);
		Tool.setOpaque(jsp);
		table.setOpaque(false);
		jsp.setOpaque(false);
		
		mainpanel.add(BorderLayout.CENTER,jsp);
		compopanel.setLayout(new FlowLayout(0,10,10));
		final JButton showcoursebutton = new JButton("显示课程");
		Tool.setIcon(Tool.find, showcoursebutton);
//		compopanel.add(showcoursebutton);
		
		module.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				// TODO 自动生成的方法存根
				StudentInfoBLService si = new StudentInfo(student_id);
				String studentcurrentgrade = si.getStuCurrentGrade();
				SelectCourseInfo mc =new SelectCourseInfo();
				ArrayList<String> tempcourselist = mc.getAllMyCoursesInTemp(student_id); //这是从临时表中获取。、不过我觉得临时表中的应该不算才对。
//				ArrayList<String> tempcourselist = new ArrayList<String>() ;// 

//				ArrayList<String> selectcourselist = mc.getAllMyCoursesInTemp(student_id);//正是表中获取。
//				tempcourselist.addAll(selectcourselist);  //合并两个课程列表。
				int size = dtm.getRowCount();
				 for(int i =0 ;i<size ;i ++){
					 dtm.removeRow(0);  //这一步是清空原来的表内容。
				 }
				for(String course_id : tempcourselist){
					String quitString = "退选";
					
					CourseInfoBLService ci = new CourseInfo();
//					String teacherID = ci.getTeacherNameByCourseID(course_id);
					String teachername = ci.getTeacherNameByCourseID(course_id);
					String coursename =ci.getCourseName(course_id);
					String type = ci.getModule(course_id);
//					String coursegrade = ci.getCourseGrade(course_id);  //该课程号的grade  开给大几的人上的。
					String coursegrade = new SelectCourseInfo().getTheGradeWhenISelectedThisCourseForQuit(student_id, course_id);
					if(coursegrade.compareToIgnoreCase(studentcurrentgrade)==0&&(type .compareToIgnoreCase((String) module.getSelectedItem())==0 )){
						Vector<String> line = new Vector<String>();
						line.add(course_id);
						line.add(coursename);
						line.add(teachername);
						line.add(type);
						line.add(quitString);
						dtm.addRow(line);
					}else{
						
					}
				}
				mainpanel.repaint();
			}
		});
		
		table.addMouseListener(new MouseAdapter(){
			
			public void mouseClicked(MouseEvent e){
				int row = table.getSelectedRow();
				int column = table.getSelectedColumn();
				if(column == 4){
					CourseSelection si =new CourseSelection();
					String coursenumber =  (String) table.getValueAt(row, 0);///error
					int result=JOptionPane.showConfirmDialog(null, "确认删除？", "Information",JOptionPane.YES_NO_OPTION);
					if(result ==0){
						si.removeCourse(student_id, coursenumber);
						showcoursebutton.doClick();
					}else{
						showcoursebutton.doClick();
					}
				}
			}
		});
		 
		showcoursebutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				StudentInfoBLService si = new StudentInfo(student_id);
				String studentcurrentgrade = si.getStuCurrentGrade();
				SelectCourseInfo mc =new SelectCourseInfo();
				ArrayList<String> tempcourselist = mc.getAllMyCoursesInTemp(student_id); //这是从临时表中获取。、不过我觉得临时表中的应该不算才对。
//				ArrayList<String> tempcourselist = new ArrayList<String>() ;// 

//				ArrayList<String> selectcourselist = mc.getAllMyCoursesInTemp(student_id);//正是表中获取。
//				tempcourselist.addAll(selectcourselist);  //合并两个课程列表。
				int size = dtm.getRowCount();
				 for(int i =0 ;i<size ;i ++){
					 dtm.removeRow(0);  //这一步是清空原来的表内容。
				 }
				for(String course_id : tempcourselist){
					String quitString = "退选";
					CourseInfoBLService ci = new CourseInfo();
//					String teacherID = ci.getTeacherNameByCourseID(course_id);
					String teachername = ci.getTeacherNameByCourseID(course_id);
					String coursename =ci.getCourseName(course_id);
					String type = ci.getModule(course_id);
//					String coursegrade = ci.getCourseGrade(course_id);  //该课程号的grade  开给大几的人上的。
					String coursegrade = new SelectCourseInfo().getTheGradeWhenISelectedThisCourseForQuit(student_id, course_id);
					if(coursegrade.compareToIgnoreCase(studentcurrentgrade)==0&&(type .compareToIgnoreCase((String) module.getSelectedItem())==0 )){
						Vector<String> line = new Vector<String>();
						line.add(course_id);
						line.add(coursename);
						line.add(teachername);
						line.add(type);
						line.add(quitString);
						dtm.addRow(line);
					}else{
						
					}
				}
				mainpanel.repaint();
//				newjsp.setBounds(r);
			}
		});
		
		JLabel label1=new JLabel("警告 ： 一旦退选，操作将不能取消，请慎重考虑");
		compopanel.add(label1);
		final JButton quitcoursebutton = new JButton("退选所选课程");
		quitcoursebutton.setEnabled(false);
		//waiting for CBB
//		
//		compopanel.add(quitcoursebutton);
		mainpanel.add(BorderLayout.NORTH,compopanel);
		compopanel.updateUI();
		compopanel.repaint();
		quitcoursebutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				CourseSelection si =new CourseSelection();
				int row = table.getSelectedRow();
				String coursenumber =  (String) table.getValueAt(row, 0);///error
				int result=JOptionPane.showConfirmDialog(null, "确认删除？", "Information",JOptionPane.YES_NO_OPTION);
				if(result ==0){
					si.removeCourse(student_id, coursenumber);
					showcoursebutton.doClick();
				}else{
					showcoursebutton.doClick();
				}
				
			}
		});
		
		
		module.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				QuitCourseButtonSetter control = null;
				switch((String)module.getSelectedItem()){
				case "通识课":
					control  = new QuitCourseButtonSetter(CourseModule.通识课);
					break;
//				case 1:
//					 control = new QuitCourseButtonSetter(CourseModule.体育课);
//					 
//					break;
				case "选修课":
					control = new QuitCourseButtonSetter(CourseModule.选修课);
					break;
				case "公选课":
					 control = new QuitCourseButtonSetter(CourseModule.公选课);
					break;
//				case "体育课" :
//					control = new QuitCourseButtonSetter(CourseModule.体育课);
//					break;
					//体育课没有退选.
				}
				
//				control.setEnable(quitcoursebutton);
			}
		});
		Tool.setOpaque(mainpanel);
		mainpanel.repaint();
		return mainpanel;
	}
	
//	private class QuitCourseListener implements ActionListener{
//
//		private JTable table;
//		private String student_id;
//		private JButton showcoursebutton;
//		
//		public QuitCourseListener(JTable table , String student_id , JButton showcoursebutton){
//			this.table = table;
//			this.showcoursebutton = showcoursebutton;
//			this.student_id = student_id;
//		}
//		
//		@Override
//		public void actionPerformed(ActionEvent arg0) {
//			// TODO Auto-generated method stub
//			// TODO 自动生成的方法存根
//			CourseSelection si =new CourseSelection();
//			int row = table.getSelectedRow();
//			String coursenumber =  (String) table.getValueAt(row, 0);///error
//			int result=JOptionPane.showConfirmDialog(null, "确认删除？", "Information",JOptionPane.YES_NO_OPTION);
//			if(result ==0){
//				si.removeCourse(student_id, coursenumber);
//				showcoursebutton.doClick();
//			}else{
//				showcoursebutton.doClick();
//			}
//		}
//		
//	}
	
//	public  static void main(String args[]){
//		
//		JFrame test=new JFrame();
//		test.add(new QuitCoursePanel().getQuitCoursePanel());
//		test.setVisible(true);
//	}
}
