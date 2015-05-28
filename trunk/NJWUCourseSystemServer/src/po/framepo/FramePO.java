package po.framepo;

import java.io.Serializable;

public class FramePO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int index;
	String content;

	public FramePO(int i, String c) {
		index = i;
		content = c;
	}

	public FramePO() {

	}

	public void setIndex(int i) {
		index = i;
	}

	public void setContent(String c) {
		content = c;
	}

	public String getContent() {
		return content;
	}

}
