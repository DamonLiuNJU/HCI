package presentation.courseselectionui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import presentation.studentui.Tool;
import businesslogic.courseselectionbl.CourseSelection;
import businesslogic.courseselectionbl.SelectCourseInfo;
import businesslogic.studentbl.Student;
import businesslogic.utilitybl.CourseModule;

public class SelectCourseModule {

	public JPanel selectModelPanel(final String student_id) {
		final Student s = new Student (student_id);
		final JPanel selectmodelpanel = new JPanel();
//		CourseListTable coursetable = new CourseListTable();
		JButton commonknowledge = new JButton("通识课选课");
		Tool.setIcon(Tool.bookimage, commonknowledge);
		commonknowledge.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				boolean valid = false;
				valid = new SelectCourseInfo().isCurrentTimeValidForSelectionCourse(CourseModule.通识课);
				if (valid) {
					new CommonKnowledgeCourseSelectionFrame(s);
				} else {
					JOptionPane.showMessageDialog(null, "不在选课时间内");
				}
			}
		});

		JButton PE = new JButton("体育选课");
		Tool.setIcon(Tool.bookimage, PE);
		PE.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				boolean valid = new SelectCourseInfo().isCurrentTimeValidForSelectionCourse(CourseModule.体育课);
				boolean selected = new CourseSelection().seletedPE(student_id);
				if (valid) {
					if(!selected){
						new PECourseFrame(student_id);
					}else{
						JOptionPane.showMessageDialog(null, "你已经选择过体育课程，请勿重复选择。\n 要查看已选的体育课程请点击查看：我的课程");
					}
				} else {
					JOptionPane.showMessageDialog(null, "不在体育选课时间内");
				}
			}
		});
		// 专业选课
		JButton major = new JButton("专业选修课选课");
		Tool.setIcon(Tool.bookimage, major);
		major.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean valid = new SelectCourseInfo().isCurrentTimeValidForSelectionCourse(CourseModule.选修课);
				if (valid) {
					Student s = new Student(student_id);
					new ElectiveCourseFrame(s);
				} else {
					JOptionPane.showMessageDialog(null, "不在选修课选课时间内");

				}

			}

		});

		// 公共选课
		JButton publiccourse = new JButton("公共选课");
		Tool.setIcon(Tool.bookimage, publiccourse);
		publiccourse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean valid = new SelectCourseInfo().isCurrentTimeValidForSelectionCourse(CourseModule.公选课);
				if (valid) {
					new PublicCourseSelection(new Student(student_id));
				} else {
					JOptionPane.showMessageDialog(null, "不在公选课选课时间内");
				}
			}
		});
		
		JButton othermajor = new JButton("跨专业选课");
		Tool.setIcon(Tool.bookimage, othermajor);
		othermajor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				new OtherMajorCourseSelection(new Student(student_id));
			}
		});
		
		JButton secondselection = new JButton("补选课程");
		Tool.setIcon(Tool.bookimage, secondselection);
		secondselection.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				boolean currenttimevalid = true;
				if(currenttimevalid){
					new SecondSelection().setVisible(true);
				}
			}
		});
		
		selectmodelpanel.setLayout(new FlowLayout(0, 50, 50));
		selectmodelpanel.add(commonknowledge);
		selectmodelpanel.add(PE);
		selectmodelpanel.add(major);
		selectmodelpanel.add(publiccourse);
		selectmodelpanel.add(othermajor);
		// commonknowledge.setBounds(buttonsize);
		Tool.setOpaque(selectmodelpanel);
		return selectmodelpanel;
	}

}
