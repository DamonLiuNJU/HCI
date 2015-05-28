package presentation.managerui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import presentation.deanui.DeanUIImage;

import businesslogic.managerbl.Manager;

public class HeadInfoLabel implements DeanUIImage{
	String id;
	
	public HeadInfoLabel(String id){
		this.id=id;
	}
	
	public JLabel getNameLabel(){
		JLabel label=new JLabel(new Manager(id).getName());
		return label;
	}
	
	public JLabel getDeanImageLabel(){
		ImageIcon img= new ImageIcon(dean);
		return getImageLabel(img);
	}
	
	public JLabel getFacultyImageLabel(){
		ImageIcon img = new ImageIcon(dean);//revise
		return getImageLabel(img);
	}
	
	public JLabel getAdminImageLabel(){
		ImageIcon img = new ImageIcon(dean);//revise
		return getImageLabel(img);
	}
	
	private JLabel getImageLabel(ImageIcon img){
		return new JLabel(img);
	}
}
