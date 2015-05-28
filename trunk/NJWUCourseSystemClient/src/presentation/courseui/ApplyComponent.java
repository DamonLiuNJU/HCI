package presentation.courseui;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import vo.coursevo.ApplyVO;
import businesslogic.coursebl.CourseApply;
import businesslogicservice.courseblservice.CourseApplyService;

public class ApplyComponent {
	public JTable getApplyList(){
		CourseApplyService ca=new CourseApply();
		ArrayList<ApplyVO> avList=ca.getApplyList();
		String [] title ={"课程名称","申请教师","所属院系"};
		@SuppressWarnings("serial")
		JTable table0=new JTable(){
			   @Override
			public boolean isCellEditable(int row, int column) { 
			    return false;
			   }
		};
		DefaultTableModel tableModel = (DefaultTableModel) table0.getModel();		
		tableModel.setColumnIdentifiers(title);
		for(int i=0;i<avList.size();i++){
			ApplyVO av=avList.get(i);
			String[] row={av.getCourseName(),av.getTeacherName(),av.getFacultyName()};
			tableModel.addRow(row);
		}

		/*for(int i=0;i<avList.size();i++){
			ApplyVO av=avList.get(i);
			content[i][0]=av.getCourseName();
			content[i][1]=av.getTeacherName();
			content[i][2]=av.getFacultyName();
		}
		JTable table=new JTable(content,title);
		return	table;*/
		return table0;
	} 
	
	public JTextArea getApplyInfo(String courseName){		
		CourseApplyService ca=new CourseApply();
		String content=ca.getContent(courseName);
		JTextArea ta=new JTextArea(content,15,30);
		ta.setLineWrap(true);
		ta.setWrapStyleWord(true);
		return ta;
	}

}
