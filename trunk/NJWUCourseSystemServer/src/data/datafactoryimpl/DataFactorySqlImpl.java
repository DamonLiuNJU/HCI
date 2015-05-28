package data.datafactoryimpl;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import data.TeacherData;
import data.coursedata.ApplyData;
import data.coursedata.CourseData;
import data.courseselectiondata.SelectCourseRecordMySql;
import data.courseselectiondata.TempSelectionMySql;
import data.managerdata.AdminData;
import data.managerdata.ManagerData;
import data.managerdata.MessageData;
import data.plandata.PlanData;
import data.statusdata.CourseStatusDataServiceMySqlImpl;
import data.statusdata.ManageStatusDataServiceMySqlImpl;
import data.studentdata.MajorTransferMySql;
import data.studentdata.StudentDataMySql;
import dataservice.TeacherDataService;
import dataservice.coursedataservice.ApplyDataService;
import dataservice.coursedataservice.CourseDataService;
import dataservice.courseselectiondataservice.SelectCourseRecordDataService;
import dataservice.courseselectiondataservice.TempSelectionDataService;
import dataservice.managerdataservice.AdminDataService;
import dataservice.managerdataservice.ManagerDataService;
import dataservice.managerdataservice.MessageDataService;
import dataservice.mydatafactory.SqlDataFactory;
import dataservice.plandataservice.PlanDataService;
import dataservice.statusdataservice.CourseStatusDataService;
import dataservice.statusdataservice.ManageStatusDataService;
import dataservice.studentdataservice.MajorTransferDataService;
import dataservice.studentdataservice.StudentDataService;

//provide for getting all tables' operating methods
@SuppressWarnings("serial")
public class DataFactorySqlImpl extends UnicastRemoteObject implements SqlDataFactory{
    PlanDataService planData;
    CourseDataService courseData;
    ApplyDataService applyData;
    SelectCourseRecordDataService selectData;
    TempSelectionDataService tempData;
    AdminDataService adminData;
    ManagerDataService managerData;
    MessageDataService messageData;
    ManageStatusDataService mstatusData;
    CourseStatusDataService cstatusData; 
    MajorTransferDataService majorData;
    StudentDataService studentData;
    TeacherDataService teacherData;
    
    public DataFactorySqlImpl() throws RemoteException{
    	planData=new PlanData();
    	courseData=new CourseData();
    	applyData=new ApplyData();
    	selectData=new SelectCourseRecordMySql();
    	tempData=new TempSelectionMySql();
    	adminData=new AdminData();
    	managerData=new ManagerData();
    	messageData=new MessageData();
    	mstatusData=new ManageStatusDataServiceMySqlImpl();
    	cstatusData=new CourseStatusDataServiceMySqlImpl(); 
    	majorData=new MajorTransferMySql();
    	studentData=new StudentDataMySql();
    	teacherData=new TeacherData();
    }
	
	public PlanDataService getPlanData(){
		return planData;
	}

	public CourseDataService getCourseData() {
		return courseData;
	}
	
	public ApplyDataService getCourseApplyData() {
		return applyData;
	}
	
	public SelectCourseRecordDataService getSelectCourseRecordData(){
		return selectData;
	}
	
	public TempSelectionDataService getTempSelectionData(){
		return tempData;
	}
	
	public AdminDataService getAdminData(){
		return adminData;
	}
	
	public ManagerDataService getManagerData(){
		return managerData;
	}
	
	public MessageDataService getMessageData(){
		return messageData;
	}
		
	public ManageStatusDataService getManageStatusData(){
		return mstatusData;
	}
	
	public CourseStatusDataService getCourseStatusData(){
		return cstatusData;
	}
	
	public MajorTransferDataService getMajorTransferData(){
		return majorData;
	}
	
	public StudentDataService getStudentData(){
		return studentData;
	}
	
	public TeacherDataService getTeacherData(){
		return teacherData;
	}
}
