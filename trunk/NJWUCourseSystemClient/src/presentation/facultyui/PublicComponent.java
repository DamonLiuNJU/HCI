package presentation.facultyui;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import presentation.courseui.CourseListTable;
import presentation.managerui.MessageGUI;
import presentation.planui.PlanList;
import businesslogic.coursebl.Transform;

public class PublicComponent  implements FacultyUIImage{

	public static void main(String arg[]) {
		
		 PublicComponent pc=new PublicComponent("100101");
		JFrame  a=new JFrame();
		a.setBounds(200,200,400,400);
		JPanel b=pc.getSearchPanel(false);


a.setLayout(null);		
		a.add(b);
	
	a.setVisible(true);
		 // JFrame f=new JFrame();
		// f.setBounds(10,10,500,500);
		// f.add(pc.getSearchPanel(true));
		// f.setVisible(true);
		// System.out.println( new Faculty("100100").getFacultyID());
		// System.out.println( Transform.getFacultyNameByFTeacherID("100100"));
	}

	
	String ID;
	int frameWidth;
	int frameHeight;
	JPanel searchPanel;
	JTextField cNoTxt;
	JTextField cNameTxt;
	JTextField tnTxt;
	JComboBox<String> gradeBox;
	JTable courseList;
	JScrollPane sp;
	JComboBox<String> facultyBox;

	public PublicComponent(String id) {
		frameWidth = 3 * GUIHelper.getFrameWidth() / 2;
		frameHeight = 3 * GUIHelper.getFrameHeight() / 2;
		ID = id;
	}

	// searchCoursePanel

	public JPanel getSearchPanel(boolean ifLimit) {
		initialSearchPanelComponent();
		

		searchPanel = new JPanel();
		searchPanel.setLayout(null);
		searchPanel.add(getCNoLabel());
		searchPanel.add(cNoTxt);
		searchPanel.add(getCNameLabel());
		searchPanel.add(cNameTxt);
		searchPanel.add(getTeaNameLabel());
		searchPanel.add(tnTxt);
		searchPanel.add(getGradeLabel());
		searchPanel.add(getGradeBox());
		searchPanel.add(getSearchBut());
		searchPanel.add(getCourseListPane());
		searchPanel.add(getFacultyBox());
		searchPanel.add(getFacultyLabel());

		searchPanel.setBounds(frameWidth / 4, frameHeight / 5,
				frameWidth * 2 / 3, frameHeight * 2 / 3);
		searchPanel.setOpaque(false);	
		if (ifLimit) {

			String facultyName = Transform.getFacultyNameByFTeacherID(ID);
			facultyBox.setSelectedItem(facultyName);
			facultyBox.setEnabled(false);
		} else {
			facultyBox.setSelectedItem("");
			facultyBox.setEnabled(true);
		}
		return searchPanel;
	};

	void initialSearchPanelComponent() {
		cNoTxt = new JTextField(10);
		cNoTxt.setBounds(frameWidth / 11, frameHeight / 60, frameWidth / 7,
				frameHeight / 30);

		cNameTxt = new JTextField(10);
		cNameTxt.setBounds(frameWidth * 2 / 5, frameHeight / 60,
				frameWidth / 7, frameHeight / 30);

		tnTxt = new JTextField(10);
		tnTxt.setBounds(frameWidth / 11, frameHeight / 13, frameWidth / 7,
				frameHeight / 30);

		String[] grade = { "", "大一上", "大一下", "大二上", "大二下", "大三上", "大三下", "大四上",
				"大四下", "上", "下" };
		gradeBox = new JComboBox<String>(grade);
		gradeBox.setBounds(frameWidth * 2 / 5, frameHeight / 13,
				frameWidth / 7, frameHeight / 30);

		facultyBox = new PlanList().getFacultyComboBox();
		
		facultyBox.addItem("全校");
		facultyBox.setBounds(frameWidth / 11, frameHeight / 8, frameWidth / 7,
				frameHeight / 30);
	}
JComboBox<String> getGradeBox(){
	gradeBox.getSelectedIndex();
	return gradeBox;
}
JComboBox<String> getFacultyBox(){
	facultyBox.getSelectedIndex();
	return facultyBox;
}	
	JLabel getFacultyLabel() {
		JLabel label = new JLabel("院系:");
		label.setFont(new Font("微软雅黑", 0, 12));
		label.setBounds(0, frameHeight / 8, frameWidth / 10, frameHeight / 30);
		return label;
	}

	JLabel getCNoLabel() {
		JLabel cnLabel = new JLabel("课程编号:");
		cnLabel.setFont(new Font("微软雅黑", 0, 12));
		cnLabel.setBounds(0, frameHeight / 60, frameWidth / 10,
				frameHeight / 30);
		return cnLabel;
	}

	JLabel getCNameLabel() {
		JLabel cnLabel = new JLabel("课程名:");
		cnLabel.setFont(new Font("微软雅黑", 0, 12));
		cnLabel.setBounds(frameWidth * 4 / 14, frameHeight / 60,
				frameWidth / 7, frameHeight / 30);
		return cnLabel;
	}

	JLabel getTeaNameLabel() {
		JLabel tnLabel = new JLabel("教师:");
		tnLabel.setFont(new Font("微软雅黑", 0, 12));
		tnLabel.setBounds(0, frameHeight / 13, frameWidth / 10,
				frameHeight / 30);
		return tnLabel;
	}

