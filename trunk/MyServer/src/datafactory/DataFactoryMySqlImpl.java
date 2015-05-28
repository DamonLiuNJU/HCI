package datafactory;


import java.rmi.RemoteException;

import po.FramePO;

import data.CourseData;
import data.framedata.FrameDataServiceMySqlImpl;
import data.statusdata.CourseStatusDataServiceMySqlImpl;
import data.statusdata.ManageStatusDataServiceMySqlImpl;
import dataservice.CourseDataService;
import dataservice.FrameDataService;
import dataservice.PlanDataService;
import dataservice.statusdataservice.CourseStatusDataService;
import dataservice.statusdataservice.ManageStatusDataService;

//provide for getting all tables' operating methods
public class DataFactoryMySqlImpl implements DataFactory{
    FrameDataService frameData=new FrameDataServiceMySqlImpl();
    //PlanDataService planData=new PlanDataServiceMySqlImpl();
    //CourseDataService courseData=new CourseData();
    ManageStatusDataService mstatusData=new ManageStatusDataServiceMySqlImpl();
    CourseStatusDataService cstatusData=new CourseStatusDataServiceMySqlImpl(); 
    
    public DataFactoryMySqlImpl() throws RemoteException{
    	
    }
    
	@Override
	public FrameDataService getFrameDataBase() {		
		return frameData;
	}
	
	public PlanDataService getPlanDataBase(){
		return null;
		//return planData;
	}

	@Override
	public CourseDataService getCourseDataBase() {
		return null;
		//return courseData;
	}
	
	public ManageStatusDataService getManageStatusData(){
		return mstatusData;
	}
	
	public CourseStatusDataService getCourseStatusData(){
		return cstatusData;
	}
	//test
	public static void main(String []args){
		FrameDataService data;
		try {
			data = new DataFactoryMySqlImpl().getFrameDataBase();
			data.insert(new FramePO(2,"培养方针"));	
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
