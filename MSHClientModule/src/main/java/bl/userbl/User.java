package bl.userbl;

import util.LoginState;
import util.ResetState;

/**
 * Created by Sorumi on 16/10/30.
 */
public class User {
    /**
     * 登录
     * @param account
     * @param password
     * @return  当前登录状态
     */
    public LoginState login(String account, String password){
        return null;
    }

    /**
     * 登出
     * @return   当前登录状态
     */
    public LoginState logout(){
        return null;
    }

    /**
     * 重置密码
     * @param account
     * @param oldPassword
     * @param newPassword
     * @return  重置密码结果状态
     */
    public ResetState resetPassword(String account, String oldPassword, String newPassword){
        return ResetState.RESET_SUCCESS;
    }
}
