package presentation.courseui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

import org.jfree.chart.ChartPanel;

import net.miginfocom.swing.MigLayout;

import presentation.planui.PlanList;
import presentation.studentui.PieChart;
import presentation.tools.Setter;
import vo.coursevo.CourseStatVO;
import businesslogic.coursebl.Course;

/**
 * courseInfoPane that dean requires
 * @author cbb
 *
 */
@SuppressWarnings("serial")
public class CourseStatInfoPane extends JPanel{
	JPanel panel;//contain general info and detail info
	JPanel generalInfo;
	JPanel detailInfo;
	JRadioButton typeButton; //信息类型选择
	@SuppressWarnings("rawtypes")
	JComboBox facultyBox; //院系选择
	String facultyName;
	JScrollPane jsp;
	JTextArea courseInfo;
	
	//variables for painting chart panel
	static int MODULE_SIZE=5;
	int[] value=new int[MODULE_SIZE];
	String[] info=new String[MODULE_SIZE];
	ChartPanel chart;
	
	Course course=new Course();
	
	public CourseStatInfoPane(){
		this.setLayout(new MigLayout());
		this.setOpaque(false);
		
		panel=new JPanel();
		panel.setOpaque(false);
		
		ButtonGroup bgroup=new ButtonGroup();
		JRadioButton jrb1=new JRadioButton("全校");
		jrb1.setOpaque(false);
		jrb1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				showGeneralInfo();
			}			
		});
		JRadioButton jrb2=new JRadioButton("院系");
		jrb2.setOpaque(false);
		jrb2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				showDetailInfo();
			}
		});
		bgroup.add(jrb1);
		bgroup.add(jrb2);
		JPanel p=new JPanel(new MigLayout());
		p.setOpaque(false);
		p.add(jrb1,"gaptop 5");
		p.add(jrb2,"gapleft 40");
		
		this.add(p,"gapleft 10,wrap");		
		this.add(new JSeparator(),"span,growx");
		this.add(panel,"gapleft 10");
	}
	
	public void showGeneralInfo(){
		panel.removeAll();
		generalInfo=new JPanel(new MigLayout());
		generalInfo.setOpaque(false);
		new Setter().setBorderTitle(generalInfo,"全校课程统计信息");
		
		JTextArea ta=new JTextArea(15,40);
		ta.setOpaque(false);
		ta.setBorder(null);
		ta.setEditable(false);
		
		StringBuilder text=new StringBuilder();
		ArrayList<CourseStatVO> csvList=course.getModuleStatics("");
		
		int i=0; //counter
		for(CourseStatVO csv:csvList){
			text.append(csv.toString()+"\r\n");
			value[i]=csv.getNum();
			info[i]=moduleNameToEngLish(csv.getType());
			i++;
		}
		ta.setText(text.toString());
		
		generalInfo.add(ta);
		
		chart=new PieChart().getPieChart(value,info);
		chart.setPreferredSize(new Dimension(300,250));
		
		panel.add(generalInfo,"gaptop 5");
		panel.add(chart,"gapleft 70");
		repaint();
	}
	
	protected void showDetailInfo() {
		panel.removeAll();		
		detailInfo=new JPanel(new MigLayout());
		detailInfo.setOpaque(false);
		
		JLabel label=new JLabel(">>课程统计信息");
		courseInfo=new JTextArea(15,45);
		courseInfo.setOpaque(false);
		courseInfo.setBorder(null);
		courseInfo.setEditable(false);
		jsp=new JScrollPane(courseInfo);
		jsp.setOpaque(false);
		jsp.getViewport().setOpaque(false); 
		
		JPanel p0=new JPanel();	
		p0.setOpaque(false);
		JLabel label1=new JLabel("院系列表");
		facultyBox=new PlanList().getFacultyComboBox();
		facultyBox.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				facultyName=facultyBox.getSelectedItem().toString();
				
				StringBuilder text=new StringBuilder();
				ArrayList<CourseStatVO> csvList=course.getModuleStatics(facultyName);
				for(CourseStatVO csv:csvList){
					text.append(csv.toString()+"\r\n");
					//value[i]=csv.getNum();
					//info[i]=moduleNameToEngLish(csv.getType());
				}
				courseInfo.setText(text.toString());								
			}			
		});
		p0.add(label1);
		p0.add(facultyBox);
		
		detailInfo.add(p0,"wrap");
		detailInfo.add(label,"wrap,gapleft 10,wrap");
		detailInfo.add(jsp,"gapleft 10");

		panel.add(detailInfo,"gaptop 5");
		repaint();
	}
	
	String moduleNameToEngLish(String m){
		String s="";
		switch(m){
		case "通识课":
			s="General";
			break;
		case "公选课":
			s="Public";
			break;
		case "必修课":
			s="Compulsory";
			break;
		case "选修课":
			s="Optional";
			break;
		case "体育课":
			s="P.E.";
			break;
		}
		return s;
	}
	
    void setZero(int[] list){
		for(int i=0;i<list.length;i++){
			list[i]=0;
		}
	}
}
