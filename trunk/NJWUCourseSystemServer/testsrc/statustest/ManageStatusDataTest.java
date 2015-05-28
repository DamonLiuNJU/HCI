package statustest;

import java.rmi.RemoteException;

import po.statuspo.ManageStatusPO;

import junit.framework.TestCase;
import data.statusdata.ManageStatusDataServiceMySqlImpl;

public class ManageStatusDataTest extends TestCase{
	ManageStatusDataServiceMySqlImpl data;
	ManageStatusPO mp;
	
	protected void setUp(){
		try {
			data=new ManageStatusDataServiceMySqlImpl();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		mp=new ManageStatusPO("audit","6-10","6-20");
	}
	
	public void testInsert(){
		data.insert(mp);
		ManageStatusPO mp1=new ManageStatusPO();
		try {
			mp1=data.find("audit");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		assertEquals(mp1.toString(),mp.toString());
	}
	
	public void testUpdate(){
		mp.setOnTime("6-11");
		data.update(mp);
		ManageStatusPO mp1=new ManageStatusPO();
		try {
			mp1=data.find("audit");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		assertEquals(mp1.getOnTime(),mp.getOnTime());
	}
	
	public void testFinds(){		
	}
	
	public void testDelete(){
		data.delete("audit");
		ManageStatusPO mp1=new ManageStatusPO();
		try {
			mp1=data.find("audit");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		assertTrue(mp1==null);
	}
	
	public void testFind(){		
	}
}
