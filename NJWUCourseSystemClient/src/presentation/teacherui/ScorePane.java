package presentation.teacherui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import presentation.mainui.TeacherMainUI;
import vo.teacherusedvo.TeacherCourseVO;
import vo.teacherusedvo.TeacherUseStudentVO;
import businesslogic.teacherbl.Teacher;

/*
 * 此类为学生成绩类 ， 当用户在InnerJP1中，点击“登记成绩”按钮时，将相应的课程传入该类，并显示该Panel
 * 该类分为3个子Panel ， searchPane ， jsp ， buttonPane
 * searchPane 处于上部， 提供检索功能 。
 * jsp 处于中部， 为该Panel的核心， 内中有一个jtable ， 显示该课程的所有学生，并在该table中完成登记成绩
 * buttonPane 处于下部， 提供“提交” ， “重置” ， “返回” 3个按钮
 */
public class ScorePane extends JPanel implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JPanel searchPane ,  buttonPane ;
	JTable jt ;
	JLabel jl;
	JButton search , cancel , submit , reset , back;
	JScrollPane jsp;
	JTextField jtf ;
	Teacher teacher; 
	TeacherCourseVO cv;
	ArrayList<TeacherUseStudentVO> tusArray;
	
	public ScorePane(Teacher teacher , TeacherCourseVO cv){
		this.teacher = teacher ;
		this.cv = cv;
		this.tusArray = teacher.showStudent(cv);
		this.setLayout(null);
		this.setBounds(0 , 0 , 1000 , 450 );
		this.setOpaque(false);
		
		searchPane = new JPanel();
		searchPane.setBounds( 0 , 0 , 1000 , 50);
		searchPane.setOpaque(false);
		
		jl = new JLabel("搜索：");
		jtf = new JTextField(30);
		
		search = new JButton("搜索");
		TeacherUITool.setButtonIcon(TeacherUITool.search, search);
		search.addMouseListener(this);
		
		cancel = new JButton("取消");
		TeacherUITool.setButtonIcon(TeacherUITool.cancel, cancel);
		cancel.addMouseListener(this);
		
		searchPane.add(jl);
		searchPane.add(jtf);
		searchPane.add(search);
		searchPane.add(cancel);
		
		this.tableInit();
		
		jsp = new JScrollPane(jt);
		jsp.setBounds(0 , 50 , 980 , 300);
		jsp.setOpaque(false);
		
		buttonPane = new JPanel();
		buttonPane.setLayout(null);
		buttonPane.setBounds(0 , 350 , 1000 , 100);
		buttonPane.setOpaque(false);
		
		submit = new JButton("提交");
		submit.setBounds(50 , 30 , 100 , 30);
		TeacherUITool.setButtonIcon(TeacherUITool.sure, submit);
		submit.addMouseListener(this);
		
		reset = new JButton("重置");
		reset.setBounds(450 , 30 , 100 , 30);
		TeacherUITool.setButtonIcon(TeacherUITool.refresh, reset);
		reset.addMouseListener(this);
		
		back = new JButton("返回");
		back.setBounds(850 , 30 , 100 , 30);
		TeacherUITool.setButtonIcon(TeacherUITool.returnLabel, back);
		back.addMouseListener(this);
		
		buttonPane.add(submit);
		buttonPane.add(reset);
		buttonPane.add(back);
		
		this.add(searchPane);
		this.add(jsp);
		this.add(buttonPane);
	}
	
	private void tableInit(){
		String[][] content= new String[tusArray.size()][4]; 
		String[] title = {"学号" ,"姓名" ,"年级" ,"成绩"};
		for(int i=0 ; i<tusArray.size() ; i++){
			TeacherUseStudentVO tus = tusArray.get(i);
			content[i][0] = tus.getId();
			content[i][1] = tus.getName();
			content[i][2] = tus.getGrade();
			content[i][3] = tus.getScore();
		}
		
		TableModel tableModel = new DefaultTableModel(content, title) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				if (column == 3) {
					return true;
				} 
				else {
					return false;
				}
			}
		};

		jt = new JTable(tableModel);
		jt.getTableHeader().setReorderingAllowed(false);
		jt.setRowHeight(20);
	}
	
	private void search(String info){
		for(int i=0; i<tusArray.size();i++){
			if(tusArray.get(i).getId().contains(info)){
				jt.setRowSelectionInterval(i,i);
				return ;
			}
		}
		JOptionPane.showMessageDialog(this, "没有搜索项");
	}
	
	private int check(){
		int index = -1;
		System.out.println();
		for(int i=0 ; i<jt.getRowCount() ; i++){
			try{
				int score = Integer.parseInt((String) jt.getValueAt(i, 3));
				if(!((score>=0)&&(score<=100))){
					return i;
				}
			}catch(NumberFormatException e){
				return i;
			}
		}
		
		return index;
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
		else if (e.getSource() == reset){
			this.remove(jsp);
			this.tableInit();
			
			jsp = new JScrollPane(jt);
			jsp.setBounds(0 , 50 , 980 , 300);
			jsp.setOpaque(false);
			
			this.add(jsp);
		}
		else if(e.getSource() == submit){
			if(!teacher.isRecordTime(cv.getCourseID())){
				JOptionPane.showMessageDialog(this, "现在不是登记成绩时间");
				return ;
			}
			int index = check();
			if(index >= 0){
				JOptionPane.showMessageDialog(jt, "第"+(index+1)+"行成绩格式错误");
				return ;
			}
			
			ArrayList<String> scoreArray = new ArrayList<String>();
			
			for(int i=0;i<jt.getRowCount() ;i++){
				String[] temp = new String[jt.getColumnCount()];
				String s = "";
				for(int j=0 ; j<jt.getColumnCount() ; j++){
					temp[i] = (String)jt.getValueAt(i, j);
					s+=(temp[i]+" ");
				}
				scoreArray.add(s);
			}
			String backInfo = teacher.recordScore(scoreArray, cv.getCourseID());
			JOptionPane.showMessageDialog(this, backInfo);
			
		}
		else if(e.getSource() == search){
			String info = jtf.getText();
			if(info.equals("")){
				JOptionPane.showMessageDialog(this, "未填写搜索信息");
			}
			else {
				this.search(info);
			}
		}
		else if(e.getSource() == cancel){
			jtf.setText("");
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
