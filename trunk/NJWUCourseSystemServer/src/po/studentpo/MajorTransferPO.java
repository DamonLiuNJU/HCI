package po.studentpo;

import java.io.Serializable;

public class MajorTransferPO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9130444777358009488L;
	/**
	 * @param args
	 */
	String student_id;
	String preschool;
	String toschool;
	String status;
	String applydate;
	
	public MajorTransferPO(String student_id,String preschool,
			String toschool,String status,String applydate)
	{
		this.student_id=student_id;
		this.preschool=preschool;
		this.toschool=toschool;
		this.status=status;
		this.applydate=applydate;
	}
	
	public String getStudent_ID(){
		return this.student_id;
	}
	public String getPreSchool(){
		return this.preschool;
	}
	public String getToSchool(){
		return this.toschool;
	}
	public String getStatus(){
		return this.status;
	}
	public String getapplydate(){
		return this.applydate;
	}
	

}
