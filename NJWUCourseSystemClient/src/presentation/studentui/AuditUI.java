package presentation.studentui;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import po.studentpo.MajorTransferPO;
import po.studentpo.StudentPO;
import vo.studentvo.CourseConditionPanelVO;
import businesslogic.studentbl.CourseCondition;
import businesslogic.studentbl.CourseInfo;
import businesslogic.studentbl.FacultyStudent;
import businesslogic.studentbl.MajorApply;
import businesslogic.studentbl.StudentInfo;

public class AuditUI {

	/**
	 * @param args
	 */
	
	/*
	 * JTable getAuditTable(String type,facultyName,String grade)
		"准入"： "学号", "姓名", "所属院系", "申请院系" ,"审核结果"
		其它： "学号", "姓名", "所属院系","审核结果"
		你内容是Vector<Vector<String>>格式的都可以用outputToTable(Vector<Vector<String>> content, String[] columnTitle, JTable table)输出
	 */
	public JTable setAuditTable(DefaultTableModel dtm , String infotype, String facultyName , String gradelimit){
//		TableModel model = inputtable.getModel();
		Vector<String> head  =null;
		Vector<Vector<String>> contentv = null ;
		JTable table  = null;
		ArrayList<StudentPO > studentlist = null;
		switch(infotype){
		case "准入" :
			contentv = new Vector<Vector<String>>();
			head = new Vector<String>();
			head.add("学号");
			head.add("姓名");
			head.add("所属院系");
			head.add("申请院系");
			head.add("审核结果");
			StudentInfo si = new StudentInfo();
			ArrayList<MajorTransferPO> content = new MajorApply().getAllContent();
			for(MajorTransferPO line : content){
				String student_id = line.getStudent_ID();
				String studentname = si.getStudentNameByID(student_id);
				String studentfacid =  si.getFacultyID(student_id);
				String toschool = line.getToSchool();
				String state = line.getStatus();
				Vector<String> linevec = new Vector<String>();
				linevec.add(student_id);
				linevec.add(studentname);
				linevec.add(studentfacid);
				linevec.add(toschool);
				linevec.add(state);
				contentv.add(linevec);
				dtm.addRow(linevec);
			}
			
			 table = new JTable(contentv,head);
			return table;
		case "准出" :
			contentv = new Vector<Vector<String>>();
			head = new Vector<String>();
			head.add("学号");
			head.add("姓名");
			head.add("所属院系");
			head.add("审核结果");
			studentlist =  new FacultyStudent().getAllStudentsByFacName(facultyName);
			for(StudentPO student : studentlist){
				String student_id = student.getStudentID();
				String studentname = student.getName();
				String studentgrade= student.getGrade();
				if(studentgrade.compareToIgnoreCase(gradelimit)==0){
					
				}else{
					continue;
				}
				String fac = student.getFacultyID();
				CourseConditionPanelVO result = new CourseCondition().getCourseCondition(student_id, fac);
				String permission = "不准出";
				fac = new CourseInfo().getFacultyName(fac);
				if(result.isPermission()){
					permission = "准出";
				}
				Vector<String> line =  new Vector<String>();
				line.add(student_id);
				line.add(studentname);
				line.add(fac);
				line.add(permission);
				contentv.add(line);
				dtm.addRow(line);
			}
			table = new JTable(contentv,head);
			return table;
		case "毕业" :
			contentv = new Vector<Vector<String>>();
			head = new Vector<String>();
			head.add("学号");
			head.add("姓名");
			head.add("所属院系");
			head.add("审核结果");
			studentlist =  new FacultyStudent().getAllStudentsByFacName(facultyName);
			for(StudentPO student : studentlist){
				String student_id = student.getStudentID();
				String studentname = student.getName();
				String studentgrade = student.getGrade();
				if(studentgrade.compareToIgnoreCase(gradelimit)==0){
					
				}else{
					continue;
				}
				String fac = student.getFacultyID(); 
				String permission = "不准毕业";
				fac = new CourseInfo().getFacultyName(fac);
				if(new CourseCondition().canGraduate(student_id)){
					permission = "准许毕业";
				}
				Vector<String> line =  new Vector<String>();
				line.add(student_id);
				line.add(studentname);
				line.add(fac);
				line.add(permission);
				contentv.add(line);
				dtm.addRow(line);
			}
			table = new JTable(contentv,head);
			return table;
		}
		return null;
	}
	
}
