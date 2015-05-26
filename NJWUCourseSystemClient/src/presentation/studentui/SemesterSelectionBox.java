package presentation.studentui;

import javax.swing.JComboBox;

public class SemesterSelectionBox extends JComboBox<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4505589820460749017L;
	/**
	 * @param args
	 */
	public SemesterSelectionBox(){
		this.addItem("大一上");
		this.addItem("大一下");
		this.addItem("大二上");
		this.addItem("大二下");
		this.addItem("大三上");
		this.addItem("大三下");
		this.addItem("大四上");
		this.addItem("大四下");
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

}
