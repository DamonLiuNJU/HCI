package vo.framevo;

import java.util.ArrayList;

import po.framepo.FramePO;

public class FrameVO {

	ArrayList<String> content;
	
	public FrameVO(ArrayList<FramePO> fpList){
		content=new ArrayList<String>();
		for(FramePO fp:fpList){
			content.add(fp.getContent());
		}
	}
	
	public ArrayList<String> getContent(){
		return content;
	}
	
}
