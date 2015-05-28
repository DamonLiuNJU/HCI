package frametest;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.framepo.FramePO;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import data.framedata.FrameDataServiceTxtImpl;

public class FrameDataTest extends TestCase{
	FrameDataServiceTxtImpl data;
	String item0;
	String item1;
	String item2;
	String item3;
	FramePO fp0;
	ArrayList<FramePO> fpoList;
	
	public FrameDataTest(String s){
		super(s);
	}
	
	protected void setUp(){
		try {
			data=new FrameDataServiceTxtImpl();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		item0="testFrame1:please verify course modules";
        item1="毕业所需总学分为152.其中各学期所占比重基本一致。必修课学分在30-50，选修课学分在20左右.";
		item2="详述专业分流方针.";
		item3="专业准入准出方案";
		fp0=new FramePO(0,item0);
		fpoList=new ArrayList<FramePO>();
		fpoList.add(new FramePO(1,item1));
		fpoList.add(new FramePO(2,item2));
		fpoList.add(new FramePO(3,item3));
	}
	
	public static Test suite(){
		TestSuite suite=new TestSuite();
		suite.addTest(new FrameDataTest("testInsert"));
		suite.addTest(new FrameDataTest("testFinds"));
		suite.addTest(new FrameDataTest("testDelete"));
		suite.addTest(new FrameDataTest("testUpdate"));		
		return suite;
	}
	
	public void testInsert(){
		try {
			data.insert(fp0);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ArrayList<FramePO> fpList=new ArrayList<FramePO>();
		try {
			fpList=data.finds();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		assertEquals(item0,fpList.get(fpList.size()-1).getContent());
	}
	
	public void testFinds(){
		ArrayList<FramePO> fpList=new ArrayList<FramePO>();
		try {
			fpList=data.finds();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ArrayList<String> sList=new ArrayList<String>();
		for(FramePO fpo:fpList){
			sList.add(fpo.getContent());
		}
		assertTrue(sList.contains(item0));
	}
	
	public void testUpdate(){
		try {
			data.update(fpoList);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ArrayList<FramePO> fpList=new ArrayList<FramePO>();
		try {
			fpList=data.finds();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ArrayList<String> sList=new ArrayList<String>();
		for(FramePO fpo:fpList){
			sList.add(fpo.getContent());
		}
		
		ArrayList<String> itemList=new ArrayList<String>();
		itemList.add(item1);
		itemList.add(item2);
		itemList.add(item3);
		assertTrue(sList.containsAll(itemList));
	}
	
	public void testDelete(){
		try {
			data.delete();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ArrayList<FramePO> fpList=new ArrayList<FramePO>();
		try {
			fpList=data.finds();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		assertTrue(fpList.size()==0);
	}
}
