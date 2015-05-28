package data.framedata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.framepo.CreditRestrictPO;
import data.helper.FileIOHelper;
import data.helper.Hint;
import dataservice.framedataservice.CreditRestrictDataService;

@SuppressWarnings("serial")
public class CreditRestrictDataTxtImpl extends UnicastRemoteObject implements CreditRestrictDataService{
	String filename="txt\\creditrestrict.txt";
	
	FileIOHelper io;
	Hint hint;
	public CreditRestrictDataTxtImpl() throws RemoteException {
		io=new FileIOHelper(filename);
		hint=new Hint(filename);
	}

	@Override
	public void insert(CreditRestrictPO cp) {
		io.appendToFile(cp.toString());
		hint.hintInsert();
	}

	@Override
	public void update(ArrayList<CreditRestrictPO> cpList) {
		int len=cpList.size();
		if(len!=0){
			io.writeToFile(cpList.get(0).toString());
			if(len>1){
				for(int i=1;i<cpList.size();i++){
					io.appendToFile(cpList.get(i).toString());
				}
			}
		}else{
			io.clear();
		}
		hint.hintUpdate();
	}
			
	@Override
	public ArrayList<CreditRestrictPO> finds() {
		ArrayList<CreditRestrictPO> list=new ArrayList<CreditRestrictPO>();
		ArrayList<String> reply=io.readFromFile();
		for(int i=0;i<reply.size();i++){
			String[] sp=reply.get(i).split(" ");
			CreditRestrictPO cp=new CreditRestrictPO(sp[0],Integer.valueOf(sp[1]),Integer.valueOf(sp[2]));
			list.add(cp);
		}
		hint.hintFind();
		return list;
	}

	@Override
	public void delete(String module) {
		//not implement
	}

}
