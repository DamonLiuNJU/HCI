package dataservice.mydatafactory;

import java.rmi.RemoteException;

import dataservice.DataService;
import dataservice.TeacherDataService;
import dataservice.coursedataservice.ApplyDataService;
import dataservice.coursedataservice.CourseDataService;
import dataservice.courseselectiondataservice.SelectCourseRecordDataService;
import dataservice.courseselectiondataservice.TempSelectionDataService;
import dataservice.managerdataservice.AdminDataService;
import dataservice.managerdataservice.ManagerDataService;
import dataservice.managerdataservice.MessageDataService;
import dataservice.plandataservice.PlanDataService;
import dataservice.statusdataservice.CourseStatusDataService;
import dataservice.statusdataservice.ManageStatusDataService;
import dataservice.studentdataservice.MajorTransferDataService;
import dataservice.studentdataservice.StudentDataService;

public interface SqlDataFactory extends DataService{
	public PlanDataService getPlanData() throws RemoteException;

	public CourseDataService getCourseData() throws RemoteException;
	
	public ManageStatusDataService getManageStatusData() throws RemoteException;
	
	public CourseStatusDataService getCourseStatusData() throws RemoteException;
	
	public ApplyDataService getCourseApplyData() throws RemoteException;
	
	public SelectCourseRecordDataService getSelectCourseRecordData() throws RemoteException;
	
	public TempSelectionDataService getTempSelectionData() throws RemoteException;
	
	public AdminDataService getAdminData() throws RemoteException;
	
	public ManagerDataService getManagerData() throws RemoteException;
	
	public MessageDataService getMessageData() throws RemoteException;
	
	public MajorTransferDataService getMajorTransferData() throws RemoteException;
	
	public StudentDataService getStudentData() throws RemoteException;
	
	public TeacherDataService getTeacherData() throws RemoteException;
}
