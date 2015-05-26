package data.managerdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.managerpo.MessagePO;

import data.helper.DataBaseHelper;
import dataservice.managerdataservice.MessageDataService;

@SuppressWarnings("serial")
public class MessageData extends UnicastRemoteObject implements MessageDataService{
	String tableName="message";
	
	DataBaseHelper db;
	public MessageData() throws RemoteException {
		db=new DataBaseHelper();		
	}

	@Override
	public void insert(MessagePO mp) throws RemoteException {
		String info = "INSERT INTO `"+tableName+"` VALUES ('" +mp.getFromID()+"','"
				+mp.getToID()+"','"+mp.getContent()+"')"; 
		db.insert(info);
		System.out.println("成功在"+tableName+"表中插入");
	}

	@Override
	public ArrayList<MessagePO> finds(String to_id) throws RemoteException {
		ArrayList<MessagePO> list=new ArrayList<MessagePO>();
		String info="select * from  `"+tableName+"`"+" where `to_id`='"+to_id+"'";
		String[] names={"from_id","to_id","content"};
		ArrayList<String> reply=db.query(info,names);
		for(String s:reply){
			if(!s.equals("")){
				String[] sp=s.split(",");
				list.add(new MessagePO(sp[0],sp[1],sp[2]));
			}
		}		
		return list;
	}

	@Override
	public ArrayList<MessagePO> findByFrom(String from_id)throws RemoteException {
		ArrayList<MessagePO> list=new ArrayList<MessagePO>();
		String info="select * from  `"+tableName+"`"+" where `from_id`='"+from_id+"'";
		String[] names={"from_id","to_id","content"};
		ArrayList<String> reply=db.query(info,names);
		for(String s:reply){
			if(!s.equals("")){
				String[] sp=s.split(",");
				list.add(new MessagePO(sp[0],sp[1],sp[2]));
			}
		}		
		return list;
	}
	
	@Override
	public void delete(String from_id,String to_id){
		String info="delete from  `"+tableName+"`"+" where `from_id` = '"+from_id
				+"' and `to_id` = '"+to_id+"'";;
		db.delete(info);
		System.out.println("成功从"+tableName+"表中删除");
	}
}
