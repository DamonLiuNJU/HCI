package frametest;

import java.rmi.RemoteException;

import junit.framework.TestCase;

import data.framedata.FrameRemarkDataServiceTxtImpl;

public class FrameRemarkDataTest extends TestCase{
	FrameRemarkDataServiceTxtImpl data;
	String remark;
	
	protected void setUp(){
		try {
			data=new FrameRemarkDataServiceTxtImpl();
		} catch (RemoteException e) {
			e.printStackTrace();
		}		
		remark="我是备注信息";
	}
	
	public void testFinds(){
		data.update(remark);
		String re=data.finds();
		assertEquals(re,remark+"\r\n");
	}
	
	public void testUpdate(){
		data.update(remark);
		String re=data.finds();
		assertEquals(remark+"\r\n",re);
	}
	
}
