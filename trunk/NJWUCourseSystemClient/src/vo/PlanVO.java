package vo;

public class PlanVO{
String id;	//院系ID
String name;//院系名
public   PlanVO(String id,String name){
	this.id=id;
	this.name=name;
}


public String getID(){
	return id;
}
public String getName(){
	return name;
}

public void setID(String id){
	this.id=id;
}
public void setName(String name){
	this.name=name;
}
}




























/*


public   static void main(String arg[]) throws RemoteException{
	  PlanVO pv=new PlanVO();
/*
	  //测试getFpList()
	  ArrayList<FacultyPO> fpList=pv.getFpList();
	  for(FacultyPO fp:fpList){
		  System.out.println(fp.getFacultyID()+" "+fp.getFacultyName()+" "+fp.getPlan());
	  }
	 
	  //测试getFacultyIDByFacultyID(String fTeacher)
  //System.out.println(pv.getFacultyIDByFteacherID("10001"));
  //System.out.println(pv.getFacultyIDByFteacherID("10002"));
}

public PlanVO() {
				//初始化 fpList
				RemoteDataFactory factory =new RemoteDataFactory();
				PlanDataService  pds=(PlanDataService)factory.getData("Plan");
				try {
						fpList=pds.finds();
				} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
	

				ManagerDataService  mds=(ManagerDataService)factory.getData("Manager");
				try {
				mds.delete("10001");
						//mpList=mds.finds();
				} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
				}


public String getFacultyIDByFteacherID(String fTeacherID) {
	// TODO Auto-generated method stub
	for(int i=0;i<mpList.size();i++){
		if(mpList.get(i).getID().equals(fTeacherID)){
			return mpList.get(i).getFacultyID();
		}
	}
	return null;
}

public ArrayList<FacultyPO> getFpList() {
	// TODO Auto-generated method stub
	return fpList;
}
}

*/