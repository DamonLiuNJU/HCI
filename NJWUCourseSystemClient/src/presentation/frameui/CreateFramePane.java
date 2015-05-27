package presentation.frameui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import net.miginfocom.swing.MigLayout;
import presentation.deanui.DeanUIImage;
import presentation.tools.OutputHelper;
import presentation.tools.Setter;
import presentation.tools.ViewReplyMessage;
import businesslogic.framebl.Frame;

@SuppressWarnings("serial")
public class CreateFramePane extends JPanel implements ViewReplyMessage,DeanUIImage{
	JPanel panel;
	ArrayList<JScrollPane> paneList=new ArrayList<JScrollPane>();
	ArrayList<JTextArea> textList=new ArrayList<JTextArea>();
	
	OutputHelper helper=new OutputHelper();
	Frame frame=new Frame();	
	
	public CreateFramePane(){		
		this.setLayout(new MigLayout());
		this.setOpaque(false);
		
		panel=new JPanel(new MigLayout());	
		panel.setOpaque(false);
		JScrollPane jsp=new JScrollPane(panel);//添加paneList的scrollpane
		jsp.setOpaque(false);
		jsp.getViewport().setOpaque(false); 
		jsp.setBorder(null);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setPreferredSize(new Dimension(480,270));
		
		JPanel p1=new JPanel(new MigLayout());
		p1.setOpaque(false);
		JLabel l=new JLabel(">> 创建新框架");
		ImageIcon icon1=new ImageIcon(addButton);
		JButton addButton=new JButton(icon1);
		new Setter().setButtonWithImage(addButton);
		addButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextArea ta=new JTextArea(2,38);
				ta.setLineWrap(true);
				ta.setWrapStyleWord(true);
				textList.add(ta);				
				int len=textList.size();				
				JLabel label=new JLabel(len+"");	
				
				JPanel pane=new JPanel();
				pane.setOpaque(false);
				pane.add(label);
				pane.add(textList.get(len-1));
				
				JScrollPane jsp2=new JScrollPane(pane);
				jsp2.setOpaque(false);
				jsp2.getViewport().setOpaque(false); 
				jsp2.setBorder(null);
				paneList.add(jsp2);
				panel.add(paneList.get(len-1),"wrap");
				panel.repaint();
			}			
		});
		JButton submitButton=new JButton("提交");
		submitButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int res=helper.ouputToQuesDialog(CREATE_VERIFY);
				if(res==0){				
					ArrayList<String> list=new ArrayList<String>();
					for(JTextArea ta:textList){
						String text=ta.getText();	
						if(!text.equals("")){
							//去掉text中空白符-->te
							Pattern p = Pattern.compile("\\s*|\t|\r|\n");							
							Matcher m = p.matcher(text);
							String te= m.replaceAll("");
				        
							if(!te.equals("")){
								list.add(ta.getText().replaceAll("\n", "\t"));
							}
						}
					}
					frame.create(list);
					helper.outputToDialog(CREATE_SUCCEED);
				}
			}			
		});		
		p1.add(l);
		p1.add(addButton,"gapleft 180");
								
		
		
		this.add(p1,"gapright 90");
		this.add(submitButton,"gapleft 180,wrap");
		this.add(jsp);
		this.add(new FrameRemarkPane(),"gapleft 120,gaptop 50,wrap");
	}
}
