package vo.coursevo;

public class CourseStatVO {
	String type;
	int num;

	public CourseStatVO(String t, int n) {
		type = t;
		num = n;
	}

	public String getType() {
		return type;
	}

	public int getNum() {
		return num;
	}

	public String toString() {
		return type + "   " + num + "\r\n";
	}
}
