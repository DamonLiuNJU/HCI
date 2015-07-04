package presentation.courseselectionui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import presentation.courseui.CourseListTable;
import presentation.studentui.Tool;
import presentation.tools.Setter;
import vo.courseselectionvo.CourseSelectionVO;
import businesslogic.courseselectionbl.CourseSelection;
import businesslogic.courseselectionbl.SelectCourseInfo;
import businesslogic.studentbl.Student;
import businesslogic.utilitybl.CourseModule;
//import businesslogic.statusbl.SelectCourseStatus;
//import businesslogic.statusbl.Status;

public class SelectCourseModule {

	public JPanel selectModelPanel(final String student_id) {
		final Student s = new Student (student_id);
		final JPanel selectmodelpanel = new JPanel();
//		CourseListTable coursetable = new CourseListTable();
		JButton commonknowledge = new JButton("通识课选课");
		Tool.setIcon(Tool.bookimage, commonknowledge);
		commonknowledge.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				boolean valid = false;
//				Status s = new SelectCourseStatus(CourseModule.通识课);
//				valid = s.current();//接口修改
				valid = new SelectCourseInfo().isCurrentTimeValidForSelectionCourse(CourseModule.通识课);
				if (valid) {
					CommonKnowledgeCourseSelectionFrame frame = new CommonKnowledgeCourseSelectionFrame(s);
				} else {
					JOptionPane.showMessageDialog(null, "不在选课时间内");
				}
			}
		});

		JButton PE = new JButton("体育选课");
		Tool.setIcon(Tool.bookimage, PE);
		PE.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
//				boolean valid = new SelectCourseStatus()
//						.current();	
				boolean valid = new SelectCourseInfo().isCurrentTimeValidForSelectionCourse(CourseModule.体育课);
				boolean selected = new CourseSelection().seletedPE(student_id);
				if (valid) {
					if(!selected){
						new PECourseFrame(student_id);
					}else{
						JOptionPane.showMessageDialog(null, "你已经选择过体育课程，请勿重复选择。\n 要查看已选的体育课程请点击查看：我的课程");
					}
				} else {
					JOptionPane.showMessageDialog(null, "不在体育选课时间内");
				}
			}
		});
		// 专业选课
		JButton major = new JButton("专业选修课选课");
		Tool.setIcon(Tool.bookimage, major);
		major.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				boolean valid = new SelectCourseStatus(CourseModule.选修课)
//						.current();
				boolean valid = new SelectCourseInfo().isCurrentTimeValidForSelectionCourse(CourseModule.选修课);
				if (valid) {
//					final String modelname = "专业";
//					new SelectCourseModule().getSelectCommonKnowlegeFrame(modelname, student_id);
					Student s = new Student(student_id);
					new ElectiveCourseFrame(s);
				} else {
					JOptionPane.showMessageDialog(null, "不在选修课选课时间内");

				}

			}

		});

		// 公共选课
		JButton publiccourse = new JButton("公共选课");
		Tool.setIcon(Tool.bookimage, publiccourse);
		publiccourse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				boolean valid = new SelectCourseStatus(CourseModule.公选课)
//						.current();
				boolean valid = new SelectCourseInfo().isCurrentTimeValidForSelectionCourse(CourseModule.公选课);
				if (valid) {
//					final String modelname = "公共课程";
//					new SelectCourseModule().getSelectCommonKnowlegeFrame(modelname, student_id);
					new PublicCourseSelection(new Student(student_id));
				} else {
					JOptionPane.showMessageDialog(null, "不在公选课选课时间内");
				}
			}
		});
		
		JButton othermajor = new JButton("跨专业选课");
		Tool.setIcon(Tool.bookimage, othermajor);
		othermajor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				new OtherMajorCourseSelection(new Student(student_id));
			}
		});
		
		JButton secondselection = new JButton("补选课程");
		Tool.setIcon(Tool.bookimage, secondselection);
		secondselection.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				boolean currenttimevalid = true;
				if(currenttimevalid){
					new SecondSelection().setVisible(true);
				}
			}
		});
		
		selectmodelpanel.setLayout(new FlowLayout(0, 50, 50));
		selectmodelpanel.add(commonknowledge);
		selectmodelpanel.add(PE);
		selectmodelpanel.add(major);
		selectmodelpanel.add(publiccourse);
		selectmodelpanel.add(othermajor);
		// commonknowledge.setBounds(buttonsize);
		Tool.setOpaque(selectmodelpanel);
		return selectmodelpanel;
	}

