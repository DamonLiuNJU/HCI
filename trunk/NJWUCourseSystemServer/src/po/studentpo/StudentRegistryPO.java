package po.studentpo;

import java.io.Serializable;

public class StudentRegistryPO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5401837500940295178L;
	/**
	 * @param args
	 */
	String id ;
	String password;
	public StudentRegistryPO(String id,String password){
		this.id = id;
		this.password = password;
	}
	public String getID(){
		return this.id;
	}
	public String getPassword(){
		return this.password;
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

}
