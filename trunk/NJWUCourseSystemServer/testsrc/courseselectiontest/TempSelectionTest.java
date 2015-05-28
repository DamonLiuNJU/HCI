package courseselectiontest;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.courseselectionpo.TempSelectionPO;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import data.courseselectiondata.TempSelectionMySql;

public class TempSelectionTest extends TestCase{
	TempSelectionMySql data;
	TempSelectionPO tp1;
	TempSelectionPO tp2;
	TempSelectionPO tp3;
	
	public TempSelectionTest(String s){
		super(s);
	}
	
	protected void setUp(){
		try {
			data=new TempSelectionMySql();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		tp1=new TempSelectionPO("11002001","c00002","0","大二上");
		tp2=new TempSelectionPO("12001001","c00002","0","大一上");
		tp3=new TempSelectionPO("12001002","c30002","0","大一上");
	}
	
	public static Test suite() {   
        TestSuite suite = new TestSuite();  
        suite.addTest(new TempSelectionTest("testInsert"));
        suite.addTest(new TempSelectionTest("testFinds"));
        suite.addTest(new TempSelectionTest("testFind"));
        suite.addTest(new TempSelectionTest("testFindStudentList"));
        suite.addTest(new TempSelectionTest("testDelete"));
        suite.addTest(new TempSelectionTest("testDeletes"));
        return suite;  
    }  
	
	public void testInsert(){
		data.insert(tp1);
		data.insert(tp2);
		data.insert(tp3);
		ArrayList<TempSelectionPO> tpList = null;
		try {
			tpList = data.finds();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ArrayList<String> list=new ArrayList<String>();
		for(TempSelectionPO tp:tpList){
			list.add(tp.toString());
		}
		assertTrue(list.contains(tp1.toString()));
		assertTrue(list.contains(tp2.toString()));
		assertTrue(list.contains(tp3.toString()));
	}
	public void testDelete(){
		data.delete("11002001","c00002");
	}
	public void testDeletes(){
		data.deletes();
		ArrayList<TempSelectionPO> tpList=new ArrayList<TempSelectionPO>();
		try {
			tpList=data.finds();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		assertTrue(tpList.size()==0);
	}
	public void testFind() {
		ArrayList<TempSelectionPO> tpList = null;
		try {
			tpList = data.find("12001001");
		} catch (RemoteException e){
			e.printStackTrace();
		}
		ArrayList<String> list=new ArrayList<String>();
		for(TempSelectionPO tp:tpList){
			list.add(tp.toString());
		}
		assertTrue(list.contains(tp2.toString()));
	}
	
	public void testFindStudentList(){
		ArrayList<TempSelectionPO> tpList = null;
		try {
			tpList = data.findStudentList("c00002");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ArrayList<String> list=new ArrayList<String>();
		for(TempSelectionPO tp:tpList){
			list.add(tp.toString());
		}
		assertTrue(list.contains(tp2.toString()));
	}
	
	public void testFinds(){
		ArrayList<TempSelectionPO> tpList = null;
		try {
			tpList = data.finds();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ArrayList<String> list=new ArrayList<String>();
		for(TempSelectionPO tp:tpList){
			
			list.add(tp.toString());
		}
		assertTrue(list.contains(tp1.toString()));
		assertTrue(list.contains(tp2.toString()));
		assertTrue(list.contains(tp3.toString()));
	}
}
