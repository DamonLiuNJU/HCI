package presentation.teacherui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import businesslogic.teacherbl.Teacher;

public class TeacherInfoPane extends JPanel implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Teacher teacher ;
	JPanel imagePane , infoPane , buttonPane ;
	JLabel name , faculty , ID , contactInfo ;
	JTextField contact;
	JButton changePassword ,save;
	
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
		name.setBounds(10 , 15 , 170 ,50);
		
		faculty = new JLabel("院系： " + teacher.getFaculty());
		faculty.setBounds(200 , 15 , 170 , 50);
		
		ID = new JLabel("工号： " + teacher.getID());
		ID.setBounds(400 , 15 , 170 , 50);
		
		contactInfo = new JLabel("邮箱： ");
		contactInfo.setBounds(10 , 80 , 70 , 50);
		
		contact = new JTextField();
		contact.setText(teacher.getContactInfo());
		contact.setBounds(80 , 90 , 170 , 30 );
		
		save = new JButton("保存");
		save.setBounds(260 , 90 , 70 ,30);
		save.addMouseListener(this);
		
		infoPane.add(name);
		infoPane.add(faculty);
		infoPane.add(ID);
		infoPane.add(contactInfo);
		infoPane.add(contact);
		infoPane.add(save);
		
		this.add(infoPane);
		
		buttonPane = new JPanel();
		buttonPane.setBounds(40 , 250 , 200 , 100);
		buttonPane.setOpaque(false);
		
		changePassword= new JButton("修改密码");
		TeacherUITool.setButtonIcon(TeacherUITool.password, changePassword);
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
		else if(e.getSource() == save){
			String info = contact.getText();
			if(!info.equals("")){
				String backInfo = teacher.updateContactInfo(info);
				JOptionPane.showMessageDialog(this, backInfo);
			}
			else {
				JOptionPane.showMessageDialog(this, "请输入您的联系方式再保存");
			}
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
