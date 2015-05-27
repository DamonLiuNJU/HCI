package dataservice;

import java.rmi.RemoteException;

import dataservice.coursedataservice.CourseDataService;
import dataservice.framedataservice.CreditRestrictDataService;
import dataservice.framedataservice.FrameDataService;
import dataservice.plandataservice.PlanDataService;
import dataservice.statusdataservice.CourseStatusDataService;
import dataservice.statusdataservice.ManageStatusDataService;

public interface DataFactory extends DataService{
   public FrameDataService getFrameDataBase() throws RemoteException;
   
   public CreditRestrictDataService getCreditRestrictData() throws RemoteException;
   
   public PlanDataService getPlanDataBase() throws RemoteException;
   
   public CourseDataService getCourseDataBase() throws RemoteException;
   
   public ManageStatusDataService getManageStatusDataBase() throws RemoteException;
   
   public CourseStatusDataService getCourseStatusDataBase() throws RemoteException;
}
