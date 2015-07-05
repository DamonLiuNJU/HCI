package presentation.courseselectionui;

import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import presentation.courseui.CourseListTable;
import presentation.studentui.Tool;
import presentation.tools.Setter;
import presentation.tools.UIConstants;
import vo.courseselectionvo.CourseSelectionVO;
import businesslogic.courseselectionbl.CourseSelection;
import businesslogic.courseselectionbl.SelectCourseInfo;
import businesslogic.studentbl.Student;

public abstract class CourseSelectionFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2835670041427574729L;
	/**
	 * 
	 */
	private JButton commitbutton;
	private JButton addtotemp;
	private JButton deletefromtemp;
	
	/*
	 * tempselect是暫時存放已選課程的表格
	 */
	protected JTable tempselect;
	
	/*
	 * 這個是所有可选课程courselist的包装。
	 */
	protected DefaultTableModel tablemodel;
	
//	public void fillFrameWithModelNameAndCourselist(final String modelname,Vector<Vector<String>> courselist){
//		this.modelname = modelname;
//		this.courselist = courselist;
//		
//	}
	
	protected Student s;
	
	public CourseSelectionFrame(Student s){
		this.s = s;
	}
	
	public void showFrame(final String modelname,Vector<Vector<String>> selectedcourselist){
		final JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(null);
//		int buttonlenght = 120;
//		int buttonheight = 25;
		int comboboxlenghth = 100;
		int comboboxheight = 25;
//		JButton showcoursebutton = new JButton("");
//		Tool.setIcon(Tool.refreshbutton, showcoursebutton);
//		panel.add(showcoursebutton);
//		showcoursebutton.setBounds(125, 10, comboboxlenghth, comboboxheight);
//		final JTable table = new CourseTable().getSelectCourseTable(modelname);
		
		final JTable courselisttable = new CourseListTable().getSelectCourseByModule(modelname);//from LL
//		final DefaultTableModel dtm = (DefaultTableModel)table.getModel();
		final JScrollPane selectcoursetablepanel = new JScrollPane(courselisttable);
		//修改
		Tool.setOpaque(selectcoursetablepanel);
		panel.add(selectcoursetablepanel);
		final Rectangle selectcoursetablepanesize = new Rectangle(10, 5, 780, 400);
		selectcoursetablepanel.setBounds(selectcoursetablepanesize);
		selectcoursetablepanel.setOpaque(false);
		JLabel lable1 = new JLabel();
		panel.add(lable1);
		Rectangle r = new Rectangle(200, 10, 600, comboboxheight);
		lable1.setBounds(r);

		final Vector<String> head = new Vector<String>();
//		Vector<Vector<String>> content = new Vector<Vector<String>>();
		head.add("课程编号");
		head.add("课程名");

		final DefaultTableModel mod = new DefaultTableModel(selectedcourselist, head);
		this.tablemodel = mod;
		final JTable tempselect = new JTable(mod);
		this.tempselect = tempselect;
		final JScrollPane jsp = new JScrollPane(tempselect);
		tempselect.setOpaque(false);
		jsp.setOpaque(false);
		Tool.setOpaque(jsp);
		panel.add(jsp);
		TableColumn firsetColumn = tempselect.getColumnModel().getColumn(0);
		firsetColumn.setPreferredWidth(60);
		firsetColumn.setMaxWidth(100);
		firsetColumn.setMinWidth(60);
		
		JLabel temp_select_label = new JLabel("你的已选课程列表:");
		this.add(temp_select_label);
		temp_select_label.setBounds(10, 430, 150, 20);
		jsp.setBounds(10, 450, 250, 100);
		Tool.setOpaque(jsp);
		final JButton commitselect = new JButton("提交选择");
		this.commitbutton = commitselect;
		commitbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				commitbuttonclicked();
			}
		});
		panel.add(commitselect);
		commitselect.setBounds(270, 450, 100, 40);
		
		this.addtotemp = new JButton("添加选定课程");
		
