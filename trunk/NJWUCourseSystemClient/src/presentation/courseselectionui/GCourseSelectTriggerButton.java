package presentation.courseselectionui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.Timer;

import presentation.deanui.DeanUIImage;
import presentation.tools.OutputHelper;
import presentation.tools.ViewReplyMessage;

import businesslogic.courseselectionbl.SelectStudentAlgorithm;
import businesslogic.utilitybl.CourseModule;

@SuppressWarnings("serial")
public class GCourseSelectTriggerButton extends JButton implements
		ViewReplyMessage, DeanUIImage {
	int clickCount = 0;

	public GCourseSelectTriggerButton(final Timer timer) {
		ImageIcon icon = new ImageIcon(triggerButton);
		this.setIcon(icon);
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 检测通识公选课选课是否已触发过
				boolean isSelected = new SelectStudentAlgorithm(
						CourseModule.通识课.toString()).isProcessed();
				if (!isSelected) {
					SelectStudentAlgorithm generalAlgorithm = new SelectStudentAlgorithm(
							"通识课");
					generalAlgorithm.processFinalList();
					SelectStudentAlgorithm optionalAlgorithm = new SelectStudentAlgorithm(
							"公选课");
					optionalAlgorithm.processFinalList();
					timer.start();
				} else {
					new OutputHelper().outputToDialog(HAVE_SELECTED);
				}
			}
		});
	}

}
