package presentation.courseselectionui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

public abstract class CommonCourseSelectionFrame extends JFrame {
	private String modelname;
	private Vector<Vector<String>> courselist;
	private JButton commitbutton;
	private JButton addtotemp;
	private JButton deletefromtemp;
	private JTable tempselect;
	private DefaultTableModel tablemodel;
	
//	public void fillFrameWithModelNameAndCourselist(final String modelname,Vector<Vector<String>> courselist){
//		this.modelname = modelname;
//		this.courselist = courselist;
//		
//	}
	
	public CommonCourseSelectionFrame(){
		
	}
	
	public void showFrame(final String modelname,Vector<Vector<String>> courselist){
		final JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(null);
//		int buttonlenght = 120;
//		int buttonheight = 25;
		int comboboxlenghth = 100;
		int comboboxheight = 25;
		JButton showcoursebutton = new JButton("");
		Tool.setIcon(Tool.refreshbutton, showcoursebutton);
//		panel.add(showcoursebutton);
		showcoursebutton.setBounds(125, 10, comboboxlenghth, comboboxheight);
//		final JTable table = new CourseTable().getSelectCourseTable(modelname);
		
		final JTable courselisttable = new CourseListTable().getSelectCourseByModule(modelname);//from LL
//		final DefaultTableModel dtm = (DefaultTableModel)table.getModel();
		final JScrollPane selectcoursetablepanel = new JScrollPane(courselisttable);
		//修改
		Tool.setOpaque(selectcoursetablepanel);
		panel.add(selectcoursetablepanel);
		final Rectangle selectcoursetablepanesize = new Rectangle(10, 40, 780, 400);
		selectcoursetablepanel.setBounds(selectcoursetablepanesize);
		selectcoursetablepanel.setOpaque(false);
		JLabel lable1 = new JLabel(
				"点击“添加课程”按钮，将要选择的课程添加到右侧列表并点击 “提交选择”（若不提交选择将不能选课）");
		panel.add(lable1);
		Rectangle r = new Rectangle(200, 10, 600, comboboxheight);
		lable1.setBounds(r);

		final Vector<String> head = new Vector<String>();
//		Vector<Vector<String>> content = new Vector<Vector<String>>();
		head.add("课程编号");
		head.add("课程名");

		final DefaultTableModel mod = new DefaultTableModel(courselist, head);
		this.tablemodel = mod;
		final JTable tempselect = new JTable(mod);
		this.tempselect = tempselect;
		final JScrollPane jsp = new JScrollPane(tempselect);
		tempselect.setOpaque(false);
		jsp.setOpaque(false);
		Tool.setOpaque(jsp);
		panel.add(jsp);
		jsp.setBounds(10, 450, 150, 110);
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
		commitselect.setBounds(170, 450, 100, 40);
		final JButton addtotempselect = new JButton("");
		this.addtotemp = addtotempselect;  
		
		Tool.setIcon(Tool.add, addtotempselect);
//		panel.add(addtotempselect);
		addtotempselect.setBounds(10, 10, 40, 40);
		addtotempselect.setIcon(new ImageIcon(Tool.add));
		addtotempselect.addActionListener(new ActionListener() {
			/*
			 * (non-Javadoc)感觉这个是都一样的，无需定制。
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				int rownumber = courselisttable.getSelectedRow();
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
					String message = "请勿重复添加课程或添加多于四个课程";
					JOptionPane.showMessageDialog(null, message);
				} else {
					mod.addRow(rowData);
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
		cancelselect.setBounds(170, 500, 100, 40);

		showcoursebutton.addActionListener(new ActionListener() {
			/*
			 * (non-Javadoc)无需定制,通用的方法。
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				final JTable table = new CourseListTable().getSelectCourseByModule(modelname);
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
		setter.addBackground(this, Tool.FrameImagePath);
		JPanel componentpanel = new JPanel();
		componentpanel.setLayout(new FlowLayout(0,10,0));
		
		componentpanel.add(addtotempselect);
		componentpanel.add(showcoursebutton);
		c.add(componentpanel);
		componentpanel.setBounds(0, 0, 100, 40);
		Tool.setOpaque(componentpanel);
		this.setVisible(true);
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				commitbuttonclicked();
			}
		});
		
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
	
	
	public abstract void commitbuttonclicked();
	public abstract void deletefromtempbuttonclicked();
	
	
	
	
	
}
