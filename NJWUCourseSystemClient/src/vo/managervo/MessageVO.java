package vo.managervo;

public class MessageVO {
	String from_name;
	String content;
	
	public MessageVO(String name,String c){
		from_name = name;
		content = c;
	}
	
	public String getFrom_Name(){
		return from_name;
	}
	
	public String getContent(){
		return content;
	}
}
