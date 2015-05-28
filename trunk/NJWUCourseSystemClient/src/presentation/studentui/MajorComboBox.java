package presentation.studentui;

import java.util.ArrayList;

import javax.swing.JComboBox;

import businesslogic.studentbl.MajorInfo;

public class MajorComboBox extends JComboBox<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8961517147285041910L;
	/**
	 * @param args
	 */
	public MajorComboBox(){
		MajorInfo mi = new MajorInfo();
		ArrayList<String> name =  mi.getMajorList();
		for(String a: name){
			this.addItem(a);
		}
//		this.addItem("软件学院");
//		this.addItem("商学院");
//		this.addItem("文学院");
//		this.addItem("社会学院");
//		this.addItem("政府管理学院");
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

}
