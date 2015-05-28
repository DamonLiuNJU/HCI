package presentation.deanui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;

import net.miginfocom.swing.MigLayout;
import presentation.courseui.ApplyComponent;
import presentation.managerui.CourseAuditAutoSendButton;
import presentation.tools.Setter;
import vo.coursevo.ApplyVO;

@SuppressWarnings("serial")
public class CourseAuditPane extends JPanel implements DeanUIImage{
	JPanel basePane;
		
	String id;
	ApplyComponent apply=new ApplyComponent();
	
	public CourseAuditPane(String id){
		this.id=id;
		this.setLayout(new MigLayout());
		this.setOpaque(false);
		
		basePane=new JPanel(new MigLayout());
		basePane.setOpaque(false);
		paintBasePane();
	
		this.add(basePane);
	}
	
	public void paintBasePane(){
		JLabel label1=new JLabel(">>通识，公选课课程审核");
		label1.setFont(new Font("华文楷体", Font.PLAIN, 14));
		
		final JTable table=apply.getApplyList();//通识公选课课程申请表	
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {// 单击鼠标左键
					if (e.getClickCount() == 2) {
						 int row =((JTable)e.getSource()).rowAtPoint(e.getPoint()); 
						 ApplyVO avo=new ApplyVO((String)table.getValueAt(row,0),
								 (String)table.getValueAt(row,1),(String)table.getValueAt(row,2));
						 JFrame f=getCourseDetailPane(avo);
						 f.setVisible(true);						 
					}
				}
			}
		});
		JScrollPane jsp=new JScrollPane(table);
		jsp.setOpaque(false);
		jsp.getViewport().setOpaque(false); 
		jsp.setPreferredSize(new Dimension(500,350));
		
		ImageIcon icon2=new ImageIcon(refreshButton);
		JButton refreshInfo_b=new JButton(icon2);
		refreshInfo_b.setToolTipText("刷新");
		refreshInfo_b.setToolTipText("刷新申请表");
		new Setter().setButtonUnOpaque(refreshInfo_b);
		refreshInfo_b.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}			
		});
		
		basePane.add(label1,"wrap");
		basePane.add(new JSeparator(),"growx,wrap");
		basePane.add(jsp,"gaptop 10,wrap");
		basePane.add(refreshInfo_b,"gapleft 460");
		refreshInfo_b.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				updatePane();
			}			
		});
	}
	
	public JFrame getCourseDetailPane(ApplyVO avo){
		String courseName=avo.getCourseName();
		
		JFrame f=new JFrame();
		f.setSize(360,360);
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		
		JPanel pane=new JPanel(new MigLayout());
		pane.setOpaque(false);
		
		JLabel la=new JLabel("申请课程："+courseName);
		JTextArea ta=apply.getApplyInfo(courseName);
		ta.setEditable(false);
		JScrollPane jsp=new JScrollPane(ta);
		
		CourseAuditAutoSendButton asb=new CourseAuditAutoSendButton(id,avo,ta.getText());
		JButton pass_b=asb.getPassButton(f);
		JButton npass_b=asb.getNotPassButton(f);
		JPanel pa=new JPanel(new MigLayout());
		pa.setOpaque(false);
		pa.add(pass_b);
		pa.add(npass_b,"gapleft 100");
		
		pane.add(la,"wrap");
		pane.add(jsp,"wrap");
		pane.add(pa,"gapleft 20");
		
		f.add(pane);		
		return f;
	}
	
	public void updatePane(){
		basePane.removeAll();
		paintBasePane();
		basePane.repaint();
	}
}
