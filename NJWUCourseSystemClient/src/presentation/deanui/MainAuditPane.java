package presentation.deanui;

import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class MainAuditPane extends JTabbedPane{
	public MainAuditPane(String id){
		this.setTabPlacement(LEFT);
		
		String title0="查看审核";
		this.addTab(title0, new AuditPane());
		
		String title1="课程审核";
		this.addTab(title1, new CourseAuditPane(id));
		
		//if id fits, add trigger tab panel
		if(id.equals("11")){
			String title2="选课触发";
			this.addTab(title2, new GCourseSelectTriggerPane());
		}
	}
}
