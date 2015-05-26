package presentation.studentui;

import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class CourseModuleBox extends JComboBox<String> {
	public CourseModuleBox(){
		
		this.addItem("请选择课程模块");
		this.addItem("通识课");
		this.addItem("体育课");
		this.addItem("选修课");
		this.addItem("公选课");
		
	}
	public static void main(String[] args) {

	}

}
