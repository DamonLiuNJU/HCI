package presentation.studentui;

import java.awt.FlowLayout;
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
		mainpanel.setLayout(new FlowLayout(0,15,20));
		
//		Dimension textfieldsize=new Dimension(60,20);
		int textfieldlength=10;
		
		JLabel  label1=new JLabel("姓名");
		final JTextField name=new JTextField(textfieldlength);
		JLabel  label2=new JLabel("学号");
		final JTextField studentnumber=new JTextField(textfieldlength);
		JLabel  label3=new JLabel("电话号码");
		final JTextField phone=new JTextField(textfieldlength);
//		JTextField birthdate=new JTextField();
		JLabel  label4=new JLabel("家庭住址");
		final JTextField homeaddress=new JTextField(textfieldlength);
		JLabel  label5=new JLabel("母亲姓名");
		final JTextField mother=new JTextField(textfieldlength);
		JLabel  label6=new JLabel("母亲电话");
		final JTextField motherphone=new JTextField(textfieldlength);
		JLabel  label7=new JLabel("父亲姓名");
		final JTextField father=new JTextField(textfieldlength);
		JLabel  label8=new JLabel("父亲电话");
		final JTextField fatherphone=new JTextField(textfieldlength);
		final JTextField contactinfo = new JTextField(textfieldlength);
		JLabel label9 = new JLabel("联系方式");
		
//		name.setSize(60,20);
//		studentnumber.setSize(textfieldsize);
//		phone.setSize(textfieldsize);
//		homeaddress.setSize(textfieldsize);
//		mother.setSize(textfieldsize);
//		motherphone.setSize(textfieldsize);
//		father.setSize(textfieldsize);
//		fatherphone.setSize(textfieldsize);
		
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
		
		JButton showpersoninfo=new JButton("显示个人信息");
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
		
//		showpersoninfo.setBounds(10, 60, 60, 20);
//		modify.setBounds(10, 80, 60, 20);
//		new Setter().addBackground(frame, backPath)
		Tool.setOpaque(mainpanel);
		return mainpanel;
	}

}
