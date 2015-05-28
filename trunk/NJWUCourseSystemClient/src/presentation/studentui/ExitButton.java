package presentation.studentui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import presentation.mainui.LoginUI;

public class ExitButton extends JButton{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5724101360473556056L;

	JFrame loginframe;
	public ExitButton(JFrame loginFrame) {
		// TODO 自动生成的构造函数存根
		loginframe = loginFrame;
	}

	public JButton getexitbutton(final JFrame mainframe){
//		this.setIcon(new ImageIcon(Tool.quitimage));
//		Setter setter = new Setter();
//		setter.setButtonWithImage(this);
		this.setSize(50, 50);
		Tool.setIcon(Tool.quitimage, this);
//		this.setText("Exit");
		this.addActionListener(new ActionListener(){

			 
			@Override
			public void actionPerformed(ActionEvent e) {
			 
				
				int result = JOptionPane.showConfirmDialog(null, "返回登录界面?","", JOptionPane.OK_OPTION);
				if(result == 0){
					mainframe.dispose();
					loginframe.setVisible(false);
					loginframe.dispose();
					
					new LoginUI();
					 
					
				}else{
					loginframe.dispose();
					
				}
				
			}
		
	});
		
		return this;
	}
		
	
	
}
