package presentation.managerui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import vo.adminvo.UserVO;
import businesslogic.managerbl.Admin;

public class RegisterPane extends JPanel implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Admin admin ;
	JLabel userType , importLabel;
	ButtonGroup group;
	JRadioButton jrb1 , jrb2 , jrb3 ,jrb4;
	JPanel selectingJP , buttonJP;
	UserInfoPane infoJP;
	JScrollPane jsp ;
	JButton sureButton ;
	String type;
	
	
	public RegisterPane(Admin admin){
		this.admin = admin;
		this.draw();
	}
	
	public void draw(){
		this.setBounds(0 , 0, 1000 ,450 );
		this.setLayout(null);
		
		this.initSelectingJP();
		
		infoJP = new UserInfoPane();//userinfoPane上显示了注册需要填写的信息
		infoJP.setBounds( 50 , 180 , 800 , 200);
		
		buttonJP = new JPanel();
		buttonJP.setBounds(0 , 380 , 900 ,50);
		
		sureButton = new JButton("确定");
		sureButton.addMouseListener(this);
		
		buttonJP.add(sureButton);
		
		this.add(infoJP);
		this.add(buttonJP);
	}
	
	//选择用户类型的Panel ， 核心是一个ButtonGroup
	private void initSelectingJP(){
		selectingJP = new JPanel();
		selectingJP.setLayout(null);
		selectingJP.setBounds(20 , 30 , 1000 , 150);
		
		userType = new JLabel("注册用户类型");
		userType.setBounds(50 , 0 , 100 , 33);
		
		jrb1 = new JRadioButton("教务处老师");
		jrb1.setBounds(180 , 0 , 100 ,33);
		jrb1.addMouseListener(this);
		
		jrb2 = new JRadioButton("院系教务老师");
		jrb2.setBounds(180 , 30 , 100 ,25);
		jrb2.addMouseListener(this);
		
		jrb3 = new JRadioButton("教师");
		jrb3.setBounds(180 , 60 , 100 ,25);
		jrb3.addMouseListener(this);
		
		jrb4 = new JRadioButton("学生");
		jrb4.setBounds(180 , 90 , 100 ,25);
		jrb4.addMouseListener(this);
		
		group = new ButtonGroup();
		group.add(jrb1);
		group.add(jrb2);
		group.add(jrb3);
		group.add(jrb4);
		
		selectingJP.add(userType);
		selectingJP.add(jrb1);
		selectingJP.add(jrb2);
		selectingJP.add(jrb3);
		selectingJP.add(jrb4);
		
		this.add(selectingJP);
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == jrb1){
			type = "教务处老师";
			infoJP.initDean();
		}
		else if(e.getSource() == jrb2){
			type = "院系教务老师";
			infoJP.initFaculty();
		}
		else if(e.getSource() == jrb3){
			type = "教师";
			infoJP.initTeacher();
		}
		else if(e.getSource() == jrb4){
			type = "学生";
			infoJP.initStudent();
			
		}
		else if(e.getSource() == sureButton){
			if(group.getSelection() == null){
				JOptionPane.showMessageDialog(this, "没有选择一个用户类型");
				return ;
			}
			else {
				UserVO uv = infoJP.getInfomation();
				if(uv == null){
					return ;
				}
				else {
					String backInfo = admin.RegisterUser(uv);
					JOptionPane.showMessageDialog(this, backInfo);
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
