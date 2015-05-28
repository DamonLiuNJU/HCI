package presentation.tools;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class Setter {

	public void setButtonUnOpaque(JButton button){
		button.setContentAreaFilled(false);
    	button.setBorderPainted(false);
	}
	
	public void setButtonWithImage(JButton button){
		setButtonUnOpaque(button);
		button.setVerticalTextPosition(SwingConstants.BOTTOM);
    	button.setHorizontalTextPosition(SwingConstants.CENTER);
	}
	
	//设置标题
	public void setBorderTitle(JPanel panel,String title){
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY,2)
				,title,TitledBorder.LEFT,TitledBorder.TOP));
	}
	
	//为frame添加背景
	public void addBackground(JFrame frame,String backPath){
	   	 
		ImageIcon background = new ImageIcon(backPath);// 背景图片
		JLabel label1 = new JLabel(background);// 把背景图片显示在一个标签里面	
		
		// 把标签的大小位置设置为图片刚好填充整个面板
		label1.setBounds(0, 0, background.getIconWidth(),
				background.getIconHeight());
		
		// 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
		JPanel  imagePanel1 = (JPanel) frame.getContentPane();
		imagePanel1.setOpaque(false);
		frame.getLayeredPane().setLayout(null);
										
		// 把背景图片添加到分层窗格的最底层作为背景
		frame.getLayeredPane().add(label1, new Integer(Integer.MIN_VALUE));
    }
}
