package presentation.studentui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import presentation.tools.ChangePasswordFrame;

public class SettingButton extends JButton {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6681747904021025682L;

	public JButton getSettingButton(final String student_id){
//		this.setIcon(new ImageIcon(Tool.settingimage));
		this.setSize(50, 50);

		Tool.setIcon(Tool.settingimage, this);
//		this.setText("修改密码");
		
		this.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				JFrame settingpassword = ChangePasswordFrame.getChangePassWordFrame(student_id);
				new Tool().setMiddle(settingpassword);
				settingpassword.setVisible(true);
				settingpassword.repaint();
			}
		});
		
		return this;
	}
	
	
	
}
