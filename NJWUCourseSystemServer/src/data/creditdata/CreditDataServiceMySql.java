package data.creditdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import po.creditpo.CreditPO;
import dataservice.creditdataservice.CreditDataService;

////这个类已经被完全测试运行正确。
public class CreditDataServiceMySql extends UnicastRemoteObject implements
		CreditDataService {
/**
	 * 
	 */
	private static final long serialVersionUID = 3894252563441426978L;

	//
//	static Connection conn;
//	static Statement st;
//	static String databasename = "coursesystem";
//	static String tablename = "scoreinfo";
//	
//	private static final long serialVersionUID = 7904207139506215056L;
//
//	private String getConditionSentence(ArrayList<String> keyhead,
//			ArrayList<String> line) {
//		String result = "";
//		
//		for (int i = 0; i < keyhead.size(); i++) {
//			result += keyhead.get(i) + "='" + line.get(i) + "'";
//			if (i != keyhead.size() - 1) {
//				result += " and ";
//			}
//		}
//
//		return result;
//	}
//
//	private String getNewValueSentence(ArrayList<String> keyhead,
//			ArrayList<String> line) {
//
//		String result = "";
//
//		for (int i = 0; i < keyhead.size(); i++) {
//			result += keyhead.get(i) + "='" + line.get(i) + "'";
//			if (i != keyhead.size() - 1) {
//				result += " , ";
//			}
//		}
//
//		return result;
//	}
//
//	/**
//	 * @param args
//	 */
//
//	private static Connection getConnection() {
//		String driver = "com.mysql.jdbc.Driver";
//		String username = "root";
//		String password = "root";
//		Connection con = null; // 创建用于连接数据库的Connection对象
//		try {
//			Class.forName(driver);// 加载Mysql数据驱动
//			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"
//					+ databasename, username, password);// 创建数据连接
//			System.out.println("Connect to database :<" + databasename
//					+ ">Success! ");
//		} catch (Exception e) {
//			System.out.println("数据库连接失败" + e.getMessage());
//		}
//
//		return con; // 返回所建立的数据库连接
//	}
//
	public CreditDataServiceMySql() throws RemoteException {
		super();
	}
//
//	public CreditPO findString(CreditPO po) {
//
////		CreditPO resultpo = null;
////		String tablename = po.getTableName();
////		ArrayList<String> keyheads = po.getKeyHeadList();
////		ArrayList<ArrayList<String>> keycontents = po.getKeyContentList();
////		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
////		ArrayList<String> resultheads = po.getResultHeadList();
////		conn = getConnection();
////
////		for (ArrayList<String> line : keycontents) {
////			String findcondition = "";
////			for (int i = 0; i < keyheads.size(); i++) {
////				String head = keyheads.get(i);
////				String value = line.get(i);
////				findcondition = findcondition + head + "='" + value + "'";
////
////				if (i != keyheads.size() - 1) {
////					findcondition += " and ";
////				}
////			}// get the sql sentence
////			try {
////				String sql = "select " + "*" + " from " + tablename
////						+ " where ( " + findcondition + " )";
////				st = (Statement) conn.createStatement();
////				ResultSet rs = st.executeQuery(sql);
////				
////				while (rs.next()) {
////					ArrayList<String> newline = new ArrayList<String>();
////					for (String tempcolumn : resultheads) {
////						String tempresult = rs.getString(tempcolumn);
////						// String tempresultstring = tempresult + "";
////						newline.add(tempresult);
////					}
////					result.add(newline);
////				}
////
////				resultpo = new CreditPO();
////				resultpo.setResultValueList(result);
////				conn.close();
////				System.out.println("find success");
////
////			} catch (SQLException e) {
////				System.out.println("查询数据失败" +e.getMessage());
////			}
////
////		}
////		return resultpo;
//	}
//
//	// completed;
//	public CreditPO findInt(CreditPO po) {
//
//		CreditPO resultpo = null;
//		String tablename = po.getTableName();
//		ArrayList<String> keyheads = po.getKeyHeadList();
//		ArrayList<ArrayList<String>> keycontents = po.getKeyContentList();
//		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
//		ArrayList<String> resultheads = po.getResultHeadList();
//		conn = getConnection();
//
//		for (ArrayList<String> line : keycontents) {
//			String findcondition = "";
//			for (int i = 0; i < keyheads.size(); i++) {
//				String head = keyheads.get(i);
//				String value = line.get(i);
//				findcondition = findcondition + head + "='" + value + "'";
//
//				if (i != keyheads.size() - 1) {
//					findcondition += " and ";
//				}
//			} 
//			try {
//				String sql = "select " + "*" + " from " + tablename
//						+ " where ( " + findcondition + " )";
//				st = (Statement) conn.createStatement();
//				ResultSet rs = st.executeQuery(sql);
//
//				while (rs.next()) {
//					ArrayList<String> newline = new ArrayList<String>();
//					for (String tempcolumn : resultheads) {
//						int tempresult = rs.getInt(tempcolumn);
//						String tempresultstring = tempresult + "";
//						newline.add(tempresultstring);
//					}
//					result.add(newline);
//				}
//
//				resultpo = new CreditPO();
//				resultpo.setResultValueList(result);
//				conn.close();
//
//			} catch (SQLException e) {
//				System.out.println("查询数据失败");
//			}
//
//		}
//		return resultpo;
//	}
//
// 
//	public void insert(CreditPO cp) {
//		// 可以随意插入一个记录的某几个数据项，但要设定默认值。
//		conn = getConnection();
//		ArrayList<String> keyheads = cp.getKeyHeadList();
//		ArrayList<ArrayList<String>> keycontents = cp.getKeyContentList();
//		String tablename = cp.getTableName();
//
//		for (ArrayList<String> line : keycontents) {
//			String insertinfo = "";
//			for (int i = 0; i < keyheads.size(); i++) {
//				if (i == keyheads.size() - 1) {
//					insertinfo = insertinfo + "'" + line.get(i) + "'";
//				} else {
//					insertinfo = insertinfo + "'" + line.get(i) + "',";
//				}
//			}
//
//			try {
//				String heads = "";
//				for (int i = 0; i < keyheads.size(); i++) {
//					if (i != keyheads.size() - 1) {
//						heads += "" + keyheads.get(i) + "" + ",";
//					} else {
//						heads += "" + keyheads.get(i) + "";
//					}
//				}
//				String sql = "INSERT INTO " + tablename + " ( " + heads + " )"
//						+ " VALUES(" + insertinfo + ")";
//				st = (Statement) conn.createStatement();
//				st.executeUpdate(sql);
//				System.out.println("insert success");
//				conn.close();
//
//			} catch (SQLException e) {
//				System.out.println("Insert Failed" + e.getMessage());
//			}
//		}
//	}
//
//	@Override
//	public void delete(CreditPO cp) {
//		// Success ， Test working correctly;
//		conn = getConnection();
//		String tablename = cp.getTableName();
//		ArrayList<String> keyheads = cp.getKeyHeadList();
//		ArrayList<ArrayList<String>> keycontent = cp.getKeyContentList();
//
//		for (ArrayList<String> line : keycontent) {
//
//			String condition = "";
//			for (int i = 0; i < keyheads.size(); i++) {
//				String head = keyheads.get(i);
//				String value = line.get(i);
//				condition += head + " = '" + value + "'";
//				if (i != keyheads.size() - 1) {
//					condition += " and ";
//				}
//			}
//			try {
//				String sql = "delete from " + tablename + " where ( "
//						+ condition + " )";
//				st = (Statement) conn.createStatement();
//				int count = st.executeUpdate(sql);
//				System.out.println("Delete  : " + count + " Records Success");
//				conn.close();
//			} catch (SQLException e) {
//				System.out.println("Failed Delete " + e.getMessage());
//			}
//		}
//
//	}
//
//	public void update(CreditPO cp) {
//
//		String tablename = cp.getTableName();
//		conn = getConnection(); // 同样先要获取连接，即连接到数据库
//		ArrayList<String> keyhead = cp.getKeyHeadList();
//		ArrayList<ArrayList<String>> keycontent = cp.getKeyContentList();
//		ArrayList<String> resulthead = cp.getResultHeadList();
//		ArrayList<ArrayList<String>> resultcontent = cp.getResultContentList();
//
//		for (int i = 0; i < keycontent.size(); i++) {
//
//			ArrayList<String> keyline = keycontent.get(i);
//			ArrayList<String> resultline = resultcontent.get(i);
//
//			String newvalue = ""; // update values
//			String condition = ""; // judge condition
//
//			condition = this.getConditionSentence(keyhead, keyline);
//			newvalue = this.getNewValueSentence(resulthead, resultline);
//
//			try {
//				String sql = "update " + tablename + " set  " + newvalue
//						+ "  where ( " + condition + " )";
//				st = (Statement) conn.createStatement();
//				int count = st.executeUpdate(sql);
//				System.out.println("stu表中更新 " + count + " 条数据");
//				conn.close();
//			} catch (SQLException e) {
//				System.out.println("更新数据失败" + e.getMessage());
//			}
//		}
//
//	}
//
//	@Override
//	public CreditPO findIntOne(CreditPO cp) throws RemoteException {
//		// TODO 自动生成的方法存根
//		return null;
//	}
//
//	@Override
//	public CreditPO findStringOne(CreditPO cp) throws RemoteException {
//		// TODO 自动生成的方法存根
//		return null;
//	}
//
//	@Override
//	public CreditPO insertOne(CreditPO cp) throws RemoteException {
//		// TODO 自动生成的方法存根
//		return null;
//	}
//
//	@Override
//	public CreditPO deleteOne(CreditPO cp) throws RemoteException {
//		// TODO 自动生成的方法存根
//		return null;
//	}
//
//	@Override
//	public CreditPO updateOne(CreditPO cp) throws RemoteException {
//		// TODO 自动生成的方法存根
//		return null;
//	}
//
//	// public static void main(String args[]) {
//	// try {
//	//
//	// CreditDataServiceMySql cdsm = new CreditDataServiceMySql();
//	// CreditPO cp = new CreditPO();
//	//
//	// String tablename = "selectcourserecord";
//	// cp.setTableName(tablename);
//	//
//	// ArrayList<String> heads = new ArrayList<String>();
//	// heads.add("student_id");
//	// heads.add("course_id");
//	// // heads.add("score");
//	// // heads.add("isgetcredit");
//	// cp.setKeyHeadList(heads);
//	// // ArrayList<String> resultheads = new ArrayList<String>();
//	// // resultheads.add("score");
//	// // cp.setResultHeadList(resultheads);
//	// ArrayList<String> line1 = new ArrayList<String>();
//	// line1.add("12125001");
//	// line1.add("0001");
//	// // line1.add("99");
//	// // line1.add("0");
//	// ArrayList<ArrayList<String>> keycontent = new
//	// ArrayList<ArrayList<String>>();
//	// keycontent.add(line1);
//	// cp.setKeyValueList(keycontent);
//	//
//	// ArrayList<String> resulthead = new ArrayList();
//	// resulthead .add("score");
//	//
//	// ArrayList<ArrayList<String>> resultcontent =new ArrayList();
//	//
//	// ArrayList<String> line =new ArrayList();
//	// line.add("10086");
//	// resultcontent .add(line);
//	// cp.setResultHeadList(resulthead);
//	// cp.setResultValueList(resultcontent);
//	// // cp = cdsm.findString(cp);
//	//
//	// cdsm.update(cp);
//	//
//	// // ArrayList<ArrayList<String>> result = cp.getResultContentList();
//	//
//	// // for(ArrayList<String> line : result ){
//	// // for(String a : line ){
//	// // System.out.print(a + " ");
//	// // }
//	// //
//	// // System.out.println(" ");
//	// // }
//	//
//	// } catch (RemoteException e) {
//	// // TODO 自动生成的 catch 块
//	// e.printStackTrace();
//	// }
//	// }

	@Override
	public CreditPO findString(CreditPO po) throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public CreditPO findInt(CreditPO cpindex) throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public void insert(CreditPO cp) throws RemoteException {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void delete(CreditPO cp) throws RemoteException {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void update(CreditPO cp) throws RemoteException {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public CreditPO findIntOne(CreditPO cp) throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public CreditPO findStringOne(CreditPO cp) throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public CreditPO insertOne(CreditPO cp) throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public CreditPO deleteOne(CreditPO cp) throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public CreditPO updateOne(CreditPO cp) throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}
}
