package presentation.frameui;

import java.awt.Dimension;
import java.awt.Font;
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
import presentation.statusui.FrameButtonSetter;
import presentation.tools.OutputHelper;
import presentation.tools.ViewReplyMessage;
import businesslogic.framebl.Frame;

@SuppressWarnings("serial")
public class FrameInfoPane extends JPanel implements ViewReplyMessage,DeanUIImage{
	JTextArea frameInfo;
	JTextArea[] ta;
	int len;//number of items
	Frame frame=new Frame();
	
	public FrameInfoPane(){
		this.setLayout(new MigLayout());
		this.setOpaque(false);
		
		JPanel p1=new JPanel(new MigLayout());
		p1.setOpaque(false);
		JLabel label=new JLabel("整体框架策略");
		label.setFont(new Font("华文楷体",Font.PLAIN, 15));
		ImageIcon icon1=new ImageIcon(editButton);
		JButton modifyButton=new JButton(icon1);
		modifyButton.setContentAreaFilled(false);
		modifyButton.setBorderPainted(false);
		boolean enable=new FrameButtonSetter().setEnable(modifyButton);
		if(!enable){
			modifyButton.setToolTipText(MODIFY_TIP);
		}
		modifyButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setInfoEditable(true);
			}			
		});
		p1.add(label,"gapleft 40");
		p1.add(modifyButton,"gapleft 230");		

		JPanel p2=new JPanel(new MigLayout());	
		p2.setOpaque(false);
		JPanel[] pa=getInfoItems();
		JScrollPane[] jsp=new JScrollPane[len];
		for(int i=0;i<len;i++){
			jsp[i]=new JScrollPane(pa[i]);
			jsp[i].setOpaque(false);
			jsp[i].getViewport().setOpaque(false); 
			jsp[i].setBorder(null);
			p2.add(jsp[i],"wrap");
		}
		
		JScrollPane jsp1=new JScrollPane(p2);
		jsp1.setOpaque(false);
		jsp1.getViewport().setOpaque(false); 
		jsp1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp1.setPreferredSize(new Dimension(460,270));
		jsp1.setBorder(null);
		
		JButton submitButton=new JButton("提交");
		submitButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> list=new ArrayList<String>();
				for(int i=0;i<len;i++){
					String text=ta[i].getText();
					
					if(!text.equals("")){
						//去掉text中空白符-->te
						Pattern p = Pattern.compile("\\s*|\t|\r|\n");							
						Matcher m = p.matcher(text);
						String te= m.replaceAll("");
			        
						if(!te.equals("")){
							list.add(ta[i].getText().replaceAll("\n", "\t"));
						}
					}
				}
				frame.modify(list);
				setInfoEditable(false);
				new OutputHelper().outputToDialog(MODIFY_SUCCEED);
			}			
		});
		
		// 框架为空
		if (pa.length == 0) {
			modifyButton.setVisible(false);
			submitButton.setVisible(false);
		}
				
		this.add(p1,"gapleft 5,gaptop 5,wrap");
		this.add(jsp1,"gapleft 5,wrap");
		this.add(submitButton,"gapleft 180,gaptop 10");
	}
	
	/**
	 * 构造一个含item panels的info panel,j仅供查看使用
	 * @param n 此参数实际是无意义的，只是为了与no-parameter constructor区分
	 */
	public FrameInfoPane(int n){
		this.setLayout(new MigLayout());
		JPanel[] pa=getInfoItems();
		JScrollPane[] jsp=new JScrollPane[len];
		for(int i=0;i<len;i++){
			jsp[i]=new JScrollPane(pa[i]);
			jsp[i].setOpaque(false);
			jsp[i].getViewport().setOpaque(false); 
			jsp[i].setBorder(null);
			this.add(jsp[i],"wrap");
		}
		this.setOpaque(false);
	}
	
	//本面板主要功能的入口方法
	public JPanel[] getInfoItems(){
		ArrayList<String> info=frame.showFrame().getContent();
		len = info.size();
		ta = new JTextArea[len];
		JPanel[] pa = new JPanel[len];
		JLabel[] la = new JLabel[len];

		for (int i = 0; i < len; i++) {
			ta[i] = new JTextArea(2, 38);
			ta[i].setLineWrap(true);
			ta[i].setWrapStyleWord(true);
			ta[i].setEditable(false);
			ta[i].setText(info.get(i));

			la[i] = new JLabel((i + 1) + "");
			pa[i] = new JPanel();
			pa[i].setOpaque(false);
			pa[i].add(la[i]);
			pa[i].add(ta[i]);
		}
		return pa;
	}

	public void setInfoEditable(boolean isEditable){
		for(int i=0;i<ta.length;i++){
			ta[i].setEditable(isEditable);
		}
	}
}
