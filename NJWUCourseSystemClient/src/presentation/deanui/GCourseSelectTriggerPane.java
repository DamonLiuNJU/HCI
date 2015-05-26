package presentation.deanui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class GCourseSelectTriggerPane extends JPanel{

	public GCourseSelectTriggerPane(){
		this.setLayout(new MigLayout());
		this.setOpaque(false);
		
		ImageIcon img = new ImageIcon("./icon/head2.png");
		JPanel pa=new JPanel(new MigLayout());
		pa.add(new JLabel(img));
	
		
		String info="*提醒：点此星形按钮将触发通识课与公选课正式开始筛选学生。";
		JLabel la2=new JLabel(info);
		
		JButton triggerButton=new GCourseSelectTriggerButton();
		
		this.add(pa,"wrap");
		this.add(la2,"wrap,gaptop 15,gapleft 160");
		this.add(triggerButton,"gaptop 15,gapleft 350");
	}
}
