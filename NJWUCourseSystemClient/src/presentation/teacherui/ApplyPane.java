package presentation.teacherui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import businesslogic.teacherbl.Teacher;
import businesslogic.utilitybl.ReplyMessage;

/*
 * 此类为申报课程类，用户选择“申报课程标签”时，出现该Panel
 * 该类分为2个Panel ， courseInfoPane在界面的中部， 所有的要填写的课程信息都在该Panel中。
 * buttonPane在下部，提供“导入课程信息” ， “确定申报” ， “重置” ， 和 “返回” 按钮。
 */
public class ApplyPane extends JPanel implements MouseListener ,ReplyMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JPanel courseInfoPane , buttonPane;
	JButton sure , reset , back ;
	JLabel courseName, intention , plan , classHour , stuNum , score , expectedTime;
	JLabel moduleLabel;
	JTextField[] jtf;
	JTextArea jta1 , jta2;
	Teacher teacher ;
	JComboBox<String> module;
	
	public ApplyPane(Teacher teacher){
		this.teacher = teacher;
		this.setLayout(null);
		this.setBounds(0,0,1000,450);
		this.setOpaque(false);
		
		courseInfoPane = new JPanel();
		courseInfoPane.setBounds(80 , 30 , 840 , 325);
		courseInfoPane.setLayout(null);
		courseInfoPane.setOpaque(false);
		courseInfoPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY,2),"课程信息"
                ,TitledBorder.LEFT,TitledBorder.TOP));
		
		courseName = new JLabel("课程名称");
		courseName.setBounds(20 , 15 , 100 , 30);
		
		intention = new JLabel("教学目的");
		intention.setBounds(20 , 135 , 100 , 30);
		
		plan = new JLabel("教学计划");
		plan.setBounds(20 , 235 , 100 ,30);
		
		classHour = new JLabel("课时数");
		classHour.setBounds(420 , 15 , 100 ,30 );
		
		stuNum = new JLabel("学生人数");
		stuNum.setBounds(20 , 55 , 100 , 30 );
		
		score = new JLabel("学分");
		score.setBounds(420 , 55 , 100 , 30);
		
		expectedTime = new JLabel("期望上课时间");
		expectedTime.setBounds(20 , 95 , 100 , 30);
		
		moduleLabel = new JLabel("课程模块");
		moduleLabel.setBounds(420 , 95 , 100 , 30);
		
		jtf = new JTextField[5];
		for(int i=0 ; i<5 ; i++){
			jtf[i] = new JTextField();
			jtf[i].setOpaque(false);
		}
		
		jtf[0].setBounds(120 , 15 , 200 , 30);
		jtf[1].setBounds(520 , 15 , 200 , 30);
		jtf[2].setBounds(120 , 55 , 200 , 30);
		jtf[3].setBounds(520 , 55 , 200 , 30);
		jtf[4].setBounds(120 , 95 , 200 , 30);
		this.setComboBox();
		module.setBounds(520 , 95 ,200 ,30);
		
		jta1 = new JTextArea();
		jta1.setBounds(20 , 170 , 800 , 50);
		jta1.setOpaque(false);
		
		jta2 = new JTextArea();
		jta2.setBounds(20 , 265 , 800 , 50);
		jta2.setOpaque(false);
		
		courseInfoPane.add(courseName);
		courseInfoPane.add(jtf[0]);
		courseInfoPane.add(intention);
		courseInfoPane.add(jtf[1]);
		courseInfoPane.add(plan);
		courseInfoPane.add(jtf[2]);
		courseInfoPane.add(classHour);
		courseInfoPane.add(jtf[3]);
		courseInfoPane.add(stuNum);
		courseInfoPane.add(jtf[4]);
		courseInfoPane.add(score);
		courseInfoPane.add(expectedTime);
		courseInfoPane.add(jta1);
		courseInfoPane.add(jta2);
		courseInfoPane.add(moduleLabel);
		courseInfoPane.add(module);
		
		buttonPane = new JPanel();
		buttonPane.setLayout(null);
		buttonPane.setBounds( 0 , 350 , 1000 ,100);
		buttonPane.setOpaque(false);
		
		sure = new JButton("确定申报");
		sure.setBounds(400 , 20 , 100 ,30 );
		TeacherUITool.setButtonIcon(TeacherUITool.sure, sure);
		sure.addMouseListener(this);
		
		reset = new JButton("重置");
		reset.setBounds(520 , 20 ,100 , 30);
		TeacherUITool.setButtonIcon(TeacherUITool.refresh, reset);
		reset.addMouseListener(this);
		
		buttonPane.add(sure);
		buttonPane.add(reset);
		
		this.add(courseInfoPane);
		this.add(buttonPane);
	}
	
	private void setComboBox(){
		String[] content = {"选修课","通识课","专业课"};
		module = new JComboBox<>(content);
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == reset){
			for(int i=0 ; i<jtf.length ; i++){
				jtf[i].setText("");
			}
			jta1.setText("");
			jta2.setText("");
		}
		else if(e.getSource() == sure){
			for(int i=0 ; i<jtf.length ; i++){
				if((jtf[i].getText().equals(""))){
					JOptionPane.showMessageDialog(this, MISSING_INFO);
					return ;
				}
			}
			if((jta1.getText().equals(""))&&(jta2.getText().equals(""))){
				JOptionPane.showMessageDialog(this, MISSING_INFO);
				return ;
			}
			
			String content = this.initApplyContent();
			if(content == null){
				return ;
			}
			String backInfo = teacher.apply(content , jtf[0].getText());
			JOptionPane.showMessageDialog(this, backInfo);
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
	
	private String initApplyContent(){
		String name = jtf[0].getText();
		try{
			int classHour = Integer.parseInt(jtf[1].getText());
			int maxStuNum = Integer.parseInt(jtf[2].getText());
			int score  = Integer.parseInt(jtf[3].getText());
		
			String expectedTime = jtf[4].getText() ;
			String intention = jta1.getText();
			String plan = jta2.getText();
			String courseModule = (String) module.getSelectedItem();
			
			String content = "";
			content = "课程名称: "+name+'\n';
			content+= "课时数： " + classHour + '\n';
			content+= "最大学生数： "+ maxStuNum + '\n';
			content += "学分： "+ score + '\n';
			content += "期望上课时间： " + expectedTime + '\n';
			content += "教学目的： " + intention + '\n';
			content += "教学计划： " + plan + '\n';
			content += "课程模块： " + courseModule + '\n';
			
			return content ;
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(this, NUMBER_FORMAT_EXCEPTION);
			return null;
		}
		

	}
	
}

