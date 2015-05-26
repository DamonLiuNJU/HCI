package presentation.deanui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import net.miginfocom.swing.MigLayout;
import presentation.managerui.SentMessageFrame;
import presentation.planui.PlanList;
import presentation.planui.PlanTextArea;
import presentation.tools.OutputHelper;
import presentation.tools.Setter;
import presentation.tools.ViewReplyMessage;

@SuppressWarnings("serial")
public class PlanPane extends JPanel implements ViewReplyMessage{	
	@SuppressWarnings("rawtypes")
	JComboBox facultyBox;
	JScrollPane jsp;
	JTextArea plan;
	String facultyName;
	
	OutputHelper helper=new OutputHelper();
	
	public PlanPane(final String id){
		this.setLayout(new MigLayout());
		this.setOpaque(false);
				
		plan=new JTextArea(15,50);
		plan.setOpaque(false);
		plan.setEditable(false);
		plan.setBorder(null);
		jsp=new JScrollPane(plan);
		jsp.setOpaque(false);
		jsp.getViewport().setOpaque(false); 
		
		JPanel p0=new JPanel();
		p0.setOpaque(false);
		JLabel label1=new JLabel("院系列表");
		facultyBox=new PlanList().getFacultyComboBox();
		facultyBox.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				facultyName=facultyBox.getSelectedItem().toString();
				new PlanTextArea().setPlanTextArea(facultyName,plan);				
			}			
		});
		p0.add(label1);
		p0.add(facultyBox);
		
		JLabel label2=new JLabel(">>教学计划");
		
		JPanel p=new JPanel(new MigLayout());
		p.setOpaque(false);
		ImageIcon img=new ImageIcon("./icon/le1.png");
		JButton b2=new JButton("修改建议",img);
		new Setter().setButtonWithImage(b2);
		b2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(facultyName!=null){
					JFrame frame=new SentMessageFrame(id,facultyName);
					frame.setVisible(true);
				}else{
					helper.outputToDialog(NOT_SELECT_FACULTY);
				}
			}
			
		});
		p.add(b2,"gaptop 180");
		
		this.add(p0,"gapleft 5,wrap");
		this.add(label2,"gapleft 15,wrap");
		this.add(jsp);
		this.add(p,"gapleft 150");
	}
	
}
