package presentation.studentui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import vo.coursevo.CourseListItemVO;
import businesslogic.coursebl.Course;
import businesslogic.courseselectionbl.SelectCourseInfo;
import businesslogic.studentbl.Student;
import businesslogicservice.studentblservice.StudentBLService;

//import businesslogic.creditbl.Credit;

public class ScorePanel {
	
	
	PieDataset pds;
	JPanel mainpanel;
	ChartPanel chartpanel;
	
		public JPanel getScorePanel(final String student_id){
			mainpanel =new JPanel();
			mainpanel.setLayout(null);
			
//			final JComboBox<String> selectsemester =new JComboBox<String>();
//			selectsemester.addItem("大一上");
//			selectsemester.addItem("大一下");
//			selectsemester.addItem("大二上");
//			selectsemester.addItem("大二下");
//			selectsemester.addItem("大三上");
//			selectsemester.addItem("大三下");
//			selectsemester.addItem("大四上");
//			selectsemester.addItem("大四下");
			final JComboBox<String> grade = new SemesterSelectionBox();

			mainpanel.add(grade);
			grade.setBounds(10, 15, 100, 20);
			Vector<String> head = new Vector<String>();
			head.add("课程编号");
			head.add("课程名");
			head.add("成绩");
			head.add("学分");
			final DefaultTableModel dtm = new DefaultTableModel(head,0);
			JTable gradetable = new JTable(dtm);
			JScrollPane jsp=new JScrollPane(gradetable);
			mainpanel.add( jsp);
			jsp.setBounds(115, 15, 300, 400);
			Tool.setOpaque(jsp);
			JButton showscores=new JButton("显示成绩单");
			mainpanel.add(showscores);
			showscores.setBounds(10, 40, 100, 20);
			
			showscores.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
//					int  semester_index = selectsemester.getSelectedIndex()+1;
					int rowcount = dtm.getRowCount();
					for(int i = 0; i< rowcount ;i++){
						dtm.removeRow(0);
					}
//					String gradeinfo = (String) grade.getSelectedItem();
//					StudentInfo si = new StudentInfo();
					StudentBLService s  = new Student(student_id);
					SelectCourseInfo mc = new SelectCourseInfo();
					ArrayList<String> mycourselist = mc.getAllMyCoursesInSelect(student_id);
					for(String course_id : mycourselist){
						 
//						String coursegradeinfo = new Course().getCourseInfo(course_id).getGrade();
						String coursegradeinfo = mc.getTheGradeWhenISelectedThisCourse(student_id, course_id);
						CourseListItemVO  vo = new Course().getCourseInfo(course_id);
						if(coursegradeinfo.compareToIgnoreCase((String) grade.getSelectedItem())==0){
							Vector<String> rowdata = new Vector<String>();
							String coursename = vo.getName();
							String score = s.getScore(course_id);
							String credit = vo.getCredit();
							rowdata.add(course_id);
							rowdata.add(coursename);
							rowdata.add(score);
							rowdata.add(credit);
							dtm.addRow(rowdata);
						}
					}
					
				}
			});
			
			//需要获取这个学生所有的已经学完了的课程；
			pds=ScorePanel.creatDataSet(0,150);
			chartpanel=getPieChart(pds);
			mainpanel.add(chartpanel);
			
			chartpanel.setBounds(450, 40, 300, 200);
			
			JButton showcredit=this.showCreditInfoButton(mainpanel,student_id);
			
			mainpanel.add(showcredit);
			
			showcredit.setBounds(550, 15, 100, 20);
			
			JButton getGPA = new JButton("按学期计算GPA");
			JButton getAllGPA = new JButton("计算当前总GPA");
			
			mainpanel.add(getGPA);
			mainpanel.add(getAllGPA);
			getGPA.setBounds(10, 65, 100	, 20);
			getAllGPA.setBounds(10, 90, 100, 20);
			final JTextField gpalabel = new JTextField();
			gpalabel.setEditable(false);
			mainpanel.add(gpalabel);
			gpalabel.setBounds(10, 115, 100, 20);
			getGPA.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					
					 
					double gpa = new Student(student_id).getGPA((String)grade.getSelectedItem());
					
					gpalabel.setText(gpa+"");
				}
			});
			
			getAllGPA.addActionListener( new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					double gpa = new Student(student_id).getTotalGPA();
					gpalabel.setText("");
					gpalabel.setText(gpa+"");
					
				}
			});
			Tool.setOpaque(mainpanel);
			return mainpanel;
		}
		
		private static PieDataset creatDataSet(int alreadygotcredit,int totalcredit){
			DefaultPieDataset dpd=new DefaultPieDataset();
			Comparable<?> 已经获得的学分="Credits that you have achieved "+alreadygotcredit;
			Comparable<?> 还未获得的学分="Credits that you haven't achieved" +( totalcredit-alreadygotcredit) ;
			dpd.setValue(已经获得的学分,alreadygotcredit);
			dpd.setValue(还未获得的学分,totalcredit-alreadygotcredit);
			
			return dpd;
			
		}
		
		
		private ChartPanel getPieChart(PieDataset dpd){
			
			ChartPanel frame;
			JFreeChart jfc= ChartFactory.createPieChart("",dpd,true,true,false);
			jfc.getTitle().setFont(new Font("微软雅黑",Font.CENTER_BASELINE,20));//设置标题字体

			jfc.setBackgroundPaint(Color.gray);
			frame=new ChartPanel (jfc,true);

			
			
			
			return frame;
				
		}
		
		private  JButton showCreditInfoButton(final JPanel mainpane,final String student_id){
			JButton jb=new JButton("显示学分获得情况");
			jb.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
//					System.out.println("Button pressed");
//					int mytotalcredit = new Credit().getTotalCredit(studentnumber);
//					DefaultPieDataset dpd = new DefaultPieDataset();
//					pds.setValue("已经获得的学分"+mytotalcredit , mytotalcredit);
//					pds.setValue("还未获得的学分"+(150-mytotalcredit), (150-mytotalcredit));
//					chartpanel
//					mainpane.updateUI();
					int mytotalcredit = new Student().getTotalCredit(student_id);
					pds=creatDataSet(mytotalcredit,150);
					
					mainpanel.remove(chartpanel);
					chartpanel = getPieChart(pds);
					mainpanel.add(chartpanel);
					chartpanel.setBounds(450, 40, 300, 200);					
				}
			});
			return jb;
		}
		
		
		
}

