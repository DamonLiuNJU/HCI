package coursetest;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.junit.Test;

import po.coursepo.CoursePO;

import data.coursedata.CourseData;


import static org.junit.Assert.*;

public class CourseTest {

		@Test
		public void test() throws RemoteException{
			CourseData cd=new CourseData();	
			
			ArrayList<CoursePO> cpList=cd.finds();
			
			assertTrue(cpList.size()==5);
		
			
			
		}	
}
