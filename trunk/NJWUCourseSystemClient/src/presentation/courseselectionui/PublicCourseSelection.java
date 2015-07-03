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

public class PublicCourseSelection {

	/**
	 * @param args
	 */
	JFrame frame  ; 
	public PublicCourseSelection(Student s){
		 final String student_id = s.getStudentID();
		 final String modelname = "公选课";
		Vector<Vector<String>> mycourselist =new SelectCourseInfo().getSelectedCourse(student_id, modelname); //store the lessons he has chosen;
		//那个暂时存放的表还是需要一些复杂逻辑，先要根据学生的ID去获取他所有的课程号，然后根据课程号判断是否和
		//modelname符合，然后还要取得课程的名字。完了还要显示在一个小型的暂时存放的表里，最后根据最终的情况判断删除了哪些
		//点了提交修改之后就直接出发事件检测删除了哪些课程，然后在数据库中操作。

		final JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);
//		int buttonlenght = 120;
//		int buttonheight = 25;
		int comboboxlenghth = 100;
		int comboboxheight = 25;
		JButton showcoursebutton = new JButton("");
		Tool.setIcon(Tool.refreshbutton, showcoursebutton);
//		panel.add(showcoursebutton);
		showcoursebutton.setBounds(125, 10, comboboxlenghth, comboboxheight);
//		final JTable table = new CourseTable().getSelectCourseTable(modelname);
		final JTable table = new CourseListTable().getSelectCourseByModule(modelname);//from LL
		final JScrollPane selectcoursetablepanel = new JScrollPane(table);
		Tool.setOpaque(selectcoursetablepanel);
		table.setOpaque(false);
		selectcoursetablepanel.setOpaque(false);
		panel.add(selectcoursetablepanel);
		final Rectangle selectcoursetablepanesize = new Rectangle(10, 40, 780, 400);
		selectcoursetablepanel.setBounds(selectcoursetablepanesize);

		JLabel lable1 = new JLabel(
				"点击“添加课程”按钮，将要选择的课程添加到右侧列表并点击 “提交选择”（若不提交选择将不能选课）");
		panel.add(lable1);
		Rectangle r = new Rectangle(200, 10, 600, comboboxheight);
		lable1.setBounds(r);

		final Vector<String> head = new Vector<String>();
//		Vector<Vector<String>> content = new Vector<Vector<String>>();
		head.add("课程编号");
		head.add("课程名");

		final DefaultTableModel mod = new DefaultTableModel(mycourselist, head);
		final JTable tempselect = new JTable(mod);
		final JScrollPane jsp = new JScrollPane(tempselect);
		Tool.setOpaque(jsp);
		panel.add(jsp);
		jsp.setBounds(10, 450, 150, 110);

		final JButton commitselect = new JButton("提交选择");
		commitselect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				CourseSelection s = new CourseSelection();
				CourseSelectionVO sv = new CourseSelectionVO(student_id);
				for (int i = 0; i < tempselect.getRowCount(); i++) {
					String course_id = null;
					course_id = (String) tempselect.getValueAt(i, 0);
					boolean courseexists = false;
					courseexists = new SelectCourseInfo().courseExist(student_id, course_id);
					if(courseexists){
						
					}else{
						sv.setCourseID(course_id);
						s.selectCourse(sv);
					}
					
				}
				
				int selectedcoursenumber = tempselect.getRowCount();
				if(selectedcoursenumber==0){
					int result=JOptionPane.showConfirmDialog(null, "确认提交空的课程选择？", "Information",JOptionPane.YES_NO_OPTION);
					if(result == 0){				
						frame.dispose();
					}else{
						 
					}
					
				}else{
					JOptionPane.showMessageDialog(null, "选择已提交");
					frame.dispose();
				}
			}
		});
		panel.add(commitselect);
		commitselect.setBounds(170, 450, 100, 40);
		final JButton addtotempselect = new JButton("");
		Tool.setIcon(Tool.add, addtotempselect);
//		panel.add(addtotempselect);
		addtotempselect.setBounds(10, 10, 40, 40);
		addtotempselect.setIcon(new ImageIcon(Tool.add));
		addtotempselect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rownumber = table.getSelectedRow();
				String courseid = (String) table.getValueAt(rownumber, 0);
				String coursename = (String) table.getValueAt(rownumber, 1);
				boolean unabletoadd = false;
				for (int i = 0; i < tempselect.getRowCount(); i++) {
					String id = (String) tempselect.getValueAt(i, 0);
					if (id.compareToIgnoreCase(courseid) == 0 || tempselect.getRowCount() >= 4) {
						unabletoadd = true;
						break;
					}
				}

				Vector<String> rowData = new Vector<String>();
				rowData.add(courseid);
				rowData.add(coursename);
				if (unabletoadd) {
					String message = "请勿重复添加课程或添加多于四个课程";
					JOptionPane.showMessageDialog(null, message);
				} else {
					mod.addRow(rowData);
				}
			}
		});

		JButton backButton = new JButton("返回");
		backButton.setBounds(700,450,70,30);
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frame.dispose();
			}
		});
		panel.add(backButton);
		
		JButton cancelselect = new JButton("删除选择");
		cancelselect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				int row = tempselect.getSelectedRow();
				if(row>=0){
					String course_id = (String) tempselect.getValueAt(row, 0);
					mod.removeRow(row);
					CourseSelection si = new CourseSelection();
					si.removeCourse( (student_id), course_id);
					JOptionPane.showMessageDialog(null, "删除成功");
				}
			}
		});

		panel.add(cancelselect);
		cancelselect.setBounds(170, 500, 100, 40);

		showcoursebutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
//				Student s = new Student();
//				Vector<Vector<String>> content = s.getChooseCourseList(modelname);
				final JTable table = new CourseListTable().getSelectCourseByModule(modelname);
//				int currentsize = mod.getRowCount();
//				for(int i = currentsize ;i>0;i--){
//					 mod.removeRow(0);
//				}
//				for(Vector<String > line : content)
//					mod.addRow(line);
//				
				panel.remove(selectcoursetablepanel);
				panel.updateUI();
				JScrollPane jsp = new JScrollPane(table);
				panel.add(jsp);
				Tool.setOpaque(jsp);
				jsp.setBounds(selectcoursetablepanesize);
				jsp.updateUI();
				panel.updateUI();
				panel.repaint();
				
			}

		});
		 frame = new JFrame(modelname);
		frame.setLayout(null);
		frame.addWindowListener(new WindowListener(){
			 

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO 自动生成的方法存根
//				commitselect.doClick();
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO 自动生成的方法存根
				
			}
			});
		Container c = frame.getContentPane();
		c.add(panel);
		panel.setBounds(0, 0, 800, 600);
		frame = new Tool().setFrameLocationAndSize(frame);
		new Setter().addBackground(frame,Tool.FrameImagePath);
		Tool.setOpaque(jsp);
		JPanel componentpanel = new JPanel();
		componentpanel.setLayout(new FlowLayout(0,10,0));
		
		componentpanel.add(addtotempselect);
		componentpanel.add(showcoursebutton);
		Tool.setOpaque(componentpanel);
		c.add(componentpanel);
		componentpanel.setBounds(0, 0, 200, 40);
		frame.setVisible(true);
		
//		return frame;
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

}
