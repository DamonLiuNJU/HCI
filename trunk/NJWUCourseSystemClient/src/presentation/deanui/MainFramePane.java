package presentation.deanui;

import javax.swing.JTabbedPane;

import presentation.frameui.CreditRestrictPane;

@SuppressWarnings("serial")
public class MainFramePane extends JTabbedPane{

	public MainFramePane(){
		this.setTabPlacement(LEFT);
		
		String title0="学分设置";
		this.addTab(title0,new CreditRestrictPane());	
		
		String title1="框架条目";
		this.addTab(title1, new FramePane());
	}
}
