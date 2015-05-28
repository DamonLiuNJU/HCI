package presentation.courseui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTable;

import vo.coursevo.CourseListItemVO;
import businesslogic.coursebl.CourseList;
import businesslogicservice.courseblservice.CourseListService;

public class CourseListTable {
	CourseListService cl;

	public static void main(String arg[]) {

		JFrame f = new JFrame();
		f.setSize(500, 600);
		CourseListTable clt = new CourseListTable();
		// f.add(clt.getSelectCourseByModule("必修课"));

		// ArrayList<String>cno=new ArrayList<String>();
		// cno.add("c0001");
		// cno.add("c0002");

		// getSearchTable(String campus,String grade,String facultyName
		f.add(clt.getTeachList("050001"));
		f.setVisible(true);

	}

	public CourseListTable() {
		cl = new CourseList();
	}

	// student:得到某模块的课程列表-------"课程号","课程名","教师姓名","校区","上课地点","上课时间","起止周","特殊要求","学分","课程类型"
	public JTable getSelectCourseByModule(String module) {
		String title[] = { "课程号", "课程名", "教师姓名", "校区", "上课地点", "上课时间", "起止周",
				"特殊要求", "学分", "限制人数", "课程类型" };
		ArrayList<CourseListItemVO> itemList = cl.getCourseListByModule(module);
		String content[][] = ge2DArrayForTableContent(itemList);
		return getProcessedTable(content, title);
	}

	public JTable getCompulsoryCourseByFaculty(String facultyName) {
		String title[] = { "课程号", "课程名", "教师姓名", "校区", "上课地点", "上课时间", "起止周",
				"特殊要求", "学分", "限制人数", "课程类型" };
		ArrayList<CourseListItemVO> itemList = cl.getComplusoryCourseList(facultyName);
		String content[][] = ge2DArrayForTableContent(itemList);
		return getProcessedTable(content, title);
	}
	
	String[][] ge2DArrayForTableContent(ArrayList<CourseListItemVO> itemList){
		String content[][] = new String[itemList.size()][11];
		for (int i = 0; i < itemList.size(); i++) {
			content[i][0] = itemList.get(i).getCno();
			content[i][1] = itemList.get(i).getName();
			content[i][2] = itemList.get(i).getTeacherName();
			content[i][3] = itemList.get(i).getCampus();
			content[i][4] = itemList.get(i).getPlace();
			content[i][5] = itemList.get(i).getTime();
			content[i][6] = itemList.get(i).getPeriod();
			content[i][7] = itemList.get(i).getRequire();
			content[i][8] = itemList.get(i).getCredit();
			content[i][9] = itemList.get(i).getLimit();
			content[i][10] = itemList.get(i).getModule();
		}
		return content;
	}

	// limit:cno cName tn facultyName grade----->"编号","课程名","教师","所属院系名","所属模块"
	public JTable getSearchList(String cno, String cName, String teacherName,
			String facultyName, String grade) {

		String[] title = { "课程号", "课程名", "教师", "所属院系", "模块" };
		ArrayList<CourseListItemVO> info = cl.getSearchList(cno, cName,
				teacherName, facultyName, grade);
		String[][] content = new String[info.size()][5];
		for (int i = 0; i < info.size(); i++) {
			content[i][0] = info.get(i).getCno();
			content[i][1] = info.get(i).getName();
			content[i][2] = info.get(i).getTeacherName();
			content[i][3] = info.get(i).getFacultyName();
			content[i][4] = info.get(i).getModule();
		}
		return getProcessedTable(content, title);
	}

	// 教师任课列表----------id name time place
	public JTable getTeachList(String teacherID) {

		String[] title = { "课程号", "课程名", "时间", "地点" };
		ArrayList<CourseListItemVO> info = cl.getTeachList(teacherID);
		String[][] content = new String[info.size()][4];
		for (int i = 0; i < info.size(); i++) {
			content[i][0] = info.get(i).getCno();
			content[i][1] = info.get(i).getName();
			content[i][2] = info.get(i).getTime();
			content[i][3] = info.get(i).getPlace();
		}
		return getProcessedTable(content, title);
	}

	// student:根据课程号获取课程信息。
	public JTable getCourseInfoTable(ArrayList<String> cno) {

		String title[] = { "课程号", "课程名", "教师姓名", "校区", "上课地点", "上课时间", "起止周",
				"特殊要求", "学分", "课程类型" };
		ArrayList<CourseListItemVO> itemList = cl
				.getCourseListByChooseList(cno);
		String content[][] = new String[itemList.size()][10];
		for (int i = 0; i < itemList.size(); i++) {
			content[i][0] = itemList.get(i).getCno();
			content[i][1] = itemList.get(i).getName();
			content[i][2] = itemList.get(i).getTeacherName();
			content[i][3] = itemList.get(i).getCampus();
			content[i][4] = itemList.get(i).getPlace();
			content[i][5] = itemList.get(i).getTime();
			content[i][6] = itemList.get(i).getPeriod();
			content[i][7] = itemList.get(i).getRequire();
			content[i][8] = itemList.get(i).getCredit();
			content[i][9] = itemList.get(i).getModule();
		}
		return getProcessedTable(content, title);
	}

	// student: 根据筛选条件获取课程信息。
	public JTable getSearchTable(String campus, String grade, String facultyName) {

		String title[] = { "课程号", "课程名", "教师姓名", "校区", "上课地点", "上课时间", "起止周",
				"特殊要求", "学分", "课程类型" };
		ArrayList<CourseListItemVO> itemList = cl.getSearchList(campus, grade,
				facultyName);
		String content[][] = new String[itemList.size()][10];
		for (int i = 0; i < itemList.size(); i++) {
			content[i][0] = itemList.get(i).getCno();
			content[i][1] = itemList.get(i).getName();
			content[i][2] = itemList.get(i).getTeacherName();
			content[i][3] = itemList.get(i).getCampus();
			content[i][4] = itemList.get(i).getPlace();
			content[i][5] = itemList.get(i).getTime();
			content[i][6] = itemList.get(i).getPeriod();
			content[i][7] = itemList.get(i).getRequire();
			content[i][8] = itemList.get(i).getCredit();
			content[i][9] = itemList.get(i).getModule();
		}
		return getProcessedTable(content, title);
	}

	// 给课程列表添加双击事件
	JTable getProcessedTable(String[][] content, String[] title) {
		@SuppressWarnings("serial")
		final JTable table = new JTable(content, title) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {// 单击鼠标左键
					if (e.getClickCount() == 2) {
						System.out.println((String) table.getValueAt(
								table.getSelectedRow(), 0));
						new CourseInfoFrame((String) table.getValueAt(
								table.getSelectedRow(), 0));
					}
				}
			}
		});
		return table;
	}

}
