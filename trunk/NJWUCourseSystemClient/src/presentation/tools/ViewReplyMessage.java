package presentation.tools;

public interface ViewReplyMessage {
	/*
	 * 用戶登錄部份
	 */
	String ID_NOT_EXIST = "用户名不存在";
    String PW_ERROR = "密码错误";
    String LOGIN_SUCCEED = "登录成功";
	
	/*
	 * 修改密码部分
	 */
    String PW_ERROR1="原密码错误";
    String PW_ERROR2="新密码格式错误，必须多于3个字符";
    String PW_ERROR3="两次输入的新密码不一致";
    String PW_ERROR4="密码信息不全或有误，请检查";
    String PW_CHANGE="密码修改成功";
	
	/*
	 * 教务处老师部分
	 */
    //框架策略
    String CREATE_TIP="不在限制时间内,无法创建";
    String CREATE_VERIFY="创建新框架会覆盖原内容，是否确认？";
    String CREATE_SUCCEED="创建成功";
    String MODIFY_TIP="不在限制时间内,无法修改";
    String MODIFY_SUCCEED="修改成功";
    String NOT_COMPLETED="未选择课程模块";
    String LOWCREDIT_ERROR="学分下限设置过低";
    String HIGHCREDIT_ERROR="学分上限设置过高";
    String NUM_ERROR="上下限请输入数字";
    String RANGE_ERROR="下限值应不大于上限";
    
	//查看教师统计信息
	String NOT_SELECT_FACULTY="请选择一个院系";
	String TEACHER_NOT_EXIST="教师信息有误";
	String CREDIT_NULL="成绩尚未统计出";
	
	//发送信息
	String EMPTY_CONTENT="发送内容不能为空";
	String SEND_SUCCEED="发送成功";
	
	//课程审核
	String SEND_PASS="已通知教务老师发布";
	String SEND_NOT_PASS="已拒绝该项申请";
	
	//触发选课相关
	String HAVE_SELECTED="已筛选过，无法再次进行";
	String TRANSFER_SUCCEED="终止补退选成功";
	String HAVE_TRANSFER="已终止过";
	//修改个人信息
	String INFO_CHANGE="修改成功";
}
