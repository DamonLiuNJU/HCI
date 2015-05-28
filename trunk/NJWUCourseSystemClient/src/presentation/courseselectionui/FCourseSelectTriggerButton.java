package presentation.courseselectionui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import businesslogic.courseselectionbl.SelectStudentAlgorithm;

import presentation.facultyui.FacultyUIImage;
import presentation.facultyui.GUIHelper;
import presentation.facultyui.PublicComponent;

public class FCourseSelectTriggerButton  implements FacultyUIImage{	
		int frameWidth;
		int frameHeight;
		PublicComponent pc;
public		FCourseSelectTriggerButton(String id){
			frameWidth = 3 * GUIHelper.getFrameWidth() / 2;
			frameHeight = 3 * GUIHelper.getFrameHeight() / 2;
			pc=new PublicComponent(id);
		}
		
		
	public JButton getTriggerBut( final String id){
		JButton triggerBut=pc.getButton(triggerIcon,"触发选课",70,70);
		triggerBut.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new SelectStudentAlgorithm("选修课").processFacultyCourse(id);
				GUIHelper.sendMessage("选课已触发！");
			}
					
		});					
			
		triggerBut.setBounds(frameWidth*15/24,frameHeight/5,70,95);
		return triggerBut;
	}
}
