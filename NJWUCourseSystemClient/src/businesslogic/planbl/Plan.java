package businesslogic.planbl;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.planpo.FacultyPO;
import rmiconnector.RemoteDataFactory;
import vo.PlanVO;
import dataservice.plandataservice.PlanDataService;
public class Plan {
			String id; //院系id
			String plan;//院系教学计划
			PlanDataService  pds=(PlanDataService)new RemoteDataFactory().getData("Plan");
	
	public  static void main(String arg[]){
		Plan plan=new Plan();
//	Faculty f=new Faculty();
	System.out.println(plan.getFacultyName("004"));
	//	System.out.println(	plan.showContent("001"));
	
	} 
	
	//构造方法
	public Plan(){
			id=null;
			plan=null;	
	}
	//构造方法：此处id为发布plan的教务员的教工号
	public Plan(String  id,String plan){
			this.id=id;
			this.plan=plan;
	}
	//院系id—>院系教学计划:教学计划默认为“”
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
	//院系id 院系名
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
	//更新院系教学计划(前置条件使用有参数的构造方法)
	public void  importPlan() throws RemoteException  {
			FacultyPO fp=new FacultyPO();
			fp = pds.find(id);
			fp.setPlan(plan);
			pds.update(fp);
	}
	
	//根据院系id获得院系的名称  若传入院系号未找到相应院系名返回“error”
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
	//根据院系名得到院系id
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
}