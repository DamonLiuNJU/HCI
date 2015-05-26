package vo.statusvo;

import java.util.ArrayList;

import po.statuspo.CourseStatusPO;
import businesslogic.utilitybl.CourseModule;

public class CourseStatusVO {
	ArrayList<String> timeList=new ArrayList<String>();
	
	/**
	 * show as required order:course(必修课(publish,select,quit_add,record score)，选修课，通识课，公选课，体育课)
	 * 字段格式：on_time off_time
	 * @return ArrayList<String>
	 */
	public CourseStatusVO(ArrayList<CourseStatusPO> csList){
		CourseModule[] cm=CourseModule.values();
		String[] type={"publish","select","quit_add","recordscore"};
		for(int i=0;i<cm.length;i++){
			for(int j=0;j<type.length;j++){
				StringBuilder sb=new StringBuilder();
				for(CourseStatusPO cs:csList){
					String m=cs.getModule();
					CourseModule currentModule=cm[i];
					String t=cs.getType();
					if(m.equals(currentModule.toString()) && t.equals(type[j])){
						sb.append(cs.getOnTime()+" "+cs.getOffTime()+"\r\n");
					}
				}
				timeList.add(sb.toString());
			}
		}
	}
	
	public ArrayList<String> getTimeList(){
		return timeList;
	}
	
	
}
