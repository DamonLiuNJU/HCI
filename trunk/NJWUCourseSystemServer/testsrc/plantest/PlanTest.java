package plantest;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.junit.Test;

import data.plandata.PlanData;
import static org.junit.Assert.*;
import po.planpo.FacultyPO;

public class PlanTest {
@Test
	public void test() throws RemoteException{
	
		PlanData pd=new PlanData();
		ArrayList<FacultyPO> fpList=pd.finds();
		assertTrue(fpList.size()==4);
		
		FacultyPO  fp=pd.find("001");
		assertTrue(fp.getFacultyName().equals("软件学院"));	
		
}
		

	
	
}
