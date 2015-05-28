package presentation.managerui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import presentation.deanui.DeanUIImage;
import presentation.tools.OutputHelper;
import presentation.tools.Setter;
import presentation.tools.ViewReplyMessage;
import vo.coursevo.ApplyVO;
import businesslogic.managerbl.Dean;
import businesslogic.managerbl.Faculty;

public class CourseAuditAutoSendButton implements ViewReplyMessage,DeanUIImage{
	String id;
	ApplyVO avo;
	String detailInfo;
	
	OutputHelper helper=new OutputHelper();
	Setter setter=new Setter();
	
	Dean dean;
	
	public CourseAuditAutoSendButton(String deanID,ApplyVO avo,String df){
		id=deanID;
		dean=new Dean(id);
		this.avo=avo;
		detailInfo=df;
	}
	
	public JButton getPassButton(final JFrame f){	
		ImageIcon img=new ImageIcon(passButton);
		JButton pass_b=new JButton("通过",img);
		setter.setButtonWithImage(pass_b);
		pass_b.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String toID=new Faculty().getMajorFacultyID(avo.getFacultyName());
				String courseName=avo.getCourseName();
				String courseInfo=avo.getTeacherName()+"--"+
						courseName+" :\r\n    "+detailInfo;
				String content="请及时发布此门课程并通知该教师，课程信息如下："+courseInfo;
				dean.sendMessageAboutApply(toID, content,courseName);
				helper.outputToDialog(SEND_PASS);
				f.dispose();
			}			
		});
		
		
		return pass_b;
	}
	
	public JButton getNotPassButton(final JFrame f){
		ImageIcon img=new ImageIcon(crossButton);
		JButton npass_b=new JButton("不通过",img);
		setter.setButtonWithImage(npass_b);
		npass_b.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String toID=new Faculty().getMajorFacultyID(avo.getFacultyName());
				String courseName=avo.getCourseName();
				String teacherName=avo.getTeacherName();
				String courseInfo=teacherName+"--"+
						courseName;
				String content="此门课程申请已被拒绝，课程信息如下："+courseInfo
						+",请通知"+teacherName+"老师。";
				dean.sendMessageAboutApply(toID, content,courseName);
				helper.outputToDialog(SEND_NOT_PASS);	
				f.dispose();
			}			
		});
		return npass_b;
	}
}
