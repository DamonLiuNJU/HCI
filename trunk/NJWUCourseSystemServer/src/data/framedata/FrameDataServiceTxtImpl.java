package data.framedata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.framepo.FramePO;

import data.helper.FileIOHelper;
import data.helper.Hint;
import dataservice.framedataservice.FrameDataService;

@SuppressWarnings("serial")
public class FrameDataServiceTxtImpl extends UnicastRemoteObject implements FrameDataService{
	String filename="txt\\frame.txt";
	
	FileIOHelper io;
	Hint hint;
	public FrameDataServiceTxtImpl() throws RemoteException {
		io=new FileIOHelper(filename);
		hint=new Hint(filename);
	}

	@Override
	public void insert(FramePO fp) throws RemoteException {
		io.appendToFile(fp.getContent());
		hint.hintInsert();
	}

	@Override
	public void update(ArrayList<FramePO> fpList) throws RemoteException {
		int len=fpList.size();
		if(len!=0){
			io.writeToFile(fpList.get(0).getContent());
			if(len>1){
				for(int i=1;i<fpList.size();i++){
					io.appendToFile(fpList.get(i).getContent());
				}
			}
		}else{
			io.clear();
		}
		hint.hintUpdate();
	}

	@Override
	public ArrayList<FramePO> finds() throws RemoteException {
		ArrayList<FramePO> list=new ArrayList<FramePO>();
		ArrayList<String> reply=io.readFromFile();
		for(int i=0;i<reply.size();i++){
			list.add(new FramePO(i+1,reply.get(i)));
		}
		hint.hintFind();
		return list;
	}

	@Override
	public void delete() throws RemoteException {
		 io.clear();
		 hint.hintDelete();
	}
	
	
}
