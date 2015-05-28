package datafactory;

import dataservice.*;
import dataservice.statusdataservice.CourseStatusDataService;
import dataservice.statusdataservice.ManageStatusDataService;

public interface DataFactory {
   public FrameDataService getFrameDataBase();
   
   public PlanDataService getPlanDataBase();
   
   public CourseDataService getCourseDataBase();
   
   public ManageStatusDataService getManageStatusData();
   
   public CourseStatusDataService getCourseStatusData();
}
