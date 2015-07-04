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
import presentation.tools.UIConstants;
import vo.courseselectionvo.CourseSelectionVO;
import businesslogic.courseselectionbl.CourseSelection;
import businesslogic.courseselectionbl.SelectCourseInfo;
import businesslogic.studentbl.Student;

public class ElectiveCourseFrame extends CourseSelectionFrame{

	/**
	 * 
	 */
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = -5338131222748149557L;

	/**
	 * @param args
	 */
	public ElectiveCourseFrame(Student s){
		super(s);
		final String modelname = UIConstants.ElectiveCourseModule;
		Vector<Vector<String>> mycourselist =new SelectCourseInfo().getSelectedCourse(s.getStudentID(), modelname); //store the lessons he has chosen;
		this.showFrame(modelname,mycourselist);
	}

	
	
	
	
	
	

}
