package presentation.teacherui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import businesslogic.teacherbl.TeacherList;

import vo.TeacherVO;

/*
 * 某个院系的教师的id，姓名对照表
 * 传入一个院系的id，在界面上显示有个frame，包含该院的所有教师的id以及其
 * 对应的姓名。
 */
public class ID_NameComparisonFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ArrayList<TeacherVO> list;
	JPanel panel;
	JTextArea jta;
	Point origin;
	
	public static void main(String[] args){
		new ID_NameComparisonFrame("100100");
	}
	
	public ID_NameComparisonFrame(String manager_id){
		list = this.getMyTeacherVO(manager_id);
		origin = new Point();
		this.draw();
	}
	
	private void draw(){
	final JFrame f=new JFrame();
		f.	setUndecorated(true);
		f.setLayout(null);
		f.setBounds(550 , 200 , 200 ,400);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0 , 0 ,f.getWidth() , f.getHeight());

		f.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				origin.x = e.getX();
				origin.y = e.getY();
			}
		});
		f.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				Point p = f.getLocation();
				f.setLocation(p.x + e.getX() - origin.x,
						p.y + e.getY() - origin.y);
			}
		});
		initJTA();
		JScrollPane sp=new JScrollPane(jta);
		sp.setBounds(10, 15, f.getWidth()-30, f.getHeight()-25);
		panel.add(sp);
		
		JButton close = new JButton("X");
		close.setForeground(Color.BLACK);
		close.setFont(new Font("微软雅黑", 1, 15));

		close.setContentAreaFilled(false);
		close.setBorderPainted(false);
		close.setBounds(f.getWidth()-25, 0, 25, 25);
		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				f.dispose();
			}
		});
		panel.add(close);
		
		panel.setBackground(new Color(0X8d8a78));
		f.add(panel);
		f.setDefaultCloseOperation(EXIT_ON_CLOSE);
		f.setVisible(true);
	
	
	
	
	
	}
	
	private void initJTA(){
		jta = new JTextArea();
		jta.setBounds(0 , 0 , panel.getWidth() , panel.getHeight());
		
		for(int i=0 ; i<list.size() ; i++){
			String s = list.get(i).getId() + "   "+list.get(i).getName()+"\n";
			jta.append(s);
		}
		
	}
	
	private ArrayList<TeacherVO> getMyTeacherVO(String manager_id) {
		
		ArrayList<TeacherVO> list = new TeacherList().getFacultyTeacherList(manager_id);

		return list;
	}
}
