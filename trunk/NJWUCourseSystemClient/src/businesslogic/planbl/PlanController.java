package businesslogic.planbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.PlanVO;
import businesslogic.managerbl.Faculty;
import businesslogicservice.planblservice.PlanBLService;

		/**
		 * PlanController的职责在将ui层发来有关院系及院系计划的请求经过初步处理后转发给后台逻辑处理
		 * 
		 * @author vlery
		 *
		 */
		public class PlanController implements PlanBLService{
								Plan plan;	
	/**
	 * 导入教学计划
	 *@param String fTeacherID
	 *@param String planContent
	 *@return void  
	 * @throws RemoteException 数据库连接失败
	 * 
	 */
		@Override
		public void importPlan(String fTeacherID,String content) {
		// TODO Auto-generated method stub
							Faculty faculty=new Faculty(fTeacherID);	
							plan=new Plan(faculty.getFacultyID(),content);
							try {
										plan.importPlan();
							} catch (RemoteException e) {
								// TODO Auto-generated catch block
										e.printStackTrace();
							}
		}
		@Override
		public String  getPlan(String facultyID) {
							// TODO Auto-generated method stub
							plan  = new Plan();
							return plan.getPlan(facultyID);
		}

		/**
		 * 得到院系列表
		 *@param void
		 *@return ArrayList<PlanPO> 
		 * @throws RemoteException 数据库连接失败
		 * 
		 */
		@Override
		public ArrayList<PlanVO> getPlanList() {
							plan=new Plan();
							return plan.getPlanList();
		}
		/**
		 *得到原教学计划
		 *@param String fTeacherID
		 *@return String 
		 * @throws RemoteException 数据库连接失败
		 * 
		 */
		@Override
		public String getOldPlan(String fTeacherID) {
							Faculty faculty=new Faculty(fTeacherID);
							Plan plan=new Plan();
							return plan.getPlan(faculty.getFacultyID());
		}
		
		/**
		 * 得到教学计划
		 *@param String facultyName
		 *@return String 
		 * @throws RemoteException 数据库连接失败
		 * 
		 */
		@Override
		public String getPlanByName(String facultyName) {
			// TODO Auto-generated method stub
			Plan plan=new Plan();
			return plan.getPlan(plan.getFacultyID(facultyName));
		}

}
