package po.managerpo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MessagePO implements Serializable{
	String from_id;
	String to_id;
	String content;
	
	public MessagePO(String fi,String ti,String c){
		from_id=fi;
		to_id=ti;
		content=c;
	}
	public MessagePO(){
		
	}
	
	public String getFromID(){
		return from_id;
	}
	
	public String getToID(){
		return to_id;
	}
	
	public String getContent(){
		return content;
	}
	
	public String toString(){
		return from_id+" "+to_id+" "+content;
	}
}