	JLabel getGradeLabel() {
		JLabel cnLabel = new JLabel("学期:");
		cnLabel.setFont(new Font("微软雅黑", 0, 12));
		cnLabel.setBounds(frameWidth * 4 / 14, frameHeight / 13,
				frameWidth / 10, frameHeight / 30);
		return cnLabel;
	}

	JButton getSearchBut() {
		JButton search = new JButton("查询");
		search.setFont(new Font("微软雅黑", 0, 12));
		search.setBounds(frameWidth * 6 / 11, frameHeight / 8, frameWidth / 10,
				frameHeight / 30);
		search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchPanel.remove(sp);
				searchPanel.add(getCourseListPane());
				searchPanel.add(sp);
				searchPanel.revalidate();
				searchPanel.repaint();
			}
		});
		return search;
	}

	JScrollPane getCourseListPane() {
		updateCourseListTable();
		sp = new JScrollPane(courseList);
		sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setBounds(0, frameHeight / 6, frameWidth *32/ 50,
				frameHeight * 12/30 );
		return sp;
	}

	void updateCourseListTable() {
				if(((String) facultyBox.getSelectedItem()).equals("全校")){
					courseList = new CourseListTable().getSearchList(cNoTxt.getText(),
							cNameTxt.getText(), tnTxt.getText(),
							"",
							(String) gradeBox.getSelectedItem());
							courseList.updateUI();
				}else{
				courseList = new CourseListTable().getSearchList(cNoTxt.getText(),
				cNameTxt.getText(), tnTxt.getText(),
				(String) facultyBox.getSelectedItem(),
				(String) gradeBox.getSelectedItem());
				courseList.updateUI();
				}
				}

	// menuBar 组件
	public JMenuItem getPswMenuItem() {
		JMenuItem pswMenuItem = new JMenuItem("密码", KeyEvent.VK_P);
		return pswMenuItem;
	}

	public JMenuItem getInfoMenuItem() {
		JMenuItem infoMenuItem = new JMenuItem("个人信息", KeyEvent.VK_I);
		return infoMenuItem;
	}

	

	public JMenuItem getReplyMenuItem() {
		JMenuItem replyMenuItem = new JMenuItem("反馈", KeyEvent.VK_R);
		replyMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JLabel reply = new JLabel("<html>" + "       反馈意见请发送邮件至<br>"
						+ "Swing@software.nju.edu.cn<br>"
						+ "           我们将尽快答复您！<html>");
				reply.setFont(new Font("微软雅黑", 0, 12));

				reply.setHorizontalAlignment(SwingConstants.CENTER);
				JFrame replyF = new JFrame("反馈");
				replyF.setBounds(550, 250, 200, 200);
				replyF.add(reply);

				replyF.setVisible(true);
			}

		});
		return replyMenuItem;
	}

	public JMenuItem getAboutMenuItem() {
		JMenuItem aboutMenuItem = new JMenuItem("关于", KeyEvent.VK_A);
		aboutMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JLabel about = new JLabel("<html>" + "----- NJWU教务系统-----<br>"
						+ "组名： Swing <br>" + "成员：cbb&ll&lr&lwt<br>"
						+ "学校：南京大学<br>" + "【NanJing University】<html>");
				about.setFont(new Font("微软雅黑", 0, 12));

				about.setHorizontalAlignment(SwingConstants.CENTER);
				JFrame replyF = new JFrame("关于");
				replyF.setBounds(550, 250, 200, 200);
				replyF.add(about);

				replyF.setVisible(true);
			}

		});
		return aboutMenuItem;
	}

	public JMenuItem getMsgMenuItem() {
		// TODO Auto-generated method stub
		JMenuItem msgMenuItem = new JMenuItem("", KeyEvent.VK_M);
		msgMenuItem.setOpaque(false);
		ImageIcon icon=new ImageIcon(letterIcon);
		msgMenuItem.setIcon(icon);
		msgMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MessageGUI msgGUI = new MessageGUI(ID);
				msgGUI.getMsgFrame();
			}
		});
		return msgMenuItem;
	}

	// 获得图标按钮
	public JButton getButton(String path, String txt, int width, int length) {
		JButton but = new JButton(txt);
		but.setHorizontalTextPosition(SwingConstants.CENTER);
		but.setVerticalTextPosition(SwingConstants.BOTTOM);
		ImageIcon pic = new ImageIcon(path);
		pic = new ImageIcon(pic.getImage().getScaledInstance(width, length,
				Image.SCALE_SMOOTH));
		but.setIcon(pic);
		but.setContentAreaFilled(false);
		but.setBorderPainted(false);
		return but;
	}

	// 返回选中的课程编号
	public String getSelectedCouName() {
		// TODO Auto-generated method stub
			if(courseList.getSelectedRow()==-1){
				return "error";
			}else{
				return (String) courseList.getValueAt(courseList.getSelectedRow(), 1);
			}
		}

	public String getSelectedCouID() {
		// TODO Auto-generated method stub
		if(courseList.getSelectedRow()==-1){
			return "error";
		}else{
		return (String) courseList.getValueAt(courseList.getSelectedRow(), 0);
		}
	}

	// 得到头像
	public JLabel getPortrait() {
		ImageIcon portraitIcon = new ImageIcon("./icon/faculty/portrait.png");
		portraitIcon = new ImageIcon(portraitIcon.getImage().getScaledInstance(
				frameWidth / 7, frameHeight / 5, Image.SCALE_SMOOTH));
		JLabel portrait = new JLabel(portraitIcon);
		portrait.setBounds(frameWidth / 35, frameHeight / 30, frameWidth / 7,
				frameHeight / 5);
		return portrait;
	}
}
