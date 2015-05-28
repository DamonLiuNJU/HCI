package managertest;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.managerpo.MessagePO;

import data.managerdata.MessageData;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class MessageDataTest extends TestCase{
	MessageData data;
	String from_id;
	MessagePO mpOne;
	MessagePO mpTwo;
	
	public  MessageDataTest(String s){
		super(s);
	}
	
	protected void setUp(){
		try {
			data=new MessageData();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		from_id="11";
		mpOne=new MessagePO(from_id,"100300","testMessage1");
		mpTwo=new MessagePO(from_id,"100400","testMessage2");
	}
	
	public static Test suite() {   
        TestSuite suite = new TestSuite();  
        suite.addTest(new MessageDataTest("testInsert"));
        suite.addTest(new MessageDataTest("testFinds"));
        suite.addTest(new MessageDataTest("testFindByFrom"));
        suite.addTest(new MessageDataTest("testDelete"));
        return suite;  
    }  
	
	public void testInsert(){
		try {
			data.insert(mpOne);
			data.insert(mpTwo);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ArrayList<MessagePO> mpList=new ArrayList<MessagePO>();
		try {
			mpList=data.findByFrom(from_id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ArrayList<String> sList=new ArrayList<String>();
		for(MessagePO mp:mpList){
			sList.add(mp.toString());
		}
		assertTrue(sList.contains(mpOne.toString()));
		assertTrue(sList.contains(mpTwo.toString()));
	}
	
	public void testFinds(){
		ArrayList<MessagePO> mpList=new ArrayList<MessagePO>();
		try {
			mpList=data.finds(mpOne.getToID());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ArrayList<String> sList=new ArrayList<String>();
		for(MessagePO mp:mpList){
			sList.add(mp.toString());
		}
		assertTrue(sList.contains(mpOne.toString()));
	}
	
	public void testFindByFrom(){
		ArrayList<MessagePO> mpList=new ArrayList<MessagePO>();
		try {
			mpList=data.findByFrom(from_id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ArrayList<String> sList=new ArrayList<String>();
		for(MessagePO mp:mpList){
			sList.add(mp.toString());
		}
		assertTrue(sList.contains(mpOne.toString()));
		assertTrue(sList.contains(mpTwo.toString()));
	}
	
	public void testDelete(){
		data.delete(from_id, mpOne.getToID());
		data.delete(from_id, mpTwo.getToID());
		ArrayList<MessagePO> mpList=new ArrayList<MessagePO>();
		try {
			mpList=data.findByFrom(from_id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ArrayList<String> sList=new ArrayList<String>();
		for(MessagePO mp:mpList){
			sList.add(mp.toString());
		}
		assertFalse(mpList.contains(mpOne.toString()));
		assertFalse(mpList.contains(mpTwo.toString()));
	}
}
