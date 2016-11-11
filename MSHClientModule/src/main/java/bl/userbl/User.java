package bl.userbl;

import util.LoginState;
import util.ResetState;
import util.ResultMessage;
import vo.*;

import java.util.ArrayList;

/**
 * Created by Sorumi on 16/10/30.
 */
public class User {

    private String currentID;

    /**
     * 登录
     *
     * @param account
     * @param password
     * @return 当前登录状态
     */
    public LoginState login(String account, String password) {
        return null;
    }

    /**
     * 登出
     *
     * @return 当前登录状态
     */
    public LoginState logout() {
        return null;
    }

    /**
     * 重置密码
     *
     * @param account
     * @param oldPassword
     * @param newPassword
     * @return 重置密码结果状态
     */
    public ResetState resetPassword(String account, String oldPassword, String newPassword) {
        return ResetState.RESET_SUCCESS;
    }

    public ResultMessage add(UserVO userVO) {
        return null;
    }

    public UserVO searchByID(String userID) {
        return null;
    }

    public ResultMessage update(UserVO userVO) {
        return null;
    }

    public ResultMessage delete(UserVO userVO) {
        return null;
    }

    public ArrayList search(String keyword) {
        return null;
    }

    public ArrayList<CreditVO> searchCreditByID(String id) {
        if (this instanceof Client) {
            return this.searchCreditByID(id);
        } else {
            return null;
        }
    }

    public int getCreditOfID(String id) {
        if (this instanceof Client) {
            return this.getCreditOfID(id);
        } else {
            return -1;
        }
    }

    public void setCurrentID(String currentID) {
        this.currentID = currentID;
    }

    public String getCurrentID() {
        return this.currentID;
    }
}
