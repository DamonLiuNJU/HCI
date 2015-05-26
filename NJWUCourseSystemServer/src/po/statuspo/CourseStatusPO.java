package po.statuspo;

import java.io.Serializable;

public class CourseStatusPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String module;
	String type;
	String on;
	String off;
	
	public CourseStatusPO(String m,String t,String on,String off){
		module=m;
		type=t;
		this.on=on;
		this.off=off;
	}
	public CourseStatusPO(){
		
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
	
	public String getModule(){
		return module;
	}
	
	public String getType(){
		return type;
	}
	
	public String toString(){
		return module+" "+type+" "+on+" "+off;
	}
}
