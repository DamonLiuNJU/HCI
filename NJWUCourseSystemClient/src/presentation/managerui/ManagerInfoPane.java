package presentation.managerui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import presentation.tools.Setter;
import vo.managervo.ManagerVO;
import businesslogic.managerbl.Manager;

@SuppressWarnings("serial")
public class ManagerInfoPane extends JPanel{
	JTextField tf3;//个人联系方式的文本框
	Manager m;
	
	Setter setter=new Setter();
	
	public ManagerInfoPane(String id,JButton return_b){	
		this.setLayout(new MigLayout());
		this.setOpaque(false);
		m=new Manager(id);
		ManagerVO mv=m.getManagerInfo();
		
		JLabel label1=new JLabel("用户名");
		JTextField tf1=new JTextField(10);				
		tf1.setText(mv.getID());
		tf1.setEditable(false);
		
		JLabel label2=new JLabel("姓名");		
		JTextField tf2=new JTextField(10);
		tf2.setText(mv.getName());
		tf2.setEditable(false);
		
		JLabel label3=new JLabel("联系方式");
		tf3=new JTextField(16);
		tf3.setText(mv.getContactInfo());
		tf3.setEditable(false);
		
		ImageIcon icon0=new ImageIcon("./icon/pen4.png");
		JButton editButton=new JButton(icon0);
		setter.setButtonUnOpaque(editButton);
		editButton.setToolTipText("编辑");
		editButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				tf3.setEditable(true);
			}					
		});
		
		JPanel p1=new JPanel(new MigLayout());
		p1.setOpaque(false);
		JButton saveButton=new JButton("保存");
		saveButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				m.setContactInfo(tf3.getText());
				tf3.setEditable(false);
			}			
		});
		p1.add(saveButton);
		setter.setButtonUnOpaque(return_b);
		return_b.setToolTipText("返回");
		p1.add(return_b,"gapleft 150");
		
		JPanel p=new JPanel(new MigLayout());
		p.setOpaque(false);
		JLabel l=new JLabel("个人信息");
		l.setFont(new Font("华文楷体", Font.BOLD, 12));
		p.add(l,"gapleft 15,wrap");
		p.add(new JSeparator(),"growx");
		p.add(new JSeparator(),"growx,wrap");
		p.add(label1,"gapleft 15,gaptop 5");
		p.add(tf1,"wrap,gapleft 25,gaptop 5");
		p.add(label2,"gapleft 15,gaptop 5");
		p.add(tf2,"wrap,gapleft 25,gaptop 5");
		
		//若是院系教务老师，个人信息里多一项为院系名
		if(mv.getFacultyName()!=null){
			JLabel label4=new JLabel("院系名");
			JTextField tf4=new JTextField(10);
			tf4=new JTextField(10);
			tf4.setText(mv.getFacultyName());
			tf4.setEditable(false);
			p.add(label4,"gapleft 15,gaptop 5");
			p.add(tf4,"gapleft 25,gaptop 5,wrap");
		}
		
		p.add(label3,"gapleft 15,gaptop 5");
		p.add(tf3,"gapleft 25,gaptop 5,wrap");
		p.add(new JSeparator(),"growx,gaptop 15");
		p.add(new JSeparator(),"growx,gaptop 15,wrap");
		
		JPanel p2=new JPanel(new MigLayout());
		p2.setOpaque(false);
		ImageIcon icon1=new ImageIcon("./icon/info2.png");
		JLabel iconLabel=new JLabel(icon1);
		p2.add(iconLabel);
		p2.add(p,"gapleft 15");
		
		
		this.add(editButton,"gaptop 30,gapleft 560,wrap");
		this.add(p2,"gapleft 150,gaptop 10,wrap");
		this.add(p1,"gapleft 320,gaptop 10,gapbottom 50");
	}
}
