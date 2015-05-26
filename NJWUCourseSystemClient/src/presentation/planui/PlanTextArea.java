package presentation.planui;

import javax.swing.JTextArea;

import businesslogic.planbl.PlanController;
import businesslogicservice.planblservice.PlanBLService;

public class PlanTextArea {

	//通过院系编号得到选定院系的计划
	public void setPlanTextArea(String facultyName,JTextArea ta){
				PlanBLService pc=new PlanController();
				String content=pc.getPlanByName(facultyName);//从界面得到院系的id
				ta.setText(content);
				ta.updateUI();
				ta.setLineWrap(true);
				ta.setWrapStyleWord(true);
	}
		//通过院系教务老师编号得到本院教学计划
	public JTextArea getPlanByFTeacherID(String fTeacherID,int row,int column){
				PlanController pc=new PlanController();
				String content=pc.getOldPlan(fTeacherID);
				System.out.println(content);
				JTextArea ta=new JTextArea(content,row,column);
				ta.setLineWrap(true);
				ta.setWrapStyleWord(true);
				return ta;
	}
}

