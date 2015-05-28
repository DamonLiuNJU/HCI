package presentation.planui;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import presentation.facultyui.GUIHelper;
import businesslogic.planbl.PlanController;
import businesslogicservice.planblservice.PlanBLService;

public class PlanUploadPanel {
int frameWidth;
int frameHeight;
	JTextField planUrl;
	String path;
	String id;
	public PlanUploadPanel(String id){
				frameWidth=GUIHelper.getFrameWidth();
				frameHeight=GUIHelper.getFrameHeight();
                System.out.println("Plan"+id);
				this.id=id;
	}
	
public 	JPanel getUploadPanel(){
	JPanel panel=new JPanel();
	//panel.setBackground(Color.red);
	panel.setLayout(null);
	panel.add(getPlanLabel());
	panel.add(getPlanUrl());
	panel.add(getScanBut());
	panel.add(getUploadBut());
	panel.setBounds(frameWidth/14,frameHeight*3/4,frameWidth,frameHeight/5);
	planUrl.setText("");
	panel.setOpaque(false);	
	return panel;
	}
	JLabel getPlanLabel(){
		JLabel plan=new JLabel("上传教学计划:");
		plan.setFont(new Font("微软雅黑",0,13));
		plan.setBounds(0,0,frameWidth/5,frameHeight/15);
		return plan;
}		
JTextField getPlanUrl(){
	 	planUrl=new JTextField(30);
		planUrl.setBounds(frameWidth/5,0,frameWidth*4/10,frameHeight/15);
		return planUrl;
}

JButton getScanBut(){
JButton scan=new JButton("浏览");
scan.setFont(new Font("微软雅黑",0,13));
scan.setBounds(frameWidth*7/10,0,frameWidth/10,frameHeight/15);
scan.addActionListener(new ActionListener(){


	@Override
	public void actionPerformed(ActionEvent e){	
			JFileChooser file = new JFileChooser();
			int result = file.showOpenDialog(new JPanel());
					if (result ==JFileChooser.APPROVE_OPTION) {						
					    path=file.getSelectedFile().getAbsolutePath();
							System.out.println(path);
							planUrl.setText(file.getSelectedFile().getAbsolutePath()); 
			
		}
	}	
});	
return scan;

}
JButton getUploadBut(){
		JButton upload=new JButton("上传");
		upload.setFont(new Font("微软雅黑",0,13));
		upload.setBounds(frameWidth*8/10,0,frameWidth/10,frameHeight/15); 
		upload.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){	
					String planContent =null;
					try {	
							planContent =getFileContent();
							System.out.println(planContent);
							updatePlan(planContent);
							GUIHelper.sendMessage("上传成功");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
	}	
	}
		});
return upload;
}

/*
public static void main(String arg[]){
	PlanUploadPanel a=new  PlanUploadPanel("1");
	
	a.run();
}
void run(){
	
	path="C:"+"\"+"Users"+"\"+"dell"+"\"+"Desktop"+"\"+"新建文本文档.txt";
	try {
		System.out.println(getFileContent());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
*/
String getFileContent()throws Exception{
	System.out.println(path);
	System.out.println("----------------");
	File file = new File(path);
try{
	  BufferedReader bf = new BufferedReader(new FileReader(file));
	  String content = "";
	  StringBuilder sb = new StringBuilder();
	  while(content != null){
	   content = bf.readLine();
	   if(content == null){
	    break;
	   }
	   sb.append(content.trim());
	  }
	bf.close();
	  return sb.toString();
	 } catch(Exception e){
		 GUIHelper.sendMessage("请重试并将错误反馈给开发人员！");
		 return null;
	 }
}
void updatePlan( String planContent){

	PlanBLService plan=new PlanController();													
	plan.importPlan(id,planContent);
	
	}
}