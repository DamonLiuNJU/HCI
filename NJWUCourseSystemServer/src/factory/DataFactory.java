package factory;

import source.ServerReplyMessage;
import data.TeacherData;
import data.coursedata.ApplyData;
import data.coursedata.CourseData;
import data.courseselectiondata.SelectCourseRecordMySql;
import data.courseselectiondata.TempSelectionMySql;
import data.datafactory.DataFactoryMySqlImpl;
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
import dataservice.DataService;


public class DataFactory implements ServerReplyMessage{

	public DataService createData(String type) {
		try{
			if(type.equals("Teacher")){
				return new TeacherData();
			}
			else if(type.equals("Student")){
				return new StudentDataMySql();
			}
			else if(type.equals("Admin")){
				return new AdminData() ;
			}
			else if(type.equals("Plan")){
				return  new  PlanData();
			}
			else if(type.equals("Course")){
				return new CourseData();
			}else if(type.equals("Apply")){
				return new ApplyData();
			}else if(type.equals("Frame")){
				return new FrameDataServiceTxtImpl();
			}else if(type.equals("ManageStatus")){
				return new ManageStatusDataServiceMySqlImpl();
			}else if(type.equals("CourseStatus")){
				return new CourseStatusDataServiceMySqlImpl();
			}else if(type.equals("DataFactory")){
				return new DataFactoryMySqlImpl();
			}else if(type.equals("Manager")){
				return new ManagerData();
			}else if(type.equals("Message")){
				return new MessageData();
			}else if(type.equals("MajorTransfer")){
				return new MajorTransferMySql();
			}else if(type.equals("SelectCourseRecord")){
				return new SelectCourseRecordMySql();
			}else if(type.equals("StudentRegistry")){
				return new StudentRegistryMySql();
			}else if(type.equals("TempSelection")){
				return new TempSelectionMySql();
			}else if(type.equals("FrameRemark")){
				return new FrameRemarkDataServiceTxtImpl();
			}else if(type.equals("CreditRestrict")){
				return new CreditRestrictDataTxtImpl();
			}
			else{
				System.out.println(NO_TYPE_EXCEPTION);
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
}
