package businesslogicservice.creditblservice;

import vo.CreditVO;

public interface CreditBLService {

	/**
	 * @param args
	 */
	public String recordScore(CreditVO cv);
	public int getTotalCredit(String studentNo);  //student get his own credit
	public double getGPA(String studentnumber, int semesterindex);//student use this to get his gpa ,if cannot be calculated ,return -1;
	public double getAverageScore(String coursenumber);//teacher use this to get the average score of his lesson;
	public int getScore(String studentnumber,String coursenumber);
	
}
