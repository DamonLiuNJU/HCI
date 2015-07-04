package presentation.courseselectionui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
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

	JButton commonknowledge = new JButton("通识课选课");
	JButton PE = new JButton("体育选课");
	JButton major = new JButton("专业选修课选课");
	JButton publiccourse = new JButton("公共选课");
	JButton othermajor = new JButton("跨专业选课");
	JButton secondselection = new JButton("补选课程");
	
	int width = 150;
	int height = 50;
	
	public JPanel selectModelPanel(final String student_id) {
		final Student s = new Student (student_id);
		final JPanel selectmodelpanel = new JPanel();
//		CourseListTable coursetable = new CourseListTable();
		
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
		Insets i = new Insets(5, 10,5, 10); 
		commonknowledge.setMargin(i);

	
		PE.setMargin(i);
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
		
		major.setMargin(i);
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
		
		Tool.setIcon(Tool.bookimage, publiccourse);
		publiccourse.setMargin(i);
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
		
		
		Tool.setIcon(Tool.bookimage, othermajor);
		othermajor.setMargin(i);
		othermajor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				new OtherMajorCourseSelection(new Student(student_id));
			}
		});
		
	
		secondselection.setMargin(i);
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
		
		Dimension d = new Dimension(width, height);
		PE.setSize(d);
		commonknowledge.setSize(d);
		major.setSize(d);
		publiccourse.setSize(d);
		othermajor.setSize(d);
		secondselection.setSize(d);
		
		selectmodelpanel.setLayout(new FlowLayout(0, 50, 40));
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
