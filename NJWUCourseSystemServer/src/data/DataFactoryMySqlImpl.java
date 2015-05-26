package data;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import po.framepo.FramePO;

import data.coursedata.CourseData;
import data.framedata.FrameDataServiceTxtImpl;
import data.plandata.PlanData;
import data.statusdata.CourseStatusDataServiceMySqlImpl;
import data.statusdata.ManageStatusDataServiceMySqlImpl;
import dataservice.DataFactory;
import dataservice.coursedataservice.CourseDataService;
import dataservice.framedataservice.FrameDataService;
import dataservice.plandataservice.PlanDataService;
import dataservice.statusdataservice.CourseStatusDataService;
import dataservice.statusdataservice.ManageStatusDataService;

//provide for getting all tables' operating methods
@SuppressWarnings("serial")
public class DataFactoryMySqlImpl extends UnicastRemoteObject implements DataFactory{
    FrameDataService frameData=new FrameDataServiceTxtImpl();
    PlanDataService planData=new PlanData();
    CourseDataService courseData=new CourseData();
    ManageStatusDataService mstatusData=new ManageStatusDataServiceMySqlImpl();
    CourseStatusDataService cstatusData=new CourseStatusDataServiceMySqlImpl(); 
    
    public DataFactoryMySqlImpl() throws RemoteException{
    	
    }
    
	@Override
	public FrameDataService getFrameDataBase() {		
		return frameData;
	}
	
	public PlanDataService getPlanDataBase(){
		return planData;
	}

	@Override
	public CourseDataService getCourseDataBase() {
		return courseData;
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

	@Override
	public ManageStatusDataService getManageStatusDataBase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CourseStatusDataService getCourseStatusDataBase() {
		// TODO Auto-generated method stub
		return null;
	}
}
