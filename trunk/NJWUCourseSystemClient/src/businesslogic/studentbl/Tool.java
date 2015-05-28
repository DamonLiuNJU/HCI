package businesslogic.studentbl;

import java.text.DecimalFormat;

public class Tool {
	static DecimalFormat df1 = new DecimalFormat("0.0"); //保留1位小数，带前导零
	public static String form(double input){
		return df1.format(input);
	}

}