//	//这个可以单独出来一个类
//	JFrame frame ; 
//	private JFrame getSelectCommonKnowlegeFrame(final String modelname,final String student_id) {
//
//		 
//		Vector<Vector<String>> mycourselist =new SelectCourseInfo().getSelectedCourse(student_id, modelname); //store the lessons he has chosen;
//		//那个暂时存放的表还是需要一些复杂逻辑，先要根据学生的ID去获取他所有的课程号，然后根据课程号判断是否和
//		//modelname符合，然后还要取得课程的名字。完了还要显示在一个小型的暂时存放的表里，最后根据最终的情况判断删除了哪些
//		//点了提交修改之后就直接出发事件检测删除了哪些课程，然后在数据库中操作。
//		
//		final JPanel panel = new JPanel();
//		panel.setOpaque(false);
//		panel.setLayout(null);
////		int buttonlenght = 120;
////		int buttonheight = 25;
////		int comboboxlenghth = 100;
//		int comboboxheight = 25;
//		JButton showcoursebutton = new JButton("");
//		Tool.setIcon(Tool.refreshbutton, showcoursebutton);
////		panel.add(showcoursebutton);
//		showcoursebutton.setBounds(50, 10, 30, 30);
//
////		final JTable table = new CourseTable().getSelectCourseTable(modelname);
//		final JTable table = new CourseListTable().getSelectCourseByModule(modelname);//from LL
//		final JScrollPane selectcoursetablepanel = new JScrollPane(table);
//		Tool.setOpaque(selectcoursetablepanel);
//		panel.add(selectcoursetablepanel);
//		final Rectangle selectcoursetablepanesize = new Rectangle(10, 40, 780, 400);
//		selectcoursetablepanel.setBounds(selectcoursetablepanesize);
//
//		JLabel lable1 = new JLabel(
//				"点击“添加课程”按钮，将要选择的课程添加到右侧列表并点击 “提交选择”（若不提交选择将不能选课）");
//		panel.add(lable1);
//		Rectangle r = new Rectangle(200, 10, 600, comboboxheight);
//		lable1.setBounds(r);
//
//		final Vector<String> head = new Vector<String>();
////		Vector<Vector<String>> content = new Vector<Vector<String>>();
//		head.add("课程编号");
//		head.add("课程名");
//
//		final DefaultTableModel mod = new DefaultTableModel(mycourselist, head);
//		final JTable tempselect = new JTable(mod);
//		final JScrollPane jsp = new JScrollPane(tempselect);
//		Tool.setOpaque(jsp);
//		panel.add(jsp);
//		jsp.setBounds(10, 450, 150, 110);
//
//		final JButton commitselect = new JButton("提交选择");
//		commitselect.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO 自动生成的方法存根
//				CourseSelection s = new CourseSelection();
//				CourseSelectionVO sv = new CourseSelectionVO(student_id);
//				for (int i = 0; i < tempselect.getRowCount(); i++) {
//					String course_id = null;
//					course_id = (String) tempselect.getValueAt(i, 0);
//					boolean courseexists = false;
//					courseexists = new SelectCourseInfo().courseExist(student_id, course_id);
//					if(courseexists){
//						
//					}else{
//						sv.setCourseID(course_id);
//						s.selectCourse(sv);
//					}
//					
//				}
//				
//				int selectedcoursenumber = tempselect.getRowCount();
//				if(selectedcoursenumber==0){
//					int result=JOptionPane.showConfirmDialog(null, "确认提交空的课程选择？", "Information",JOptionPane.YES_NO_OPTION);
//					if(result == 0){				
//						frame.dispose();
//					}else{
//						 
//					}
//					
//				}else{
//					JOptionPane.showMessageDialog(null, "选择已提交");
//					frame.dispose();
//				}
//				
//			}
//		});
//		panel.add(commitselect);
//		commitselect.setBounds(170, 450, 100, 40);
//		final JButton addtotempselect = new JButton("");
////		Tool.setIcon(Tool.add, addtotempselect);
//		addtotempselect.setIcon(new ImageIcon(Tool.add));
//		addtotempselect.setBorderPainted(false);
//		addtotempselect.setOpaque(false);
////		panel.add(addtotempselect);
//		addtotempselect.setBounds(10, 10,30, 30);
//		addtotempselect.setOpaque(false);
//		addtotempselect.setContentAreaFilled(false);
//		addtotempselect.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO 自动生成的方法存根
//				int rownumber = table.getSelectedRow();
//				String courseid = (String) table.getValueAt(rownumber, 0);
//				String coursename = (String) table.getValueAt(rownumber, 1);
//				boolean unabletoadd = false;
//				for (int i = 0; i < tempselect.getRowCount(); i++) {
//					String id = (String) tempselect.getValueAt(i, 0);
//					if (id.compareToIgnoreCase(courseid) == 0
//							|| tempselect.getRowCount() >= 4) {
//						unabletoadd = true;
//						break;
//					}
//				}
//
//				Vector<String> rowData = new Vector<String>();
//				rowData.add(courseid);
//				rowData.add(coursename);
//				if (unabletoadd) {
//					String message = "请勿重复添加课程或添加多于四个课程";
//					JOptionPane.showMessageDialog(null, message);
//				} else {
//					mod.addRow(rowData);
//				}
//			}
//		});
//
//		JButton cancelselect = new JButton("删除选择");
//		cancelselect.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO 自动生成的方法存根
//				int row = tempselect.getSelectedRow();
//				if(row>=0){
//					String course_id = (String) tempselect.getValueAt(row, 0);
//					mod.removeRow(row);
//					CourseSelection si = new CourseSelection();
//					si.removeCourse( (student_id), course_id);
//					JOptionPane.showMessageDialog(null, "删除成功");
//				}
//			}
//		});
//
//		panel.add(cancelselect);
//		cancelselect.setBounds(170, 500, 100, 40);
//
//		showcoursebutton.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO 自动生成的方法存根
////				Student s = new Student();
////				Vector<Vector<String>> content = s.getChooseCourseList(modelname);
//				final JTable table = new CourseListTable().getSelectCourseByModule(modelname);
////				int currentsize = mod.getRowCount();
////				for(int i = currentsize ;i>0;i--){
////					 mod.removeRow(0);
////				}
////				for(Vector<String > line : content)
////					mod.addRow(line);
////				
//				panel.remove(selectcoursetablepanel);
//				panel.updateUI();
//				JScrollPane jsp = new JScrollPane(table);
//				panel.add(jsp);
//				Tool.setOpaque(jsp);
//				jsp.setBounds(selectcoursetablepanesize);
//				jsp.updateUI();
//				panel.updateUI();
//				panel.repaint();
//				
//			}
//
//		});
//		frame = new JFrame(modelname);
//		frame.setLayout(null);
//		frame.addWindowListener(new WindowListener(){
//			 
//
//			@Override
//			public void windowOpened(WindowEvent e) {
//				// TODO 自动生成的方法存根
//				
//			}
//
//			@Override
//			public void windowClosing(WindowEvent e) {
//				// TODO 自动生成的方法存根
//				
//			}
//
//			@Override
//			public void windowClosed(WindowEvent e) {
//				// TODO 自动生成的方法存根
////				commitselect.doClick();
//			}
//
//			@Override
//			public void windowIconified(WindowEvent e) {
//				// TODO 自动生成的方法存根
//				
//			}
//
//			@Override
//			public void windowDeiconified(WindowEvent e) {
//				// TODO 自动生成的方法存根
//				
//			}
//
//			@Override
//			public void windowActivated(WindowEvent e) {
//				// TODO 自动生成的方法存根
//				
//			}
//
//			@Override
//			public void windowDeactivated(WindowEvent e) {
//				// TODO 自动生成的方法存根
//				
//			}
//			});
//		Container c = frame.getContentPane();
//		c.add(panel);
//		panel.setBounds(0, 0, 800, 600);
//		frame  = new Tool().setFrameLocationAndSize(frame);
//		JPanel componentpanel = new JPanel();
//		componentpanel.setLayout(new FlowLayout(0, 10, 0));
//		componentpanel.add(addtotempselect);
//		componentpanel.add(showcoursebutton);
//		Tool.setOpaque(componentpanel);
//		c.add(componentpanel);
//		componentpanel.setBounds(0, 0, 200, 40);
//		frame.setVisible(true);
//		frame.setTitle("通识课选课");
//		new Setter().addBackground(frame, Tool.FrameImagePath);
//		return frame;
//	}

}
