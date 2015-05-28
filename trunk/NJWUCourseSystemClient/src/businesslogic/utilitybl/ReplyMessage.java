package businesslogic.utilitybl;

public interface ReplyMessage {
	String ID_NOT_EXIST = "用户名不存在";
    String PW_ERROR = "密码错误";
    String LOGIN_SUCCEED = "登录成功";
    String CHANGE_SUCCEED="密码修改成功";
     
    //admin相关
    String REGISTER_SUCCEED = "注册用户成功";
    String REGISTER_FAILED = "注册用户失败";
    String DELETE_FAILED = "删除用户失败";
    String MISSING_GROUPSELECTION= "请选择一个类型";
    String ID_EXIST = "已存在该学（工）号，注册失败";
    String NO_TYPE_EXCEPTION = "不存在该类型用户";
    String UPDATE_SUCCEED = "更新成功";
    String UPDATE_FAILED = "更新失败";
     
    // frame,plan相关
    String CREATE_SUCCEED = "创建成功";
    String CREATE_FAIL = "不在限制时间内，无法创建";
    String MODIFY_SUCCEED = "修改成功";
    String MODIFY_FAIL = "不在限制时间内，无法修改";
    String PUBLISH_SUCCEED="发布成功";
    String PUBLISH_FAIL = "不在限制时间内，无法发布";
    String DELETE_SUCCEED="删除成功";
    String IMPORT_SUCCEED="上传成功";

    // advice相关
    String SEND_SUCCEED = "信息已发送";     
    String SEND_FAIL1 = "收件人有误，发送失败";

    // 修改密码相关
    String PW_CHANGE = "密码修改成功";
    String PW_ERROR2 = "原密码输入错误";
    

    // 选课相关
    String CHOOSE_SUCCEED = "选课成功";
    String CHOOSE_FAIL1 = "课程权限不符不可选，选课失败";
    String QUIT_SUCCEED = "退选成功";
    
    //course相关
    String ADD_SUCCEED = "添加成功";
    String APPLY_SUCCEED="申请成功";
    String APPLY_FAILED="申请失败";
    
    //teacherui 相关
    String MISSING_INFO = "未填写完所有信息";
    String NUMBER_FORMAT_EXCEPTION = "课时数，最大学生人数，以及学分需要输入数字";
    String OUT_OF_APPLY_TIME = "不在申报课程时间";
    
    //table 相关
    String NULL_SELECT = "未选择一门课程";
    
    //rmi 相关
    String CONNECTION_REFUSED = "与服务器连接失败";
    
}
