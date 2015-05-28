package data.studentdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.Statement;

import po.studentpo.StudentRegistryPO;
import dataservice.studentdataservice.StudentRegistryDataService;

public class StudentRegistryMySql extends UnicastRemoteObject implements StudentRegistryDataService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6100083786824251140L;

	public StudentRegistryMySql() throws RemoteException {
		super();
		// TODO 自动生成的构造函数存根
	}

	/**
	 * @param args
	 */
	static Connection conn;
	static Statement st;
	static String databasename = "coursesystem";
	static String tablename = "studentregistry";
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void insert(StudentRegistryPO po) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void delete(StudentRegistryPO po) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void update(StudentRegistryPO po) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public StudentRegistryPO find(StudentRegistryPO po) {
		// TODO 自动生成的方法存根
		return null;
	}


}
