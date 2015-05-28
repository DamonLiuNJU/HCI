package selectcoursetest;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.junit.Test;

import po.courseselectionpo.TempSelectionPO;

import rmiconnector.RemoteDataFactory;

import dataservice.courseselectiondataservice.TempSelectionDataService;

import businesslogic.courseselectionbl.SelectStudentAlgorithm;

public class SelectCourseAlgrithmTest {

	@Test
	public void test() {
//		fail("Not yet implemented");
		String module = "选修课";
		this.setup();
		SelectStudentAlgorithm test = new SelectStudentAlgorithm(module);
		
		boolean result = test.processFacultyCourse("001");
		assertTrue(result);
	}
	
	private void setup(){
		TempSelectionDataService temp = (TempSelectionDataService) new RemoteDataFactory()
				.getData("TempSelection");
		ArrayList<TempSelectionPO> tempArray = new ArrayList<TempSelectionPO>();
		TempSelectionPO tp1 = new TempSelectionPO("12001002", "c100102", "0", "大一上");
		TempSelectionPO tp2 = new TempSelectionPO("12001002", "c100101", "0", "大一上");
		TempSelectionPO tp3 = new TempSelectionPO("12001003", "c100102", "0", "大一上");
		TempSelectionPO tp4 = new TempSelectionPO("12001003", "c100101", "0", "大一上");
		TempSelectionPO tp5 = new TempSelectionPO("12001002", "c100103", "0", "大一上");
		
		tempArray.add(tp1);
		tempArray.add(tp2);
		tempArray.add(tp3);
		tempArray.add(tp4);
		tempArray.add(tp5);
		
		for(int i=0 ; i<tempArray.size(); i++){
			try {
				temp.insert(tempArray.get(i));
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
