package entity;

public class StatusCode {
    public static final int OK = 20000;           //成功
    public static final int ERROR = 20001;        //失败
    public static final int LOGINERROR = 20002;   //账号或密码错误
    public static final int ACCESSERROR = 20003;  //权限不足
    public static final int REMOTERROR = 20004;   //远程调用错误
    public static final int REERROR = 20005;      //重复操作
    public static final int NOTFOUNDERROR = 20006;//没有对应的抢购数据
}
