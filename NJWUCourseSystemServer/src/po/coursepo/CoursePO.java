package po.coursepo;

import java.io.Serializable;

public class CoursePO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
    private String name;
    private String teacherID;
    private String campus;
      private String  grade;
     private String place;
    private String time;
  private  String period;
  private String  require;
  private String facultyID;
   private String credit;
   private String module;
private String limit;
   private String specificInfo;

  
  //构造组 
    public CoursePO(){
		id=null;
	    name= null; 
	     teacherID= null;
	    campus=null; 
	    grade=null;
	    place= null;
	    time= null;
	     period= null;
	    require=null;
	    facultyID=null;
	    credit=null;
	   module=null;
	   limit=null;
	   specificInfo=null;
	 
	   
   }
public   CoursePO(String  id,String name,String teacherID,String campus,String grade,String place,
								String time,String period,String require,String facultyID,String credit,String module,
								String limit,String specificInfo){
	this.id=id;
    this.name= name;
    this .teacherID= teacherID;
     this.time= time ;
    this .campus=campus;		 
    this.grade=grade;

    this.require=require;
	
   this.period= period;
   this.place= place;
   this.facultyID=facultyID;

   this.credit= credit;
   this. module=module;
this.limit=limit;
   this.specificInfo=specificInfo;
   }
  //set组
   public void setID(String  id){
	   this.id=id;
   }
   public void  setName(String name){
	   this .name=name;	   
   }
   public void setTeacherID(String  id){
	   teacherID=id;
   }
 public void setTime(String time){
	this.time=time;   
   }
  public void setCampus(String campus){
	  this.campus=campus;
  }
  public void setRequire(String require){
	  this.require=require;
  }
 public void setGrade(String grade){
	 this.grade=grade;
 }
public void setPeriod(String period){
	   this.period=period;
   }
   public void setPlace(String place){
   this.place=place;
   }
 public void setFacultyID(String id){
	 facultyID=id;
 }
public void setLimit(String limit){
	this.limit=limit;
}
   public void setCredit(String credit){
	   this.credit=credit;
   }
   public void setModule(String module){
	   this.module=module;
   }
   public void setInfo(String info){
	   specificInfo=info;
   }
	
    //get组
 public String getID(){
	  return id;
  }
   public String getName(){
	  return name;
  } 
  public String  getTeacherID(){
	  return teacherID;
  }
    public  String getTime(){
	  return time;
  }
  public String getCampus(){
	  return campus;
  }
  public String getRequire(){
	  return require;
  }
public String getGrade(){
	return grade;
}  
public  String getPeriod(){
	  return period;
  }
 public  String getPlace(){
	  return place;
  }
public String getFacultyID(){
	return facultyID;
}  
  public  String  getCredit(){
	  return credit;
  }
   public String getModule(){
	  return module;
  }
  
   public String getLimit(){
	   return limit;
   }
public  String getInfo(){
	  return specificInfo;
  }


//mysql
public String getAttributeNames(){
		return "`id`,`name`,`teacherID`,`campus`,`grade`,`place`,`time`,`period`," +
				"`require`,`facultyID`,`credit`,`module`,`limit`,`specificInfo`";
	}
 public String getValues(){
		StringBuilder sb=new StringBuilder();
		sb.append("(");
		sb.append(s(id)+","+s(name)+","+s(teacherID)+","+s(campus)+","+s(grade)+","+s(place)+","+s(time)+","
		+s(period)+","+s(require)+","+s(facultyID)+","+s(credit)+","+s(module)+","+s(limit)+","+s(specificInfo));
		sb.append(")");
		return sb.toString();		
	}
	public String s(Object o){
	return "'"+o+"'";
}


}


