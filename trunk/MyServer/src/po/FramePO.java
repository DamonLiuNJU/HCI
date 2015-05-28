package po;

public class FramePO {
    int index;
    String content;
	
    public FramePO(int i,String c){
    	index=i;
    	content=c;
    }
    public FramePO(){
    	
    }
    
    public void setIndex(int i){
    	index=i;
    }
    public int getIndex(){
    	return index;
    }
    
    public void setContent(String c){
    	content=c;
    }
	public String getContent(){
		return content;
	}
	
	public String getValues(){
		StringBuilder sb=new StringBuilder();
		sb.append("(");
		sb.append(s(index)+","+s(content));
		sb.append(")");
		return sb.toString();		
	}
	public String s(Object o){
		return "'"+o+"'";
	}
	public String getAttributeNames(){
		return "`index`,`content`";
	}
}
