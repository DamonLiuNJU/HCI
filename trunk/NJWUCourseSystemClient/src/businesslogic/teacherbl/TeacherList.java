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
 * 该类处理与教师列表的相关操作
 * 有showGeneralInfo , showDetailInfo , getTeacherList , getFacultyTeacherList
 * 4个提供的public方法
 * plusNum1个private方法
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
	 * 返回的全校的教师统计信息，包含在校教师总数
	 * 讲师数量，教授数量，副教授数量
	 */
	@Override
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
	 * 某个院的教师规模信息
	 * @return ArrayList<String>
	 * 返回的全校的教师统计信息，包含在校教师总数
	 * 讲师数量，教授数量，副教授数量
	 */
	@Override
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
	/*
	 * (non-Javadoc)
	 * @see businesslogicservice.teacherblservice.TeacherListService#getTeacherList(java.lang.String)
	 * 获取一个院系的教师列表，返回TeacherVO的list
	 * TeacherVO包含id,name,seniority,courseCount(课程总数)
	 */
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
	
	/*
	 * 在统计教师信息时调用，增加一个教师
	 */
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
	/*
	 * (non-Javadoc)
	 * @see businesslogicservice.teacherblservice.TeacherListService#getFacultyTeacherList(java.lang.String)
	 * 获取一个教师院系的教师列表，提供的数据是院系教务老师的id
	 * 通过查找其id获取他的院系，再调用getTeacherList方法
	 */
	public ArrayList<TeacherVO> getFacultyTeacherList(String manager_id) {
		// TODO Auto-generated method stub
		String faculty_id = new Faculty(manager_id).getFacultyID();
		String faculty_name = new Plan().getFacultyName(faculty_id);
		
		ArrayList<TeacherVO> teacherArray = this.getTeacherList(faculty_name);
		
		return teacherArray;
	}



	
}
