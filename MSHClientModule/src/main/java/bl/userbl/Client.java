package bl.userbl;

import util.LoginState;
import util.ResultMessage;
import vo.ClientVO;
import vo.CreditVO;

import java.util.ArrayList;

/**
 * Created by Kray on 2016/10/30.
 */
public class Client extends User {

    /**
     * 登录方法
     *
     * @param account
     * @param password
     * @return 当前登录状态
     */
    public LoginState login(String account, String password) {
        if (true) {
            //去找
            super.setCurrentID("STRING FROM DB");

            return LoginState.LOGIN_SUCCESS_Client;
        } else {
            return LoginState.LOGIN_FAIL;
        }
    }

    /**
     * 添加客户
     *
     * @param clientVO
     * @return 是否添加成功
     */
    public ResultMessage add(ClientVO clientVO) {
        return null;
    }

    /**
     * 根据ID查找客户
     *
     * @param clientID
     * @return 查询到的ClientVO
     */
    public ClientVO searchByID(String clientID) {
        return null;
    }

    /**
     * 更新用户信息
     *
     * @param clientVO
     * @return 是否更新成功
     */
    public ResultMessage update(ClientVO clientVO) {
        return null;
    }

    /**
     * 删除客户
     *
     * @param clientID
     * @return 是否删除成功
     */
    public ResultMessage delete(String clientID) {
        return null;
    }

    /**
     * 根据关键词搜索客户
     *
     * @param keyword
     * @return 符合关键词的所有客户
     */
    public ArrayList<ClientVO> search(String keyword) {
        return null;
    }

    /**
     * 给对应ID的客户增加信用记录
     *
     * @param clientID
     * @param creditVO
     * @return 是否增加成功
     */
    public ResultMessage addCreditByID(String clientID, CreditVO creditVO) {
        return null;
    }

    /**
     * 根据客户ID查找信用记录
     *
     * @param clientID
     * @return 该客户的所有信用记录
     */
    public ArrayList<CreditVO> searchCreditByID(String clientID) {
        return null;
    }

}
