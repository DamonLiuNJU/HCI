package data.statusdata;

import data.DataBaseHelper;
import dataservice.statusdataservice.ManageStatusDataService;

public class ManageStatusDataServiceMySqlImpl implements ManageStatusDataService{
	 String tableName="managestatus";
	 
	 DataBaseHelper db=new DataBaseHelper();
}
