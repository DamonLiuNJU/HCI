package network;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import factory.MyDataFactory;

public class MyRMIConnector {
	
	public void startConnection(){
		try {
			Registry registry = LocateRegistry.createRegistry(8885);
			
			MyDataFactory factory = new MyDataFactory();
			
			registry.rebind("Remote", factory);
			System.out.println(">>>>>INFO:" +"Remote"+ "远程对象绑定成功！");
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
