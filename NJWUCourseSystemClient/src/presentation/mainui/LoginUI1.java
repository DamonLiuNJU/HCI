package presentation.mainui;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.AutumnSkin;
import org.jvnet.substance.skin.SubstanceSaharaLookAndFeel;
import org.jvnet.substance.theme.SubstanceOliveTheme;
import org.jvnet.substance.watermark.SubstanceBubblesWatermark;

import presentation.tools.OutputHelper;
import presentation.tools.UserType;
import presentation.tools.ViewReplyMessage;
import vo.studentvo.StudentInfoVO;
import businesslogic.managerbl.Admin;
import businesslogic.managerbl.Manager;
import businesslogic.studentbl.StudentInfo;
import businesslogic.teacherbl.Teacher;

@SuppressWarnings("serial")
public class LoginUI1 extends JFrame implements ViewReplyMessage{
	OutputHelper helper = new OutputHelper();
    JFrame frame;
    JPanel p;
    @SuppressWarnings("rawtypes")
	JComboBox roleBox;
    JTextField tf;
    JPasswordField pf;

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public LoginUI1() {
    	
    	 frame = new JFrame("NJWU学生选课系统-登录");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);    
        com.sun.awt.AWTUtilities.setWindowOpacity(frame, 0.93f);  
        
        p = new JPanel();
        p.setSize(600, 400);
        
       
		
        JPanel thepanel = new JPanel();
        thepanel.setBounds(250, 300, 350, 30);
        thepanel.setOpaque(false); // 将面板设为透明
        JButton button1 = new JButton("登录");
        button1.setBounds(0, 0, 100, 30);
        JButton button2 = new JButton("重置");
        button2.setBounds(250, 0, 100, 30);
        button1.addActionListener(new BListener());
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                roleBox.setSelectedIndex(0);
                tf.setText("");
                pf.setText("");
            }
        });
        thepanel.setLayout(null);
        thepanel.add(button1);
        thepanel.add(button2);

        JPanel panel = new JPanel();
        panel.setBounds(200, 100, 400, 150);
        panel.setLayout(new GridLayout(3, 2, 0, 10));
        panel.setOpaque(false);
        JLabel roleLabel = new JLabel("用户类型");
        String[] roleList = new String[] { "管理员","教务处老师","院系教务老师", "任课教师", "学生" };
        roleBox = new JComboBox(roleList);
        JLabel idlabel = new JLabel("用户名");
        tf = new JTextField(25);
        JLabel pwlabel = new JLabel("密码");
        pf = new JPasswordField(25);
        panel.add(roleLabel);
        panel.add(roleBox);
        panel.add(idlabel);
        panel.add(tf);
        panel.add(pwlabel);
        panel.add(pf);
 
        p.setLayout(null);
        p.add(panel);
        p.add(thepanel);
        p.setOpaque(false);

        ImageIcon img = new ImageIcon("./Icon/8.jpg"); // 设置背景
        JLabel bLabel = new JLabel(img);
        bLabel.setBounds(0, 0, 850, 550);

        frame.getContentPane().add(p);
        frame.add(bLabel);
        frame.setSize(850, 550);
        int windowwedth=frame.getWidth();
		int windowheight=frame.getHeight();
		int screenwedth=Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenheight=Toolkit.getDefaultToolkit().getScreenSize().height;
		frame.setLocation((screenwedth-windowwedth)/2,  (screenheight-windowheight)/2);
       
    	
        frame.setVisible(true);

    }

    public static void main(String[] args) {
    	try{
			try{
				UIManager.setLookAndFeel(new SubstanceLookAndFeel());										
			}catch(UnsupportedLookAndFeelException  ex){
				System.out.println(ex.getMessage());
			}
		}catch(Exception et){
				System.out.println(et.getMessage());
		}
		SubstanceSaharaLookAndFeel.setSkin(new AutumnSkin());
		SubstanceLookAndFeel.setCurrentWatermark(new SubstanceBubblesWatermark());
		SubstanceLookAndFeel.setCurrentTheme(new SubstanceOliveTheme());

		JFrame.setDefaultLookAndFeelDecorated(true);
		
        new LoginUI1();
    }
    
    class BListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	UserType type = change(roleBox.getSelectedItem().toString());
            String userid = tf.getText();
        	char[] password = pf.getPassword();
        	String pass = "";
        	for(int i=0 ; i <password.length ; i++){
        		pass+=password[i];
        	}       	
        	boolean valid;
        	int login=0;//登陆验证类型：0 用户不存在，1 密码错误，2 成功
        	
        	IDFormat format=new IDFormat(type);
        	
        	if(type==UserType.管理员||type==UserType.教务处老师||type==UserType.院系教务老师){
        		login=new Manager().login(userid, pass); 
            	if(login==0||!format.isFormatValid(userid)){
            		login=0;
            		helper.outputToDialog(ID_NOT_EXIST);
            	}else if(login==1){
            		helper.outputToDialog(PW_ERROR);
            	}
        	}
        	
            switch(type){
            case 管理员:  
            	if(login==2){
            		frame.dispose();
            		new AdminMainUI(new Admin());
            	}
            	break;
            case 教务处老师:          	
            	if(login==2){   
            		frame.dispose(); 
            		new DeanMainUI(userid);
            	}       		
            	break;
            case 院系教务老师:
            	if(login==2){
            		frame.dispose(); 
            		new FacultyMainFrame(userid).start();       	
            	}
            	break;
            case 任课教师:         	
            	Teacher teacher = new Teacher(userid , pass);
            	if(teacher.isVaild() == 0){
            		teacher.initTeacher();
            		new TeacherMainUI(teacher);
            		frame.dispose();
            	}else if(teacher.isVaild() == 1){
            		helper.outputToDialog(ID_NOT_EXIST);
            	}
            	else if(teacher.isVaild() == 2){
            		helper.outputToDialog(PW_ERROR);
            	}
            	else {
            		helper.outputToDialog("与服务器连接失败");
            	}
            	break;
            case 学生 :
            	StudentInfoVO siv = new StudentInfoVO();
            	siv.setID(userid);
            	siv.setKey(password);
            	valid = new StudentInfo().isKeyValid(siv);
            	if(valid){
            		frame.dispose();
            		JFrame f = new StudentMainUI().createFrame(userid,frame); 
            		f.setVisible(true);
            	}else{
            		helper.outputToDialog("Wrong PassWord !");
            	}
            	break;           
            	
            }
            
        }
    }

	public UserType change(String s) {
		UserType type = null;
		switch (s) {
		case "管理员":
			type=UserType.管理员;
			break;
		case "教务处老师":
			type = UserType.教务处老师;
			break;
		case "院系教务老师":
			type = UserType.院系教务老师;
			break;
		case "任课教师":
			type = UserType.任课教师;
			break;
		case "学生":
			type = UserType.学生;
			break;
		}
		return type;
	}

}
