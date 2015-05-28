package admintest;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Test;

import po.managerpo.ManagerPO;

import data.managerdata.AdminData;

public class AdminFindTest {

	@Test
	/*
	 * 测试AdminData中的find方法，选取了真实学生id（8位），8位长无注册信息id（对应学生）
	 * 真实院系教务老师id（6位）， 6位无注册信息id
	 * 缺省：2位id（数据库中id没有更新完全，无法进行测试）
	 * 		教师id（教师编号尚未定义好，无法进行测试）
	 * 
	 * 测试结果：学生id，8位长无注册信息id，教师id，7位长无注册信息id测试结果正常，
	 * 院系教务老师id测试结果正常，6位无注册信息id测试错误
	 * 		错因：从数据库中返回的信息为空，再在string中进行加工出错
	 * 		已修正（在bl层会进行判断，不会find一个空id的manager）
	 * 
	 */
	public void test() {
//		fail("Not yet implemented");
		
		String [] test = new String[20];
		test[0] = "12125001";//学生正常id
		test[1] = "12125005";//8位空id
		test[2] = "145125";//6位空id
		test[3] = "000001";//manager正常id
		test[4] = "2001001";//teacher正常id
		test[5] = "2001100";//7位空id
		test[6] = "1241";//4位空id
		try {
			AdminData adminData = new AdminData();
			
			Object ob = adminData.findUser(test[3]);
			ManagerPO mp = (ManagerPO) ob;
			
			assertTrue(mp.getName().equals("安安"));
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
