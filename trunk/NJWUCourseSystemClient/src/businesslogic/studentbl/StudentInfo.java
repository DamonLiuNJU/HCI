package businesslogic.studentbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;

import businesslogicservice.studentblservice.StudentInfoBLService;

import po.studentpo.StudentPO;
import rmiconnector.RemoteDataFactory;
import vo.studentvo.StudentInfoVO;
import dataservice.studentdataservice.StudentDataService;
/**
 * 
 * @author LiuWT-ASUS
 * 学生个人信息的操作。
 *
 */
public class StudentInfo implements StudentInfoBLService   {

	private String student_id = "default No";
	// private String name = "default name";
	// private String Major = "default Major";
	private String phone = "default tele number";
	String homeaddress;
	private String mothername = "";
	private String fathername = "";
	private String mothertelenumber = "";
	private String fathertelenumber = "";
	// private String grade = "";
	String contactinfo = "";
	RemoteDataFactory factory = new RemoteDataFactory();

	public StudentInfo() {

	}

	public StudentInfo(ArrayList<String> studentinfo) {
		// 八个属性，对应面板上的八个textfield;
		int counter = 0;
		student_id = studentinfo.get(counter++);
		contactinfo = studentinfo.get(counter++);
		phone = studentinfo.get(counter++);
		homeaddress = studentinfo.get(counter++);
		mothername = studentinfo.get(counter++);
		mothertelenumber = studentinfo.get(counter++);
		fathername = studentinfo.get(counter++);
		fathertelenumber = studentinfo.get(counter++);
	}

	public StudentInfo(int StudentNo) {
		// get student number from login panel and use this to get other
		// improtant information of the student
		this.student_id = StudentNo + "";
	}

	public StudentInfo(String student_id) {
		// TODO 自动生成的构造函数存根
		this.student_id = student_id;
	}

