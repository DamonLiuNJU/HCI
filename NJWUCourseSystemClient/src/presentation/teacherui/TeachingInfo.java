package presentation.teacherui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import presentation.mainui.TeacherMainUI;
import vo.teacherusedvo.TeacherCourseVO;
import businesslogic.teacherbl.Teacher;
import businesslogic.utilitybl.ReplyMessage;

/*
 * 此类为教学信息类 ， 当用户点击教学信息“教学信息”标签时，会出现该类
 */
public class TeachingInfo extends JPanel implements MouseListener , ReplyMessage{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JScrollPane jsp1;
	JTable courseTable ;
	JPanel buttonPane1 ;
	JButton jb1_1 , jb1_2;
	JPanel stuInfoPane , scorePane;
	Teacher teacher ;
	
	public TeachingInfo(Teacher teacher){
		this.teacher = teacher;
		this.setLayout(null);
		this.setBounds(0 , 0 , 1000 , 450);
		
		this.tableInit();
		this.setOpaque(false);
		
		jsp1 = new JScrollPane(courseTable);
		jsp1.setSize(980 , 300);
		
		buttonPane1 = new JPanel();
		buttonPane1.setLayout(null);
		buttonPane1.setBounds( 0 , 350 , 1000 , 100);
		buttonPane1.setOpaque(false);
		
		jb1_1 = new JButton("查看学生列表");
		jb1_1.setBounds(150 , 0 , 150 ,30);
		TeacherUITool.setButtonIcon(TeacherUITool.show, jb1_1);
		jb1_1.addMouseListener(this);
		
		jb1_2 = new JButton("登记成绩");
		jb1_2.setBounds(650 , 0 , 100 ,30);
		TeacherUITool.setButtonIcon(TeacherUITool.record, jb1_2);
		jb1_2.addMouseListener(this);
		
		buttonPane1.add(jb1_1);
		buttonPane1.add(jb1_2);
		
		this.add(buttonPane1);
		this.add(jsp1);
	}
	
	private void tableInit(){
		String[] head = {"课程编号" , "课程名称" , "上课时间" , "上课地点"};
		
		ArrayList<TeacherCourseVO> courseArray = new ArrayList<TeacherCourseVO>();
		courseArray = teacher.getTeacherCourseList();
		String[][] content = new String[courseArray.size()][4];
		
		for(int i=0 ; i<content.length ; i++){
			content[i][0] = courseArray.get(i).getCourseID() ;
			content[i][1] = courseArray.get(i).getCourseName();
			content[i][2] = courseArray.get(i).getClassTime();
			content[i][3] = courseArray.get(i).getLocation();
		}
		
		TableModel model = new DefaultTableModel(content , head){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		courseTable = new JTable(model);
		courseTable.getTableHeader().setReorderingAllowed(false);
		courseTable.setRowHeight(30);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == jb1_1){
			int row = courseTable.getSelectedRow();
			if(row == -1){
				JOptionPane.showMessageDialog(this, NULL_SELECT);
				return ;
			}
			TeacherCourseVO cv = teacher.getTeacherCourseList().get(row);
			stuInfoPane = new StudentInfoPane(teacher , cv);
			TeacherMainUI.jtp.add(stuInfoPane , 2);
			TeacherMainUI.jtp.setTitleAt(2 , "课程信息" );
			TeacherMainUI.jtp.remove(1);
		}
		else if(e.getSource() == jb1_2){
			int row = courseTable.getSelectedRow();
			if(row == -1){
				JOptionPane.showMessageDialog(this, NULL_SELECT);
				return ;
			}
			TeacherCourseVO cv = teacher.getTeacherCourseList().get(row);
			scorePane = new ScorePane(teacher , cv ); 
			TeacherMainUI.jtp.add(scorePane , 2);
			TeacherMainUI.jtp.setTitleAt(2 , "课程信息" );
			TeacherMainUI.jtp.remove(1);
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
