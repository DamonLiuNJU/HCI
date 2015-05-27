package businesslogic.teacherbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.TeacherPO;
import rmiconnector.RemoteDataFactory;
import vo.TeacherVO;
import businesslogic.managerbl.Faculty;
import businesslogic.planbl.Plan;
import businesslogicservice.teacherblservice.TeacherListService;
import dataservice.TeacherDataService;
/*
 * 全校：教师总数，各个职称的教师数
 * 院系：教师总数，各个职称的教师数；
 *     
 */
public class TeacherList implements TeacherListService{
	
	private ArrayList<TeacherPO> teacherArray = new ArrayList<TeacherPO>();
	private int teacherNumber;//在校教师总数
	private int teacher ;//讲师数量
	private int professor ;//教授数量
	private int associateProfessor ;//副教授数量
	
	/*
	 * 全校教师规模信息
	 * @return ArrayList<String>
	 */
	public ArrayList<String> showGeneralInfo(){
		teacherArray.clear();
		teacherNumber = 0 ;
		teacher = 0;
		professor = 0;
		associateProfessor = 0;
		RemoteDataFactory factory = new RemoteDataFactory();
		TeacherDataService teacherData = (TeacherDataService)factory.getData("Teacher");
		
		try {
			
			teacherArray = teacherData.findAll();
			teacherNumber = teacherArray.size();
			for(int i=0 ; i<teacherArray.size() ; i++){
				plusNum(teacherArray.get(i).getSeniority());
			}
			
			ArrayList<String> info = new ArrayList<String>();
			String str1 = "全校教师人数： "+teacherNumber ;
			String str2 = "讲师总数: " + teacher ;
			String str3 = "教授总数： " + professor ;
			String str4 = "副教授总数： " + associateProfessor ;
			
			info.add(str1);
			info.add(str2);
			info.add(str3);
			info.add(str4);
			
			return info;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	/*
	 * id,name,seniority,course count
	 */
	public ArrayList<String> showDetailedInfo(String facultyName){
		teacherArray.clear();
		teacherNumber = 0 ;
		teacher = 0;
		professor = 0;
		associateProfessor = 0;
		RemoteDataFactory factory = new RemoteDataFactory();
		TeacherDataService teacherData = (TeacherDataService) factory.getData("Teacher");
		String faculty_id = new Plan().getFacultyID(facultyName);
		
		try {
			teacherArray = teacherData.finds(faculty_id);
			
			teacherNumber = teacherArray.size();
			for(int i=0 ; i<teacherArray.size() ; i++){
				plusNum(teacherArray.get(i).getSeniority());
			}
			
			ArrayList<String> info = new ArrayList<String>();
			String str1 = "全院教师人数： "+teacherNumber ;
			String str2 = "讲师总数: " + teacher ;
			String str3 = "教授总数： " + professor ;
			String str4 = "副教授总数： " + associateProfessor ;
			
			info.add(str1);
			info.add(str2);
			info.add(str3);
			info.add(str4);
			
			return info ;
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null ;
		}
		

	}
	
	@Override
	public ArrayList<TeacherVO> getTeacherList(String faculty_name) {
		// TODO Auto-generated method stub
		teacherArray.clear();
		RemoteDataFactory factory = new RemoteDataFactory();
		TeacherDataService teacherData = (TeacherDataService) factory.getData("Teacher");
		String faculty_id = new Plan().getFacultyID(faculty_name);
		try {
			ArrayList<TeacherVO> tvArray = new ArrayList<TeacherVO>();
			
			teacherArray = teacherData.finds(faculty_id);
			for(int i=0 ; i<teacherArray.size();i++){
				TeacherPO tp = teacherArray.get(i);
				Teacher teacher = new Teacher(tp.getId());
				teacher.initTeacher();
				TeacherVO tv = new TeacherVO(teacher);
				tvArray.add(tv);
			}
			return tvArray;
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return null;
	}
	
	private void plusNum(String type){
		if(type.equals("教授"))
			professor+=1;
		else if(type.equals("副教授"))
			associateProfessor+=1;
		else if(type.equals("讲师"))
			teacher+=1;
		else {
			System.out.println("没有该类型");
			return ;
		}
	}

	@Override
	public ArrayList<TeacherVO> getSearchList(String manager_id ,String teacher_name) {
		// TODO Auto-generated method stub
		String faculty_id = new Faculty(manager_id).getFacultyID();
		String faculty_name = new Plan().getFacultyName(faculty_id);
		ArrayList<TeacherVO> temp = this.getTeacherList(faculty_name);
		ArrayList<TeacherVO> result = new ArrayList<TeacherVO>();
		for(int i=0 ; i<temp.size() ; i++){
			if(temp.get(i).getName().equals(teacher_name)){
				result.add(temp.get(i));
			}
		}
		return result;
	}

	
}
