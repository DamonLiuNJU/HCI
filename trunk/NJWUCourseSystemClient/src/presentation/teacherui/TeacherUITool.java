package presentation.teacherui;

import java.awt.Image;

import javax.swing.*;

public class TeacherUITool {
	
	public static String returnLabel = "./icon/return.png";
	public static String password = "./icon/key5.png";
	public static String teacherStat = "./icon/teacherstat2.png";
	public static String show = "./icon/audit.png";
	public static String record = "./icon/pen3.png";
	public static String sure = "./icon/student/correct.png";
	public static String refresh = "./icon/student/refreshbutton.png";
	public static String search = "./icon/search2.png";
	public static String cancel = "./icon/refresh.png";

	public static void setButtonIcon(String path , JButton button){
		ImageIcon icon = new ImageIcon(path);  
		icon.getImage();
		Image temp = icon.getImage().getScaledInstance(25,25, Image.SCALE_DEFAULT);  
		icon = new ImageIcon(temp);  
		button.setIcon(icon);  
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
	}
	
	public static void setLabelIcon(String path , JLabel label){
		ImageIcon icon = new ImageIcon(path);
		icon.getImage();
		Image temp = icon.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT);  
		icon = new ImageIcon(temp);  
		label.setIcon(icon);
	}
	
}
