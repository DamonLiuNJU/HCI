package presentation.studentui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

public class CourseCommentPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -65426511723914423L;
	/**
	 * @param args
	 */

	JButton start ;  // start to comment ;
	JPanel comment;
	JRadioButton[] group ;
	JLabel[] label ; 
	JPanel[] panelgroup; 

	public CourseCommentPanel(String student_id) {
		start = new JButton("开始评估"); 
		group = new JRadioButton[40];
		label = new JLabel[10];
		panelgroup = new JPanel[10];
		for(int i = 0 ; i< 10 ; i++){
			panelgroup[i]= new JPanel();
		}
		for (int i = 0; i < group.length; i++) {
			if (i % 4 == 0) {
				group[i]=this.getBest();
				continue;
			}
			if (i % 4 == 1) {
				group[i]=this.getGood();
				continue;
			}
			if (i % 4 == 2) {
				group[i]=this.getJustSoSo();
				continue;
			}
			if (i % 4 == 3) {
				group[i]=this.getBad();
				continue;
			}
		}
		comment = getCommentPanel(student_id);
		this.add(comment);
		comment.setVisible(false);
		comment.setLocation(10, 50);
		Tool.setOpaque(this);
		this.setLayout(null);

		// 等待一个状态允许的方法。CBB

		this.add(start);

		start.setBounds(10, 10, 100, 30);
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				boolean valid = true; // dengdai

				if (valid) {
					comment.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "不是评估时间");
				}

			}
		});
	}

	public JPanel getCommentPanel(String student_id) {
		JPanel mainpanel = new JPanel();
		mainpanel.setLayout(null);
		mainpanel.setSize(700, 420);
		mainpanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.GRAY, 2), "教师评估",
				TitledBorder.RIGHT, TitledBorder.TOP));
		Tool.setOpaque(mainpanel);
		JButton commit = new JButton("提交");
		JLabel s1 = new JLabel("1.该课程的教师上课精神饱满，有感染力");// 10条标准
		JLabel s2 = new JLabel("2.教师言传身教，认真敬业");
		JLabel s3 = new JLabel("3.语言表达清楚，准确");
		JLabel s4 = new JLabel("4.有效利用上课时间，课堂教学内容充实");
		JLabel s5 = new JLabel("5.通过交流了解我们的学习状况，并提供有效的指导");
		JLabel s6 = new JLabel("6.能积极有效的利用各种教学辅助手段");
		JLabel s7 = new JLabel("7.课程进度合理，教学详略得当，重点突出，思路清晰");
		JLabel s8 = new JLabel("8.教师鼓励我们表达自己的思想");
		JLabel s9 = new JLabel("9.教学生动，有新意，能激发我们的学习兴趣");
		JLabel s10 = new JLabel("10.作业适当，起到巩固课堂学习的作用");
		label[0]= s1;
		label[1]= s2;
		label[2]= s3;
		label[3]= s4;
		label[4]= s5;
		label[5]= s6;
		label[6]= s7;
		label[7]= s8;
		label[8]= s9;
		label[9]= s10;
		int width = 700;
		int height = 20;
		for(int i = 0 ; i < 10; i++){
			JPanel p = panelgroup[i];
			JRadioButton b1 = group[i*4];
			JRadioButton b2 = group[i*4+1];
			JRadioButton b3 = group[i*4+2];
			JRadioButton b4 = group[i*4+3];
			JLabel l = label[i];
			p.setLayout(null);
			p.add(l);
			p.add(b1);
			p.add(b2);
			p.add(b3);
			p.add(b4);
			l.setBounds(10,0,width,height);
			b1.setBounds(10, 20, 100, height);
			b2.setBounds(120, 20, 100, height);
			b3.setBounds(230, 20, 100, height);
			b4.setBounds(340, 20, 100, height);
			b1.setVisible(true);
			b2.setVisible(true);
			b3.setVisible(true);
			b4.setVisible(true);
			b1.setOpaque(false);
			b2.setOpaque(false);
			b3.setOpaque(false);
			b4.setOpaque(false);
			ButtonGroup bg = new ButtonGroup();
			bg.add(b1);
			bg.add(b2);
			bg.add(b3);
			bg.add(b4);
//			item.add(b1);
//			item.add(b2);
//			item.add(b3);
//			item.add(b4);
//			p.add();
//			item.setBounds(10, 20, width, height);
			Tool.setOpaque(p);
			mainpanel.add(p);
			p.setBounds(10, i*40+10, width, 40);
		}
		
//		commit = new JButton("确认提交");
		commit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				int result = 0 ;
				for(int i = 0 ; i < 10; i++){
					JRadioButton b1 = group[i*4];
					JRadioButton b2= group[i*4+1];
					JRadioButton b3= group[i*4+2];
					JRadioButton b4= group[i*4+3];
					if(b1.isSelected()){
						result +=10;
						continue;
					}
					if(b2.isSelected()){
						result +=8.75;
						continue;
					}
					if(b3.isSelected()){
						result +=7.5;
						continue;
					}
					if(b4.isSelected()){
						result +=6.25;
						continue;
					}
				}
				System.out.println("REsult : " + result );
				
				
			}
		});
		mainpanel.add(commit);
		commit.setBounds(550, 350, 100, 30);
//		s1.setBounds(10, 10, width, height);
//		s2.setBounds(10, 50, width, height);
//		s3.setBounds(10, 90, width, height);
//		s4.setBounds(10, 130, width, height);
//		s5.setBounds(10, 170, width, height);
//		s6.setBounds(10, 210, width, height);
//		s7.setBounds(10, 250, width, height);
//		s8.setBounds(10, 290, width, height);
//		s9.setBounds(10, 330, width, height);
//		s10.setBounds(10, 370, width, height);

		return mainpanel;
	}

	public JRadioButton getBest() {
		return new JRadioButton("优秀");
	}

	public JRadioButton getGood() {
		return new JRadioButton("良好");
	}

	public JRadioButton getJustSoSo() {
		return new JRadioButton("一般");
	}

	public JRadioButton getBad() {
		return new JRadioButton("较差");
	}

}
