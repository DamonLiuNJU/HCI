package data.helper;

import java.io.*;
import java.util.ArrayList;

public class FileIOHelper {
	String filename;
	
	public FileIOHelper(String name){
		filename=name;
	}
	/**
     * 
     * @param filename
     * @return list
     */
    public ArrayList<String> readFromFile(){
        ArrayList<String> list = new ArrayList<String>();
        try {
            File f = new File(filename);
            String line = null;
            BufferedReader br = new BufferedReader(new FileReader(f));
            while ((line = br.readLine()) != null){
                list.add(line);
            }
            br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
        }
        return list ;
    }
    /**
     * 
     * @param s
     * @param filename
     */
    public void appendToFile(String s){       //不覆盖写入
        try {
            FileWriter fw = new FileWriter(new File(filename),true);
            fw.write(s + "\r\n");
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
   /**
    * 
    * @param s
    * @param filename
    */
    public void writeToFile(String s){       //覆盖写入
        try {
            FileWriter fw = new FileWriter(new File(filename));
            fw.write(s + "\r\n");
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void clear(){
    	try {
            FileWriter fw = new FileWriter(new File(filename));
            fw.write("");
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
