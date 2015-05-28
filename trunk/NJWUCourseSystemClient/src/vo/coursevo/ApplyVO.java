package vo.coursevo;
public class ApplyVO {
			String courseName;
			String teacherName;
			String facultyName;
			public ApplyVO(String courseName,String teacherName ,String  facultyName){
				this.courseName=courseName;
				this.teacherName=teacherName;
				this.facultyName=facultyName;
			}
			
			public String getCourseName(){
				return courseName;
				
			}
			public String getTeacherName(){
				return teacherName;
				
			}
			public String getFacultyName(){
				return facultyName;
			}
}

