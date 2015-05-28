package coursetest;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.junit.Test;

import po.coursepo.ApplyPO;
import static org.junit.Assert.*;
import data.coursedata.ApplyData;

public class ApplyTest {
	
	@Test
	public void test() throws RemoteException{
		ApplyData ad=new ApplyData();
		ArrayList<ApplyPO> apList=ad.finds();
		assertTrue(apList.size()==1);
		
		String  delete=ad.delete("");
		assertTrue(delete==null);
		
	}
		
	}


