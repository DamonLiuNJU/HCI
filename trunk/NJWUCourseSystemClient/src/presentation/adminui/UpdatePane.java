package presentation.adminui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


import vo.adminvo.UserVO;
import businesslogic.managerbl.Admin;
import businesslogic.planbl.Plan;

/*
 * 更新用户的panel，包含一个查找的区域（panel）和一个userinfopane，用来显示各种用户的信息
 * panel上提供编辑和保存以及删除的按钮。
 */
public class UpdatePane extends JPanel implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Admin admin;
	UserInfoPane uip ;
	JScrollPane jsp;
	JPanel searchPane , editPane , deletePane;
	JLabel searchLabel;
	JButton searchButton , edit , delete ,save ;
	JTextField searchField;
	
	boolean uipInited;
	
	public UpdatePane(Admin admin){
		this.admin = admin ;
		this.draw();
		uipInited = false;
	}
	
	/*
	 * 初始化界面，将所有组件添加在panel上，userInfoPane中不显示信息
	 */
	private void draw(){
		this.setBounds(0 ,0 , 1000 , 450);
		this.setLayout(null);
		
		this.initSearchPane();
		
		uip = new UserInfoPane();
		jsp = new JScrollPane(uip);
		jsp.setBounds( 50 , 100 , 800 , 200);
		
		editPane = new JPanel();
		editPane.setBounds(650 , 40 , 150 , 60);
		
		edit = new JButton("编辑");
		edit.addMouseListener(this);
		
		editPane.add(edit);
		
		deletePane = new JPanel();
		deletePane.setBounds(550 ,320 , 350 , 60);
		
		save = new JButton("保存");
		save.addMouseListener(this);
		
		delete = new JButton("删除");
		delete.addMouseListener(this);
		
		deletePane.add(save);
		deletePane.add(delete);
		
		this.add(jsp);
		this.add(editPane);
		this.add(deletePane);
	}
	
	/*
	 * 初始化搜索pane，有一个label和一个textField以及一个按钮
	 */
	private void initSearchPane(){
		searchPane = new JPanel();
		searchPane.setBounds(50 , 40 ,400 ,60);
		
		searchLabel = new JLabel("搜索：");
		searchField = new JTextField(20);
		searchButton = new JButton("搜索");
		searchButton.addMouseListener(this);
		
		searchPane.add(searchLabel);
		searchPane.add(searchField);
		searchPane.add(searchButton);
		
		this.add(searchPane);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == searchButton){
			if(searchField.getText().equals("")){
				JOptionPane.showMessageDialog(this, "请输入搜索信息");
				return ;
			}
			else {
				String id = searchField.getText();
				UserVO user = admin.findUser(id);
				if(user == null){
					JOptionPane.showMessageDialog(this, "未查找到该学（工）号用户");
				}
				else{
					uip.showInfo(user);
					uipInited = true;
				}
			}
		}
		else if(e.getSource() == edit){
			if(!uipInited){
				JOptionPane.showMessageDialog(this, "请先搜索一个用户");
			}
			else{
				uip.setEditable(true);
			}
		}
		else if(e.getSource() == save){
			if(!uipInited){
				JOptionPane.showMessageDialog(this, "请先搜索一个用户");
			}
			else {
				UserVO uv = uip.getInfomation();
				uv.setFaculty_id(new Plan().getFacultyID(uv.getFaculty_name()));
				
				String backInfo = admin.updateUser(uv);
				JOptionPane.showMessageDialog(this, backInfo);
				uip.setEditable(false);
			}
		}
		else if(e.getSource() == delete){
			if(!uipInited){
				JOptionPane.showMessageDialog(this, "请先搜索一个用户");
			}
			else {
				uip.setEditable(false);
				String type = uip.type;
				String id = uip.jtf[0].getText();
				
				String backInfo = admin.deleteUser(id, type);
				JOptionPane.showMessageDialog(this, backInfo);
				if(backInfo.equals("删除成功")){
					uip.removeAll();
					uip.type = null;
					this.uipInited = false;
					searchField.setText("");
					this.repaint();
				}
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
