package businesslogic.statusbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.statuspo.CourseStatusPO;
import rmiconnector.RemoteDataFactory;
import vo.statusvo.CourseStatusVO;
import businesslogic.utilitybl.CourseModule;
import dataservice.statusdataservice.CourseStatusDataService;

/**
 * 课程状态类，继承抽象类Status
 * @author cbb
 *
 */
public class CourseStatus extends Status {
	CourseModule module;
	CourseStatusDataService data;

	public CourseStatus(CourseModule m) {
		module = m;
		init();
	}

	public CourseStatus() {
		init();
	}

	/**
	 * 初始化课程状态的data对象
	 */
	@Override
	public void init() {
		data = (CourseStatusDataService) new RemoteDataFactory()
				.getData("CourseStatus");
	}

	/**
	 * 得到课程各项限制的时间列表
	 * @return CourseStatusVO
	 */
	@Override
	public final CourseStatusVO getTimeList() {
		ArrayList<CourseStatusPO> csList = new ArrayList<CourseStatusPO>();
		try {
			csList = data.finds();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return new CourseStatusVO(csList);
	}
}
