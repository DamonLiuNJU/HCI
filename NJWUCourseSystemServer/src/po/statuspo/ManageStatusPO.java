package po.statuspo;

import java.io.Serializable;

public class ManageStatusPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type;
	private String on;
	private String off;
	
	public ManageStatusPO(String t,String on,String off){
		type=t;
		this.on=on;
		this.off=off;
	}
	
	public ManageStatusPO(){
		
	}
	
	public String getType(){
		return type;
	}
	
	public String getOnTime(){
		return on;
	}
	public void setOnTime(String on){
		this.on=on;
	}
	
	public String getOffTime(){
		return off;
	}
	public void setOffTime(String off){
		this.off=off;
	}
	
	public String toString(){
		return type+" "+on+" "+off;
	}
}
