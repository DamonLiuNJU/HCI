package rmiconnector;

import dataservice.DataService;

public class RemoteDataFactory{
	private Connector connector ;
	
	public RemoteDataFactory(){
		connector = new Connector();
	}
	
	public DataService getData(String type){
		DataService myDS = connector.connect(type);
		
		return myDS;
	}
}
