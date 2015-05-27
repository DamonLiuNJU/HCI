package presentation.managerui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import net.miginfocom.swing.MigLayout;
import presentation.deanui.DeanUIImage;
import presentation.tools.OutputHelper;
import presentation.tools.Setter;
import presentation.tools.ViewReplyMessage;
import businesslogic.managerbl.Manager;

public class ChangePWPane implements ViewReplyMessage,DeanUIImage{
	JPasswordField pf1;
	JPasswordField pf2;
	JPasswordField pf3;
	
	JLabel tLabel1;
	JLabel tLabel2;
	JLabel tLabel3;
	
	boolean succeed=false;
	
	OutputHelper helper=new OutputHelper();
	Manager m;
	
	public ChangePWPane(String id){
		m=new Manager(id);
	}
	
	public JPanel getChangePWPane(JButton return_b){
		
		JPanel changePane=new JPanel(new MigLayout());
		changePane.setOpaque(false);
		ImageIcon image = new ImageIcon(keyIcon);
		JLabel keyLabel=new JLabel(image);
		
		JPanel pane=new JPanel(new MigLayout());
		pane.setOpaque(false);
	
		JPanel p=getMainPane();
		
		JPanel p2=new JPanel(new MigLayout());
		p2.setOpaque(false);
		p2.add(getSaveButton(),"gapleft 90");
		new Setter().setButtonUnOpaque(return_b);
		return_b.setToolTipText("返回");
		p2.add(return_b,"gapleft 40");
		
		pane.add(p,"gaptop 60,wrap");	
		pane.add(p2,"gapleft 40");
		
		changePane.add(keyLabel,"gapleft 140,gaptop 60");
		changePane.add(pane,"gaptop 50");
		return changePane;
	}
	
	public JPanel getChangePWPane(){
		JPanel changePane=new JPanel(new MigLayout());
		changePane.setOpaque(false);
		ImageIcon image = new ImageIcon(keyIcon);
		JLabel keyLabel=new JLabel(image);
		
		JPanel p=getMainPane();
		
		JPanel p2=new JPanel(new MigLayout());
		p2.setOpaque(false);
		p2.add(getSaveButton(),"gapleft 90");
		
		JPanel pane=new JPanel(new MigLayout());
		pane.setOpaque(false);	
		pane.add(p,"gaptop 60,wrap");	
		pane.add(p2,"gapleft 40");
		
		changePane.add(keyLabel,"gapleft 140,gaptop 60");
		changePane.add(pane,"gaptop 50");
		return changePane;
	}
	
	public JPanel getMainPane(){
		JPanel p=new JPanel(new MigLayout());
		p.setOpaque(false);
		JLabel label1=new JLabel("原密码");
		final JLabel tLabel1=new JLabel(PW_ERROR1);
		tLabel1.setForeground(Color.red);
		tLabel1.setVisible(false);
		pf1=new JPasswordField(15);
		pf1.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {
			}

			@Override
			public void focusLost(FocusEvent e) {
				String pass=getPassStr(pf1.getPassword());				
				if(!m.getPassword().equals(pass)){
					tLabel1.setVisible(true);
				}else{
					tLabel1.setVisible(false);
				}
			}		
		});
		JLabel label2=new JLabel("新密码");
		final JLabel tLabel2=new JLabel(PW_ERROR2);
		tLabel2.setForeground(Color.red);
		tLabel2.setVisible(false);
		pf2=new JPasswordField(15);
		pf2.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {
			}

			@Override
			public void focusLost(FocusEvent e) {
				String pass=getPassStr(pf2.getPassword());
				if(pass.length()<=3){
					tLabel2.setVisible(true);
				}else{
					tLabel2.setVisible(false);
				}
			}			
		});
		JLabel label3=new JLabel("再次输入新密码：");
		final JLabel tLabel3=new JLabel(PW_ERROR3);
		tLabel3.setForeground(Color.red);
		tLabel3.setVisible(false);
		pf3=new JPasswordField(15);
		pf3.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {
			}

			@Override
			public void focusLost(FocusEvent e) {
				String pass2=getPassStr(pf2.getPassword());
				String pass3=getPassStr(pf3.getPassword());
				if(!pass2.equals(pass3)){
					tLabel3.setVisible(true);
				}else{
					tLabel3.setVisible(false);
				}
			}			
		});
		
		p.add(label1,"gapleft 50");
		p.add(pf1,"gapleft 8");
		p.add(tLabel1,"gapleft 5,wrap");
		p.add(label2,"gapleft 50");
		p.add(pf2,"gapleft 8");
		p.add(tLabel2,"gapleft 5,wrap");
		p.add(label3);
		p.add(pf3,"gapleft 8");
		p.add(tLabel3,"gapleft 5");	
		
		return p;
	}
	
	public JButton getSaveButton(){		
		JButton saveButton=new JButton("保存");
		saveButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(pf1.getPassword().length==0||pf2.getPassword().length==0||pf3.getPassword().length==0||
						tLabel1.isVisible()||tLabel2.isVisible()||tLabel3.isVisible()){
					helper.outputToDialog(PW_ERROR4);
				}else{
					String pass2=getPassStr(pf2.getPassword());
					m.changePassword(pass2);
					helper.outputToDialog(PW_CHANGE);
				}
			}			
		});
		return saveButton;
	}
	
	String getPassStr(char[] pass){
		StringBuilder sb=new StringBuilder();
		for(char c:pass){
			sb.append(c);
		}
		return sb.toString();
	}
}
