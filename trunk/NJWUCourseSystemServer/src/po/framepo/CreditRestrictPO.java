package po.framepo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CreditRestrictPO implements Serializable{
	private String module;
	private int low;
	private int high;
	
	public CreditRestrictPO(String module,int low,int high){
		this.module=module;
		this.low=low;
		this.high=high;
	}
	
	public String getModule(){
		return module;
	}
	public int getLow(){
		return low;
	}
	public int getHigh(){
		return high;
	}
	
	public void setModule(String m){
		module=m;
	}
	public void setLow(int l){
		low=l;
	}
	public void setHigh(int h){
		high=h;
	}
	
	public String toString(){
		return module+" "+low+" "+high;
	}
}
