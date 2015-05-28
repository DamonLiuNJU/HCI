package presentation.deanui;

import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class MainAuditPane extends JTabbedPane{
	static char lastCharAsFlag='1';	//根据系统设定的id格式，学校教务处老师中只有主教务老师id最后一位为'1'
	
	public MainAuditPane(String id){
		this.setTabPlacement(LEFT);
		
		String title0="查看审核";
		this.addTab(title0, new AuditPane());
		
		String title1="课程审核";
		this.addTab(title1, new CourseAuditPane(id));
		
		if(id.charAt(id.length()-1)==lastCharAsFlag){
			String title2="课程触发";
			this.addTab(title2, new GCourseSelectTriggerPane());
		}
	}
}