//		addtotemp.setFont(UIConstants.DEFAULT_FONT);
		addtotemp.setBounds(700, 600, 70, 30);
		addtotemp.addActionListener(new ActionListener() {
			/*
			 * (non-Javadoc)感觉这个是都一样的，无需定制。
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				int rownumber = courselisttable.getSelectedRow();
				if(rownumber < 0){
					return;
				}
				String courseid = (String) courselisttable.getValueAt(rownumber, 0);
				String coursename = (String) courselisttable.getValueAt(rownumber, 1);
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
					String message = UIConstants.DO_NOT_ADD_TOO_MUCH_COURSE;
					JOptionPane.showMessageDialog(null, message);
				} else {
					mod.addRow(rowData);
					JOptionPane.showMessageDialog(null, UIConstants.SELECT_SUCCESS);
				}
			}
			
			
			
		});

		JButton cancelselect = new JButton("删除选择");
		this.deletefromtemp = cancelselect;
		deletefromtemp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				deletefromtempbuttonclicked();
			}
		});
//		cancelselect.addActionListener(new ActionListener() {
//			/*
//			 * (non-Javadoc)貌似需要个人定制
//			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
//			 */
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

		panel.add(cancelselect);
		cancelselect.setBounds(270, 500, 100, 40);

//		showcoursebutton.addActionListener(new ActionListener() {
//			/*
//			 * (non-Javadoc)无需定制,通用的方法。
//			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
//			 */
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO 自动生成的方法存根
//				final JTable table = new CourseListTable().getSelectCourseByModule(modelname);
//				panel.remove(selectcoursetablepanel);
//				panel.updateUI();
//				JScrollPane jsp = new JScrollPane(table);
//				panel.add(jsp);
//				Tool.setOpaque(jsp);
//				jsp.setBounds(selectcoursetablepanesize);
//				jsp.updateUI();
//				panel.updateUI();
//				panel.repaint();
//			}
//		});
		this.setTitle(modelname);
		this.setLayout(null);
		this.addWindowListener(null);
		Container c = this.getContentPane();
		Tool.setOpaque(panel);
		c.add(panel);
		panel.setBounds(0, 0, 800, 600);
		new Tool().setFrameLocationAndSize(this);
		Setter setter = new Setter();
		Tool.setOpaque(jsp);
		setter.addBackground(this, Tool.FrameImagePath);
//		setter.addBackground(this, Tool.FrameImagePath);
		JPanel componentpanel = new JPanel();
		componentpanel.setLayout(null);
		this.add(addtotemp);
		
//		componentpanel.add(showcoursebutton);
		c.add(componentpanel);
		componentpanel.setBounds(0, 0, UIConstants.WINDOWWIDTH, 40);
		Tool.setOpaque(componentpanel);
		
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				commitbuttonclicked();
			}
		});
		
		JButton backButton = new JButton("返回");
		
		backButton.setBounds(600,500,100,30);
		addtotemp.setBounds(600, 450, 100, 30);
		
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		this.add(backButton);
		
		this.setVisible(true);
	}

	public JButton getCommitbutton() {
		return commitbutton;
	}

	public void setCommitbutton(JButton commitbutton) {
		this.commitbutton = commitbutton;
	}

	public JButton getAddtotemp() {
		return addtotemp;
	}

	public void setAddtotemp(JButton addtotemp) {
		this.addtotemp = addtotemp;
	}

	public JButton getDeletefromtemp() {
		return deletefromtemp;
	}

	public void setDeletefromtemp(JButton deletefromtemp) {
		this.deletefromtemp = deletefromtemp;
	}

	public JTable getTempselect() {
		return tempselect;
	}

	public void setTempselect(JTable tempselect) {
		this.tempselect = tempselect;
	}

	public DefaultTableModel getTablemodel() {
		return tablemodel;
	}

	public void setTablemodel(DefaultTableModel tablemodel) {
		this.tablemodel = tablemodel;
	}
	
	
//	public abstract void commitbuttonclicked();
//	public abstract void deletefromtempbuttonclicked();
	
	public void commitbuttonclicked() {
		// TODO Auto-generated method stub
		CourseSelection cs = new CourseSelection();
		CourseSelectionVO sv = new CourseSelectionVO(s.getStudentID());
		for (int i = 0; i < tempselect.getRowCount(); i++) {
			String course_id = null;
			course_id = (String) tempselect.getValueAt(i, 0);
			boolean courseexists = false;
			courseexists = new SelectCourseInfo().courseExist(s.getStudentID(), course_id);
			if(courseexists){
				
			}else{
				sv.setCourseID(course_id);
				cs.selectCourse(sv);
			}
			
		}
//		int selectedcoursenumber = tempselect.getRowCount();
//		if(selectedcoursenumber==0){
//			int result=JOptionPane.showConfirmDialog(null, "确认提交空的课程选择？", "Information",JOptionPane.YES_NO_OPTION);
//			if(result == 0){				
//				this.dispose();
//			}else{
//				 
//			}
//			
//		}else{
//			JOptionPane.showMessageDialog(null, "选择已提交");
//			this.dispose();
//		}
	}

	public void deletefromtempbuttonclicked() {
		// TODO Auto-generated method stub
		int row = tempselect.getSelectedRow();
		if(row>=0){
			String course_id = (String) tempselect.getValueAt(row, 0);
			tablemodel.removeRow(row);
			CourseSelection si = new CourseSelection();
			si.removeCourse( (s.getStudentID()), course_id);
			JOptionPane.showMessageDialog(null, UIConstants.DELETE_SUCCESS);
		}
	}
	
	
	
}
