package data.helper;

public class Hint {
	private String insertHint;
	private String updateHint;
	private String findHint;
	private String findOneHint;
	private String deleteHint;
	
	String fileName;
	public Hint(String fileName){
		this.fileName=fileName;
		init();
	}
	
	void init(){
		String back=" in "+fileName+" succeed!";;
		insertHint="Insert"+back;
		updateHint="Update"+back;
		findHint="Find"+back;
		findHint="Find one"+back;
		deleteHint="Delete"+back;
	}
	
	public void hintInsert(){
		out(insertHint);
	}
	
	public void hintUpdate(){
		out(updateHint);
	}
	
	public void hintFind(){
		out(findHint);
	}
	
	public void hintFindOne(){
		out(findOneHint);
	}
	
	public void hintDelete(){
		out(deleteHint);
	}
	
	void out(String s){
		System.out.println(s);
	}
}
