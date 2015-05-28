package presentation.deanui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.Timer;

import presentation.courseselectionui.GCourseSelectTriggerButton;
import presentation.courseselectionui.TransferButton;
import presentation.tools.OutputHelper;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class GCourseSelectTriggerPane extends JPanel implements DeanUIImage{
	Timer timer;
	int count;
	
	static JButton triggerButton;
	
	public GCourseSelectTriggerPane(){
		this.setLayout(new MigLayout());
		this.setOpaque(false);
		
		JPanel pa=new MovingIconPanel(subHead);
		pa.setOpaque(false);
		pa.setPreferredSize(new Dimension(260,200));	
		
		String info="*提醒：点此星形按钮将触发通识课与公选课正式开始筛选学生。";
		JLabel la2=new JLabel(info);
		
		triggerButton=new GCourseSelectTriggerButton(timer);
		
		String info2="*提醒：点此按钮将触发学生补退选的终止。";
		JLabel la3=new JLabel(info2);
		JButton transferButton=new TransferButton();
		
		this.add(pa,"wrap,gaptop 5,gapleft 180");
		this.add(la2,"wrap,gaptop 15,gapleft 160");
		this.add(triggerButton,"gaptop 15,gapleft 320,wrap");
		this.add(new JSeparator(),"growx,gaptop 10,wrap");
		this.add(la3,"wrap,gapleft 225,gaptop 15");
		this.add(transferButton,"gapleft 290,gaptop 15");
	}
	
	class MovingIconPanel extends JPanel{
		private Image image;
		private int xCoordinate=20;
		private int yCoordinate=20;
		
		public MovingIconPanel(String path){
			ImageIcon icon = new ImageIcon(path);
			image=icon.getImage();
			//create a timer
			timer=new Timer(35,new TimerListener());
		}
		
		/*paint iconlabel*/
		@Override
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			if(xCoordinate>(getWidth()-200)){
				xCoordinate=-200;
			}
			xCoordinate+=10;
			g.drawImage(image,xCoordinate,yCoordinate,getWidth(),getHeight(),this);
		}
		class TimerListener implements ActionListener{
			@Override		
			public void actionPerformed(ActionEvent e) {
				if(count<80){
					repaint();
					count++;
				}else{
					timer.stop();
					new OutputHelper().outputToDialog("筛选成功");
					triggerButton.setEnabled(false);
				}
			}
		}		
	}
}
