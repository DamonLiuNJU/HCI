package presentation.studentui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import vo.studentvo.CourseConditionPanelVO;
import businesslogic.studentbl.CourseCondition;
import businesslogic.studentbl.StudentInfo;
import businesslogicservice.studentblservice.StudentInfoBLService;

public class CourseConditionPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
//	private static final long serialVersionUID = -4373358602256931952L;

	/**
	 * @param args
	 */
	Vector<Vector<String>> content = new Vector<Vector<String>>();
	Vector<String> head = new Vector<String>();
	String student_id ; 
	DefaultTableModel dtm = new DefaultTableModel(content,head);
	JComboBox<String> major = new MajorComboBox();
	JLabel outlabel = new JLabel("状态 : ");
	JLabel inlabel = new JLabel("状态 : ");
	public CourseConditionPanel(String student_id){
		//准出为专业选修课的准出情况
		//准入则需要选择院系查看.
		this.student_id = student_id;
		head.add("课程号");
		head.add("课程名");
		head.add("课程类型");
		head.add("学分");
		head.add("成绩");
		Tool.setOpaque(this);
		JButton out = this.outButton();
		
		this.add(major);
		major.setBounds(120, 40, 100, 25);
		JButton in =  this.inButton();
		this.setLayout(null);
		this.add(out);
		this.add(in);
	
		this.add(outlabel);
		outlabel.setBounds(120, 10, 200, 25);
		
		this.add(inlabel);
		inlabel.setBounds(225, 40, 200, 25);
		JTable table = new JTable(dtm);
		JScrollPane jsp = new JScrollPane(table);
		this.add(jsp);
		jsp.setBounds(10, 70, 500, 400);
		Tool.setOpaque(jsp);
		
	}
	
	public JButton inButton(){
		JButton out = new JButton("准入课程统计");
		out.setBounds(10, 40, 100, 25);
		out.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				int size = dtm.getRowCount();
				for(;size>0;size--){
					dtm.removeRow(0);
				}
			 
				CourseCondition cc = new CourseCondition();
				String selectedfacultyname  = (String)major.getSelectedItem(); 
				String facultyID =  new CourseCondition().getFacultyID(selectedfacultyname);
				CourseConditionPanelVO vo = cc.getCourseCondition(student_id, facultyID);
				Vector<Vector<String>> content = vo.getTableContent();
				
			
				for(Vector<String> line : content){
					dtm.addRow(line);
				}
				
				 vo = cc.isCurrentCreditsEnough(student_id	, facultyID);
				boolean result = vo.isPermission();
				if(result){
					inlabel.setText("状态 : 准入");
				}else{
					inlabel.setText("状态 : 学分未达要求数量,不准入.");
				}
				
			}
		});
		return out;
	}
	
	public JButton outButton(){
		JButton out = new JButton("准出课程统计");
		out.setBounds(10, 10, 100, 25);
		out.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
			 
				CourseCondition cc= new CourseCondition();
				StudentInfoBLService si = new StudentInfo(student_id);
				String studentfacultyID = si.getFacultyID(student_id);
				CourseConditionPanelVO vo = cc.getCourseCondition(student_id,studentfacultyID);
				Vector<Vector<String>> content = vo.getTableContent();
				int size = dtm.getRowCount();
				for(;size>0;size--){
					dtm.removeRow(0);
				}
				 
				for(Vector<String> line : content){
					dtm.addRow(line);
				}
				
				 vo = cc.isCurrentCreditsEnough(student_id	, studentfacultyID);
					boolean result = vo.isPermission();
					if(result){
						outlabel.setText("状态 : 准出");
					}else{
						outlabel.setText("状态 : 学分未达要求数量,不准出.");
					}
			}
		});
		return out;
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

}
