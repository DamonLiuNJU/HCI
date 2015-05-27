package presentation.frameui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import net.miginfocom.swing.MigLayout;
import businesslogic.framebl.FrameRemark;

@SuppressWarnings("serial")
public class FrameRemarkPane extends JPanel{
	JTextArea remarkInfo;
	FrameRemark remark;
	
	public FrameRemarkPane(){
		this.setLayout(new MigLayout());
		this.setOpaque(false);
		
		JLabel l=new JLabel("备注信息");
		remarkInfo=new JTextArea(8,12);
		remark=new FrameRemark();
		remarkInfo.setText(remark.getContent());
		remarkInfo.setLineWrap(true);
		remarkInfo.setWrapStyleWord(true);
		JScrollPane jsp=new JScrollPane(remarkInfo);
		jsp.setOpaque(false);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JButton b=new JButton("保存");
		b.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				remark.setContent(remarkInfo.getText());				
			}			
		});
		this.add(l,"wrap");
		this.add(jsp,"wrap");
		this.add(b,"gapleft 30");
	}
}
