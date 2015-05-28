package vo.studentvo;

import java.util.Vector;

public class CourseConditionPanelVO {

	/**
	 * @param args
	 */
	//课程号， 课程名字，课程类型， 学分 ， 成绩
	Vector<Vector<String>> content ; 
	boolean permission;
	public boolean isPermission() {
		return permission;
	}
	public void setPermission(boolean permission) {
		this.permission = permission;
	}
	public void setTableContent(Vector<Vector<String>> content){
		this.content  = content;
	}
	public Vector<Vector<String>> getTableContent(){
		return this.content;
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

}
