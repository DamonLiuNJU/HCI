package presentation.adminui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;


import businesslogic.managerbl.Admin;

/*
 * 管理用户的界面，主要由一个tabbedPane构成
 * 此tabbedPane包含2个标签，一个为注册用户，一个为更新用户
 */
public class UserManagePane extends JPanel implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Admin admin;
	JTabbedPane tabbedPane;
	
	public UserManagePane(Admin admin){
		this.admin = admin ;
		this.draw();
	}
	
	private void draw(){
		this.setBounds(0 ,0 ,1000 , 450);
		this.setLayout(null);
		
		this.initTabbedPane();
		
	}
	
	private void initTabbedPane(){
		tabbedPane = new JTabbedPane(SwingConstants.LEFT);
		tabbedPane.setBounds(0 , 0 , 970 ,450);
		
		RegisterPane register = new RegisterPane(admin);
		UpdatePane update = new UpdatePane(admin);
		
		tabbedPane.addTab("注册", register);
		tabbedPane.addTab("更新", update);
		this.add(tabbedPane);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
