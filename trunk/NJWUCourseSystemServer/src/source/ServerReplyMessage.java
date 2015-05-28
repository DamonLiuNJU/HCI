package source;

public interface ServerReplyMessage {
	
	String INSERT_TEACHER_SUCCEED = "插入一个教师PO";
	String INSERT_TEACHER_FAILED = "插入教师PO失败";
	
	String DELETE_TEACHER_SUCCEED = "删除一个教师PO";
	String DELETE_TEACHER_FAILED = "删除教师PO失败";
	
	
	//Factory相关
	String NO_TYPE_EXCEPTION = "不存在该类型Data";
	
	//database 相关
	String DELETE_SUCCEED = "删除成功";
	
}
