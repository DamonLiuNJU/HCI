package vo.managervo;

public class SentMessageVO {
	String to_name;
	String content;
	
	public SentMessageVO(String name,String c){
		to_name = name;
		content = c;
	}
	
	public String getFrom_Name(){
		return to_name;
	}
	
	public String getContent(){
		return content;
	}
}
