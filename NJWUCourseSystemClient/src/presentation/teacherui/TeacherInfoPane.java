package presentation.teacherui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import businesslogic.teacherbl.Teacher;

public class TeacherInfoPane extends JPanel implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Teacher teacher ;
	JPanel imagePane , infoPane , buttonPane ;
	JLabel name , faculty , ID , notice ;
	JButton changePassword ;
	
	public TeacherInfoPane(Teacher teacher){
		this.teacher = teacher;
		draw();
	}
	
	public void draw(){
		this.setLayout(null);
		this.setBounds(0,0,1000,450);
		this.setOpaque(false);
		
		imagePane = new JPanel();
		imagePane.setLayout(null);
		imagePane.setBounds(40 , 50 , 200 , 200);
		imagePane.setOpaque(false);
		
		JLabel portrait = new JLabel(new ImageIcon("./icon/faculty/portrait.png"));
		portrait.setBounds(0 , 0 , 200 , 200);
		imagePane.add(portrait);
		
		this.add(imagePane);
		
		infoPane = new JPanel();
		infoPane.setLayout(null);
		infoPane.setBounds(300 , 50 , 600 , 300);
		infoPane.setOpaque(false);
		infoPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY,2),"信息中心"
                ,TitledBorder.LEFT,TitledBorder.TOP));
		
		name = new JLabel("姓名： " + teacher.getName());
		name.setBounds(10 , 5 , 170 ,30);
		
		faculty = new JLabel("院系： " + teacher.getFaculty());
		faculty.setBounds(200 , 5 , 170 , 30);
		
		ID = new JLabel("工号： " + teacher.getID());
		ID.setBounds(400 , 5 , 170 , 30);
		
		notice = new JLabel("最新消息：");
		notice.setBounds(10 , 40 , 200 , 30);
		
		infoPane.add(name);
		infoPane.add(faculty);
		infoPane.add(ID);
		infoPane.add(notice);
		
		this.add(infoPane);
		
		buttonPane = new JPanel();
		buttonPane.setBounds(40 , 250 , 200 , 100);
		buttonPane.setOpaque(false);
		
		changePassword= new JButton("修改密码");
		changePassword.addMouseListener(this);
		
		buttonPane.add(changePassword);
		
		this.add(buttonPane);
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == changePassword){
			new TeacherChangePasswordDialog(teacher);
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
