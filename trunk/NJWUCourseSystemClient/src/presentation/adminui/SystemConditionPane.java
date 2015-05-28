package presentation.adminui;

import javax.swing.JButton;
import javax.swing.JPanel;

import presentation.statusui.AdminStatusPane;
import businesslogic.managerbl.Admin;

/*
 * 存放系统状态的panel
 */
public class SystemConditionPane extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Admin admin ;
	JPanel buttonPane ;
	JButton edit , save;
	JPanel statusPane;
	
	public SystemConditionPane(Admin admin){
		this.admin = admin ;
		this.draw();
	}
	
	private void draw(){
		this.setBounds(0 ,0 ,1000 , 450);
		this.setLayout(null);
		
		new AdminStatusPane().setStatusPane(this);
		
		
	}



}
