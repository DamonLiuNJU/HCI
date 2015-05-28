package businesslogic.statusbl;

/**
 * 限制日期类
 * @author cbb
 *
 */
public class BoundDate {
   int month;
   int day;
   
	public BoundDate(int m, int d) {
		month = m;
		day = d;
	}

	public BoundDate(String s) {
		String[] sp = s.split("-");
		month = Integer.parseInt(sp[0]);
		day = Integer.parseInt(sp[1]);
	}

	public BoundDate() {

	}
	
	/**
	 * 判断限制时间是否在传入时间之前
	 * @param m
	 * @param d
	 * @return
	 */
	public boolean previous(int m, int d) {
		if (m > month || (m == month && d >= day)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断限制时间是否在传入时间之后
	 * @param m
	 * @param d
	 * @return
	 */
	public boolean after(int m, int d) {
		if (m < month || (m == month && d <= day)) {
			return true;
		}
		return false;
	}

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	@Override
	public String toString() {
		return month + "-" + day;
	}
}
