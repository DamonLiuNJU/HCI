package statustest;

import java.rmi.RemoteException;

import po.statuspo.CourseStatusPO;

import junit.framework.TestCase;
import data.statusdata.CourseStatusDataServiceMySqlImpl;

public class CourseStatusTest extends TestCase{
	CourseStatusDataServiceMySqlImpl data;
	String module;
	CourseStatusPO cp;
	
	protected void setUp(){
		try {
			data=new CourseStatusDataServiceMySqlImpl();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		module="必修课";
		cp=new CourseStatusPO(module, "apply", "6-20", "6-27");
	}
	
	public void testInsert(){
		data.insert(cp);
		CourseStatusPO cp1=new CourseStatusPO();
		try {
			cp1=data.find(module, "apply");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		assertEquals(cp1.toString(),cp.toString());
	}
	
	public void testUpdate(){
		cp.setOffTime("7-01");
		data.update(cp);
		CourseStatusPO cp1=new CourseStatusPO();
		try {
			cp1=data.find(module, "apply");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		assertEquals(cp.getOffTime(),cp1.getOffTime());
	}
	
	public void testDelete(){
		data.delete(module, "apply");
		CourseStatusPO cp1=new CourseStatusPO();
		try {
			cp1=data.find(module, "apply");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		assertTrue(cp1==null);
	}
	
}
