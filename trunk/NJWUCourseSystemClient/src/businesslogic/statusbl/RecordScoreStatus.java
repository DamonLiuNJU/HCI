package businesslogic.statusbl;

import java.rmi.RemoteException;

import po.statuspo.CourseStatusPO;
import businesslogic.utilitybl.CourseModule;

/**
 * 登记成绩的状态限制类
 * @author cbb
 *
 */
public class RecordScoreStatus extends CourseStatus {

	public RecordScoreStatus(CourseModule m) {
		super(m);
	}

	@Override
	public void init() {
		super.init();
		CourseStatusPO csp;
		try {
			csp = data.find(module.toString(), "recordscore");
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
			data.update(new CourseStatusPO(module.toString(), "recordscore", on
					.toString(), off.toString()));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
