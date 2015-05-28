

import javax.swing.JFrame;

import presentation.courseui.ApplyComponent;


public class Applyui_TestCase {
	 static	JFrame f;
	public Applyui_TestCase(){
				f=new JFrame();
				f.setBounds(0,0,400,400);	
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setVisible(true);
	}
	
	
	
	public static void main(String arg[]){
		new Applyui_TestCase();
		//测试教务出老师查看课程申请的组件
				//课程名存在
		//			test_ApplyTextArea("游动的光影世界");
				
				//课程名不存在（此情况一般不会发生）
		//			test_ApplyTextArea("");
	
	
		//测试教务出老师查看申请课程列表的组件                            //error
		//         test_ApplyList();		
		
	}
	
	
			//测试教务出老师查看课程申请的组件
			static	void test_ApplyTextArea(String courseName){
						f.add(new ApplyComponent().getApplyInfo(courseName));			
			}
			//测试教务出老师查看申请课程列表的组件
			static  void test_ApplyList(){
				f.add(new ApplyComponent().getApplyList());
			}
			
		
		
}

