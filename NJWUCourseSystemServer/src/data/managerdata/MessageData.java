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
	String[] names={"index","from_id","to_id","content"};
	
	DataBaseHelper db;
	public MessageData() throws RemoteException {
		db=new DataBaseHelper();		
	}

	@Override
	public void insert(MessagePO mp) throws RemoteException {
		String info = "INSERT INTO `"+tableName+"` VALUES ('" +getNextIndex()+"','"
				+mp.getFromID()+"','"+mp.getToID()+"','"+mp.getContent()+"')"; 
		db.insert(info);
		System.out.println("成功在"+tableName+"表中插入");
	}
	
	int getNextIndex(){
		String info="select * from  `"+tableName+"`";
		ArrayList<String> reply=db.query(info,names);
		return reply.size()+1;
	}

	@Override
	public ArrayList<MessagePO> finds(String to_id) throws RemoteException {
		ArrayList<MessagePO> list=new ArrayList<MessagePO>();
		String info="select * from  `"+tableName+"`"+" where `to_id`='"
				+to_id+"' ORDER BY `index` ASC ";
		ArrayList<String> reply=db.query(info,names);
		for(String s:reply){
			if(!s.equals("")){
				String[] sp=s.split(",");
				list.add(new MessagePO(sp[1],sp[2],sp[3]));
			}
		}		
		return list;
	}

	@Override
	public ArrayList<MessagePO> findByFrom(String from_id)throws RemoteException {
		ArrayList<MessagePO> list=new ArrayList<MessagePO>();
		String info="select * from  `"+tableName+"`"+" where `from_id`='"
				+from_id+"' ORDER BY `index` ASC ";
		ArrayList<String> reply=db.query(info,names);
		for(String s:reply){
			if(!s.equals("")){
				String[] sp=s.split(",");
				list.add(new MessagePO(sp[1],sp[2],sp[3]));
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
