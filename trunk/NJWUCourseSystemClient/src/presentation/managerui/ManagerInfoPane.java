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
import presentation.deanui.DeanUIImage;
import presentation.tools.OutputHelper;
import presentation.tools.Setter;
import presentation.tools.ViewReplyMessage;
import vo.managervo.ManagerVO;
import businesslogic.managerbl.Manager;

public class ManagerInfoPane implements ViewReplyMessage,DeanUIImage{
	JTextField tf3;//个人联系方式的文本框
	Manager m;
	
	Setter setter=new Setter();
	
	public ManagerInfoPane(String id){
		m=new Manager(id);
	}
	
	public JPanel getManagerInfoPane(JButton return_b){	
		JPanel infoPane = new JPanel(new MigLayout());
		infoPane.setOpaque(false);
		
		JPanel p = getMainPane();
		
		JPanel p1=new JPanel(new MigLayout());
		p1.setOpaque(false);
		JButton saveButton=new JButton("保存");
		saveButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				m.setContactInfo(tf3.getText());
				tf3.setEditable(false);
				new OutputHelper().outputToDialog(INFO_CHANGE);
			}			
		});
		setter.setButtonUnOpaque(return_b);
		return_b.setToolTipText("返回");
		p1.add(saveButton);
		p1.add(return_b,"gapleft 150");
		
		infoPane.add(getEditButton(),"gaptop 30,gapleft 560,wrap");
		infoPane.add(p,"gapleft 150,gaptop 10,wrap");
		infoPane.add(p1,"gapleft 320,gaptop 10,gapbottom 50");
		
		return infoPane;
	}
	
	public JPanel getManagerInfoPane(){
		JPanel infoPane=new JPanel(new MigLayout());
		infoPane.setOpaque(false);

		JPanel p=getMainPane();
		
		infoPane.add(getEditButton(),"gaptop 30,gapleft 560,wrap");
		infoPane.add(p,"gapleft 150,gaptop 10,wrap");
		infoPane.add(getSaveButton(),"gapleft 320,gaptop 10,gapbottom 50");
		return infoPane;
	}

	public JPanel getMainPane() {
		JPanel p2=new JPanel(new MigLayout());
		p2.setOpaque(false);
		
		ImageIcon icon1=new ImageIcon(personalInfo);
		JLabel iconLabel=new JLabel(icon1);
	
		JPanel p = new JPanel(new MigLayout());
		ManagerVO mv = m.getManagerInfo();

		JLabel label1 = new JLabel("用户名");
		JTextField tf1 = new JTextField(10);
		tf1.setText(mv.getID());
		tf1.setEditable(false);

		JLabel label2 = new JLabel("姓名");
		JTextField tf2 = new JTextField(10);
		tf2.setText(mv.getName());
		tf2.setEditable(false);

		JLabel label3 = new JLabel("联系方式");
		tf3 = new JTextField(16);
		tf3.setText(mv.getContactInfo());
		tf3.setEditable(false);

		ImageIcon icon0 = new ImageIcon(editButton);
		JButton editButton = new JButton(icon0);
		setter.setButtonUnOpaque(editButton);
		editButton.setToolTipText("编辑");
		editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tf3.setEditable(true);
			}
		});
		
		p.setOpaque(false);
		JLabel l = new JLabel("个人信息");
		l.setFont(new Font("华文楷体", Font.BOLD, 12));
		p.add(l, "gapleft 15,wrap");
		p.add(new JSeparator(), "growx");
		p.add(new JSeparator(), "growx,wrap");
		p.add(label1, "gapleft 15,gaptop 5");
		p.add(tf1, "wrap,gapleft 25,gaptop 5");
		p.add(label2, "gapleft 15,gaptop 5");
		p.add(tf2, "wrap,gapleft 25,gaptop 5");
		
		// 若是院系教务老师，个人信息里多一项为院系名
		if (mv.getFacultyName() != null) {
			JLabel label4 = new JLabel("院系名");
			JTextField tf4 = new JTextField(10);
			tf4 = new JTextField(10);
			tf4.setText(mv.getFacultyName());
			tf4.setEditable(false);
			p.add(label4, "gapleft 15,gaptop 5");
			p.add(tf4, "gapleft 25,gaptop 5,wrap");
		}

		p.add(label3, "gapleft 15,gaptop 5");
		p.add(tf3, "gapleft 25,gaptop 5,wrap");
		p.add(new JSeparator(), "growx,gaptop 15");
		p.add(new JSeparator(), "growx,gaptop 15,wrap");
		
		p2.add(iconLabel);
		p2.add(p,"gapleft 15");

		return p2;
	}
	
	public JButton getEditButton(){
		ImageIcon icon0=new ImageIcon(editButton);
		JButton editButton=new JButton(icon0);
		setter.setButtonUnOpaque(editButton);
		editButton.setToolTipText("编辑");
		editButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				tf3.setEditable(true);
			}					
		});
		return editButton;
	}
	
	public JButton getSaveButton(){
		JButton saveButton=new JButton("保存");
		saveButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				m.setContactInfo(tf3.getText());
				tf3.setEditable(false);
			}			
		});		
		return saveButton;
	}
}
