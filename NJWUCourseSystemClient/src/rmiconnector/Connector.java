package rmiconnector;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import businesslogic.utilitybl.ReplyMessage;
import dataservice.DataService;


public class Connector implements ReplyMessage{
	
	public DataService connect(String type){
		
		try {
			Registry registry = LocateRegistry.getRegistry("127.0.0.1" , 8885);
			
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
