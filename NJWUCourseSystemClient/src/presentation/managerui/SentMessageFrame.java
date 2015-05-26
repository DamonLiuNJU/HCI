package presentation.managerui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import net.miginfocom.swing.MigLayout;
import presentation.tools.OutputHelper;
import presentation.tools.ViewReplyMessage;
import businesslogic.managerbl.Dean;
import businesslogic.managerbl.Faculty;

@SuppressWarnings("serial")
public class SentMessageFrame extends JFrame implements ViewReplyMessage{
	JTextArea text;
	Dean dean;
	
	OutputHelper helper=new OutputHelper();
	
	public SentMessageFrame(final String fromID,String facultyName){	
		dean=new Dean(fromID);
		this.setTitle("发送建议");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		JPanel sendPane=new JPanel(new MigLayout());
		
		JLabel l=new JLabel(">>收件人: "+facultyName+"教务长");
		final String toID=new Faculty().getMajorFacultyID(facultyName);
		text=new JTextArea(8,18);
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		JScrollPane jsp=new JScrollPane(text);
		jsp.setPreferredSize(new Dimension(300, 280));///
		
		JButton sendButton=new JButton("发送");
		sendButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!text.getText().equals("")){
					dean.sendMessage(toID,text.getText());
					helper.outputToDialog(SEND_SUCCEED);
					showMessageInfo(fromID);
				}else{
					helper.outputToDialog(EMPTY_CONTENT);
				}
			}			
		});
		
		sendPane.add(l,"wrap");
		sendPane.add(jsp,"wrap");
		sendPane.add(sendButton,"gapleft 90");
		
		this.add(sendPane);
		this.setSize(300, 200);
	}
	
	public void showMessageInfo(String id){
		this.dispose();
		JFrame f=new MessageInfoFrame(id);
		f.setVisible(true);		
	}
}
