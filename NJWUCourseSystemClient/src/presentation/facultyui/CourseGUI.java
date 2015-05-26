
package presentation.facultyui;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import vo.coursevo.CourseListItemVO;
import businesslogic.coursebl.Course;
import businesslogic.coursebl.CourseList;
import businesslogic.managerbl.Faculty;
import businesslogicservice.courseblservice.CourseBLService;
import businesslogicservice.courseblservice.CourseListService;

public class CourseGUI {
	CourseBLService course;
	CourseListService courseList;
	String id;
	int frameWidth;
	int frameHeight;
	JPanel publishPanel;
	JPanel revisePanel;
	JPanel scanPanel;
	JPanel deletePanel;
	JTextField cno;
	JTextField name;
	JTextField teacher;
	JTextField place;
	JTextField week_start;
	JTextField week_end;
	JTextField limit;
	JTextField credit;

	JComboBox<String> campus;
	JComboBox<String> grade;
	JComboBox<String> module;
	JComboBox<String> profession;
	JComboBox<String> dayInWeek;
	JComboBox<String> class_start;
	JComboBox<String> class_end;
	JComboBox<String> timeTable;

	JTextArea require;
	JTextArea info;
	ArrayList<String> classTime;

	PublicComponent pc;

	public CourseGUI(String id) {
		frameWidth = 3 * GUIHelper.getFrameWidth() / 2;
		frameHeight = 3 * GUIHelper.getFrameHeight() / 2;
		this.id = id;
		initialComponent();
		course = new Course();
		courseList = new CourseList();

		pc = new PublicComponent(id);
	}

