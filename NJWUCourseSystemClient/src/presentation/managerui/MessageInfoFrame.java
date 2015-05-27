package presentation.managerui;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import presentation.deanui.DeanUIImage;

import net.miginfocom.swing.MigLayout;
import vo.managervo.SentMessageVO;
import businesslogic.managerbl.Dean;

@SuppressWarnings("serial")
public class MessageInfoFrame extends JFrame implements DeanUIImage{
	
	public MessageInfoFrame(String id){
		this.setTitle("发件箱");
		this.setLocationRelativeTo(null);
		Image image=new ImageIcon(letterIcon).getImage();
		this.setIconImage(image);
		
		JPanel pane=new JPanel(new MigLayout());
		JLabel label=new JLabel(">>已发送");
		ArrayList<JPanel> paList=new ArrayList<JPanel>();
		ArrayList<JTextArea> taList=new ArrayList<JTextArea>();
		ArrayList<JLabel> laList=new ArrayList<JLabel>();
		ArrayList<SentMessageVO> smvList=new Dean(id).showMyMessage();
		int len=smvList.size();
		for(int i=0;i<len;i++){
			JTextArea ta=new JTextArea(2,31);
			String message="To "+smvList.get(i).getFrom_Name()+":\r\n    "
					+smvList.get(i).getContent();
			ta.setText(message);
			ta.setEditable(false);
			ta.setBorder(null);
			ta.setLineWrap(true);
			ta.setWrapStyleWord(true);
			taList.add(ta);
			laList.add(new JLabel((i+1)+"."));
		}
		pane.add(label,"wrap");
		for(int i=0;i<len;i++){
			JPanel p=new JPanel(new MigLayout());
			paList.add(p);
			p.add(laList.get(i));
			p.add(taList.get(i),"gapleft 3");
			pane.add(p,"gapleft 5,wrap");
		}
		
		JScrollPane jsp=new JScrollPane(pane);
		
		this.add(jsp);
		this.setSize(420,300);
	}
	
}
