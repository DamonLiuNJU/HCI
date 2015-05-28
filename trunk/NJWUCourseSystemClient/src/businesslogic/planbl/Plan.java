package businesslogic.planbl;
import java.rmi.RemoteException;
import java.util.ArrayList;
import po.planpo.FacultyPO;
import rmiconnector.RemoteDataFactory;
import vo.PlanVO;
import dataservice.plandataservice.PlanDataService;
/**
 * Plan用于处理PlanController发来的请求通识完成与bl层其他模块的类质之间的协作
 * @author vlery
 *
 */

public class Plan {
			String id; //院系id
			String plan;//院系教学计划
			PlanDataService  pds=(PlanDataService)new RemoteDataFactory().getData("Plan");
	
			/**
			 *  构造方法
			 * 
			 */
	public Plan(){
			id=null;
			plan=null;	
	}

	
	
	
	/**
	 *构造方法
	 * 
	 */
	public Plan(String  id,String plan){
			this.id=id;
			System.out.println(id);
			this.plan=plan;
	}
	/**
	 *得到教学计划
	 *@param String facultyID 
	 *@return String 
	 * @throws RemoteException 数据库连接失败
	 * 
	 */
	public String getPlan(String facultyID) {		  
			String s="error";
			FacultyPO fp;
			try {
					fp = pds.find(facultyID);
					s=fp.getPlan();
			} catch (RemoteException e) {
					e.printStackTrace();
			}			
			if(s.equals("")){
					return "教学计划还未上传";
			}else{
					return s;
			}	 
	}

	/**
	 *得到院系列表
	 *@param void
	 *@return ArrayList<PlanVO>
	 * @throws RemoteException 数据库连接失败
	 * 
	 */
	public ArrayList<PlanVO > 	getPlanList(){
			ArrayList<FacultyPO>fpList=new ArrayList<FacultyPO>();
			try {
					fpList=pds.finds();
			} catch (RemoteException e) {
					e.printStackTrace();
			}
			ArrayList<PlanVO> list=new ArrayList<PlanVO>();
			for(int i=0;i<fpList.size();i++){
					list.add(new PlanVO(fpList.get(i).getFacultyID(),fpList.get(i).getFacultyName()));
			}		
			return list; 
	}
	
	/**
	 *上传/更新教学计划
	 *前置条件使用有参数的构造方法
	 *@param  void
	 *@return  void
	 * @throws RemoteException 数据库连接失败
	 * 
	 */
	public void  importPlan() throws RemoteException  {
			FacultyPO fp=new FacultyPO();
			fp = pds.find(id);
			fp.setPlan(plan);
			pds.update(fp);
	}
	
	/**
	 *根据院系id获得院系的名称  
	 *若传入院系号未找到相应院系名返回“error”
	 *@param String  facultyID
	 *@return  String
	 * @throws RemoteException 数据库连接失败
	 * 
	 */
	public String getFacultyName(String facultyID) {
			FacultyPO fp;
			try {
					fp = pds.find(facultyID);
					return fp.getFacultyName();	
			} catch (RemoteException e) {
			// TODO Auto-generated catch block
					e.printStackTrace();
			}
			System.out.println("根据院系id未找到相应院系");
			return "error";	 
	}
	
	/**
	 *根据院系名得到院系id
	 *@param String facultyName
	 *@return  String
	 * @throws RemoteException 数据库连接失败
	 * 
	 */
	public String getFacultyID(String facultyName){
			ArrayList<FacultyPO>fpList=new ArrayList<FacultyPO>();
			try {
					fpList=pds.finds();
			} catch (RemoteException e) {
					e.printStackTrace();
			}			 
			for(int i=0;i<fpList.size();i++){
					if(fpList.get(i).getFacultyName().equals(facultyName)){
						return fpList.get(i).getFacultyID();
					}
			}
			return "error";
		}
    
	/**
	 *得到院系plan未上传的个数
	 *@param  void
	 *@return  int
	 * @throws RemoteException 数据库连接失败
	 * 
	 */
	public int getEmptyPlanNum(){
		int n=0;
		ArrayList<FacultyPO>fpList=new ArrayList<FacultyPO>();
		try {
				fpList=pds.finds();
		} catch (RemoteException e) {
				e.printStackTrace();
		}
		for(int i=0;i<fpList.size();i++){
			System.out.println(fpList.get(i).getPlan());
			if(fpList.get(i).getPlan().equals("")){
				
				n++;
			}
		}
		return n;
	}

	
}