	void initialComponent() {
		String[] content1 = { "仙林", "鼓楼", "浦口" };
		campus = new JComboBox<String>(content1);
		campus.setBounds(frameWidth / 10, frameHeight * 2 / 15, frameWidth / 6,
				frameHeight / 25);

		// get this year
		String[] content2 = { "大一上", "大一下", "大二上", "大二下", "大三上", "大三下", "大四上",
				"大四下", "上", "下" };
		grade = new JComboBox<String>(content2);
		grade.setSelectedItem("大一上");
		grade.setBounds(frameWidth * 3 / 7, frameHeight * 2 / 15,
				frameWidth / 6, frameHeight / 25);

		String[] content3 = { "必修课", "选修课", "通识课", "公选课", "体育课" };
		module = new JComboBox<String>(content3);
		module.setSelectedItem("必修课");
		module.setBounds(frameWidth / 10, frameHeight / 5, frameWidth / 6,
				frameHeight / 25);

		// get profession [] from bl 【弃
		String[] content4 = { "专业1", "专业2", "专业3" };
		profession = new JComboBox<String>(content4);
		profession.setBounds(frameWidth * 3 / 7, frameHeight / 5,
				frameWidth / 6, frameHeight / 25);

		// 星期天不允许开课。若有特殊需求在reuqire里填写
		String[] content5 = { "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

		dayInWeek = new JComboBox<String>(content5);
		dayInWeek.setSelectedItem("星期一");
		dayInWeek.setBounds(0, frameHeight / 3, frameWidth / 11,
				frameHeight / 25);

		String[] content6 = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		class_start = new JComboBox<String>(content6);
		class_start.setSelectedItem("1");
		class_start.setBounds(frameWidth / 10, frameHeight / 3,
				frameWidth / 20, frameHeight / 25);

		String[] content7 = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		class_end = new JComboBox<String>(content7);
		class_end.setSelectedItem("1");
		class_end.setBounds(frameWidth / 6, frameHeight / 3, frameWidth / 20,
				frameHeight / 25);

		classTime = new ArrayList<String>();
		updateTimeTable();

		require = new JTextArea(15, 15);
		require.setText("");
		require.setLineWrap(true);
		require.setWrapStyleWord(true);

		info = new JTextArea(15, 15);
		info.setText("");
		info.setLineWrap(true);
		info.setWrapStyleWord(true);
		credit = new JTextField(10);
		credit.setBounds(frameWidth * 3 / 7, frameHeight / 5, frameWidth / 6,
				frameHeight / 25);

		cno = new JTextField(10);
		cno.setText("");
		cno.setBounds(frameWidth * 3 / 7, 0, frameWidth / 6, frameHeight / 25);

		name = new JTextField(10);
		name.setText("");
		name.setBounds(frameWidth / 10, 0, frameWidth / 6, frameHeight / 25);

		teacher = new JTextField(10);
		teacher.setText("");
		teacher.setBounds(frameWidth / 10, frameHeight / 15, frameWidth / 6,
				frameHeight / 25);

		place = new JTextField(10);
		place.setText("");
		place.setBounds(frameWidth * 3 / 7, frameHeight / 15, frameWidth / 6,
				frameHeight / 25);

		limit = new JTextField(10);
		limit.setText("");
		limit.setBounds(frameWidth * 3 / 7, frameHeight * 4 / 15,
				frameWidth / 6, frameHeight / 25);

		week_start = new JTextField(2);
		week_start.setText("");
		week_start.setBounds(frameWidth / 10, frameHeight * 4 / 15,
				frameWidth / 40, frameHeight / 25);

		week_end = new JTextField(2);
		week_end.setText("");
		week_end.setBounds(frameWidth / 6, frameHeight * 4 / 15,
				frameWidth / 40, frameHeight / 25);

	}

	void initialPublishPanel() {
		publishPanel = new JPanel();
		publishPanel.setLayout(null);
		cno.setEditable(true);
		publishPanel.add(getCouIDLabel());
		publishPanel.add(getCouNameLabel());
		publishPanel.add(getTeaIDLabel());
		publishPanel.add(getTimeLabel());
		publishPanel.add(getCampusLabel());
		publishPanel.add(getGradeLabel());
		publishPanel.add(getPlaceLabel());
		publishPanel.add(getModuleLabel());
		publishPanel.add(getCreditLabel());
		publishPanel.add(getRequireLabel());
		publishPanel.add(getInfoLabel());
		publishPanel.add(getLimitLabel());
		publishPanel.add(getCouNumberTxt());
		publishPanel.add(getCouNameTxt());
		publishPanel.add(getTeacherTxt());
		publishPanel.add(getPlaceTxt());
		publishPanel.add(getWeekStartTxt());
		publishPanel.add(getWeekEndTxt());
		publishPanel.add(getPiao());
		publishPanel.add(getWeek());
		publishPanel.add(getLimitTxt());
		publishPanel.add(getCampusBox());
		publishPanel.add(getGradeBox());
		publishPanel.add(getModuleBox());
		publishPanel.add(getCreditTxt());
		publishPanel.add(getDayInWeekBox());
		publishPanel.add(getClassStartBox());
		publishPanel.add(getClassEndBox());
		publishPanel.add(getPiao2());
		publishPanel.add(getLesson());
		publishPanel.add(getTimeTableBox());
		publishPanel.add(getAddBut());
		publishPanel.add(getDeleteBut());
		publishPanel.add(getRequireArea());
		publishPanel.add(getInfoArea());
		publishPanel.add(getConfirmBut());
		publishPanel.add(getCancelBut());

		publishPanel.setBounds(frameWidth / 4, frameHeight / 5,
				frameWidth * 2 / 3, frameHeight * 2 / 3);
	}

	void initialRevisePanel(String id) {
		revisePanel = new JPanel();
		revisePanel.setLayout(null);
		// getReviseState(id);
		// id ——>getCourseInfo
		cno.setEditable(false);

		revisePanel.add(getCouIDLabel());
		revisePanel.add(getCouNameLabel());
		revisePanel.add(getTeaIDLabel());
		revisePanel.add(getTimeLabel());
		revisePanel.add(getCampusLabel());
		revisePanel.add(getGradeLabel());
		revisePanel.add(getPlaceLabel());
		revisePanel.add(getModuleLabel());
		revisePanel.add(getCreditLabel());
		revisePanel.add(getRequireLabel());
		revisePanel.add(getInfoLabel());
		revisePanel.add(getLimitLabel());
		revisePanel.add(getCouNumberTxt());
		revisePanel.add(getCouNameTxt());
		revisePanel.add(getTeacherTxt());
		revisePanel.add(getPlaceTxt());
		revisePanel.add(getWeekStartTxt());
		revisePanel.add(getWeekEndTxt());
		revisePanel.add(getPiao());
		revisePanel.add(getWeek());
		revisePanel.add(getLimitTxt());
		revisePanel.add(getCampusBox());
		revisePanel.add(getGradeBox());
		revisePanel.add(getModuleBox());
		revisePanel.add(getCreditTxt());
		revisePanel.add(getDayInWeekBox());
		revisePanel.add(getClassStartBox());
		revisePanel.add(getClassEndBox());
		revisePanel.add(getPiao2());
		revisePanel.add(getLesson());
		revisePanel.add(getTimeTableBox());
		revisePanel.add(getReviseAddBut());
		revisePanel.add(getReviseDeleteBut());
		revisePanel.add(getRequireArea());
		revisePanel.add(getInfoArea());
		revisePanel.add(getReviseConfirmBut());
		revisePanel.add(getReviseCancelBut());
		getReviseState(id);
		revisePanel.setBounds(frameWidth / 4, frameHeight / 5,
				frameWidth * 2 / 3, frameHeight * 2 / 3);

	}

	// 根据课程id刷新组件
	void getReviseState(String id) {
		CourseListItemVO info = course.getCourseInfo(id); // course
		cno.setText(info.getCno());
		name.setText(info.getName());
		teacher.setText(info.getTeacherID());
		campus.setSelectedItem(info.getCampus());
		grade.setSelectedItem(info.getGrade());
		place.setText(info.getPlace());
		String[] s1 = info.getTime().split("&");

		classTime.clear();
		for (int i = 0; i < s1.length; i++) {
			System.out.println(s1[i]);
			classTime.add(s1[i]);
		}

		updateTimeTable();

		String[] s2 = info.getPeriod().split("-");
		week_start.setText(s2[0]);
		week_end.setText(s2[1]);
		require.setText(info.getRequire());
		credit.setText(info.getCredit());
		module.setSelectedItem(info.getModule());
		limit.setText(info.getLimit());
		this.info.setText(info.getInfo());
	}

	// get panel
	public JPanel getPublishPanel() {
		// TODO Auto-generated method stub
		initialPublishPanel();
		clear();
		publishPanel.setOpaque(false);	
		return publishPanel;
	}

	public JPanel getRevisePanel(String id) {
		initialRevisePanel(id);
		revisePanel.setOpaque(false);	
		return revisePanel;
	}

	// 获得组件
	JComboBox<String> getCampusBox() {
		return campus;
	}

	JComboBox<String> getGradeBox() {
		return grade;
	}

	JComboBox<String> getModuleBox() {
		return module;
	}

	JComboBox<String> getProfessionBox() {
		return profession;
	}

	JComboBox<String> getDayInWeekBox() {
		return dayInWeek;
	}

	JComboBox<String> getClassStartBox() {
		return class_start;
	}

	JComboBox<String> getClassEndBox() {
		return class_end;
	}

	JComboBox<String> getTimeTableBox() {
		return timeTable;
	}

	JScrollPane getRequireArea() {
		JScrollPane sp = new JScrollPane(require);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setBounds(0, frameHeight * 8 / 16, frameWidth * 4 / 13,
				frameHeight / 10);
		return sp;
	}

	JScrollPane getInfoArea() {
		JScrollPane sp = new JScrollPane(info);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setBounds(frameWidth / 3, frameHeight * 6 / 16, frameWidth * 4 / 13,
				frameHeight * 11 / 50);
		return sp;
	}

	JTextField getCreditTxt() {
		return credit;
	}

	JTextField getCouNumberTxt() {
		return cno;
	}

	JTextField getCouNameTxt() {
		return name;
	}

	JTextField getTeacherTxt() {
		return teacher;
	}

	JTextField getPlaceTxt() {
		return place;
	}

	JTextField getWeekStartTxt() {
		return week_start;
	}

	JTextField getWeekEndTxt() {
		return week_end;
	}

	JTextField getLimitTxt() {
		return limit;
	}

	JLabel getCouNameLabel() {
		JLabel label = new JLabel("课程名：");
		label.setFont(new Font("微软雅黑", 0, 13));
		label.setBounds(0, 0, frameWidth / 5, frameHeight / 30);
		return label;
	}

	JLabel getCouIDLabel() {
		JLabel label = new JLabel("课程号：");
		label.setFont(new Font("微软雅黑", 0, 13));
		label.setBounds(frameWidth / 3, 0, frameWidth / 5, frameHeight / 30);
		return label;
	}

	JLabel getTeaIDLabel() {
		JLabel label = new JLabel("教师号：");
		label.setFont(new Font("微软雅黑", 0, 13));
		label.setBounds(0, frameHeight / 15, frameWidth / 5, frameHeight / 30);
		return label;
	}

	JLabel getPlaceLabel() {
		JLabel label = new JLabel("地点：");
		label.setFont(new Font("微软雅黑", 0, 13));
		label.setBounds(frameWidth / 3, frameHeight / 15, frameWidth / 5,
				frameHeight / 30);
		return label;
	}

	JLabel getCampusLabel() {
		JLabel label = new JLabel("校区：");
		label.setFont(new Font("微软雅黑", 0, 13));
		label.setBounds(0, frameHeight * 2 / 15, frameWidth * 2 / 15,
				frameHeight / 30);
		return label;
	}

	JLabel getGradeLabel() {
		JLabel label = new JLabel("学期：");
		label.setFont(new Font("微软雅黑", 0, 13));
		label.setBounds(frameWidth / 3, frameHeight * 2 / 15, frameWidth / 5,
				frameHeight / 30);
		return label;
	}

	JLabel getModuleLabel() {
		JLabel label = new JLabel("模块：");
		label.setFont(new Font("微软雅黑", 0, 13));
		label.setBounds(0, frameHeight / 5, frameWidth / 5, frameHeight / 30);
		return label;
	}

	JLabel getCreditLabel() {
		JLabel label = new JLabel("学分：");
		label.setFont(new Font("微软雅黑", 0, 13));
		label.setBounds(frameWidth / 3, frameHeight / 5, frameWidth / 5,
				frameHeight / 30);
		return label;
	}

	JLabel getTimeLabel() {
		JLabel label = new JLabel("时间：");
		label.setFont(new Font("微软雅黑", 0, 13));
		label.setBounds(0, frameHeight * 4 / 15, frameWidth / 5,
				frameHeight / 30);
		return label;
	}

	JLabel getLimitLabel() {
		JLabel label = new JLabel("限制人数：");
		label.setFont(new Font("微软雅黑", 0, 13));
		label.setBounds(frameWidth / 3, frameHeight * 4 / 15, frameWidth / 5,
				frameHeight / 30);
		return label;
	}

	JLabel getRequireLabel() {
		JLabel label = new JLabel("特殊要求：");
		label.setFont(new Font("微软雅黑", 0, 13));
		label.setBounds(0, frameHeight * 7 / 15, frameWidth / 5,
				frameHeight / 30);
		return label;
	}

	JLabel getInfoLabel() {
		JLabel label = new JLabel("课程介绍：");
		label.setFont(new Font("微软雅黑", 0, 12));
		label.setBounds(frameWidth / 3, frameHeight / 3, frameWidth / 5,
				frameHeight / 30);
		return label;
	}

	JLabel getPiao() {
		JLabel label = new JLabel("~");
		label.setFont(new Font("微软雅黑", 1, 19));
		label.setBounds(frameWidth * 8 / 58, frameHeight * 4 / 15,
				frameWidth / 30, frameHeight / 30);
		return label;
	}

	JLabel getPiao2() {
		JLabel label = new JLabel("~");
		label.setFont(new Font("微软雅黑", 0, 12));
		label.setBounds(frameWidth * 9 / 60, frameHeight / 3, frameWidth / 40,
				frameHeight / 30);
		return label;
	}

	JLabel getWeek() {
		JLabel label = new JLabel("周");
		label.setFont(new Font("微软雅黑", 0, 13));
		label.setBounds(frameWidth / 5, frameHeight * 4 / 15, frameWidth / 20,
				frameHeight / 30);
		return label;
	}

	JLabel getLesson() {
		JLabel label = new JLabel("节");
		label.setFont(new Font("微软雅黑", 0, 13));
		label.setBounds(frameWidth * 11 / 50, frameHeight / 3, frameWidth / 20,
				frameHeight / 30);
		return label;
	}

	// publish

	// publishPanel
	JButton getAddBut() {
		JButton add = new JButton("添加");
		add.setFont(new Font("微软雅黑", 0, 10));
		add.setBounds(frameWidth * 19 / 80, frameHeight / 3, frameWidth / 14,
				frameHeight / 25);
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				classTime.add((String) dayInWeek.getSelectedItem() + " "
						+ (String) class_start.getSelectedItem() + "-"
						+ (String) class_end.getSelectedItem());
				publishPanel.remove(timeTable);
				updateTimeTable();
				publishPanel.add(timeTable);
				publishPanel.repaint();
				publishPanel.revalidate();
			}
		});
		return add;
	}

	JButton getDeleteBut() {
		JButton del = new JButton("删除");
		del.setFont(new Font("微软雅黑", 0, 10));
		del.setBounds(frameWidth * 19 / 80, frameHeight * 2 / 5,
				frameWidth / 14, frameHeight / 25);
		del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				classTime.remove(timeTable.getSelectedItem());
				publishPanel.remove(timeTable);
				updateTimeTable();
				publishPanel.add(timeTable);
				publishPanel.repaint();
				publishPanel.revalidate();
			}

		});

		return del;
	}

	JButton getConfirmBut() {
		JButton add = new JButton("确认");
		add.setFont(new Font("微软雅黑", 0, 12));
		add.setBounds(frameWidth / 5, frameHeight * 5 / 8, frameWidth / 10,
				frameHeight / 25);
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String courseID = cno.getText();
				boolean if_conflict = course.isCourseIDUsable(courseID); // course
				if (if_conflict) {
					GUIHelper.sendMessage("该课程号已被占用！");
					cno.setText("");
				} else {
					CourseListItemVO info = getCourseInfo();
					course.addCourse(info); // course
					GUIHelper.sendMessage("课程发布成功！");
				}

			}
		});
		return add;
	}

	JButton getCancelBut() {
		JButton cal = new JButton("取消");
		cal.setFont(new Font("微软雅黑", 0, 12));
		cal.setBounds(frameWidth / 3, frameHeight * 5 / 8, frameWidth / 10,
				frameHeight / 25);
		cal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		return cal;
	}

	// revisePanel

	// revisePanel
	JButton getReviseConfirmBut() {
		JButton revise = new JButton("确认");
		revise.setFont(new Font("微软雅黑", 0, 12));
		revise.setBounds(frameWidth / 5, frameHeight * 5 / 8, frameWidth / 10,
				frameHeight / 25);
		revise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CourseListItemVO info = getCourseInfo();
				course.modifyInfo(info); // course
				GUIHelper.sendMessage("修改课程成功！");
			}
		});
		return revise;
	}

	JButton getReviseAddBut() {
		JButton add = new JButton("添加");
		add.setFont(new Font("微软雅黑", 0, 10));
		add.setBounds(frameWidth * 19 / 80, frameHeight / 3, frameWidth / 14,
				frameHeight / 25);
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				classTime.add((String) dayInWeek.getSelectedItem() + " "
						+ (String) class_start.getSelectedItem() + "-"
						+ (String) class_end.getSelectedItem());
				revisePanel.remove(timeTable);
				updateTimeTable();
				revisePanel.add(timeTable);
				revisePanel.repaint();
				revisePanel.revalidate();
			}
		});
		return add;
	}

	JButton getReviseDeleteBut() {
		JButton del = new JButton("删除");
		del.setFont(new Font("微软雅黑", 0, 10));
		del.setBounds(frameWidth * 19 / 80, frameHeight * 2 / 5,
				frameWidth / 14, frameHeight / 25);
		del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				classTime.remove(timeTable.getSelectedItem());
				revisePanel.remove(timeTable);
				updateTimeTable();
				revisePanel.add(timeTable);
				revisePanel.repaint();
				revisePanel.revalidate();
			}

		});

		return del;
	}

	JButton getReviseCancelBut() {
		JButton cal = new JButton("取消");
		cal.setFont(new Font("微软雅黑", 0, 12));
		cal.setBounds(frameWidth / 3, frameHeight * 5 / 8, frameWidth / 10,
				frameHeight / 25);
		cal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// revisePanel.removeAll();
				getReviseState(cno.getText());
				revisePanel.repaint();
				revisePanel.revalidate();
			}
		});
		return cal;
	}

	// 得到CourseProcessVO
	CourseListItemVO getCourseInfo() {
		String cno = this.cno.getText();
		String name = this.name.getText();
		String teacherID = this.teacher.getText();
		String campus = (String) this.campus.getSelectedItem();
		String grade = (String) this.grade.getSelectedItem();
		String place = this.place.getText();
		String time = getClassTimeString();
		String period = week_start.getText() + "-" + week_end.getText();
		String require = this.require.getText();

		String facultyName = new Faculty(id).getFacultyName();
		String credit = this.credit.getText();
		String module = (String) this.module.getSelectedItem();
		String limit = this.limit.getText();
		String info = this.info.getText();
		return new CourseListItemVO(name, cno, teacherID, place, campus, grade,
				module, credit, period, limit, time, require, info, facultyName);
	}

	// 取消是清空已填信息
	void clear() {
		cno.setText("");
		name.setText("");
		teacher.setText("");
		place.setText("");
		week_start.setText("");
		week_end.setText("");
		limit.setText("");
		require.setText("");
		info.setText("");
		classTime.clear();
		publishPanel.remove(timeTable);
		updateTimeTable();
		publishPanel.add(timeTable);
		publishPanel.repaint();
		publishPanel.revalidate();
	}

	String[] getClassTimeList() {
		String[] a = new String[classTime.size()];
		for (int i = 0; i < classTime.size(); i++) {
			a[i] = classTime.get(i);
		}
		return a;
	}

	String getClassTimeString() {
		String a = "";
		for (int i = 0; i < classTime.size() - 1; i++) {
			a += classTime.get(i) + "&";
		}
		a += classTime.get(classTime.size() - 1);
		return a;
	}

	void updateTimeTable() {
		timeTable = new JComboBox<String>(getClassTimeList());
		timeTable.setBounds(0, frameHeight * 2 / 5, frameWidth / 5,
				frameHeight / 25);
	}

}