	/* （非 Javadoc）
	 * @see businesslogic.studentbl.StudentInfoBLService#getStudentNo()
	 */
	@Override
	public String getStudentNo() {
		return this.student_id;
	}

	 
	/* （非 Javadoc）
	 * @see businesslogic.studentbl.StudentInfoBLService#update()
	 */
	@Override
	public boolean update() {
		StudentPO po = new StudentPO();
		po.setStudentID(student_id);
		StudentDataService data = (StudentDataService) factory
				.getData("Student");
		try {
			po = data.find(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		po.setHomeAddress(this.homeaddress);
		po.setContactInfo(contactinfo);
		po.setPhone(phone);
		po.setMotherName(mothername);
		po.setMotherPhone(mothertelenumber);
		po.setFatherName(fathername);
		po.setFatherPhone(fathertelenumber);

		try {
			data.update(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return true;
	}


	/* （非 Javadoc）
	 * @see businesslogic.studentbl.StudentInfoBLService#getPersonInfo(java.lang.String)
	 */
	@Override
	public StudentInfoVO getPersonInfo(String studnet_id) {
		// ArrayList<String> result = new ArrayList<String>();
		StudentPO po = new StudentPO(studnet_id);
		StudentDataService sds = (StudentDataService) factory
				.getData("Student");
		try {
			po = sds.find(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		StudentInfoVO vo = new StudentInfoVO();

		vo.setID(po.getStudentID());
		vo.setKey(po.getPassWord());
		vo.setName(po.getName());
		vo.setFacultyID(po.getFacultyID());
		vo.setEntranceYear(po.getGrade());
		vo.setContactInfo(po.getContactInfo());
		vo.setPhone(po.getPhone());
		vo.setHomeAddress(po.getHomeAddress());
		vo.setMotherName((po.getMotherName()));
		vo.setMotherPhone(po.getMotherPhone());
		vo.setFatherName(po.getFatherName());
		vo.setFatherPhone(po.getFatherPhone());

		return vo;

	}

	/* （非 Javadoc）
	 * @see businesslogic.studentbl.StudentInfoBLService#isKeyValid(vo.studentvo.StudentInfoVO)
	 */
//	@Override
	/*
	 * 密码是否正确。
	 */
	public int isKeyValid(StudentInfoVO siv) {
		// TODO 自动生成的方法存根
		String id = siv.getID();
		String key = siv.getKey();
		StudentDataService sds = (StudentDataService) factory.getData("Student");
		StudentPO po = null;
		try {
			po = sds.find(new StudentPO(id));
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		if(po == null){
			return 1;
		}
		String password = po.getPassWord();
		if(password ==  null){
			return 0;
		}
		if (key.compareToIgnoreCase(password) == 0) {
			return 2;
		} else {
			return 0;
		}

	}

	/* （非 Javadoc）
	 * @see businesslogic.studentbl.StudentInfoBLService#changePassWord(java.lang.String, char[], char[])
	 */
//	@Override
	@Override
	public boolean changePassWord(String student_id, char[] oldkey,
			char[] newkey) {
		// boolean valid = true;
		StudentPO po = new StudentPO();
		po.setStudentID(student_id);
		StudentDataService sds = (StudentDataService) factory
				.getData("Student");
		try {
			po = sds.find(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		String oldkeystring = new String(oldkey);
		if (po.getPassWord().compareToIgnoreCase(oldkeystring) == 0) {
			po.setPassword(new String(newkey));
			try {
				sds.update(po);
			} catch (RemoteException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			return true;
		} else {
			return false;
		}

		// return valid;
	}

	/* （非 Javadoc）
	 * @see businesslogic.studentbl.StudentInfoBLService#getStudentNameByID(java.lang.String)
	 */
	@Override
	public String getStudentNameByID(String student_id) {
		String result = null;
		StudentPO sp = new StudentPO(student_id);
		StudentDataService sds = (StudentDataService) factory
				.getData("Student");

		try {
			sp = sds.find(sp);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		result = sp.getName();

		return result;
	}

	

	/* （非 Javadoc）
	 * @see businesslogic.studentbl.StudentInfoBLService#getStuCurrentGrade()
	 */
	@Override
	public String getStuCurrentGrade() {
		// 根据学生的入学时间返回该学生现在是第几个学期；
		// 作出修改 因为这个方法目前能用到的地方就是选择课程时候这个时候总是提前一个学期的。
		String result = null;
		StudentPO sp = new StudentPO(student_id);
		StudentDataService sds = (StudentDataService) factory
				.getData("Student");
		try {
			sp = sds.find(sp);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		String grade = sp.getGrade();
		int gradeint = Integer.parseInt(grade);

		// gradeint //因为总是提前一个学期选课，所以这里处理一下。
		// 针对上一行的情况 不可以简单的减一。

		Calendar c = Calendar.getInstance();// 可以对每个时间域单独修改
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);


		int length = (year - gradeint) * 12 + month - 9;
		int g = length / 12;
		String up = "上";

		length = length % 12;
		if (length < 5) {

		} else {
			up = "下";
		}

		// String part1 = "";

		String[] temp = { "大一", "大二", "大三", "大四" };
		if( g > 3){
			g = 3;
		}
		result = temp[g] + up;

		// switch(result){
		// //问题是 大一第一次选课怎么搞
		// case "大一上":
		// result = "大一下";
		// break;
		// case "大一下":
		// result = "大二上";
		// break;
		// case "大二上":
		// result = "大二下";
		// break;
		// case "大二下":
		// result = "大三上";
		// break;
		// case "大三上":
		// result = "大三下";
		// break;
		// case "大三下":
		// result = "大四上";
		// break;
		// case "大四上":
		// result = "大四下";
		// break;
		// // case "大四下":
		// // result = "大四下"; //大四下就不用再选课了。
		// // break;
		// }

		return result;
	}

	/* （非 Javadoc）
	 * @see businesslogic.studentbl.StudentInfoBLService#getStuGradeForSelection()
	 */
	@Override
	public String getStuGradeForSelection() {
		// 根据学生的入学时间返回该学生现在是第几个学期；
		// 作出修改 因为这个方法目前能用到的地方就是选择课程时候这个时候总是提前一个学期的。
		// 取消修改 认为学生只在当前学期选择课程.
		String result = null;
		StudentPO sp = new StudentPO(student_id);
		StudentDataService sds = (StudentDataService) factory
				.getData("Student");
		try {
			sp = sds.find(sp);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		String grade = sp.getGrade();
		int gradeint = Integer.parseInt(grade);

		// gradeint //因为总是提前一个学期选课，所以这里处理一下。
		// 针对上一行的情况 不可以简单的减一。

		Calendar c = Calendar.getInstance();// 可以对每个时间域单独修改
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		// int date = c.get(Calendar.DATE);
		// int hour = c.get(Calendar.HOUR_OF_DAY);
		// int minute = c.get(Calendar.MINUTE);
		// int second = c.get(Calendar.SECOND);

		int length = (year - gradeint) * 12 + month - 9;
		int g = length / 12;
		String up = "上";

		length = length % 12;
		if (length < 5) {

		} else {
			up = "下";
		}

		// String part1 = "";

		String[] temp = { "大一", "大二", "大三", "大四" };
		if(g > 3){
			g = 3;
		}
		result = temp[g] + up;
		return result;
	}

	/* （非 Javadoc）
	 * @see businesslogic.studentbl.StudentInfoBLService#getStudentInfo()
	 */
	@Override
	public StudentPO getStudentInfo() {
		StudentDataService data = (StudentDataService) factory
				.getData("Student");
		StudentPO result = null;
		try {
			result = data.find(new StudentPO(this.student_id));
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		return result;

	}

	/* （非 Javadoc）
	 * @see businesslogic.studentbl.StudentInfoBLService#getFacultyID(java.lang.String)
	 */
	@Override
	public String getFacultyID(String student_id) {
		StudentInfoBLService si = new StudentInfo(student_id);
		StudentPO po = si.getStudentInfo();
		String facultyID = po.getFacultyID();

		return facultyID;
	}

}
