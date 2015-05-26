package presentation.statusui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import vo.statusvo.CourseStatusVO;
import vo.statusvo.ManageStatusVO;
import businesslogic.statusbl.BoundDate;
import businesslogic.statusbl.CourseStatus;
import businesslogic.statusbl.FrameStatus;
import businesslogic.statusbl.ManageStatus;
import businesslogic.statusbl.PlanStatus;
import businesslogic.statusbl.PublishCourseStatus;
import businesslogic.statusbl.Quit_AddCourseStatus;
import businesslogic.statusbl.RecordScoreStatus;
import businesslogic.statusbl.SelectCourseStatus;
import businesslogic.statusbl.Status;
import businesslogic.utilitybl.CourseModule;

public class AdminStatusPane {

	JTextField[] jtf;
	JButton edit, save;

	/*
	 * 该方法初始化了admin界面中的系统状态界面 有4个系统状态可供查看和修改，分别为： 框架策略的创建和修改 ， 课程的申请和发布，
	 * 教学计划的创建的修改 ，选课的补选和退选
	 */
	public void setStatusPane(JPanel jp) {

		jtf = new JTextField[22];
		for (int i = 0; i < jtf.length; i++) {
			jtf[i] = new JTextField();
		}

		JLabel managerLabel = new JLabel("教务处状态");
		managerLabel.setBounds(40, 20, 200, 30);

		JLabel courseLabel = new JLabel("课程状态");
		courseLabel.setBounds(40, 125, 200, 30);

		jp.add(managerLabel);
		jp.add(courseLabel);

		this.getOnePair(jp, "框架策略", 0);
		this.getOnePair(jp, "教学计划", 1);

		JPanel required = this.setCourseModulePane(CourseModule.必修课);
		required.setLocation(40, 135);

		JPanel optional = this.setCourseModulePane(CourseModule.选修课);
		optional.setLocation(40, 260);

		JPanel tongshike = this.setCourseModulePane(CourseModule.通识课);
		tongshike.setLocation(470, 10);

		JPanel gongxuanke = this.setCourseModulePane(CourseModule.公选课);
		gongxuanke.setLocation(470, 135);

		JPanel PE = this.setCourseModulePane(CourseModule.体育课);
		PE.setLocation(470, 260);

		this.setManagerInfo(new ManageStatus().getTimeList());
		this.setCourseInfo(new CourseStatus().getTimeList());
		jtf[3].setEnabled(false);
		jtf[4].setEnabled(false);
		jtf[20].setEnabled(false);
		// 初始时不能编辑
		this.setEditable(false);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(null);
		buttonPane.setBounds(40, 380, 870, 70);

		MyActionListener myActionListener = new MyActionListener();

		edit = new JButton("编辑");
		edit.setBounds(20, 5, 70, 25);
		edit.addActionListener(myActionListener);

		save = new JButton("保存");
		save.setBounds(780, 5, 70, 25);
		save.addActionListener(myActionListener);

		buttonPane.add(edit);
		buttonPane.add(save);

		jp.add(buttonPane);
		jp.add(required);
		jp.add(optional);
		jp.add(tongshike);
		jp.add(gongxuanke);
		jp.add(PE);
	}

	// 设置jtf中的内容
	public void setManagerInfo(ManageStatusVO mvs) {
		ArrayList<String> manageArray = this.toMyList(mvs.getTimeList());
		for (int i = 0; i < 2; i++) {
			jtf[i].setText(manageArray.get(i));
		}
	}

	// 设置jtf中的内容
	public void setCourseInfo(CourseStatusVO csv) {
		ArrayList<String> courseArray = this.toMyList(csv.getTimeList());
		for (int i = 0; i < 20; i++) {
			jtf[i + 2].setText(courseArray.get(i));
		}
	}

	// 将temp中的‘-’转化为‘.’
	private ArrayList<String> toMyList(ArrayList<String> temp) {
		ArrayList<String> myArray = new ArrayList<String>();
		for (int i = 0; i < temp.size(); i++) {
			String info = temp.get(i).replaceAll("-", ".");
			info = info.replace(" ", "-");
			myArray.add(info);
		}

		return myArray;
	}

	// 保存修改后的状态信息
	private void saveInfo() {
		ArrayList<String> timeArray = this.getInfoFromJTF();
		Status frame = new FrameStatus();
		this.saveStatus(frame, timeArray.get(0));
		Status plan = new PlanStatus();
		this.saveStatus(plan, timeArray.get(1));
		this.saveCourseModuleInfo(CourseModule.必修课, timeArray);
		this.saveCourseModuleInfo(CourseModule.选修课, timeArray);
		this.saveCourseModuleInfo(CourseModule.通识课, timeArray);
		this.saveCourseModuleInfo(CourseModule.公选课, timeArray);
		this.saveCourseModuleInfo(CourseModule.体育课, timeArray);

	}

