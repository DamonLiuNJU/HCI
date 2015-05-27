package presentation.teacherui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import presentation.mainui.TeacherMainUI;
import vo.teacherusedvo.TeacherCourseVO;
import vo.teacherusedvo.TeacherUseStudentVO;
import businesslogic.teacherbl.Teacher;

/*
 * 此类为查看学生信息的panel ， 分为，searchPane, table , buttonPane , infoPane四个面板。
 * searchPane提供查找；table 里面显示学生信息， 且可以选择某一学生查看其信息；
 * buttonPane 提供 到处信息和返回2个按钮，infoPane显示学生的详细信息。
 */
public class StudentInfoPane extends JPanel implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel searchLabel ;
	JTextField jtf ;
	JTable stuInfo ;
	JButton sure , cancel , back ;
	JPanel searchPane , buttonPane , infoPane ;
	JScrollPane jsp ;
	Teacher teacher;
	TeacherCourseVO cv ;
	ArrayList<TeacherUseStudentVO> tusArray;
	
	//下列组件为在infoPane中的组件
	JPanel jp1 , jp2 , imagePane ;
	JLabel[] jl;
	JLabel[] labelArray;
	JLabel present;
	JTextArea jta;
	
	public StudentInfoPane(Teacher teacher , TeacherCourseVO cv){
		this.teacher = teacher ;
		this.cv = cv;
		this.tusArray = teacher.showStudent(cv);
		this.setLayout(null);
		this.setBounds(0 , 0 , 1000 , 450 );
		this.setOpaque(false);
		
		searchLabel = new JLabel("搜索：");
		jtf = new JTextField(20);
		
		sure = new JButton("确定");
		TeacherUITool.setButtonIcon(TeacherUITool.search, sure);
		sure.addMouseListener(this);
		
		cancel = new JButton("取消");
		TeacherUITool.setButtonIcon(TeacherUITool.cancel, cancel);
		cancel.addMouseListener(this);
		
		searchPane = new JPanel();
		searchPane.setBounds(0 , 0 , 600 , 50);
		searchPane.setOpaque(false);
		
		searchPane.add(searchLabel);
		searchPane.add(jtf);
		searchPane.add(sure);
		searchPane.add(cancel);
		
		ArrayList<TeacherUseStudentVO> stuArray = teacher.showStudent(cv);
		
		this.tableInit(stuArray);
		
		jsp = new JScrollPane(stuInfo);
		jsp.setBounds(10 , 50 , 590 , 300);
		
		buttonPane = new JPanel();
		buttonPane.setLayout(null);
		buttonPane.setBounds(0 , 350 , 600 , 100);
		buttonPane.setOpaque(false);
		
		back = new JButton("返回");
		back.setBounds(500 , 30 , 70 , 30);
		TeacherUITool.setButtonIcon(TeacherUITool.returnLabel, back);
		back.addMouseListener(this);
		
		
		buttonPane.add(back);
		
				
		this.infoPaneInit();
		
		this.add(jsp);
		this.add(searchPane);
		this.add(buttonPane);
		this.add(infoPane);
		
	}
	
	private void tableInit(ArrayList<TeacherUseStudentVO> stuArray){
		String[] title = {"学号" , "姓名" , "年级" , "院系"};
		String[][] content = new String[stuArray.size()][4];
		
		for(int i=0 ; i<content.length ; i++){
			TeacherUseStudentVO tus = stuArray.get(i);
			content[i][0] = tus.getId();
			content[i][1] = tus.getName();
			content[i][2] = tus.getGrade();
			content[i][3] = tus.getFaculty_name();
		}
		
		TableModel model = new DefaultTableModel(content ,title){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			
		};
		
		stuInfo = new JTable(model);
		stuInfo.getTableHeader().setReorderingAllowed(false);
		stuInfo.setRowHeight(20);
		stuInfo.addMouseListener(this);
	}
	
	private void infoPaneInit(){
		infoPane = new JPanel();
		infoPane.setLayout(null);
		infoPane.setBounds(630 , 50 , 300 , 300);
		infoPane.setOpaque(false);
		
		ImageIcon image = new ImageIcon("./icon/list.png");
		JLabel imageLabel= new JLabel(image);
		imageLabel.setBounds(0 , 0 , image.getIconWidth() , image.getIconHeight());
		
		JPanel imagePane = new JPanel();
		imagePane.setLayout(null);
		imagePane.setBounds(30 , 30 , image.getIconWidth() , image.getIconHeight());
		
	
		imagePane.add(imageLabel);
		
		jp1 = new JPanel();
		jp1.setBounds(120 , 10 , 150 ,138);
		jp1.setLayout(new GridLayout(4,2));
		jp1.setOpaque(false);
		
		jl = new JLabel[6];
		for(int i=0 ; i<6 ; i++){
			jl[i] = new JLabel();
			jp1.add(jl[i]);
		}
		
		jl[0].setText("姓名：");
		jl[2].setText("学号：");
		jl[4].setText("年级：");
		
		jp2 = new JPanel();
		jp2.setBounds(12 , 140 , 280 , 138);
		jp2.setLayout(new GridLayout(6,2));
		jp2.setOpaque(false);
		
		labelArray = new JLabel[12];
		for(int i=0;i<labelArray.length ; i++){
			labelArray[i] = new JLabel();
			jp2.add(labelArray[i]);
		}
		labelArray[0].setText("电话号码");
		labelArray[2].setText("邮箱");
		labelArray[4].setText("母亲姓名");
		labelArray[6].setText("母亲电话");
		labelArray[8].setText("父亲姓名");
		labelArray[10].setText("父亲电话");
		
		infoPane.add(imagePane);
		infoPane.add(jp1);
		infoPane.add(jp2);
		
		
		infoPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black,2),"详细信息"
			                      ,TitledBorder.LEFT,TitledBorder.TOP));
	}
	
	private void showDetail(int index){
		TeacherUseStudentVO tus = tusArray.get(index);
		jl[1].setText(tus.getName());
		jl[3].setText(tus.getId());
		jl[5].setText(tus.getGrade());
		
		labelArray[1].setText(tus.getPhone());
		labelArray[3].setText(tus.getContactInfo());
		labelArray[5].setText(tus.getMother_name());
		labelArray[7].setText(tus.getMother_phone());
		labelArray[9].setText(tus.getFather_name());
		labelArray[11].setText(tus.getFather_phone());
		
		this.repaint();
	}
	
	private void search(String info){
		for(int i=0; i<tusArray.size();i++){
			if(tusArray.get(i).getId().contains(info)){
				stuInfo.setRowSelectionInterval(i,i);
				return ;
			}
		}
		JOptionPane.showMessageDialog(this, "没有搜索项");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == back){
			TeachingInfo inner1 = new TeachingInfo(teacher);
			TeacherMainUI.jtp.add(inner1 , 2);
			TeacherMainUI.jtp.setTitleAt(2 , "课程信息" );
			TeacherMainUI.jtp.remove(1);
		}
		else if(e.getSource() == cancel){
			jtf.setText("");
		}
		else if(e.getSource() == sure){
			if(jtf.getText().equals("")){
				JOptionPane.showMessageDialog(this, "请填写搜索信息");
			}
			else {
				this.search(jtf.getText());
			}
		}
		else if(e.getSource() == stuInfo){
			this.showDetail(stuInfo.getSelectedRow());
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

