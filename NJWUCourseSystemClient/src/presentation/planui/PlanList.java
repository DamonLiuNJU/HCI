package presentation.planui;

import java.util.ArrayList;

import javax.swing.JComboBox;

import vo.PlanVO;
import businesslogic.planbl.PlanController;
import businesslogicservice.planblservice.PlanBLService;
	
public class PlanList {

	// JComboBox ：院系名
	public JComboBox<String> getFacultyComboBox() {
		PlanBLService plan = new PlanController();
		ArrayList<PlanVO> pvList = plan.getPlanList();
		String[] list = new String[pvList.size() + 1];
		list[0] = "";
		for (int i = 0; i < pvList.size(); i++) {
			list[i] = pvList.get(i).getName();
		}
		list[pvList.size()] = "";
		return new JComboBox<String>(list);
	}
}
