package businesslogicservice;

import java.util.ArrayList;

import vo.framevo.FrameVO;
import businesslogic.utilitybl.ReplyMessage;

public interface FrameBLService extends ReplyMessage{
	public String create(ArrayList<String> list);
	public String modify(ArrayList<String> list);
	public FrameVO showFrame();
	public void setStatus(int m1,int d1,int m2,int d2);
}
