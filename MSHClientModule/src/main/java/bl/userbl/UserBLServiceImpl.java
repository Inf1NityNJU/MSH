package bl.userbl;

import blservice.userblservice.UserBLService;
import util.CreditAction;
import util.LoginState;
import util.ResetState;
import util.ResultMessage;

/**
 * Created by Kray on 2016/11/6.
 */
public class UserBLServiceImpl implements UserBLService {
    public LoginState login(String account, String password){
        return null;
    }

    /**
     * 登出
     * @return 当前登录状态
     */
    public LoginState logout(){
        return null;
    }

    /**
     * 重置密码
     * @param account
     * @param oldPassword
     * @param newPassword
     * @return 重置密码结果状态
     */
    public ResetState reset(String account, String oldPassword, String newPassword){
        return null;
    }

    /**
     * 给客户增加信用记录
     * @param clientID
     * @param credit
     * @param creditAction
     * @return 增加是否成功
     */
    public ResultMessage addCreditRecord(String clientID, int credit, CreditAction creditAction){
        return null;
    }

    /**
     * 根据客户 id 查找信用值
     * @param clientID
     * @return  该客户的信用值
     */
    public int searchCreditByID(String clientID){
        return 0;
    }
}
