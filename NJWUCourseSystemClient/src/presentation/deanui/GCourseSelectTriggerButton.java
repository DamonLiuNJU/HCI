package presentation.deanui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class GCourseSelectTriggerButton extends JButton{

	public GCourseSelectTriggerButton(){
		ImageIcon icon = new ImageIcon("./icon/star.png");
		this.setIcon(icon);
		this.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//触发通识及公选课选课算法开始筛选学生
			}			
		});
	}
}
