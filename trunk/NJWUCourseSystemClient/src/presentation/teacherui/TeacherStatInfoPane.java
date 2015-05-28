package presentation.teacherui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;
import presentation.deanui.DeanUIImage;
import presentation.planui.PlanList;
import presentation.tools.OutputHelper;
import presentation.tools.Setter;
import presentation.tools.ViewReplyMessage;
import vo.TeacherVO;
import vo.teacherusedvo.TeacherScoreVO;
import businesslogic.teacherbl.Teacher;
import businesslogic.teacherbl.TeacherList;

/**
 * teacherInfoPane that dean requires
 * @author cbb
 *
 */
@SuppressWarnings("serial")
public class TeacherStatInfoPane extends JPanel implements ViewReplyMessage,DeanUIImage{
	JPanel panel;//contain general info and detail info
	JPanel generalInfo;
	JPanel detailInfo;
	JTextArea teacherInfo=new JTextArea(15,45);;
	JPanel moreDetailInfo;
	JRadioButton typeButton; //信息类型选择
	JComboBox<String> facultyBox; //院系选择
	String facultyName;	
	JTable teacherTable;
	JTable avgScoreTable;
	
	OutputHelper helper=new OutputHelper();
	Setter setter=new Setter();
	
	TeacherList teacherList=new TeacherList();
	
