package businesslogic.statusbl;

import java.rmi.RemoteException;

import po.statuspo.CourseStatusPO;
import businesslogic.utilitybl.CourseModule;

/**
 * 选择课程的状态限制类
 * @author cbb
 *
 */
public class SelectCourseStatus extends CourseStatus {

	public SelectCourseStatus(CourseModule m) {
		super(m);
	}

	@Override
	public void init() {
		super.init();
		CourseStatusPO csp;
		try {
			String module_string = module.toString();
			csp = data.find(module_string, "select");
			on = new BoundDate(csp.getOnTime());
			off = new BoundDate(csp.getOffTime());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	} 

	@Override
	public void setTime(BoundDate ft1, BoundDate ft2) {
		super.setTime(ft1, ft2);
		try {
			data.update(new CourseStatusPO(module.toString(), "select", on
					.toString(), off.toString()));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
