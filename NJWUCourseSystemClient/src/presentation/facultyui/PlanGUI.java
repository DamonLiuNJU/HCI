package presentation.facultyui;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import presentation.frameui.FrameInfoPane;
import presentation.planui.PlanRevisePanel;
import presentation.planui.PlanUploadPanel;

public class PlanGUI {
	int frameWidth;
	int frameHeight;
	JPanel createPanel;
	JPanel revisePanel;

	JTextArea plan;
	String id;
	String path;
	JScrollPane planSp;

	public PlanGUI(String id) {
		frameWidth = 3 * GUIHelper.getFrameWidth() / 2;
		frameHeight = 3 * GUIHelper.getFrameHeight() / 2;
		this.id = id;

		createPanel = new JPanel();
		revisePanel = new JPanel();
	}

	void initialCrePanel() {
		createPanel.setLayout(null);
		createPanel.add(getFrameLabel());
		// createPanel.add(getDownloadBut());
		JPanel uploadPanel = new PlanUploadPanel(id).getUploadPanel();
		createPanel.add(uploadPanel);
		createPanel.add(getFrameContent());
		createPanel.setBounds(frameWidth / 4, frameHeight / 5,
				frameWidth * 2 / 3, frameHeight * 2 / 3);
	}

	void initialRevPanel() {
		revisePanel.removeAll();
		revisePanel.setLayout(null);
		JPanel uploadPanel = new PlanUploadPanel(id).getUploadPanel();
		revisePanel.add(uploadPanel);
		revisePanel.add(new PlanRevisePanel(id).getRevisePanel());
		revisePanel.setBounds(frameWidth / 4, frameHeight / 5,
				frameWidth * 2 / 3, frameHeight * 2 / 3);
	}

	public JPanel getCreatePanel() {
		// TODO Auto-generated method stub
		initialCrePanel();
		createPanel.setOpaque(false);	
		return createPanel;
	}

	public JPanel getRevisePanel() {
		// TODO Auto-generated method stub
		initialRevPanel();
		revisePanel.setOpaque(false);	
		return revisePanel;
	}

	JLabel getFrameLabel() {
		JLabel frameLabel = new JLabel("教学计划框架：");
		frameLabel.setFont(new Font("微软雅黑", 1, 15));
		frameLabel.setBounds(0, 0, frameWidth / 4, frameHeight / 15);
		return frameLabel;
	}

	JScrollPane getFrameContent() {

		JPanel frame = new FrameInfoPane(0);
	
		JScrollPane sp = new JScrollPane(frame);
		sp.setOpaque(false);
		sp.getViewport().setOpaque(false);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setVisible(true);
		sp.setBounds(frameWidth / 45, frameHeight / 15, frameWidth * 32 / 50,
				frameHeight * 2 / 5);
		
		return sp;
	}

	JButton getDownloadBut() {
		JButton download = new JButton("下载至本地");
		download.setFont(new Font("微软雅黑", 0, 13));
		download.setBounds(frameWidth / 2, frameHeight / 60, frameWidth / 7,
				frameHeight / 30);
		download.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 下载结构框架文档至本地
			}
		});
		return download;
	}

}