	// 获取jtf中所有的String，加工后以ArrayList的方式返回
	private ArrayList<String> getInfoFromJTF() {
		ArrayList<String> temp = new ArrayList<String>();
		for (int i = 0; i < jtf.length; i++) {
//			if((i==3)||(i==4)||(i==20)){
//				temp.add("--");
//				continue;
//			}
			temp.add(jtf[i].getText());
		}

		return temp;
	}

	// 将status更新，time格式为：“11.12-11.15”前面为开始时间，后面为结束时间
	private void saveStatus(Status status, String time) {
		time = time.trim();
		time= time.replaceAll(" ","");
		String temp[] = time.split("-");
		System.out.println(time);
		for (int i = 0; i < temp.length; i++) {
			temp[i] = temp[i].replace(".", "-");
			System.out.println(temp[i]);
		}
		BoundDate bd1 = new BoundDate(temp[0]);
		BoundDate bd2 = new BoundDate(temp[1]);

		status.setTime(bd1, bd2);
	}

	// 保存一个course模块的信息
	private void saveCourseModuleInfo(CourseModule module,
			ArrayList<String> timeArray) {
		int index = this.judgeModule(module);
		if(module == CourseModule.体育课){
			Status publish = new PublishCourseStatus(module);
			this.saveStatus(publish, timeArray.get(2+(index * 4)));
			Status select = new SelectCourseStatus(module);
			this.saveStatus(select, timeArray.get(3 + (index * 4)));
			Status record = new RecordScoreStatus(module);
			this.saveStatus(record, timeArray.get(5 + (index * 4)));
		}
		else if(module == CourseModule.公选课){
			Status publish = new PublishCourseStatus(module);
			this.saveStatus(publish, timeArray.get(2+(index * 4)));
			Status select = new SelectCourseStatus(module);
			this.saveStatus(select, timeArray.get(3 + (index * 4)));
			Status quit = new Quit_AddCourseStatus(module);
			this.saveStatus(quit, timeArray.get(4 + (index * 4)));
			Status record = new RecordScoreStatus(module);
			this.saveStatus(record, timeArray.get(5 + (index * 4)));
		}
		else if(module == CourseModule.必修课){
			Status publish = new PublishCourseStatus(module);
			this.saveStatus(publish, timeArray.get(2+(index * 4)));
			Status record = new RecordScoreStatus(module);
			this.saveStatus(record, timeArray.get(5 + (index * 4)));
		}
		else if(module == CourseModule.选修课){
			Status publish = new PublishCourseStatus(module);
			this.saveStatus(publish, timeArray.get(2+(index * 4)));
			Status select = new SelectCourseStatus(module);
			this.saveStatus(select, timeArray.get(3 + (index * 4)));
			Status quit = new Quit_AddCourseStatus(module);
			this.saveStatus(quit, timeArray.get(4 + (index * 4)));
			Status record = new RecordScoreStatus(module);
			this.saveStatus(record, timeArray.get(5 + (index * 4)));
		}
		else if(module == CourseModule.通识课){
			Status publish = new PublishCourseStatus(module);
			this.saveStatus(publish, timeArray.get(2+(index * 4)));
			Status select = new SelectCourseStatus(module);
			this.saveStatus(select, timeArray.get(3 + (index * 4)));
			Status quit = new Quit_AddCourseStatus(module);
			this.saveStatus(quit, timeArray.get(4 + (index * 4)));
			Status record = new RecordScoreStatus(module);
			this.saveStatus(record, timeArray.get(5 + (index * 4)));
		}
		
	}


	/*
	 * 设置一对组件，一个label加上一个JTextFiled，title就是label显示的内容
	 * part是判断该组组件属于哪一个板块（part=0是manager板块，part=1 是course板块）
	 * index表明了该组组件是第几个组件（在整个panel中的计数）
	 */
	private void getOnePair(JPanel jp, String title, int index) {
		JLabel label = new JLabel(title);
		label.setBounds(60, 55 + index * 40, 70, 30);
		jtf[index].setBounds(140, 55 + index * 40, 150, 30);

		jp.add(label);
		jp.add(jtf[index]);
	}

	// 初始化界面时，设置一个coursemodule的界面，里面有模块的名称和4个时间状态
	private JPanel setCourseModulePane(CourseModule module) {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setSize(400, 120);

		int index = judgeModule(module);

		JLabel label = new JLabel(module.name());
		label.setBounds(20, 15, 70, 30);

		setModules(panel, index);

		panel.add(label);
		return panel;
	}

