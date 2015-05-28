package data.framedata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import data.helper.FileIOHelper;
import data.helper.Hint;
import dataservice.framedataservice.FrameRemarkDataService;

@SuppressWarnings("serial")
public class FrameRemarkDataServiceTxtImpl extends UnicastRemoteObject implements FrameRemarkDataService{
	String filename="txt\\remark.txt";
	
	FileIOHelper io;
	Hint hint;
	public FrameRemarkDataServiceTxtImpl() throws RemoteException {
		io=new FileIOHelper(filename);
		hint=new Hint(filename);
	}
	@Override
	public void update(String remark) {
		io.writeToFile(remark);
		hint.hintUpdate();
	}
	@Override
	public String finds() {
		ArrayList<String> list=io.readFromFile();
		StringBuilder sb=new StringBuilder();
		for(String s:list){
			sb.append(s+"\r\n");
		}
		hint.hintFind();
		return sb.toString();
	}

}
