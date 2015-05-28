package presentation.frameui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import presentation.tools.OutputHelper;
import presentation.tools.ViewReplyMessage;

import vo.framevo.CreditRestrictVO;

import businesslogic.framebl.Frame;
import businesslogic.utilitybl.CourseModule;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class CreditRestrictPane extends JPanel implements ViewReplyMessage{
	JComboBox<CourseModule> moduleBox;
	JTextField lowTf;
	JTextField highTf;
	JTable creditRestrictTable;
	
	OutputHelper helper=new OutputHelper();
	public CreditRestrictPane(){
		this.setLayout(new MigLayout());
		this.setOpaque(false);
		
		JButton editButton=new JButton("编辑");
		editButton.setBorderPainted(false);
		editButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				lowTf.setEditable(true);
				highTf.setEditable(true);
			}		
		});
		
		JButton saveButton=new JButton("保存");
		saveButton.setBorderPainted(false);
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (moduleBox.getSelectedItem() != null) {
					if ((isNum(highTf.getText()) && isNum(lowTf.getText()))) {
						if (Integer.valueOf(highTf.getText()) > 150) {
							helper.outputToDialog(HIGHCREDIT_ERROR);
						} else if (Integer.valueOf(lowTf.getText()) < 0) {
							helper.outputToDialog(LOWCREDIT_ERROR);
						} else if (Integer.valueOf(lowTf.getText()) > Integer
								.valueOf(highTf.getText())) {
							helper.outputToDialog(RANGE_ERROR);
						} else {
							CreditRestrictVO cv = new CreditRestrictVO(
									moduleBox.getSelectedItem().toString(),
									Integer.valueOf(lowTf.getText()), Integer
											.valueOf(highTf.getText()));
							new Frame().modify(cv);
							lowTf.setEditable(false);
							highTf.setEditable(false);
							setCreditRestrictTable();
						}
					} else {
						helper.outputToDialog(NUM_ERROR);
					}
				} else {
					helper.outputToDialog(NOT_COMPLETED);
				}
			}
		});
		JPanel p2=new JPanel(new MigLayout());
		p2.setOpaque(false);
		p2.add(editButton,"gapleft 10");
		p2.add(saveButton,"gapleft 170");
		
		JPanel p1=new JPanel(new MigLayout());
		
		p1.setOpaque(false);
		JLabel moduleLabel = new JLabel("课程模块");
		moduleBox=new JComboBox<CourseModule>();
		CourseModule[] modules=CourseModule.values();
		moduleBox = new JComboBox<CourseModule>(modules);	
		moduleBox.setSelectedIndex(-1);
		JLabel lowLabel = new JLabel("下限");
		lowTf=new JTextField(3);	
		lowTf.setEditable(false);
		JLabel highLabel = new JLabel("上限");
		highTf=new JTextField(3);
		highTf.setEditable(false);
		
		p1.add(moduleLabel,"gapleft 25");
		p1.add(moduleBox,"gapleft 5");
		p1.add(lowLabel,"gapleft 10");
		p1.add(lowTf,"gapleft 5");
		p1.add(highLabel,"gapleft 10");
		p1.add(highTf,"gapleft 5");
		
		creditRestrictTable = new JTable() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};		
		setCreditRestrictTable();
		creditRestrictTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					if (e.getClickCount() == 1) {
						int row =((JTable)e.getSource()).rowAtPoint(e.getPoint()); 
						String module=(String) creditRestrictTable.getValueAt(row,0);
						String low=(String) creditRestrictTable.getValueAt(row,1);
						String high=(String) creditRestrictTable.getValueAt(row,2);
						moduleBox.setSelectedItem(CourseModule.valueOf(module));
						lowTf.setEditable(true);
						lowTf.setText(low);
						highTf.setEditable(true);
						highTf.setText(high);
					}
				}
			}
		});
		JScrollPane jsp=new JScrollPane(creditRestrictTable);
		jsp.setPreferredSize(new Dimension(350,120));
		
		this.add(p2,"gapleft 10,wrap,gaptop 20");
		this.add(p1,"wrap");
		this.add(jsp,"gapleft 30,gaptop 15");
	}
	
	private void setCreditRestrictTable(){		
		ArrayList<CreditRestrictVO> cvList=new Frame().showCreditRestricts();
		
		String[] head  ={"课程模块","下限","上限"};
		Vector<Vector<String>> content = null ;
		content = new Vector<Vector<String>>();
		for(CreditRestrictVO cv:cvList){
			Vector<String> line = new Vector<String>();
			line.add(cv.getModule());
			line.add(cv.getLow()+"");
			line.add(cv.getHigh()+"");
			content.add(line);
		}
		new OutputHelper().outputToTable(content,head,creditRestrictTable);
		this.repaint();
	}
	
	/**
	 * 得到学分限制的textarea,以便在院系教务老师发布课程时作为参考
	 * @return JTextArea
	 */
	public JTextArea getCreditRestrictsInfo(){
		JTextArea ta=new JTextArea();
		StringBuilder sb=new StringBuilder();
		ArrayList<CreditRestrictVO> cvList=new Frame().showCreditRestricts();
		for(CreditRestrictVO cv:cvList){
			sb.append(cv.toString()+"\r\n");
		}
		ta.setText(sb.toString());
		return ta;
	}
	
	private boolean isNum(String str){
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+)))$");
	}
}
