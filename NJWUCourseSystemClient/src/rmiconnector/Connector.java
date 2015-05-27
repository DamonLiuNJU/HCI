package rmiconnector;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmiconnector.io.RMIIOHelper;

import businesslogic.utilitybl.ReplyMessage;
import dataservice.DataService;


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
