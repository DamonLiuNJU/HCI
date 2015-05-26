package businesslogic.managerbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.managerpo.MessagePO;
import rmiconnector.RemoteDataFactory;
import vo.managervo.SentMessageVO;
import dataservice.managerdataservice.MessageDataService;

public class Message {
	String fromID;
	String toID;
	String content;  
   
	MessageDataService data=(MessageDataService) new RemoteDataFactory().getData("Message");
	
	public Message(String fi,String ti,String c){
		fromID=fi;
		toID=ti;
		content=c;
	}
	public Message(){
		
	}
	
	public void add(){
		try {
			data.insert(new MessagePO(fromID, toID, content));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
   
	public ArrayList<SentMessageVO> getMessageByFrom(String from_id){
		ArrayList<MessagePO> mpList;
		ArrayList<SentMessageVO> smvList=new ArrayList<SentMessageVO>();
		try {
			mpList = data.findByFrom(from_id);
			for(MessagePO mp:mpList){
				Manager m=new Manager(mp.getToID());
				smvList.add(new SentMessageVO(m.getName(),mp.getContent()));
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}		
		return smvList;
	}
}
