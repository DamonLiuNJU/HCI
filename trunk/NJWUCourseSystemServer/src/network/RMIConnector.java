package network;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import dataservice.DataService;
import factory.DataFactory;

public class RMIConnector {
	
	ArrayList<String> typeArray;
	
	public static void main(String[] args){
		RMIConnector rmiC = new RMIConnector();
		rmiC.connectStart();
	}
	
	public void connectStart(){
		DataFactory dataFactory = new DataFactory();
		
		try{
			Registry registry = LocateRegistry.createRegistry(8885);
			this.rmiInit();
			
			for(int i=0 ; i<typeArray.size(); i++){
				DataService data = dataFactory.createData(typeArray.get(i));
				registry.rebind(typeArray.get(i), data);
				System.out.println(">>>>>INFO:" +typeArray.get(i)+ "远程对象绑定成功！");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void rmiInit(){
		typeArray = new ArrayList<String>();
		
		typeArray.add("Teacher");
		typeArray.add("Student");
		typeArray.add("Plan");
		typeArray.add("Course");
		typeArray.add("Admin");
//		typeArray.add("Credit");
		typeArray.add("Apply");
		typeArray.add("ManageStatus");
		typeArray.add("Frame");
		typeArray.add("CourseStatus");
		typeArray.add("Manager");
		typeArray.add("Message");
		typeArray.add("MajorTransfer");
		typeArray.add("SelectCourseRecord");
		typeArray.add("StudentRegistry");
		typeArray.add("TempSelection");
		typeArray.add("FrameRemark");
		typeArray.add("CreditRestrict");
		
		typeArray.add("TxtDataFactory");
	}
}
