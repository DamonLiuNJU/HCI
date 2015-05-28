package coursebltest;

public class MockCoursePo {
	
		String id;
		String name;
		String teacherID;
		String campus;
		String grade;
		String place;
		String time;
		String period;
		String require;
		String facultyID;
		String credit;
		String module;
		String limit;
		String specificInfo;

		MockCoursePo mcp1=new MockCoursePo("c100101","课程1","2001001","校区1","大一","地点1","时间1","课时1",
																						"要求1","001","学分1","必修课","限制人数1","详细信息1");
		MockCoursePo mcp2=new MockCoursePo("c100102","课程2","2001001","校区2","大二","地点2","时间2","课时2",
				"要求2","002","学分2","选修课","限制人数2","详细信息2");

		MockCoursePo mcp3=new MockCoursePo("c100103","课程3","2001001","校区3","大三","地点3","时间3","课时3",
				"要求3","003","学分3","体育课","限制人数3","详细信息3");
		MockCoursePo mcp4=new MockCoursePo("c100104","课程4","2001001","校区1","大四","地点4","时间4","课时4",
				"要求4","004","学分4","通识课","限制人数4","详细信息4");
		MockCoursePo mcp5=new MockCoursePo("c100105","课程5","2001001","校区2","大一","地点5","时间5","课时5",
				"要求5","001","学分5","公选课","限制人数5","详细信息5");
		MockCoursePo mcp6=new MockCoursePo("c100106","课程6","2001001","校区1","大一","地点1","时间1","课时1",
				"要求1","001","学分1","必修课","限制人数1","详细信息6");
		MockCoursePo mcp7=new MockCoursePo("c100107","课程7","2001001","校区1","大一","地点1","时间1","课时1",
				"要求1","002","学分1","必修课","限制人数1","详细信息7");
		MockCoursePo mcp8=new MockCoursePo("c100108","课程8","2001001","校区1","大一","地点1","时间1","课时1",
				"要求1","001","学分1","通识课","限制人数1","详细信息8");
		MockCoursePo mcp9=new MockCoursePo("c100109","课程1","2001001","校区1","大一","地点1","时间1","课时1",
				"要求1","001","学分1","必修课","限制人数1","详细信息9");
		public MockCoursePo(String id ,String name,String teacherID,String campus,String grade,String place,String time,String period,
									String require,String facultyID,String credit,String module,String limit,String specificInfo){
			this.id=id;
			this.name=name;
			this.teacherID=teacherID;
			this.campus=campus;
			this.grade=grade;
			this.place=place;
			this.time=time;
			this.period=period;
			this.require=require;
			this.facultyID=facultyID;
			this.credit=credit;
			this.module=module;
			this.limit=limit;
			this.specificInfo=specificInfo;
		}
		 
		
		
		
	
}
