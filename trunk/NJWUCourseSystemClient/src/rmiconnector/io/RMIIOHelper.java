package rmiconnector.io;

import java.io.*;

public class RMIIOHelper {
	
	static final String fileName = "./txt/RMI配置.txt";
	private static String ip;
	private static int port;
	
	public RMIIOHelper(){
		this.getInfomation();
	}
	
	private void getInfomation(){
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String[] input = br.readLine().split(" ");
			ip = input[0];
			port = Integer.parseInt(input[1]);
			
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getIP(){
		
		return ip;
	}
	
	public int getPort(){
		
		return port;
	}
}
