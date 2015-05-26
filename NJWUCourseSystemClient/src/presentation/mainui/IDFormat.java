package presentation.mainui;

import presentation.tools.UserType;

/**
 * 用户id格式类
 * @author bb
 *
 */
public class IDFormat {
	UserType type;
	
	char firstCharAsFlag;
	int idLength;
	
	public IDFormat(UserType type){
		this.type=type;
		setFormat();
	}
	
	public void setFormat(){
		switch(type){
		case 管理员:
			firstCharAsFlag='0';
			idLength=2;
			break;
		case 教务处老师:
			firstCharAsFlag='1';
			idLength=2;	
			break;
		case 院系教务老师:
			firstCharAsFlag='1';
			idLength=6;
			break;
		case 任课教师:
			firstCharAsFlag='2';
			idLength=7;
			break;
		default:
		}
	}
	
	public boolean isFormatValid(String id){
		if(id.charAt(0)==firstCharAsFlag && id.length()==idLength){
			return true;
		}
		return false;		
	}
}
