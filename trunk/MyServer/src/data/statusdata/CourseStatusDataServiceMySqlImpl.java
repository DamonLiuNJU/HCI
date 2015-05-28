package data.statusdata;

import data.DataBaseHelper;
import dataservice.statusdataservice.CourseStatusDataService;

public class CourseStatusDataServiceMySqlImpl implements CourseStatusDataService{
	 String tableName="coursestatus";
	 
	 DataBaseHelper db=new DataBaseHelper();
}
