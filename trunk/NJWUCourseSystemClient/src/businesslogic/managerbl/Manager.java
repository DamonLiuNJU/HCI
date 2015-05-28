package businesslogic.managerbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.managerpo.ManagerPO;
import rmiconnector.RemoteDataFactory;
import vo.managervo.ManagerVO;
import dataservice.managerdataservice.ManagerDataService;
import dataservice.managerdataservice.MessageDataService;

/**
 * manager的实现类，是Admin,Dean,Faculty的父类
 * @author cbb
 *
 */
public class Manager{
	ManagerDataService data=(ManagerDataService) new RemoteDataFactory().getData("Manager");
	MessageDataService messageData=(MessageDataService) new RemoteDataFactory().getData("Message");
		
	String id;
	String password;
	String name;
	String contactInfo;
	
	public Manager(String id){
		this.id=id;
		ManagerPO mp=new ManagerPO();
		try {
			mp=data.find(id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		password=mp.getPassword();
		name=mp.getName();
		contactInfo=mp.getContactInfo();
	}
	
	public Manager(){

	}
	
	/**
	 * 返回请求登录得结果
	 * 0 用户不存在   ,1 密码错误，2 登录成功
	 * @param id
	 * @param psw
	 * @return
	 */
	public int login(String id, String psw) {
		ManagerPO mp=new ManagerPO();
		try {
			mp=data.find(id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		if(mp==null){
			return 0;
		}else if(!mp.getPassword().equals(psw)){
			return 1;
		}else{
			return 2;
		}
	}
	
	/**
	 * 修改密码
	 * @param newPw
	 */
	public void changePassword(String newPw){
		password=newPw;
		try {
			data.updatePw(new ManagerPO(id,password));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	 
	public ManagerVO getManagerInfo(){
		ManagerPO mp;
		try {
			mp = data.find(id);
			return new ManagerVO(mp);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 得到所有managerpo列表
	 * @return
	 */
	protected ArrayList<ManagerPO> getAllManagers(){
		ArrayList<ManagerPO> mpList=new ArrayList<ManagerPO>();
		try {
			mpList=data.finds();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return mpList;
	}
	
	public boolean isIDExist(String id){
		if(login(id,"")==0){
			return false;
		}else{
			return true;
		}
	}
	
	public String getID(){
		return id;
	}
	public String getPassword(){
		return password;
	}	
	public String getName(){
		return name;
	}
	public String getContactInfo(){
		return contactInfo;
	}
	public void setContactInfo(String info){
		contactInfo=info;
		try {
			data.updateCi(new ManagerPO(id,contactInfo,null));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
