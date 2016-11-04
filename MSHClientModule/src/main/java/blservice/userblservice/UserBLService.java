package blservice.userblservice;

import util.CreditAction;
import util.LoginState;
import util.ResetState;
import util.ResultMessage;

/**
 * Created by Kray on 2016/10/13.
 */
public interface UserBLService {

    /**
     * 登录
     * @param account
     * @param password
     * @return 当前登录状态
     */
    public LoginState login(String account, String password);

    /**
     * 登出
     * @return 当前登录状态
     */
    public LoginState logout();

    /**
     * 重置密码
     * @param account
     * @param oldPassword
     * @param newPassword
     * @return 重置密码结果状态
     */
    public ResetState reset(String account, String oldPassword, String newPassword);

    /**
     * 给客户增加信用记录
     * @param clientID
     * @param credit
     * @param creditAction
     * @return 增加是否成功
     */
    public ResultMessage addCreditRecord(String clientID, int credit, CreditAction creditAction);

    /**
     * 根据客户 id 查找信用值
     * @param clientID
     * @return  该客户的信用值
     */
    public int searchCreditByID(String clientID);
}
