package data.coursedata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


import po.coursepo.CoursePO;

import data.helper.DataBaseHelper;
import dataservice.coursedataservice.CourseDataService;




@SuppressWarnings("serial")

public class CourseData extends UnicastRemoteObject implements
		CourseDataService {
	static String tableName = "course";

	DataBaseHelper db;

	public CourseData() throws RemoteException {
		db = new DataBaseHelper();
	}

	@Override
	public ArrayList<CoursePO> finds() {
		ArrayList<CoursePO> list = new ArrayList<CoursePO>();
		String info = "select * from `" + tableName + "`";
		String[] names = { "id", "name", "teacherID", "campus", "grade",
				"place", "time", "period", "require", "facultyID", "credit",
				"module", "limit", "specificInfo" };
		ArrayList<String> reply = db.query(info, names);
		for (String s : reply) {
			String[] sp = s.split(",");
			for (int i = 0; i < sp.length; i++) {
				if (sp[i].equals("%&%"))
					sp[i] = "";
			}
			list.add(new CoursePO(sp[0], sp[1], sp[2], sp[3], sp[4], sp[5],
					sp[6], sp[7], sp[8], sp[9], sp[10], sp[11], sp[12], sp[13]));
		}
		return list;
	}

	@Override
	public void delete(String index) throws RemoteException {
		String info = "delete from  `" + tableName + "`" + " where `id` = '"
				+ index + "'";
		db.delete(info);
		System.out.println("delete" + tableName + "successfully");

	}

	@Override
	public void insert(CoursePO cp) throws RemoteException {
		String info = "INSERT INTO `" + tableName + "`("
				+ cp.getAttributeNames() + ") VALUES " + cp.getValues();
		db.insert(info);
		System.out.println("insert  " + tableName + " succefully!");
	}

	@Override
	public void update(CoursePO cp) throws RemoteException {
		delete(cp.getID());
		insert(cp);
	}

}
