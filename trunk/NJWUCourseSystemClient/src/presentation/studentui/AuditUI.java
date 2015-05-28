package presentation.studentui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import po.studentpo.MajorTransferPO;
import po.studentpo.StudentPO;
import presentation.tools.OutputHelper;
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
	
	
	//
	/*
	 * JTable getAuditTable(String type,facultyName,String grade)
		"准入"： "学号", "姓名", "所属院系", "申请院系" ,"审核结果"
		其它： "学号", "姓名", "所属院系","审核结果"
		你内容是Vector<Vector<String>>格式的都可以用outputToTable(Vector<Vector<String>> content, String[] columnTitle, JTable table)输出
	 */
//	public JTable setAuditTable(DefaultTableModel dtm , String infotype, String facultyName , String gradelimit){
////		TableModel model = inputtable.getModel();
//		Vector<String> head  =null;
//		Vector<Vector<String>> contentv = null ;
//		JTable table  = null;
//		ArrayList<StudentPO > studentlist = null;
//		switch(infotype){
//		case "准入" :
//			contentv = new Vector<Vector<String>>();
//			head = new Vector<String>();
//			head.add("学号");
//			head.add("姓名");
//			head.add("所属院系");
//			head.add("申请院系");
//			head.add("审核结果");
//			StudentInfo si = new StudentInfo();
//			ArrayList<MajorTransferPO> content = new MajorApply().getAllContent();
//			if(content == null){
//				return table;
//			}
//			for(MajorTransferPO line : content){
//				String student_id = line.getStudent_ID();
//				String studentname = si.getStudentNameByID(student_id);
//				String studentfacid =  si.getFacultyID(student_id);
//				String toschool = line.getToSchool();
//				String state = line.getStatus();
//				Vector<String> linevec = new Vector<String>();
//				linevec.add(student_id);
//				linevec.add(studentname);
//				linevec.add(studentfacid);
//				linevec.add(toschool);
//				linevec.add(state);
//				contentv.add(linevec);
//				dtm.addRow(linevec);
//			}
//			
//			 table = new JTable(contentv,head);
//			return table;
//		case "准出" :
//			contentv = new Vector<Vector<String>>();
//			head = new Vector<String>();
//			head.add("学号");
//			head.add("姓名");
//			head.add("所属院系");
//			head.add("审核结果");
//			studentlist =  new FacultyStudent().getAllStudentByFacName(facultyName);
//			for(StudentPO student : studentlist){
//				String student_id = student.getStudentID();
//				String studentname = student.getName();
//				String studentgrade= student.getGrade();
//				if(studentgrade.compareToIgnoreCase(gradelimit)==0){
//					
//				}else{
//					continue;
//				}
//				String fac = student.getFacultyID();
//				CourseConditionPanelVO result = new CourseCondition().getCourseCondition(student_id, fac);
//				String permission = "不准出";
//				fac = new CourseInfo().getFacultyName(fac);
//				if(result.isPermission()){
//					permission = "准出";
//				}
//				Vector<String> line =  new Vector<String>();
//				line.add(student_id);
//				line.add(studentname);
//				line.add(fac);
//				line.add(permission);
//				contentv.add(line);
//				dtm.addRow(line);
//			}
//			table = new JTable(contentv,head);
//			return table;
//		case "毕业" :
//			contentv = new Vector<Vector<String>>();
//			head = new Vector<String>();
//			head.add("学号");
//			head.add("姓名");
//			head.add("所属院系");
//			head.add("审核结果");
//			studentlist =  new FacultyStudent().getAllStudentByFacName(facultyName);
//			for(StudentPO student : studentlist){
//				String student_id = student.getStudentID();
//				String studentname = student.getName();
//				String studentgrade = student.getGrade();
//				if(studentgrade.compareToIgnoreCase(gradelimit)==0){
//					
//				}else{
//					continue;
//				}
//				String fac = student.getFacultyID(); 
//				String permission = "不准毕业";
//				fac = new CourseInfo().getFacultyName(fac);
//				if(new CourseCondition().canGraduate(student_id)){
//					permission = "准许毕业";
//				}
//				Vector<String> line =  new Vector<String>();
//				line.add(student_id);
//				line.add(studentname);
//				line.add(fac);
//				line.add(permission);
//				contentv.add(line);
//				dtm.addRow(line);
//			}
//			table = new JTable(contentv,head);
//			
//			
//			return table;
//		}
//		return null;
//	}
	/**
	 * 
	 * @param facutyname = 申请准入的院系名
	 * @param stugradelimit = 年纪限制
	 * @return
	 */
	public JTable getAllowInTable(JTable inputtable , String facutyname,String stugradelimit){
	  //既然可以申请
		ArrayList<MajorTransferPO> tablecontont =  new MajorApply().getAllContent();
		Vector<Vector<String>> contentv = null;
		Vector<String> head = null;
		contentv = new Vector<Vector<String>>();
		head = new Vector<String>();
		head.add("学号");
		head.add("姓名");
		head.add("所属院系");
		head.add("申请院系");
		head.add("结果");
		DefaultTableModel dtm = new DefaultTableModel(contentv,head);
//		TableModel dtm = inputtable.getModel();
		JTable table = new JTable(dtm);
		for(MajorTransferPO po : tablecontont  ){
			if(po.getToSchool().compareToIgnoreCase(facutyname)!=0){
				continue;
			}
			String toschool = po.getToSchool();
			String student_id = po.getStudent_ID();
			String studentname = new StudentInfo().getStudentNameByID(student_id);
			String prechool = po.getPreSchool();
			String status = po .getStatus();
			Vector<String> line = new Vector<String>();
			line.add(student_id);
			line.add(studentname);
			line.add(prechool);
			line.add(toschool);
			line.add(status);
			(dtm).addRow(line);
		}
		 
		OutputHelper oh = new OutputHelper();
		
		String columnTitle[] = {"学号","姓名","所属院系","申请院系","结果"};
		oh.outputToTable(contentv, columnTitle, inputtable);
		
		return table;
		
//		return null;
	}
	public JTable getAllowOutTable(JTable inputtable,String facutyname,String stugrade){
		CourseCondition cc= new CourseCondition();
		Vector<Vector<String>> contentv = null;
		Vector<String> head = null;
		ArrayList<StudentPO > studentlist = null;
		contentv = new Vector<Vector<String>>();
		head = new Vector<String>();
		head.add("学号");
		head.add("姓名");
		head.add("所属院系");
		head.add("结果");
		DefaultTableModel dtm = new DefaultTableModel(contentv,head);
		JTable table = new JTable(dtm);
		studentlist =  new FacultyStudent().getAllStudentByFacName(facutyname);
		for(StudentPO student : studentlist){
			String student_id = student.getStudentID();
			String studentname = new StudentInfo().getStudentNameByID(student_id);
			String studentgrade= new StudentInfo(student_id).getStuCurrentGrade();
			studentgrade =  new StudentInfo(student_id).getStuGradeForSelection();
			if((studentgrade.charAt(1)==stugrade.charAt(1))){
				
			}else{
				continue;
			}
			String fac = new StudentInfo(student_id).getFacultyID(student_id);
			CourseConditionPanelVO result = cc.isCurrentCreditsEnough(student_id, fac);
			String permission = "不能准出";
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
//			dtm.addRow(line);
		}
		
		OutputHelper oh = new OutputHelper();
		String columnTitle[] = {"学号","姓名","所属院系","结果"};
		oh.outputToTable(contentv, columnTitle, inputtable);
		return table;
	}
	/**
	 * 
	 * @param facutyname 要查看的院系名
	 * @param stugradelimit 要查看的年纪
	 * @return GUI组件
	 */
	public JTable getGraduateTable(JTable inputtable,String facutyname,String stugradelimit){
		Vector<Vector<String>> contentv = null;
		Vector<String> head = null;
		ArrayList<StudentPO > studentlist = null;
		contentv = new Vector<Vector<String>>();
		head = new Vector<String>();
		head.add("学号");
		head.add("姓名");
		head.add("所属院系");
		head.add("结果");
		DefaultTableModel dtm = new DefaultTableModel(contentv,head);
		JTable table = new JTable(dtm);
		studentlist =  new FacultyStudent().getAllStudentByFacName(facutyname);
		for(StudentPO student : studentlist){
			String student_id = student.getStudentID();
			StudentInfo si = new StudentInfo(student_id);
			String studentname = si.getStudentNameByID(student_id);
			String studentgrade= si.getStuGradeForSelection();
			if(studentgrade.charAt(1)==stugradelimit.charAt(1)){
				
			}else{
				continue;
			}
			String fac = si.getFacultyID(student_id);
			
			boolean result = new CourseCondition().canGraduate(student_id);
			String permission = "不能毕业";
			fac = new CourseInfo().getFacultyName(fac);
			if(result){
				permission = "可以毕业";
			}
			Vector<String> line =  new Vector<String>();
			line.add(student_id);
			line.add(studentname);
			line.add(fac);
			line.add(permission);
			contentv.add(line);
//			dtm.addRow(line);
		}
		OutputHelper oh = new OutputHelper();
		String columnTitle[] = {"学号","姓名","所属院系","结果"};
		oh.outputToTable(contentv, columnTitle, inputtable);
		return table;
		
	}
	
	public static void main(String args[]){
		JFrame test = new JFrame();
		test.setSize(500, 500);
//		DefaultTableModel dtm = new DefaultTableModel();
		Container c= test.getContentPane();
//		JTable table = new JTable(dtm);
		c.setLayout(new BorderLayout());
//		JTable table = new AuditUI().getAllowOutTable("软件学院", "2010");
//		c.add(new JScrollPane(table),BorderLayout.CENTER);
		test.setVisible(true);
	}
	
}
