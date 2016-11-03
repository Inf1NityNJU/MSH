package bl.userbl;

import util.LoginState;
import util.ResetState;

/**
 * Created by Kray on 2016/10/30.
 */
public class MockUser extends User {

    /**
     * 登录
     * @param account
     * @param password
     * @return
     */
//    public LoginState login(String account, String password){
//        return LoginState.LOGIN_SUCCESS;
//    }

    /**
     * 登出
     * @return
     */
    public LoginState logout(){
        return LoginState.LOGOUT;
    }

    /**
     * 重置密码
     * @param account
     * @param oldPassword
     * @param newPassword
     * @return
     */
    public ResetState resetPassword(String account, String oldPassword, String newPassword){
        return ResetState.RESET_SUCCESS;
    }
}
