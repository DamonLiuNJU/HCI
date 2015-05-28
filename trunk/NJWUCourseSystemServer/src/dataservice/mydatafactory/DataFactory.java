package dataservice.mydatafactory;

import java.rmi.RemoteException;

import dataservice.coursedataservice.CourseDataService;
import dataservice.framedataservice.CreditRestrictDataService;
import dataservice.framedataservice.FrameDataService;
import dataservice.framedataservice.FrameRemarkDataService;
import dataservice.plandataservice.PlanDataService;
import dataservice.statusdataservice.CourseStatusDataService;
import dataservice.statusdataservice.ManageStatusDataService;

public interface DataFactory extends SqlDataFactory,TxtDataFactory{
   public FrameDataService getFrameData() throws RemoteException;
   
   public CreditRestrictDataService getCreditRestrictData() throws RemoteException;
   
   public FrameRemarkDataService getFrameRemarkData() throws RemoteException;
   
   public PlanDataService getPlanData() throws RemoteException;
   
   public CourseDataService getCourseData() throws RemoteException;
   
   public ManageStatusDataService getManageStatusData() throws RemoteException;
   
   public CourseStatusDataService getCourseStatusData() throws RemoteException;
}
