package presentation.adminui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import presentation.planui.PlanList;
import vo.adminvo.UserVO;

/*
 * 该类为用户信息的界面，里面有注册或更新是需要填写的消息记录
 * 该类被UpdatePane和RegisterPane使用
 */
public class UserInfoPane extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JTextField[] jtf ;//输入和输出的信息都在这个jtf数组中
	JComboBox<String> comboBox;
	JLabel faculty;
	String type;

	public UserInfoPane(){
		this.draw();
	}
	
	private void draw(){
		this.setLayout(null);
		this.setBounds( 50 , 180 , 800 , 250);
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY,2),"用户信息"
                ,TitledBorder.LEFT,TitledBorder.TOP));
		faculty = new JLabel("院系名称");
		comboBox = new PlanList().getFacultyComboBox();
	}
	
	/*
	 * 初始化Dean的信息界面
	 */
	public void initDean(){
		this.removeAll();
		this.setBorderTitle("教务处老师信息");
		this.type = "Manager";
		jtf = new JTextField[4];
		for(int i=0; i<jtf.length ; i++){
			jtf[i] = new JTextField();
			jtf[i].setOpaque(false);
		}
		jtf[0].setEditable(false);
		
		JLabel id = new JLabel("工号：");
		this.setOneInfoPane(id, 0, 20, 20 );
		
		JLabel password = new JLabel("密码：");
		this.setOneInfoPane(password, 1, 400, 20);
		
		JLabel name = new JLabel("姓名：");
		this.setOneInfoPane(name, 2, 20, 60);
		
		JLabel contactInfo = new JLabel("联系方式：");
		this.setOneInfoPane(contactInfo, 3, 20, 100);
	
	}
	
	/*
	 * 初始化faculty的信息界面
	 */
	public void initFaculty(){
		this.removeAll();
		this.setBorderTitle("院系教务老师信息");
		this.type = "Manager";
		jtf = new JTextField[4];
		for(int i=0; i<jtf.length ; i++){
			jtf[i] = new JTextField();
			jtf[i].setOpaque(false);
		}
		jtf[0].setEditable(false);
		
		JLabel id = new JLabel("工号：");
		this.setOneInfoPane(id, 0, 20, 20 );
		
		JLabel password = new JLabel("密码：");
		this.setOneInfoPane(password, 1, 400, 20);
		
		JLabel name = new JLabel("姓名：");
		this.setOneInfoPane(name, 2, 20, 60);
		
		JLabel contactInfo = new JLabel("联系方式：");
		this.setOneInfoPane(contactInfo, 3, 20, 100);

		faculty.setBounds(400 , 60 , 70 , 25);

		comboBox.setBounds(480 , 60 , 150 , 25);
		if(comboBox.getActionListeners().length > 0){
		comboBox.getActionListeners()[0].actionPerformed(null);
		}
	
		this.add(faculty);
		this.add(comboBox);
	}
	
	//初始化教师信息，其中包含id , password, name , faculty , seniority , contactInfo信息。
	public void initTeacher(){
		this.removeAll();
		this.setBorderTitle("教师信息");
		this.type = "Teacher";
		jtf = new JTextField[5];
		for(int i=0; i<jtf.length ; i++){
			jtf[i] = new JTextField();
			jtf[i].setOpaque(false);
		}
		jtf[0].setEditable(false);
		
		JLabel id = new JLabel("工号：");
		this.setOneInfoPane(id, 0, 20, 20 );
		
		JLabel password = new JLabel("密码：");
		this.setOneInfoPane(password, 1, 400, 20);
		
		JLabel name = new JLabel("姓名：");
		this.setOneInfoPane(name, 2, 20, 60);
		
		JLabel seniority = new JLabel("职称：");
		this.setOneInfoPane(seniority, 3, 20, 100);
		
		JLabel contactInfo = new JLabel("联系方式：");
		this.setOneInfoPane(contactInfo, 4, 400, 100);

		faculty.setBounds(400 , 60 , 70 , 25);

		comboBox.setBounds(480 , 60 , 150 , 25);
		if(comboBox.getActionListeners().length > 0){
		comboBox.getActionListeners()[0].actionPerformed(null);
		}
	

		this.add(faculty);
		this.add(comboBox);
		
	}
	
	/*
	 * 初始化student的信息界面
	 */
	public void initStudent(){
		this.removeAll();
		this.setBorderTitle("学生信息");
		this.type = "Student";
		jtf = new JTextField[11];
		for(int i=0; i<jtf.length ; i++){
			jtf[i] = new JTextField();
			jtf[i].setOpaque(false);
		}
		jtf[0].setEditable(false);
		
		JLabel id = new JLabel("学号：");
		this.setOneInfoPane(id, 0, 20, 20);
		
		JLabel password = new JLabel("密码:");
		this.setOneInfoPane(password, 1, 280, 20);
		
		JLabel name = new JLabel("姓名：");
		this.setOneInfoPane(name, 2, 540, 20);
		
		JLabel grade = new JLabel("入学年度：");
		this.setOneInfoPane(grade, 3, 280, 60);
		
		JLabel contactInfo = new JLabel("联系方式：");
		this.setOneInfoPane(contactInfo, 4, 540, 60);
		
		JLabel phone = new JLabel("电话号码：");
		this.setOneInfoPane(phone, 5, 20, 100);
		
		JLabel homeAddress = new JLabel("家庭住址：");
		this.setOneInfoPane(homeAddress, 6, 280, 100);
		
		JLabel motherName = new JLabel("母亲姓名：");
		this.setOneInfoPane(motherName, 7, 540, 100);
		
		JLabel motherPhone = new JLabel("母亲电话：");
		this.setOneInfoPane(motherPhone, 8, 20, 140);
		
		JLabel fatherName = new JLabel("父亲姓名：");
		this.setOneInfoPane(fatherName, 9, 280, 140);
		
		JLabel fatherPhone = new JLabel("父亲电话：");
		this.setOneInfoPane(fatherPhone, 10, 540, 140);

		faculty.setBounds(20 , 60 , 70 , 25);

		comboBox.setBounds(100 , 60 , 150 , 25);
		if(comboBox.getActionListeners().length > 0){
		comboBox.getActionListeners()[0].actionPerformed(null);
		}
	

		this.add(faculty);
		this.add(comboBox);
	}
	
	
	//设置标题
	public void setBorderTitle(String title){
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY,2),title
                ,TitledBorder.LEFT,TitledBorder.TOP));
	}
	
	/*
	 * 将userVO的信息显示在界面上
	 */
	public void showInfo(UserVO uv){
		String type = uv.getType();
		if(type.equals("Teacher")){
			this.showTeacher(uv);
		}
		else if(type.equals("Student")){
			this.showStudent(uv);
		}
		else if(type.equals("Manager")){
			this.showManager(uv);
		}
		jtf[0].setEditable(false);
	}
	
	//使userinfoPane中的jtf可编辑或不可
	public void setEditable(boolean b){
		for(int i=1 ; i<jtf.length ; i++){
			jtf[i].setEditable(b);
		}
		comboBox.setEnabled(b);
	}
	
	//获取填写的用户信息，返回一个userVO
	public UserVO getInfomation(){
		if(this.check()){//判断信息是否填写完全
			
			if(type.equals("Manager")){
				return this.getManagerInfo();
			}
			else if(type.equals("Teacher")){
				return this.getTeacherInfo();
			}
			else if(type.equals("Student")){
				return this.getStudentInfo();
			}
			return null;
		}
		JOptionPane.showMessageDialog(this, "有信息未填写");
		return null;
	}
	
	//初始化一组组件（一个JLabel加上一个JTextFiled） ， name 是JLabel的标题 x,y为label的初始坐标
	private void setOneInfoPane(JLabel label , int index , int x , int y){
		label.setBounds(x , y , 70 ,25);
		jtf[index].setBounds(x+80 , y , 150 ,25);
		
		this.add(label);
		this.add(jtf[index]);
	}
	
	//确认是否填写完全信息
	private boolean check(){
		for(int i=0; i<jtf.length;i++){
			if((jtf[i].getText().equals(""))){
				return false ;
			}
		}
		return true ;
	}
	
	//将userVO的信息显示在jtf中
	private void showTeacher(UserVO uv){
		this.initTeacher();
		String faculty = uv.getFaculty_name();
		this.setComboBox(faculty);
		jtf[0].setText(uv.getId());
		jtf[1].setText(uv.getPassword());
		jtf[2].setText(uv.getName());
		jtf[3].setText(uv.getSeniority());
		jtf[4].setText(uv.getContactInfo());
		this.setEditable(false);
	}
	
	//将userVO的信息显示在jtf中
	private void showStudent(UserVO uv){
		this.initStudent();
		String faculty = uv.getFaculty_name();
		this.setComboBox(faculty);
		jtf[0].setText(uv.getId());
		jtf[1].setText(uv.getPassword());
		jtf[2].setText(uv.getName());
		jtf[3].setText(uv.getGrade());
		jtf[4].setText(uv.getContactInfo());
		jtf[5].setText(uv.getPhone());
		jtf[6].setText(uv.getHomeAddress());
		jtf[7].setText(uv.getMotherName());
		jtf[8].setText(uv.getMotherPhone());
		jtf[9].setText(uv.getFatherName());
		jtf[10].setText(uv.getFatherPhone());
		this.setEditable(false);
	}
	
	//将userVO的信息显示在jtf中
	private void showManager(UserVO uv){
		String id = uv.getId();
		char[] temp = id.toCharArray();
		if(temp.length == 6){
			this.initFaculty();
			String faculty = uv.getFaculty_name();
			this.setComboBox(faculty);
		}
		else if(temp[0] == '0'){
			this.initDean();
			this.setBorderTitle("管理员信息");
		}
		else {
			this.initDean();
		}
		
		jtf[0].setText(uv.getId());
		jtf[1].setText(uv.getPassword());
		jtf[2].setText(uv.getName());
		jtf[3].setText(uv.getContactInfo());
		this.setEditable(false);
	}
	
	private void setComboBox(String faculty_name){
		comboBox.setSelectedItem(faculty_name);
	}
	
	private UserVO getManagerInfo(){
		UserVO uv = new UserVO("Manager");
		uv.setId(jtf[0].getText());
		uv.setPassword(jtf[1].getText());
		uv.setName(jtf[2].getText());
		uv.setFaculty_name((String)comboBox.getSelectedItem());
		uv.setContactInfo(jtf[3].getText());
		return uv;
	}
	
	private UserVO getTeacherInfo(){
		UserVO uv = new UserVO("Teacher");
		uv.setId(jtf[0].getText());
		uv.setPassword(jtf[1].getText());
		uv.setName(jtf[2].getText());
		uv.setFaculty_name((String)comboBox.getSelectedItem());
		uv.setSeniority(jtf[3].getText());
		uv.setContactInfo(jtf[4].getText());
		return uv;
	}
	
	private UserVO getStudentInfo(){
		UserVO uv = new UserVO("Student");
		uv.setId(jtf[0].getText());
		uv.setPassword(jtf[1].getText());
		uv.setName(jtf[2].getText());
		uv.setFaculty_name((String)comboBox.getSelectedItem());
		uv.setGrade(jtf[3].getText());
		uv.setContactInfo(jtf[4].getText());
		uv.setPhone(jtf[5].getText());
		uv.setHomeAddress(jtf[6].getText());
		uv.setMotherName(jtf[7].getText());
		uv.setMotherPhone(jtf[8].getText());
		uv.setFatherName(jtf[9].getText());
		uv.setFatherPhone(jtf[10].getText());
		return uv;
	}
	
}
