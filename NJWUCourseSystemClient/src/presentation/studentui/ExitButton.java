package presentation.studentui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ExitButton extends JButton{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5724101360473556056L;

	public JButton getexitbutton(final JFrame mainframe){
//		this.setIcon(new ImageIcon(Tool.quitimage));
//		Setter setter = new Setter();
//		setter.setButtonWithImage(this);
		this.setSize(50, 50);
		Tool.setIcon(Tool.quitimage, this);
//		this.setText("Exit");
		this.addActionListener(new ActionListener(){

			 
			public void actionPerformed(ActionEvent e) {
			 
				mainframe.dispose();
				System.exit(0);
			}
		
	});
		
		return this;
	}
		
	
	
}
