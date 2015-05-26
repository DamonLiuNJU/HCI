package presentation.studentui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import presentation.courseui.CourseListTable;
import businesslogic.courseselectionbl.SelectCourseInfo;
import businesslogic.utilitybl.CourseModule;
//import presentation.courseui.CourseListTable;
//import businesslogic.coursebl.Course;

public class GetCourseInfoPanel {

	/**
	 * @param args
	 */
	public JPanel getCourseInfoPanel(final String studentnumber) {

		Vector<String> head = new Vector<String>();
		head.add("课程号");
		head.add("课程名");
		head.add("教师姓名");
		head.add("校区");
		head.add("开课年份");
		head.add("上课地点");
		head.add("上课时间	");
		head.add("起止周");
		head.add("特殊要求");
		head.add("学分");
		head.add("课程类型");
//		Vector<Vector<String>> content = new Vector<Vector<String>>();

//		final DefaultTableModel dtm = new DefaultTableModel(content, head);
//		final JTable table = new JTable(dtm);
//		final JScrollPane jsp = new JScrollPane(table);

		final JComboBox<String> modelselect = new JComboBox<String>();
		modelselect.addItem("我的课程");
		modelselect.addItem("全校课程");

		final JPanel componentpanel = new JPanel();
		componentpanel.setOpaque(false);
		componentpanel.setLayout(new FlowLayout(0, 10, 10));
		componentpanel.add(modelselect);
		final JPanel mainpanel = new JPanel();
		mainpanel.setOpaque(false);
		BorderLayout layout = new BorderLayout();
		mainpanel.setLayout(layout);
		int comboboxlenghth = 140;
		int comboboxheight = 25;
		final JComboBox<?> campus = new CampusBox();
//		campus.addItem("仙林");
//		campus.addItem("鼓楼");
		componentpanel.add(campus);
		final JComboBox<String> grade = new SemesterSelectionBox();
//		grade.addItem("大一上");
//		grade.addItem("大一下");
//		grade.addItem("大二上");
//		grade.addItem("大二下");
//		grade.addItem("大三上");
//		grade.addItem("大三下");
//		grade.addItem("大四上");
//		grade.addItem("大四下");
		componentpanel.add(grade);

		final JComboBox<?> major = new MajorComboBox();
//		major.addItem("软件学院");
//		major.addItem("商学院");
//		major.addItem("文学院");
//		major.addItem("社会学院");
//		major.addItem("政府管理学院");
		componentpanel.add(major);
		
		major.setEnabled(false);
		campus.setEnabled(false);
//		grade.setEnabled(false);

		modelselect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				int index = modelselect.getSelectedIndex();
				if (index == 0) {
					//查看我的课程的时候
					major.setEnabled(false);
					campus.setEnabled(false);
					componentpanel.repaint();
					mainpanel.repaint();
//					grade.setEnabled(false);
					
				}else{
					major.setEnabled(true);
					campus.setEnabled(true);
					grade.setEnabled(true);
					componentpanel.repaint();
					mainpanel.repaint();
				}
			}
		});

		JButton showcoursebutton = new JButton("显示");
		showcoursebutton.setSize(comboboxlenghth, comboboxheight);
		// mainpanel.add(BorderLayout.NORTH,showcoursebutton);
		componentpanel.add(showcoursebutton);
//		mainpanel.add(BorderLayout.CENTER, jsp);
		showcoursebutton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int mode = modelselect.getSelectedIndex();
				
				if(mode == 0){
					//查看自己的课程；
					
					String gradeinfo  = (String) grade.getSelectedItem();
//					CourseInfo ci = new CourseInfo();
					String student_id = studentnumber;
					SelectCourseInfo mc = new SelectCourseInfo();
					ArrayList<String> mycourselistinselect = mc.getAllMyCoursesInSelect(student_id);
					ArrayList<String> mycourselistintemp = mc.getAllMyCoursesInTemp(student_id);
					boolean currenttimespecial = false;
					//这里是默认了退选课程的时间都是和通识课一样的。
					currenttimespecial = new SelectCourseInfo().isCurrentTimeSpecial(CourseModule.通识课);
					if(currenttimespecial){
						//如果当前时间是处于开学两星期内。
						mycourselistinselect.addAll(mycourselistintemp);
					}
					ArrayList<String> totallist =mycourselistinselect;
					ArrayList<String> cno = new ArrayList<String>();
					for(String course_id: totallist){
						String mygrade=new SelectCourseInfo().getTheGradeWhenISelectedThisCourse(student_id, course_id);
						if(mygrade .compareToIgnoreCase(gradeinfo)==0){
							cno.add(course_id);
						}else{
							
						}
					}
//					CourseListTable clt = new CourseListTable();
					JTable newtable = new CourseListTable().getCourseInfoTable(cno);
					JScrollPane newjsp= new JScrollPane(newtable);
					Tool.setOpaque(newjsp);
					mainpanel.removeAll();
					mainpanel.add(BorderLayout.NORTH, componentpanel);
					mainpanel.add(BorderLayout.CENTER,newjsp);
					mainpanel.updateUI();
					
				}else if(mode ==1){
					//查看全校的课程；
				 
//					CourseListTable clt = new CourseListTable();
				 
					String campus_limit = (String) campus.getSelectedItem();
					String grade_limit = (String) grade.getSelectedItem();
					String major_limit =(String) major.getSelectedItem();
					JTable newtable = new CourseListTable().getSearchTable(campus_limit, grade_limit, major_limit);
					JScrollPane newjsp = new JScrollPane(newtable);
					Tool.setOpaque(newjsp);
					mainpanel.removeAll();
					mainpanel.add(BorderLayout.NORTH, componentpanel);
					componentpanel.updateUI();
					mainpanel.add(BorderLayout.CENTER,newjsp);
					mainpanel.updateUI();

				}
				
			}
		});

		mainpanel.add(BorderLayout.NORTH, componentpanel);
		
		
		Tool.setOpaque(mainpanel);
		modelselect.repaint();
		mainpanel.updateUI();
		mainpanel.repaint();
		return mainpanel;
	}

//	public static void main(String args[]) {
//		JFrame testframe = new JFrame();
//		Container c = testframe.getContentPane();
//		JPanel panel = new GetCourseInfoPanel().getCourseInfoPanel(null);
//		c.add(panel);
//		testframe.setVisible(true);
//
//	}
}
