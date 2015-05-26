package presentation.deanui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;
import presentation.planui.PlanList;
import presentation.studentui.AuditUI;
import presentation.tools.OutputHelper;
import businesslogic.utilitybl.AuditType;

@SuppressWarnings("serial")
public class AuditPane extends JPanel{
	JComboBox<String> typeBox;
	JComboBox<String> facultyBox;
	JComboBox<String> gradeBox;
	
	JTable auditTable=new JTable(new DefaultTableModel());
	
	OutputHelper helper=new OutputHelper();
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AuditPane(){
		this.setLayout(new MigLayout());
		this.setOpaque(false);
		
		//面板首行选项栏
		JPanel pa1=new JPanel(new MigLayout());
		pa1.setOpaque(false);
		
		JLabel l1=new JLabel("资格类型");				
		AuditType[] typeList=AuditType.values();
		typeBox=new JComboBox(typeList);
	
		JLabel l2=new JLabel("院系列表");		
		facultyBox=new PlanList().getFacultyComboBox();
		
		JLabel l3=new JLabel("年级");
		String[] gList={"大一","大二","大三","大四"};
		gradeBox=new JComboBox(gList);
	
		JButton b=new JButton("查看");
		b.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String type=typeBox.getSelectedItem().toString();
				String facultyName=facultyBox.getSelectedItem().toString();
				String grade=gradeBox.getSelectedItem().toString();
				
				DefaultTableModel tableModel = (DefaultTableModel) auditTable.getModel();
                tableModel.setRowCount(0);
                tableModel.setColumnCount(0); // 清空原有内容
				showAuditTable(type,facultyName,grade);
			}		
		});
		
		pa1.add(l1,"gapleft 10");
		pa1.add(typeBox);
		pa1.add(l2,"gapleft 10");
		pa1.add(facultyBox);
		pa1.add(l3,"gapleft 10");
		pa1.add(gradeBox);
		pa1.add(b,"gapleft 50");
		
		JScrollPane jsp=new JScrollPane(auditTable);
		jsp.setOpaque(false);
		jsp.getViewport().setOpaque(false); 
		jsp.setPreferredSize(new Dimension(530,320));
		
		this.add(pa1,"wrap");
		this.add(new JSeparator(),"growx,wrap");
		this.add(jsp,"gaptop 15");
	}

	public void showAuditTable(String type,String facultyName,String grade) {
		new AuditUI().setAuditTable((DefaultTableModel)auditTable.getModel(),type, facultyName, grade);
	}
	
}
