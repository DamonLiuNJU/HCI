package presentation.courseselectionui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import presentation.tools.OutputHelper;
import presentation.tools.ViewReplyMessage;

import businesslogic.courseselectionbl.CourseSelection;
import businesslogic.courseselectionbl.TempCourseSelection;

@SuppressWarnings("serial")
public class TransferButton extends JButton implements ViewReplyMessage{
	public TransferButton(){
		this.setText("终止补退选");
		this.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				TempCourseSelection temp=new TempCourseSelection();
				CourseSelection formal=new CourseSelection();
				OutputHelper helper =new OutputHelper();
				if(!temp.isTransfered()){
					formal.transferFromTempSelection();
					helper.outputToDialog(TRANSFER_SUCCEED);
					TransferButton.this.setEnabled(false);
				}else{
					helper.outputToDialog(HAVE_TRANSFER);
				}
			}			
		});
	}
}
