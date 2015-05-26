package managertest;

import java.rmi.RemoteException;

import po.managerpo.ManagerPO;

import data.managerdata.ManagerData;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.framework.Test;  

public class ManagerDataTest extends TestCase{
	ManagerData data;
	String id;
	ManagerPO mpOne;
	ManagerPO mpTwo;
	ManagerPO mpThree;

	public ManagerDataTest(String s) {
		super(s);
	}

	public void setUp(){
		try {
			data=new ManagerData();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		id="12";
		mpOne=new ManagerPO(id,"dean2","疯兔子","-1","无");
		mpTwo=new ManagerPO(id,"路灯");
		mpThree=new ManagerPO(id,"waytohome","");
	}
	
	public static Test suite() {   
        TestSuite suite = new TestSuite();  
        suite.addTest(new ManagerDataTest("testInsert"));
        suite.addTest(new ManagerDataTest("testUpdate"));
        suite.addTest(new ManagerDataTest("testUpdatePw"));
        suite.addTest(new ManagerDataTest("testUpdateCi"));
        suite.addTest(new ManagerDataTest("testDelete"));
        return suite;  
    }  
	
	public void testInsert(){
		data.insert(mpOne);
		ManagerPO mp1=new ManagerPO();
		try {
			mp1=data.find(id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		assertEquals(mpOne.toString(),mp1.toString());
	}
	
	public void testUpdate(){
		String newName="疯兔子警察";
		mpOne.setName(newName);
		try {
			data.update(mpOne);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		ManagerPO mp1=new ManagerPO();
		try {
			mp1=data.find(id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		assertEquals(mp1.getName(),newName);
	}
	
	public void testUpdatePw(){
		try {
			data.updatePw(mpTwo);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ManagerPO mp1=new ManagerPO();
		try {
			mp1=data.find(id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		assertEquals(mpTwo.getPassword(),mp1.getPassword());
	}
	
	public void testUpdateCi(){
		try {
			data.updateCi(mpThree);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ManagerPO mp1=new ManagerPO();
		try {
			mp1=data.find(id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		assertEquals(mpThree.getContactInfo(),mp1.getContactInfo());
	}
	
	public void testDelete(){
		try {
			data.delete(id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ManagerPO mp1=new ManagerPO();
		try {
			mp1=data.find(id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		assertTrue(mp1==null);
	}
	
	public void testFind1(){		
	}
	
	public void testFind2(){		
	}
	
	public void testFind(){		
	}
}
