package presentation.teacherui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import businesslogic.teacherbl.Teacher;

public class TeacherChangePasswordDialog extends JDialog implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel old , newP , newP2 ;
	JPasswordField oldPass , newPass , newPass2 ;
	JButton sure , cancel ;
	JPanel panel , buttonPane;
	Teacher teacher ;

	public TeacherChangePasswordDialog(Teacher teacher){
		this.teacher = teacher;
		this.setBounds(500,400,300,200);
		this.setLayout(null);
		
		old = new JLabel("旧密码：");
		newP = new JLabel("新密码：");
		newP2 = new JLabel("再次输入：");
		
		oldPass = new JPasswordField(17);
		newPass = new JPasswordField(17);
		newPass2 = new JPasswordField(16);
		
		panel = new JPanel();
		panel.setBounds(0 , 0 , 300 , 100);
		
		buttonPane = new JPanel();
		buttonPane.setBounds(0 , 100 , 300 , 100);
		buttonPane.setLayout(null);
		
		sure = new JButton("确定");
		sure.setBounds(30 , 20, 70, 30);
		sure.addMouseListener(this);
		cancel = new JButton("取消");
		cancel.setBounds(180 , 20 , 70 ,30);
		cancel.addMouseListener(this);

		buttonPane.add(sure);
		buttonPane.add(cancel);
		panel.add(old);
		panel.add(oldPass);
		panel.add(newP);
		panel.add(newPass);
		panel.add(newP2);
		panel.add(newPass2);
		this.add(panel);
		this.add(buttonPane);
		
		this.setVisible(true);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == sure){
			if((oldPass.getPassword().length != 0)&&(newPass.getPassword().length !=0)&&(newPass2.getPassword().length!=0)){
				String newPass1 = new String(newPass.getPassword());
				String pass2 = new String(newPass2.getPassword());
				if(!newPass1.equals(pass2)){
					JOptionPane.showMessageDialog(this, "两次输入密码不一致，请重新输入");
					return ;
				}
				
				String newPassword = new String(newPass.getPassword());
				String oldPassword = new String(oldPass.getPassword());
				
				String backInfo = teacher.changePassword(oldPassword, newPassword);
				JOptionPane.showMessageDialog(this, backInfo);
				this.dispose();
			}
			else JOptionPane.showMessageDialog(this, "输入不完整，请重新输入");
		}
		else if(e.getSource() == cancel){
			this.dispose();
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
