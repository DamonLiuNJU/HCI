package vo.framevo;

import po.framepo.CreditRestrictPO;

public class CreditRestrictVO {
	private String module;
	private int low;
	private int high;
	
	public CreditRestrictVO(String module,int low,int high){
		this.module=module;
		this.low=low;
		this.high=high;
	}
	
	public CreditRestrictVO(CreditRestrictPO cp){
		module=cp.getModule();
		low=cp.getLow();
		high=cp.getHigh();
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
	@Override
	public String toString(){
		return module+":"+low+"-"+high;
	}
}
