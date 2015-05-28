package businesslogicservice.planblservice;
  import java.util.ArrayList;

import vo.PlanVO;

  public interface  PlanBLService {
       //传入教学计划
		public void  importPlan(String fTeacherID,String conten);
        //根据院系编号得到院系教学计划
		public  String getPlan(String facultyID);	
        //得到院系的列表
		public ArrayList<PlanVO>  getPlanList();
		//根据院系教务老师的id获得本院系的教学计划
		public String getOldPlan(String fTeacherID);
		//根据院系名得到教学计划
		public String getPlanByName(String facultyName) ;
  }
