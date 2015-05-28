package presentation.studentui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import po.studentpo.MajorTransferPO;
import vo.courseselectionvo.CourseSelectionVO;
import businesslogic.studentbl.CourseCondition;
import businesslogic.studentbl.CourseInfo;
import businesslogic.studentbl.MajorApply;
import businesslogic.studentbl.StudentInfo;
import businesslogicservice.studentblservice.MajorApplyBLService;

public class MajorTransferPanel {
	
	public JPanel getPanel(final CourseSelectionVO sv) {
		// TODO 自动生成的方法存根
		
		final int length =100;
		final int height  =25;
		
		final JPanel mainpanel = new JPanel();
		final Vector<String> head=new Vector<String>();
		head.add("原专业");
		head.add("申请专业");
		head.add("申请状态");
		head.add("申请时间");
		
		Vector<Vector<String>> content =new Vector<Vector<String>>();
//		Vector<Vector<String>> content =
		final DefaultTableModel dtm =  new DefaultTableModel(content,head);
		final JTable table=new JTable(dtm);
		final JScrollPane jsp=new JScrollPane(table);
		Tool.setOpaque(jsp);
//		mainpanel.setLayout(new BorderLayout());
//		mainpanel.add(BorderLayout.CENTER,jsp);
		table.setCellSelectionEnabled(false);
		table.setEnabled(true);
		final JButton showbutton=new JButton("显示审核状态");
//		mainpanel.add(BorderLayout.NORTH,showbutton);
		final JPanel apply = getApplyMajorPanel(sv.getStudentID());
		Tool.setBorder(apply, "Major Transfer");
		showbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jsp.setVisible(true);
				int size = dtm.getRowCount();
				for(int i =0 ;i < size; i++){
					dtm.removeRow(0); //清空列表。确保每一次都不是前一次显示的重复。
				}
//				Student s=new Student(sv);
				String student_id = sv.getStudentID();
				MajorApplyBLService ma = new MajorApply();
				Vector<String> line = ma.getMajorApplyStatus(student_id);
				dtm.addRow(line);
				mainpanel.add(jsp);
				jsp.setBounds(10,40 , 400, 40);
				apply.setVisible(false);
			}
		});
		mainpanel.setLayout(null);
		mainpanel.add(showbutton);
		JButton applymajor  = new JButton("转专业申请");
		mainpanel.add(applymajor);
		
		showbutton.setBounds(10, 10, length, height);
		applymajor.setBounds(15+length,10,length,height);
		
		applymajor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				jsp.setVisible(false);
//				table.setVisible(false);
//				showbutton.doClick();
				boolean hasapplied = false;
				hasapplied = new MajorApply().hasApplied(sv.getStudentID());
				String student_id = sv.getStudentID();
				String faculty_id = new StudentInfo().getFacultyID(student_id);
				boolean canout = new CourseCondition().isCurrentCreditsEnough(student_id,faculty_id ).isPermission();			
				
				
				
				if(!hasapplied&&canout){
					apply.setVisible(true);
					mainpanel.add(apply);
					apply.setBounds(320, 40, 300, 200);
				}else if(hasapplied){
					apply.setVisible(false);
					JOptionPane.showMessageDialog(null, "你已经生成了一个转专业申请，请勿重复申请或申请多个专业");
				}else if(!canout){
					apply.setVisible(false);
					JOptionPane.showMessageDialog(null, "你本专业的必修课程未达限定数,不能准出");
				}
				
			}
		});
		
		
		
		Tool.setOpaque(mainpanel);
		return mainpanel;
	}
	
	private JPanel  getApplyMajorPanel(final String student_id){
		final JPanel mainpanel = new JPanel();
		JButton confirmapply = new JButton("确认申请");
		mainpanel.setLayout(null);
		String studentfacname = new CourseInfo().getFacultyName(new StudentInfo().getFacultyID(student_id));
		final JComboBox<String>  major = new  MajorComboBox();
		major.removeItem(studentfacname);
//		major.addItem("商学院");
//		major.addItem("材料学院");
//		major.addItem("法学院");
//		major.addItem("电子学院");
		mainpanel.add(major);
		major.setBounds(10, 40, 100, 25);
		mainpanel.add(confirmapply);
		confirmapply.setBounds(120, 40, 100, 25);
		confirmapply.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				String applydate = new Tool().getTime();
				String toschool = (String) major.getSelectedItem();
				String preschool = new StudentInfo(student_id).getStudentInfo().getFacultyID();
				preschool = new CourseInfo().getFacultyName(preschool);
				//如何根据id获取是哪个院系
				String status  = "尚未处理";
				MajorApplyBLService ma= new MajorApply();
				MajorTransferPO po = new MajorTransferPO(student_id, preschool, toschool, status, applydate);
				ma.apply(po);
				JOptionPane.showMessageDialog(null, "申请成功，请等待审核结果");
				mainpanel.setVisible(false);
			}
		});
		Tool.setOpaque(mainpanel);
		return mainpanel;
	}
	
	
//	JButton permitintocourse = new JButton("准入课程统计");
	
	

}
