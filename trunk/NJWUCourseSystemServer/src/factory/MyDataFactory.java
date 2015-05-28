package factory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import data.TeacherData;
import data.coursedata.ApplyData;
import data.coursedata.CourseData;
import data.courseselectiondata.SelectCourseRecordMySql;
import data.courseselectiondata.TempSelectionMySql;
import data.framedata.CreditRestrictDataTxtImpl;
import data.framedata.FrameDataServiceTxtImpl;
import data.framedata.FrameRemarkDataServiceTxtImpl;
import data.managerdata.AdminData;
import data.managerdata.ManagerData;
import data.managerdata.MessageData;
import data.plandata.PlanData;
import data.statusdata.CourseStatusDataServiceMySqlImpl;
import data.statusdata.ManageStatusDataServiceMySqlImpl;
import data.studentdata.MajorTransferMySql;
import data.studentdata.StudentDataMySql;
import data.studentdata.StudentRegistryMySql;
import dataservice.TeacherDataService;
import dataservice.coursedataservice.ApplyDataService;
import dataservice.coursedataservice.CourseDataService;
import dataservice.courseselectiondataservice.SelectCourseRecordDataService;
import dataservice.courseselectiondataservice.TempSelectionDataService;
import dataservice.framedataservice.CreditRestrictDataService;
import dataservice.framedataservice.FrameDataService;
import dataservice.framedataservice.FrameRemarkDataService;
import dataservice.managerdataservice.AdminDataService;
import dataservice.managerdataservice.ManagerDataService;
import dataservice.managerdataservice.MessageDataService;
import dataservice.plandataservice.PlanDataService;
import dataservice.statusdataservice.CourseStatusDataService;
import dataservice.statusdataservice.ManageStatusDataService;
import dataservice.studentdataservice.MajorTransferDataService;
import dataservice.studentdataservice.StudentDataService;
import dataservice.studentdataservice.StudentRegistryDataService;

import source.ServerReplyMessage;

public class MyDataFactory extends UnicastRemoteObject implements ServerReplyMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyDataFactory() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public TeacherDataService getTeacherData(){
		try {
			return new TeacherData();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public StudentDataService getStudentData(){
		try {
			return new StudentDataMySql();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public PlanDataService getPlanData(){
		try {
			return new PlanData();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public CourseDataService getCourseData(){
		try {
			return new CourseData();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public AdminDataService getAdminData(){
		try {
			return new AdminData();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ApplyDataService getApplyData(){
		try {
			return new ApplyData();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ManageStatusDataService getManagerStatusData(){
		try {
			return new ManageStatusDataServiceMySqlImpl();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public FrameDataService getFrameData(){
		try {
			return new FrameDataServiceTxtImpl();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public CourseStatusDataService getCourseStatusData(){
		try {
			return new CourseStatusDataServiceMySqlImpl();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ManagerDataService getManagerData(){
		try {
			return new ManagerData();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public MessageDataService getMessageData(){
		try {
			return new MessageData();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public MajorTransferDataService getMajorTransferData(){
		try {
			return new MajorTransferMySql();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public SelectCourseRecordDataService getSelectCourseRecordData(){
		try {
			return new SelectCourseRecordMySql();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public StudentRegistryDataService getStudentRegistyData(){
		try {
			return new StudentRegistryMySql();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public TempSelectionDataService getTempSelectionData(){
		try {
			return new TempSelectionMySql();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public FrameRemarkDataService getFrameRemarkData(){
		try {
			return new FrameRemarkDataServiceTxtImpl();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public CreditRestrictDataService getCreditRestrictData(){
		try {
			return new CreditRestrictDataTxtImpl();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
//	public TxtDataFactory getTxtDataFactoryData(){
//		try {
//			return new TxtDataFactory();
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
	
}
