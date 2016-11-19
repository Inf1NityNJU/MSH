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
        return LoginState.LOGOUT;
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

    /**
     * 增加用户
     *
     * @param userVO
     * @return 是否增加成功
     */
    public ResultMessage add(UserVO userVO) {
        return null;
    }

    /**
     * 根据ID查找用户
     *
     * @param userID
     * @return 查到的用户
     */
    public UserVO searchByID(String userID) {
        return null;
    }

    /**
     * 更新用户信息
     *
     * @param userVO
     * @return 是否更新成功
     */
    public ResultMessage update(UserVO userVO) {
        return null;
    }

    /**
     * 删除用户
     *
     * @param userID
     * @return 是否删除成功
     */
    public ResultMessage delete(String userID) {
        return null;
    }

    /**
     * 搜索符合关键词的用户列表
     *
     * @param keyword
     * @return 返回的用户列表
     */
    public ArrayList search(String keyword) {
        return null;
    }

    /**
     * 给客户增加信用记录
     *
     * @param clientID
     * @param creditVO
     * @return 增加是否成功
     */
    public ResultMessage addCreditByID(String clientID, CreditVO creditVO) {
        return null;
    }

    /**
     * 根据客户ID查找信用记录
     *
     * @param id
     * @return 该客户的信用记录列表
     */
    public ArrayList<CreditVO> searchCreditByID(String id) {
        return null;
    }

    /**
     * 得到某客户的总信用值
     *
     * @param clientID
     * @return 客户总信用值
     */
    public int getCreditOfID(String clientID) {
        if (this instanceof Client) {
            return this.getCreditOfID(clientID);
        } else {
            return -1;
        }
    }

    /**
     * 设置当前用户ID
     *
     * @param currentID
     */
    public void setCurrentID(String currentID) {
        this.currentID = currentID;
    }

    /**
     * 返回当前用户ID
     *
     * @return 当前用户ID
     */
    public String getCurrentID() {
        return this.currentID;
    }
}
