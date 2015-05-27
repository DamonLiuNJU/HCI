package businesslogic.statusbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.statuspo.CourseStatusPO;
import rmiconnector.RemoteDataFactory;
import vo.statusvo.CourseStatusVO;
import businesslogic.utilitybl.CourseModule;
import dataservice.statusdataservice.CourseStatusDataService;

public class CourseStatus extends Status{  
   CourseModule module;
   CourseStatusDataService data;
   
   public CourseStatus(CourseModule m){
	   module=m;
	   init();
   }
   
   public CourseStatus(){
	   init();
   }
   
   @Override
   public void init() {
	   data=(CourseStatusDataService) new RemoteDataFactory().getData("CourseStatus");
   }

   @Override
   public final CourseStatusVO getTimeList() {
	   ArrayList<CourseStatusPO> csList=new ArrayList<CourseStatusPO>();
	try {
		csList = data.finds();
	} catch (RemoteException e) {
		e.printStackTrace();
	}		
	return new CourseStatusVO(csList);
   }
}
