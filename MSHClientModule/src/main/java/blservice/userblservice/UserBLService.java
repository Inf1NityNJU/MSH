package blservice.userblservice;

import util.CreditAction;
import util.LoginState;
import util.ResetState;
import util.ResultMessage;
import vo.CreditVO;
import vo.UserVO;

import java.util.ArrayList;

/**
 * Created by Kray on 2016/10/13.
 */
public interface UserBLService {

    /**
     * 登录
     *
     * @param account
     * @param password
     * @return 当前登录状态
     */
    public LoginState login(String account, String password);

    /**
     * 登出
     *
     * @return 当前登录状态
     */
    public LoginState logout();

    /**
     * 重置密码
     *
     * @param account
     * @param oldPassword
     * @param newPassword
     * @return 重置密码结果状态
     */
    public ResetState reset(String account, String oldPassword, String newPassword);

    /**
     * 增加用户
     *
     * @param userVO
     * @return 是否增加成功
     */
    public ResultMessage add(UserVO userVO);

    /**
     * 根据ID查找用户
     *
     * @param userID
     * @return 查到的用户
     */
    public UserVO searchByID(String userID);

    /**
     * 更新用户信息
     *
     * @param userVO
     * @return 是否更新成功
     */
    public ResultMessage update(UserVO userVO);

    /**
     * 删除用户
     *
     * @param userID
     * @return 是否删除成功
     */
    public ResultMessage delete(String userID);

    /**
     * 搜索符合关键词的用户列表
     *
     * @param keyword
     * @return 返回的用户列表
     */
    public ArrayList search(String keyword);

    /**
     * 给客户增加信用记录
     *
     * @param clientID
     * @param creditVO
     * @return 增加是否成功
     */
    public ResultMessage addCreditRecord(String clientID, CreditVO creditVO);

    /**
     * 根据客户ID查找信用记录
     *
     * @param clientID
     * @return 该客户的信用记录列表
     */
    public ArrayList<CreditVO> searchCreditByID(String clientID);

}
