package rmiconnector;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmiconnector.io.RMIIOHelper;

import businesslogic.utilitybl.ReplyMessage;
import dataservice.DataService;

/*
 * 该类是与服务器连接的接口
 * type与服务器相应的RMI的dataservice对应
 * 返回的都是DataService，具体的对象需要在使用的时候强制转换
 */
public class Connector implements ReplyMessage{
	
	public DataService connect(String type){
		
		try {
			RMIIOHelper helper = new RMIIOHelper();
			String ip = helper.getIP();
			int port = helper.getPort();
			Registry registry = LocateRegistry.getRegistry(ip, port);
			
			DataService myData = (DataService)registry.lookup(type);
			return  myData ;
			
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(CONNECTION_REFUSED);
			return null;
		}
		
		
	}

}
