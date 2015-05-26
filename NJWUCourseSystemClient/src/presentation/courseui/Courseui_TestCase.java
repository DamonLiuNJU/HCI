package presentation.courseui;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class Courseui_TestCase {
	 static	JFrame f;
	public static void main(String arg[]){
		new Courseui_TestCase();
		//测试ApplyComponent.getApplyList() :查看课程申请列表【success
		//					test_getApplyList();
		
		//测试ApplyComponent.getApplyInfo(String courseName):通过课程名查询相应申请内容
						//课程名存在【success
		//					test_getApplyInfo("游动的光影世界");
				
						//课程名不存在（表项选择,此情况一般不会发生）【success
		//					test_getApplyInfo("");
	
		
		
		//测试CourseInfoFrame.CourseInfoFrame:得到包含查询的课程号详细信息的窗口
						//课程号存在【success
		//					test_getCourseInfoFrame("c0001");	
						//课程号不存在（表项选择,此情况一般不会发生）【success
		//					test_getCourseInfoFrame("");
		
		
		//测试CourseInfoTextArea.getCourseInfo:得到包含传入课程号的详细信息的JTextArea
						//课程号存在【success
		//					test_getCourseInfoTextArea("c0001");	
						//课程号不存在【success
		//					test_getCourseInfoTextArea("");
		
		//测试CourseInfoTextArea.setModuleStaticTextArea(String，JTextArea):得到课程模块的统计信息	【fail
		//					test_getModuleStaticsTextArea("");
		//测试CourseListTable.getSelectCourseByModule（String）：得到选定模块的可选课程
		//只显示上半学期可选课程【success
		 				//	test_getCouListByModule("通识课");
						//test_getCouListByModule("体育课");
		
		//测试CourseListTable.getSearchList(String,String,String ,String,String)：通过cno cName tn facultyName  grade
		//查询默认为无条件【success
						//test_getCouList("","","","","");
						//test_getCouList("","","","","上");
			
		//测试CourseListTable.getTeachList(String teacherID):教师查找任课列表【success
						//test_getTeachList("050001");
		//测试CourseListTable.getCourseInfoTable（ArrayList<String>cno）：学生查看选课列表【success
						//ArrayList<String >cno=new ArrayList<String>();
						//cno.add("c0001");
						//cno.add("c2003");
						//test_getChooseList(cno);
		//测试CourseListTable.getSearchTable(String campus,String grade,String facultyName ):学生查询课程【success
						test_getSearchTable("仙林","","");
		
						
		
		
						f.setVisible(true);
	}
	 
	 
	 
	 
	 
	 
	 public Courseui_TestCase(){
					f=new JFrame();
					f.setBounds(0,0,400,400);	
					f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					
		}
	 	
	 
	 //测试ApplyComponent.getApplyList() 查看课程申请列表
	static void test_getApplyList(){
		 JScrollPane sp=new JScrollPane(new ApplyComponent().getApplyList());
		 sp.setBounds(0,0,200,200);
		 f.add(sp);
	 }
	//测试ApplyComponent.getApplyInfo(String courseName):通过课程名查询相应申请内容
	static void test_getApplyInfo(String courseName){
		 JScrollPane sp=new JScrollPane(new ApplyComponent().getApplyInfo(courseName));
		 sp.setBounds(0,0,200,200);
		f.add(sp);
	}
		
	//测试CourseInfoFrame.CourseInfoFrame:得到包含查询的课程号详细信息的窗口
	static void test_getCourseInfoFrame(String courseID){
		new CourseInfoFrame(courseID);
	}
	//测试CourseInfoTextArea.getCourseInfo:得到包含传入课程号的详细信息的JTextArea
	static void test_getCourseInfoTextArea(String courseID){
		 JScrollPane sp=new JScrollPane(new CourseInfoTextArea().getCourseInfo(courseID,15,20));
		 sp.setBounds(0,0,200,200);
		f.add(sp);
	}

	//测试CourseListTable.getSelectCourseByModule（String）：得到选定模块的可选课程
	static void test_getCouListByModule(String module){
		 JScrollPane sp=new JScrollPane(new CourseListTable().getSelectCourseByModule(module));
		 sp.setBounds(0,0,200,200);
		f.add(sp);
	}

	//测试CourseListTable.getSearchList(String,String,String ,String,String)：通过cno cName tn facultyName  grade查询默认为无条件
	static void test_getCouList(String cno,String courseName,String teacherName,String facultyName,String grade){
		 JScrollPane sp=new JScrollPane(new CourseListTable().getSearchList(cno,courseName,teacherName,facultyName,grade));
		 sp.setBounds(0,0,200,200);
		 f.add(sp);
	}
	//测试CourseListTable.getTeachList(String teacherID):教师查找任课列表
	static void test_getTeachList(String teacherID){
		 JScrollPane sp=new JScrollPane(new CourseListTable().getTeachList(teacherID));
		 sp.setBounds(0,0,200,200);
		 f.add(sp);
	}
	//测试CourseListTable.getCourseInfoTable（ArrayList<String>cno）：学生查看选课列表
	static void test_getChooseList(ArrayList<String>cno){
		 JScrollPane sp=new JScrollPane(new CourseListTable().getCourseInfoTable(cno));
		 sp.setBounds(0,0,200,200);
		 f.add(sp); 
	}
	//测试CourseListTable.getSearchTable(String campus,String grade,String facultyName ):学生查询课程
	static void test_getSearchTable(String campus,String grade,String facultyName){
		 JScrollPane sp=new JScrollPane(new CourseListTable().getSearchTable(campus,grade,facultyName));
		 sp.setBounds(0,0,200,200);
		 f.add(sp); 
	}	

	
	
	
}
