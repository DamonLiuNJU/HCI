package presentation.studentui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

public class Tool {
	
	public static String FrameImagePath ="./icon/0622.png";;
	public static String schoolmarkpath = "./icon/student/mark.png";
	public static String quitimage = "./icon/student/quit32.png";
	public static String helpimage = "./icon/student/help32.png";
	public static String settingimage = "./icon/key.png";
	public static String mailimage = "./icon/student/mail32.png";
	public static String bookimage = "./icon/student/book 64 .png";
	public static String clock = "./icon/student/clock32.png";
	public static String books = "./icon/student/books.png";
	public static String rubbishcan = "./icon/student/rubbish.png";
	public static String find = "./icon/student/find.png";
	public static String score = "./icon/student/score.png";
	public static String change = "./icon/student/change.png";
	public static String personalinfo = "./icon/student/personalinfo.png";
	public static String permission = "./icon/student/permission.png";
	public static String refreshbutton = "./icon/student/refreshebutton.png";
	public static String add = "./icon/student/add.png";
	public static String correct = "./icon/student/correct.png";
	
	public static void setIcon(String file, JButton iconButton) {  
		ImageIcon icon = new ImageIcon(file);  
		icon.getImage();
		Image temp = icon.getImage().getScaledInstance(25,25, Image.SCALE_DEFAULT);  
		icon = new ImageIcon(temp);  
		iconButton.setIcon(icon);  
		iconButton.setOpaque(false);
		iconButton.setContentAreaFilled(false);
		iconButton.setBorderPainted(false);
	}  

	public static void setLabel(String filename,JLabel label){
		ImageIcon icon = new ImageIcon(filename);
		icon.getImage();
		Image temp = icon.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT);  
		icon = new ImageIcon(temp);  
		label.setIcon(icon);
	}
	public JFrame setFrameLocationAndSize(JFrame frame){

		int windowwedth=800;
		int windowheight=600;
		int screenwedth=Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenheight=Toolkit.getDefaultToolkit().getScreenSize().height;
		Container container=frame.getContentPane();
		container.setLayout(null);
		frame.setBounds((screenwedth-windowwedth)/2, (screenheight-windowheight)/2, windowwedth, windowheight); //set position at the center of the screen
		frame.setResizable(false);
		frame.setLocation((screenwedth-windowwedth)/2,  (screenheight-windowheight)/2);
//		this.setSize(windowwedth,windowheight); //set size;
//		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);	
		 		
		
		
		return frame;
	}
		
		public JFrame setMiddle(JFrame frame){
			
			Container container=frame.getContentPane();
			container.setLayout(null);
//			frame.setBounds((screenwedth-windowwedth)/2, (screenheight-windowheight)/2, windowwedth, windowheight); //set position at the center of the screen
			frame.setResizable(false);
			int windowwedth=frame.getWidth();
			int windowheight=frame.getHeight();
			int screenwedth=Toolkit.getDefaultToolkit().getScreenSize().width;
			int screenheight=Toolkit.getDefaultToolkit().getScreenSize().height;
			frame.setLocation((screenwedth-windowwedth)/2,  (screenheight-windowheight)/2);
//			this.setSize(windowwedth,windowheight); //set size;
//			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);	
			frame.repaint();
			return frame;
		}
	
	public String getTime(){
		@SuppressWarnings("deprecation")
		String time = new Date().toLocaleString();
		return time;
	}
	public static void setOpaque(JPanel a){
		 a.setOpaque(false);//设置JPanel为透明   
	}
	public static void setOpaque(JScrollPane s){
		s.setOpaque(false);
		s.getViewport().setOpaque(false); 
	}
	
	public static JPanel setBorder(JPanel mainpanel,String title){
		mainpanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.GRAY, 2), title,
				TitledBorder.RIGHT, TitledBorder.TOP));
		
		return mainpanel;
	}
//	public static void setOpaque)
}
