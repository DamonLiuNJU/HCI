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

public class ElectiveCourseFrame extends CommonCourseSelectionFrame{

	/**
	 * 
	 */
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = -5338131222748149557L;

	final JTable tempselect ;
	final DefaultTableModel tablemodel ;
	final String student_id ;
	/**
	 * @param args
	 */
//	JFrame frame ;
	public ElectiveCourseFrame(Student s){
		super();
		
		student_id= s.getStudentID();
		final String modelname = "选修课";
		Vector<Vector<String>> mycourselist =new SelectCourseInfo().getSelectedCourse(student_id, modelname); //store the lessons he has chosen;
		this.showFrame(modelname,mycourselist);
//		super.fillFrameWithModelNameAndCourselist(modelname, mycourselist);
		 
		
		//那个暂时存放的表还是需要一些复杂逻辑，先要根据学生的ID去获取他所有的课程号，然后根据课程号判断是否和
		//modelname符合，然后还要取得课程的名字。完了还要显示在一个小型的暂时存放的表里，最后根据最终的情况判断删除了哪些
		//点了提交修改之后就直接出发事件检测删除了哪些课程，然后在数据库中操作。
		tempselect = this.getTempselect();
		tablemodel = this.getTablemodel();
	}

	@Override
	public void commitbuttonclicked() {
		// TODO Auto-generated method stub
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

	@Override
	public void deletefromtempbuttonclicked() {
		// TODO Auto-generated method stub
		int row = tempselect.getSelectedRow();
		if(row>=0){
			String course_id = (String) tempselect.getValueAt(row, 0);
			tablemodel.removeRow(row);
			CourseSelection si = new CourseSelection();
			si.removeCourse( (student_id), course_id);
			JOptionPane.showMessageDialog(null, "删除成功");
		}
	}
	
	
	
	
	

}
