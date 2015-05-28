package presentation.facultyui;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import presentation.studentui.StudentListTable;




public class StudentGUI {
	int frameWidth;
	int frameHeight;
	String id;
	PublicComponent pc;
	JPanel courseListPanel;
	JPanel studentListPanel;

	public StudentGUI(String id) {
		frameWidth = 3 * GUIHelper.getFrameWidth() / 2;
		frameHeight = 3 * GUIHelper.getFrameHeight() / 2;
		this.id = id;
		pc = new PublicComponent(id);
	}

	void initialStuListPanel(String courseID, String name) {
		studentListPanel = new JPanel();
		JLabel title = getTitleLabel(name);

		// getFrom studentUI
		JScrollPane stuList = getStuList(courseID);
		studentListPanel.setLayout(null);
		studentListPanel.add(title);
		studentListPanel.add(stuList);
		studentListPanel.setBounds(frameWidth / 4, frameHeight / 5,
				frameWidth * 2 / 3, frameHeight * 2 / 3);
	}

	public JPanel getStuListPanel(String courseID, String courseName) {
		initialStuListPanel(courseID, courseName);
		studentListPanel.setOpaque(false);	
		return studentListPanel;
	}

	JLabel getTitleLabel(String name) {
		String courseName = name;
		JLabel title = new JLabel("选择 " + courseName + " 的学生有：");
		title.setFont(new Font("微软雅黑", 1, 14));
		title.setBounds(0, 0, frameWidth / 2, frameHeight / 20);
		return title;
	}

	JScrollPane getStuList(String id) {

		StudentListTable stt = new StudentListTable();
		JTable stuList = stt.getStudentListTable(id);
		JScrollPane sp = new JScrollPane(stuList);
		sp.setBounds(frameWidth / 20, frameHeight / 9, frameWidth / 2,
				frameHeight / 2);
		sp.setPreferredSize(new Dimension(500, 200));
		return sp;

	}

}
