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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import presentation.courseui.CourseListTable;
import presentation.studentui.MajorComboBox;
import presentation.studentui.Tool;
import presentation.tools.Setter;
import vo.courseselectionvo.CourseSelectionVO;
import businesslogic.courseselectionbl.CourseSelection;
import businesslogic.courseselectionbl.SelectCourseInfo;
import businesslogic.studentbl.CourseInfo;
import businesslogic.studentbl.Student;
import businesslogic.studentbl.StudentInfo;

public class OtherMajorCourseSelection extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2377415682213331838L;
	/**
	 * @param args
	 */
	JFrame frame;
	public Vector<Vector<String>> removeMyFacultyCourse(Vector<Vector<String>> mycourselist,String student_id){
		//在查看跨专业选课的课程的时候是不可以查看到自己本专业的必修课的.
		String studentfaculty = new StudentInfo(student_id).getFacultyID(student_id);
		int size = mycourselist.size();
		Vector<Vector<String>> result = new Vector<Vector<String>>();
	
		for(int i = 0 ; i < size ; i ++){
			Vector<String> line = mycourselist.get(i);
			String course_id = line.get(0);
			String course_faculty = new CourseInfo().getFacultyIDByCourseID(course_id);
			if(studentfaculty .compareToIgnoreCase(course_faculty)==0){
				continue;
			}else{
				result.add(line);
			}
		}
		
		return result;
	}
	
	public OtherMajorCourseSelection(Student s){
		
		 final String student_id = s.getStudentID();
		 final String modelname = "必修课";
		 Vector<Vector<String>> mycourselist =new SelectCourseInfo().getSelectedCourse(student_id, modelname); //store the lessons he has chosen;
		//那个暂时存放的表还是需要一些复杂逻辑，先要根据学生的ID去获取他所有的课程号，然后根据课程号判断是否和
		//modelname符合，然后还要取得课程的名字。完了还要显示在一个小型的暂时存放的表里，最后根据最终的情况判断删除了哪些
		//点了提交修改之后就直接出发事件检测删除了哪些课程，然后在数据库中操作。
		 mycourselist = this.removeMyFacultyCourse(mycourselist, student_id);
		final JPanel componentpanel = new JPanel();
		componentpanel.setLayout(new FlowLayout(0,10,0));
		final JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(null);
//		int buttonlenght = 120;
//		int buttonheight = 25;
//		int comboboxlenghth = 100;
//		int comboboxheight = 25;
		JButton showcoursebutton = new JButton("");
		Tool.setIcon(Tool.refreshbutton, showcoursebutton);
		final JComboBox<?> faculty =  new MajorComboBox();
		String studentfaculyt = new StudentInfo(student_id).getFacultyID(student_id);
		String facultyname = new CourseInfo().getFacultyName(studentfaculyt); //?????
		faculty.removeItem(facultyname);
		
		componentpanel.add(faculty);
		componentpanel.add(showcoursebutton);
//		showcoursebutton.setBounds(125, 10, comboboxlenghth, comboboxheight);
//		final JTable table = new CourseTable().getSelectCourseTable(modelname);
		final JTable table = new CourseListTable().getCompulsoryCourseByFaculty((String) faculty.getSelectedItem());
		final JScrollPane selectcoursetablepanel = new JScrollPane(table);
		Tool.setOpaque(selectcoursetablepanel);
		panel.add(selectcoursetablepanel);
		final Rectangle selectcoursetablepanesize = new Rectangle(10, 40, 780, 400);
		selectcoursetablepanel.setBounds(selectcoursetablepanesize);
		selectcoursetablepanel.setOpaque(false);
//		JLabel lable1 = new JLabel(
//				"点击“添加课程”按钮，将要选择的课程添加到右侧列表");
		
//		Rectangle r = new Rectangle(200, 10, 600, comboboxheight);
//		lable1.setBounds(r);

		final Vector<String> head = new Vector<String>();
//		Vector<Vector<String>> content = new Vector<Vector<String>>();
		head.add("课程编号");
		head.add("课程名");

		final DefaultTableModel mod = new DefaultTableModel(mycourselist, head);
		final JTable tempselect = new JTable(mod);
		final JScrollPane jsp = new JScrollPane(tempselect);
		tempselect.setOpaque(false);
		jsp.setOpaque(false);
		Tool.setOpaque(jsp);
		panel.add(jsp);
		jsp.setBounds(10, 450, 150, 110);
		Tool.setOpaque(jsp);
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
		componentpanel.add(addtotempselect);
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

		final JButton cancelselect = new JButton("删除选择");
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

		panel.add(cancelselect);
		cancelselect.setBounds(170, 500, 100, 40);

		showcoursebutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
//				Student s = new Student();
//				Vector<Vector<String>> content = s.getChooseCourseList(modelname);
				panel.removeAll();
				table.removeAll();
				panel.remove(table);
				panel.remove(selectcoursetablepanel);
				panel.updateUI();
				panel.repaint();
				final JTable newtable = new CourseListTable().getCompulsoryCourseByFaculty((String) faculty.getSelectedItem());
				JScrollPane newjsp = new JScrollPane(newtable);
				
				panel.updateUI();
				panel.add(componentpanel);
				componentpanel.setLocation(0, 0);
				panel.add(jsp);
				panel.add(commitselect);
				panel.add(cancelselect);
				panel.add(newjsp);
				newjsp.setBounds(selectcoursetablepanesize);
				Tool.setOpaque(newjsp);
				panel.updateUI();
				panel.repaint();
				panel.getParent().repaint();
				
//				int currentsize = mod.getRowCount();
//				for(int i = currentsize ;i>0;i--){
//					 mod.removeRow(0);
//				}
//				for(Vector<String > line : content)
//					mod.addRow(line);
//				
//				panel.remove(selectcoursetablepanel);
//				panel.repaint();
//				panel.updateUI();
//				JScrollPane jsp = new JScrollPane(table);
//				panel.add(jsp);
//				Tool.setOpaque(jsp);
//				jsp.setBounds(selectcoursetablepanesize);
//				jsp.updateUI();
//				panel.updateUI();
//				panel.repaint();
				
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
//		componentpanel.add(lable1);
		Tool.setOpaque(componentpanel);
		panel.add(componentpanel);
		componentpanel.setBounds(0, 0, 400, 40);
//		componentpanel.setLocation(0, 0);
		Container c = frame.getContentPane();
		Tool.setOpaque(panel);
		c.add(panel);
		panel.setBounds(0, 0, 800, 600);
		frame = new Tool().setFrameLocationAndSize(frame);
		Setter setter = new Setter();
		Tool.setOpaque(jsp);
		setter.addBackground(frame, Tool.FrameImagePath);
		setter.addBackground(this, Tool.FrameImagePath);
		frame.setVisible(true);
//		return frame;
	}
	
	

}
