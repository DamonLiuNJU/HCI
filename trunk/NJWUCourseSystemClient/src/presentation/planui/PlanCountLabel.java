package presentation.planui;

import javax.swing.JLabel;

import businesslogic.planbl.Plan;

@SuppressWarnings("serial")
public class PlanCountLabel extends JLabel{
	public PlanCountLabel(){
		String info="*至今尚未提交有效教学计划的院系数： ";
		int count=new Plan().getEmptyPlanNum();
		this.setText(info+count);
	}
}
