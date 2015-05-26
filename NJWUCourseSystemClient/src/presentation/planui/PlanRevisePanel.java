package presentation.planui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import presentation.facultyui.GUIHelper;
import businesslogic.planbl.PlanController;
import businesslogicservice.planblservice.PlanBLService;

public class PlanRevisePanel {
	
	int frameWidth;
	int frameHeight;
	String oldPlan;
	String id;
	JTextArea plan;
	public PlanRevisePanel(String id){
		this.id=id;
		frameWidth=GUIHelper.getFrameWidth();
		frameHeight=GUIHelper.getFrameHeight();
		PlanTextArea pta=new PlanTextArea();
		plan=pta.getPlanByFTeacherID(id,15,50);
		oldPlan=plan.getText();
	}
public 	JPanel getRevisePanel(){
	JPanel panel=new JPanel();
	panel.setBounds(0,0,frameWidth,frameHeight*2/3);
	panel.setLayout(null);
	panel.add(getYourPlanLabel());
	panel.add(getOldPlan());
	panel.add(getConfirmBut());
	panel.add(getCancelBut());
	panel.setOpaque(false);																													//
	return panel;
} 
JLabel getYourPlanLabel(){
	JLabel plan=new JLabel("原教学计划：");
	plan.setFont(new Font("微软雅黑",0,13));
	plan.setBounds(0,0,frameWidth/4,frameHeight/15);
	return plan;
}
	
JScrollPane getOldPlan(){ 
	JScrollPane planSp= new JScrollPane(plan);  
	planSp.setBounds(frameWidth/20,frameHeight/15,frameWidth*9/10,frameHeight/2);
	planSp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
	planSp.setVisible(true);
	return planSp;
}

JButton getConfirmBut(){
	JButton confirm=new JButton("确认");
	confirm.setFont(new Font("微软雅黑",0,13));
confirm.setBounds(frameWidth*20/30,frameHeight*6/10,frameWidth/10,frameHeight/20);
	confirm.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){	
			updatePlan(plan.getText());																																																				//修改  plan
			oldPlan=plan.getText();
			GUIHelper.sendMessage("修改成功！");		
		}
	});
	return confirm;
}
	
JButton getCancelBut(){
JButton cancel=new JButton("取消");
cancel.setFont(new Font("微软雅黑",0,13));
	cancel.setBounds(frameWidth*24/30,frameHeight*6/10,frameWidth/10,frameHeight/20);
	cancel.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){	
		plan.setText(oldPlan);
		plan.updateUI();
			}
});
return cancel;
}
void updatePlan( String planContent){
	PlanBLService plan=new PlanController();													
	plan.importPlan(id,planContent);
}
	
}