	// index为第几个course模块
	private void setModules(JPanel jp, int index) {
		JLabel publish = new JLabel("发布");
		publish.setBounds(40, 50, 50, 30);
		jtf[index * 4 + 2].setBounds(100, 50, 100, 30);
		JLabel select = new JLabel("选择");
		select.setBounds(210, 50, 50, 30);
		jtf[index * 4 + 3].setBounds(270, 50, 100, 30);
		JLabel quit = new JLabel("退选");
		quit.setBounds(40, 90, 50, 30);
		jtf[index * 4 + 4].setBounds(100, 90, 100, 30);
		JLabel record = new JLabel("登记成绩");
		record.setBounds(210, 90, 50, 30);
		jtf[index * 4 + 5].setBounds(270, 90, 100, 30);

		jp.add(publish);
		jp.add(select);
		jp.add(quit);
		jp.add(record);
		jp.add(jtf[index * 4 + 2]);
		jp.add(jtf[index * 4 + 3]);
		jp.add(jtf[index * 4 + 4]);
		jp.add(jtf[index * 4 + 5]);

	}

	// 判断模块的序数，返回一个int值，用于将模块与响应的JTextField对应起来
	private int judgeModule(CourseModule module) {
		int index = -1;
		switch (module) {
		case 必修课:
			index = 0;
			break;
		case 选修课:
			index = 1;
			break;
		case 通识课:
			index = 2;
			break;
		case 公选课:
			index = 3;
			break;
		case 体育课:
			index = 4;
			break;
		}

		return index;
	}

	// 设置是否能够编辑
	private void setEditable(boolean bool) {
		for (int i = 0; i < jtf.length; i++) {
			jtf[i].setEditable(bool);
		}
	}

	// 检查时候所有的jtextfiled中都有填写信息
//	private boolean check() {
//		for (int i = 0; i < jtf.length; i++) {
//			if (jtf[i].getText().equals("")) {
//				return false;
//			}
//		}
//		return true;
//	}
	
	private boolean checkStatusInput(){
		for (int i = 0; i < jtf.length; i++) {
			if((i==3)||(i==4)||(i==20)){
				continue;
			}
			if (jtf[i].getText().equals("")) {
				return false;
			}
		}
		return true;
	}

	// index指向出错的行数，没有错误是返回-1
	private int checkStatus() {
		int index = -1;
		for (int i = 0; i < jtf.length; i++) {
			if((i==3)||(i==4)||(i==20)){
				continue;
			}
			if (!checkFormat(jtf[i].getText())) {
				index = i;
				return index;
			}
		}

		return index;
	}

	private boolean checkFormat(String input) {
		input = input.trim();
		input = input.replaceAll(" ","");
		String[] temp = input.split("-");
		if (temp.length != 2) {
			return false;
		} else {
			for (int i = 0; i < temp.length; i++) {
				temp[i] = temp[i].replace(".", "-");
				String[] subTemp = temp[i].split("-");
				if (subTemp.length != 2) {
					return false;
				}
				try {
					int[] date = new int[subTemp.length];
					for (int j = 0; j < subTemp.length; j++) {
						date[j] = Integer.parseInt(subTemp[j]);
					}
					if (!judgeDate(date)) {
						return false;
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
					return false;
				}
			}
			return true;
		}
	}

	private boolean judgeDate(int date[]) {
		int month = 0;
		int big = 31, small = 30, special = 29;
		switch (date[0]) {
		case 1:
			month = big;
			break;
		case 2:
			month = special;
			break;
		case 3:
			month = big;
			break;
		case 4:
			month = small;
			break;
		case 5:
			month = big;
			break;
		case 6:
			month = small;
			break;
		case 7:
			month = big;
			break;
		case 8:
			month = big;
			break;
		case 9:
			month = small;
			break;
		case 10:
			month = big;
			break;
		case 11:
			month = small;
			break;
		case 12:
			month = big;
			break;
		default:
			return false;
		}
		if (date[1] <= 0) {
			return false;
		}
		switch (month) {
		case 31:
			if (date[1] > 31)
				return false;
			else
				return true;
		case 30:
			if (date[1] > 30)
				return false;
			else
				return true;
		case 29:
			if (date[1] > 29)
				return false;
			else
				return true;

		default:
			return false;
		}

	}

	private class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == edit) {
				setEditable(true);
			} else if (e.getSource() == save) {
				if (checkStatusInput()) {
					int index = checkStatus();
					if (index == -1) {
						saveInfo();
						setEditable(false);
						System.out.println("-------------");
						JOptionPane.showMessageDialog(save, "保存成功");
					} else {
						JOptionPane.showMessageDialog(jtf[index], "填写格式错误");
					}
				} else {
					JOptionPane.showMessageDialog(save, "有空白项未填写");
				}

			}

		}

	}

}
