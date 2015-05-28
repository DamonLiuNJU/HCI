package presentation.studentui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import vo.studentvo.StudentInfoVO;
import businesslogic.studentbl.StudentInfo;
import businesslogicservice.studentblservice.StudentInfoBLService;

public class PersonalInfoPanel {

	/**
	 * @param args
	 */
//	public static void main(String[] args) {
//		// TODO 自动生成的方法存根
//
//	}

	public JPanel getMainPanel(final String student_id) {
		// TODO 自动生成的方法存根
		JPanel mainpanel=new JPanel();
		mainpanel.setOpaque(false);
//		mainpanel.setLayout(new FlowLayout(0,15,20));
		mainpanel.setLayout(null);
		
//		Dimension textfieldsize=new Dimension(60,20);
		int textfieldlength=200;
		
		JLabel  label1=new JLabel("姓      名 : ");
		final JTextField name=new JTextField(textfieldlength);
		JLabel  label2=new JLabel("学      号 : ");
		final JTextField studentnumber=new JTextField(textfieldlength);
		JLabel  label3=new JLabel("电话号码 : ");
		final JTextField phone=new JTextField(textfieldlength);
//		JTextField birthdate=new JTextField();
		JLabel  label4=new JLabel("家庭住址 : ");
		final JTextField homeaddress=new JTextField(textfieldlength);
		JLabel  label5=new JLabel("母亲姓名 : ");
		final JTextField mother=new JTextField(textfieldlength);
		JLabel  label6=new JLabel("母亲电话 : ");
		final JTextField motherphone=new JTextField(textfieldlength);
		JLabel  label7=new JLabel("父亲姓名 : ");
		final JTextField father=new JTextField(textfieldlength);
		JLabel  label8=new JLabel("父亲电话 : ");
		final JTextField fatherphone=new JTextField(textfieldlength);
		final JTextField contactinfo = new JTextField(textfieldlength);
		JLabel label9 = new JLabel("联系方式 : ");
		
//		name.setSize(60,20);
//		studentnumber.setSize(textfieldsize);
//		phone.setSize(textfieldsize);
//		homeaddress.setSize(textfieldsize);
//		mother.setSize(textfieldsize);
//		motherphone.setSize(textfieldsize);
//		father.setSize(textfieldsize);
//		fatherphone.setSize(textfieldsize);
		int height = 30 ; 
		studentnumber.setEditable(false);
		name.setEditable(false);
		mainpanel.add(label1);
		mainpanel.add(name);
		mainpanel.add(label2);
		mainpanel.add(studentnumber);
		mainpanel.add(label3);
		mainpanel.add(phone);
		mainpanel.add(label4);
		mainpanel.add(homeaddress);
		mainpanel.add(label5);
		mainpanel.add(mother);
		mainpanel.add(label6);
		mainpanel.add(motherphone);
		mainpanel.add(label7);
		mainpanel.add(father );
		mainpanel.add(label8);
		mainpanel.add(fatherphone);
		mainpanel.add(label9);
		mainpanel.add(contactinfo);
		
		int gap = 90;
		label1.setLocation(40, 10);   name.setBounds(10+gap, 10, textfieldlength, height);
		label2.setLocation(40, 50);	studentnumber.setBounds(10+gap, 50, textfieldlength, height);
		label3.setLocation(40, 90);	phone.setBounds(10+gap, 90, textfieldlength, height);
		label4.setLocation(40, 130);	homeaddress.setBounds(10+gap, 130, textfieldlength, height);
		label5.setLocation(40, 170);	mother.setBounds(10+gap, 170, textfieldlength, height);
		label6.setLocation(40, 210);	motherphone.setBounds(10+gap, 210, textfieldlength, height);
		label7.setLocation(40, 250);	father.setBounds(10+gap, 250, textfieldlength, height);
		label8.setLocation(40, 290);	fatherphone.setBounds(10+gap, 290, textfieldlength, height);
		label9.setLocation(40, 330);	contactinfo.setBounds(10+gap, 330, textfieldlength, height);
		
		
		label1.setSize(textfieldlength, height);
		label2.setSize(textfieldlength, height);
		label3.setSize(textfieldlength, height);
		label4.setSize(textfieldlength, height);
		label5.setSize(textfieldlength, height);
		label6.setSize(textfieldlength, height);
		label7.setSize(textfieldlength, height);
		label8.setSize(textfieldlength, height);
		label9.setSize(textfieldlength, height);
		
		
		
		
		
		
		
		
		JButton showpersoninfo=new JButton("显示信息");
		Tool.setIcon(Tool.find, showpersoninfo);
		showpersoninfo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				StudentInfoBLService si = new StudentInfo();
				StudentInfoVO vo = si.getPersonInfo(student_id);
				name.setText(vo.getName());
				studentnumber.setText(vo.getID());
				homeaddress.setText(vo.getHomeAddress());
				phone.setText(vo.getPhone());
				mother.setText(vo.getMotherName());
				motherphone.setText(vo.getMotherPhone());
				father.setText(vo.getFatherName());
				fatherphone.setText(vo.getFatherPhone());
				contactinfo .setText(vo.getContactInfo());
			
			}
		});
		JButton modify =new JButton("提交修改");
		Tool.setIcon(Tool.correct, modify);
		modify.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				
				ArrayList<String> info = new ArrayList<String>();
				info.add(studentnumber.getText());
				info.add(contactinfo.getText());
				info.add(phone.getText());
				info.add(homeaddress.getText());
				info.add(mother.getText());
				info.add(motherphone.getText());
				info.add(father.getText());
				info.add(fatherphone.getText());	
				StudentInfoBLService si = new StudentInfo(info);
				si.update();
				JOptionPane.showMessageDialog(null, "修改成功");
			}
		});
		mainpanel.add(showpersoninfo);
		mainpanel.add(modify);
		
		showpersoninfo.setBounds(40, 370, 100, height);
		modify.setBounds(40+100+10, 370, textfieldlength, height);
		
//		showpersoninfo.setBounds(10, 60, 60, 20);
//		modify.setBounds(10, 80, 60, 20);
//		new Setter().addBackground(frame, backPath)
		Tool.setOpaque(mainpanel);
		return mainpanel;
	}
	
	public static void main(String args[]){
		
	}

}