	public TeacherStatInfoPane(){
		this.setLayout(new MigLayout());
		this.setOpaque(false);
		
		panel=new JPanel();
		panel.setOpaque(false);
		
		ButtonGroup bgroup=new ButtonGroup();
		JRadioButton jrb1=new JRadioButton("全校");
		jrb1.setOpaque(false);
		jrb1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				showGeneralInfo();
			}			
		});
		JRadioButton jrb2=new JRadioButton("院系");
		jrb2.setOpaque(false);
		jrb2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				showDetailInfo();
			}	
		});
		bgroup.add(jrb1);
		bgroup.add(jrb2);
		JPanel p=new JPanel(new MigLayout());
		p.setOpaque(false);
		p.add(jrb1,"gaptop 5");
		p.add(jrb2,"gapleft 40");
		
		this.add(p,"gapleft 10,wrap");		
		this.add(new JSeparator(),"span,growx");
		this.add(panel,"gapleft 10");
	}
	
	public void showGeneralInfo(){
		panel.removeAll();
		generalInfo=new JPanel(new MigLayout());
		setter.setBorderTitle(generalInfo,"全校教师统计信息");
		generalInfo.setOpaque(false);
		
		JTextArea ta=new JTextArea(18,40);
		ta.setOpaque(false);
		ta.setEditable(false);
		ta.setBorder(null);
		helper.outputToATextArea(teacherList.showGeneralInfo(), ta);
		ta.setLineWrap(true);
		ta.setWrapStyleWord(true);
		generalInfo.add(ta);
		
		panel.add(generalInfo,"gaptop 5");
		repaint();
	}
	
	public void showDetailInfo(){
		panel.removeAll();		
		detailInfo=new JPanel(new MigLayout());
		detailInfo.setOpaque(false);
		
		JLabel label=new JLabel(">>教师统计信息");
		teacherInfo.setEditable(false);
		teacherInfo.setOpaque(false);
		teacherInfo.setBorder(null);
		JScrollPane jsp=new JScrollPane(teacherInfo);
		jsp.setOpaque(false);
		jsp.getViewport().setOpaque(false); 
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setPreferredSize(new Dimension(450,270));
		
		JPanel p0=new JPanel();		
		p0.setOpaque(false);
		JLabel label1=new JLabel("院系列表");
		facultyBox=new PlanList().getFacultyComboBox();
		facultyBox.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				facultyName=facultyBox.getSelectedItem().toString();
				helper.outputToATextArea(teacherList.showDetailedInfo(facultyName), teacherInfo);
			}			
		});
		p0.add(label1);
		p0.add(facultyBox);
			
		JPanel p1=new JPanel(new MigLayout());
		p1.setOpaque(false);
		ImageIcon img = new ImageIcon(listButton);
		JButton b=new JButton("教师列表",img);
		b.setVerticalTextPosition(SwingConstants.BOTTOM);
    	b.setHorizontalTextPosition(SwingConstants.CENTER);
    	b.setContentAreaFilled(false);
    	b.setBorderPainted(false);
		b.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(facultyName==null){
					helper.outputToDialog(NOT_SELECT_FACULTY);
				}else{					
					showMoreDetailTeacherInfo();
				}
			}
			
		});
		p1.add(b,"gaptop 200");
		
		detailInfo.add(p0,"wrap");
		detailInfo.add(label,"wrap,gapleft 10,wrap");
		detailInfo.add(jsp,"gapleft 10");
		detailInfo.add(p1,"gapleft 15");
		panel.add(detailInfo,"gaptop 5");
		repaint();
	}
	
	public void showMoreDetailTeacherInfo(){
		panel.removeAll();
		
		moreDetailInfo=new JPanel(new MigLayout());
		moreDetailInfo.setOpaque(false);

		setTeacherTable();
		teacherTable.setOpaque(false);
		JScrollPane jsp1=new JScrollPane(teacherTable);
		jsp1.setOpaque(false);
		jsp1.getViewport().setOpaque(false); 
		jsp1.setPreferredSize(new Dimension(350,270));		
		
		avgScoreTable=new JTable(){
			   @Override
			public boolean isCellEditable(int row, int column) { 
				    return false;
				   }
		};
		avgScoreTable.setOpaque(false);
		JScrollPane jsp2=new JScrollPane(avgScoreTable);
		jsp2.setOpaque(false);
		jsp2.getViewport().setOpaque(false); 
		jsp2.setPreferredSize(new Dimension(360,270));
		jsp2.setBorder(null);
		
		JLabel label1=new JLabel(facultyName+" 教师统计信息---详细");
		
		JPanel p1=new JPanel(new MigLayout());
		p1.setOpaque(false);
		JLabel label2=new JLabel("教师列表");
		final JTextField tf=new JTextField(9);
		final String hint="请输入教师信息";
		tf.setText(hint);
		tf.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {
				if(tf.getText().equals(hint)){
					tf.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {}			
		});
		
		ImageIcon icon0=new ImageIcon(searchButton);
		JButton b=new JButton(icon0);
		setter.setButtonUnOpaque(b);
		b.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String index=tf.getText();
				boolean isExist=false;
				for(int i=0;i<teacherTable.getRowCount();i++){
					if(teacherTable.getValueAt(i, 0).equals(index)
							||teacherTable.getValueAt(i, 1).equals(index)){
						isExist=true;
						break;
					}
				}
				if(isExist){
					int row;
					//focus on the required row automatically
					for(row=0;row<teacherTable.getRowCount();row++){
						if(teacherTable.getValueAt(row, 0).equals(index)
								||teacherTable.getValueAt(row, 1).equals(index)){
							break;
						}
					}
					teacherTable.setRowSelectionInterval(row,row);
				}else{				
					helper.outputToDialog(TEACHER_NOT_EXIST);
				}
			}			
		});

		teacherTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					if (e.getClickCount() == 2) {
						int row =((JTable)e.getSource()).rowAtPoint(e.getPoint()); 
						String id=(String) teacherTable.getValueAt(row,0);
						setAvgScoreTable(id);																		 
					}
				}
			}
		});
		
		p1.add(label2);
		p1.add(tf,"gapleft 120");
		p1.add(b);
		
		JLabel label3=new JLabel(">>所授课程平均成绩统计");
		
		ImageIcon icon1=new ImageIcon(returnButton);
		JButton return_b=new JButton(icon1);
		setter.setButtonUnOpaque(return_b);
		return_b.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				showDetailInfo();
			}			
		});
		
		moreDetailInfo.add(label1,"wrap");
		moreDetailInfo.add(p1);
		moreDetailInfo.add(label3,"gapleft 50,wrap");
		moreDetailInfo.add(jsp1);
		moreDetailInfo.add(jsp2,"gapleft 50,wrap");
		moreDetailInfo.add(return_b,"gaptop 5");
		
		panel.add(moreDetailInfo);
		repaint();
	}
	
	public void setTeacherTable(){
		ArrayList<TeacherVO> tvList=teacherList.getTeacherList(facultyName);
		String[] columnTitle={"工号","姓名","课程数"};
		teacherTable=new JTable(){
			   @Override
			public boolean isCellEditable(int row, int column) { 
				    return false;
			   }
		};
		DefaultTableModel tableModel = (DefaultTableModel) teacherTable.getModel();
        tableModel.setColumnIdentifiers(columnTitle);
        if(tvList!=null){
        	for(int i=0;i<tvList.size();i++) { 
        		TeacherVO tv=tvList.get(i);
        		String[] str ={tv.getId(),tv.getName(),tv.getCourseCount()+""};
        		tableModel.addRow(str);         
        	}
        }
	}
	
	public void setAvgScoreTable(String teacherId){
		Teacher teacher=new Teacher(teacherId);
		ArrayList<TeacherScoreVO> tsvList=teacher.showAverageCourseScore();
		String[] columnTitle={"课程号","课程名","平均分"};
		DefaultTableModel tableModel = (DefaultTableModel) avgScoreTable.getModel();
		tableModel.setRowCount(0);// 清除原有行
		tableModel.setColumnIdentifiers(columnTitle);
		if(tsvList!=null){
			for(int i=0;i<tsvList.size();i++) { 
				TeacherScoreVO tsv=tsvList.get(i);
				String cre=tsv.getCredit()+"";
				if(tsv.getCredit()==0){
					cre=CREDIT_NULL;
				}
				String[] str ={tsv.getCourseID(),
						tsv.getCourseName(),cre};
				tableModel.addRow(str);         
			}
		}
	}
}
