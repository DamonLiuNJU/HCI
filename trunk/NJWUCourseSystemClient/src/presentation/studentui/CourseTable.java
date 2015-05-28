package presentation.studentui;

import javax.swing.JTable;
//import presentation.courseui.CourseListTable;

public class CourseTable extends JTable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5906777796778145168L;

	/**
	 * @param args
	 */
//	CourseListTable coursetable = new CourseListTable();
	
	
	
	@SuppressWarnings("null")
	public JTable getGetCourseInfoTable(String studentnumber , String campus, String grade, String major){
		
//		Vector<String> columnname=new Vector<>();
//		Vector<Vector<String>> content=new Vector<>();
//		columnname.add("ID");
//		columnname.add("Lesson Name");
//		columnname.add("Teacher Name");
//		columnname.add("Year");
//		columnname.add("Campus");
//		columnname.add("Special Request");
//		columnname.add("Type");
//		Vector<String > line1=new Vector<>();
//		line1.add("100001");
//		line1.add("SoftWare Engineering");
//		line1.add("Liu Qin");
////		line1.add("Monday 1-2");
////		line1.add("Xian Lin Campus");
//		line1.add("No Request");
//		line1.add("Optional");
//		content.add(line1);
//		
//		CourseVO cv;
//		StudentVO sv = new StudentVO(studentnumber);
//		if(studentnumber == null){
//			cv  = new CourseVO();
//			content  = new Student().getCourseInfo(cv);
//		}
//		else{
//			content = new Student().getCourseInfo(sv);
//		}
		
		JTable courseinfotable=null;
//		courseinfotable = coursetable.getCourseInfoTable(cno);
		
		courseinfotable.setOpaque(false);
		return courseinfotable;
	}
	
//	public JTable getGradeTable(int semester,String studentnumber){
//		//使用bl层的获取课程成绩。获得某一个学期的成绩。
//		
//		
//		
//		Vector<String> head =new Vector();
//		Vector<Vector<String> > content=new Vector();
//		
//		head.add("Course No");
//		head.add("CourseName ");
//		head.add("Grade");
//		head.add("Credit");
//		
//		Student s= new Student(studentnumber);
//		CreditVO cv = new CreditVO(semester,studentnumber);
//		content = s .getScore(cv);
//		
//		
//		JTable table=new JTable(content,head);
//		
//		return table;
//	}
	
//	public static void main(String[] args) {
//		 
//		
//	}

}
