package presentation.studentui;

import java.awt.Container;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import vo.studentvo.CourseInfoVO;
import businesslogic.studentbl.CourseInfo;
//import businesslogic.planbl.Plan;
import businesslogicservice.studentblservice.CourseInfoBLService;

public class StudentListTable {

	/**
	 * @param args
	 */
	
	public JTable getStudentListTable(String course_id){
		//放到BL  用VO
		
//		Vector<Vector<String> > content = new Vector<Vector<String>>();
//		ArrayList<SelectCourseRecordPO> list =  new CourseInfo().getStudentList(course_id);
//		for(SelectCourseRecordPO po : list){
//			Vector<String> line = new Vector<String>();
//			String student_id = po.getStudent_ID();
//			line.add(student_id);
//			String studentname = po.getStudent_ID();
//			StudentInfo si = new StudentInfo();
//			studentname = si.getStudentNameByID(student_id);
////			StudentPO po = si.getStudjentInfo();
//			line.add(studentname);
//			String facultyname = si.getFacultyID(student_id);  //暂时放ID
//			facultyname = 
//		}
		CourseInfoBLService ci = new CourseInfo();
		CourseInfoVO inputvo = new CourseInfoVO();
		inputvo.setCourseID(course_id);
		CourseInfoVO result_vo =  ci.getStudentList(inputvo);
		Vector<String> head   = new Vector<String>();
		head.add("学号");
		head.add("学生姓名");
		head.add("院系");
		Vector<Vector<String>> content = result_vo.getContent();
		DefaultTableModel dtm  = new DefaultTableModel(content,head);
		JTable table = new JTable(dtm);
//		Tool.setOpaque(table);
		
		return table;
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		JTable table= new StudentListTable().getStudentListTable("c0002");
		JFrame f = new JFrame();
		Container c= f.getContentPane();
		c.add(table);
		f.setVisible(true);
		
	}

}
