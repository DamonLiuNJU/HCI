package presentation.teacherui;

import java.util.ArrayList;

import javax.swing.JTextArea;

import businesslogic.teacherbl.TeacherList;

/*
 * 用于显示包含teacher信息的textArea。
 * 方法有： setGeneralInfoTextArea , 用于显示全校的教师统计信息。
 * setDetailInfoTextArea , 用于显示某一院系的教师统计信息。
 */
public class TeacherTextArea {

	TeacherList teacherList ;
	
	public void setGeneralInfoTextArea(JTextArea jta){
		teacherList = new TeacherList();
		ArrayList<String> infoArray = teacherList.showGeneralInfo();
		
		for(int i=0 ; i<infoArray.size() ; i++){
			jta.append(infoArray.get(i));
		}
		
	}
	
	public void setDetailInfoTextArea(JTextArea jta , String faculty_id){
		teacherList = new TeacherList();
		ArrayList<String> infoArray = teacherList.showDetailedInfo(faculty_id);
		
		for(int i=0 ; i<infoArray.size() ; i++){
			jta.append(infoArray.get(i));
		}
	}
}
