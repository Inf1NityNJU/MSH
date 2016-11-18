package bl.userbl;

import util.LoginState;
import util.ResultMessage;
import vo.SalesmanVO;
import vo.UserVO;

import java.util.ArrayList;

/**
 * Created by Kray on 2016/10/30.
 */
public class Salesman extends User {

    /**
     * 登录
     *
     * @param account
     * @param password
     * @return 当前登录状态
     */
    public LoginState login(String account, String password) {
        if (true) {
            //去DB找
            super.setCurrentID("STRING FROM DB");
            return LoginState.LOGIN_SUCCESS_Salesman;
        } else {
            return LoginState.LOGIN_FAIL;
        }
    }

    /**
     * 增加网站营销人员
     *
     * @param userVO
     * @return 是否增加成功
     */
    public ResultMessage add(UserVO userVO) {
        SalesmanVO salesmanVO = (SalesmanVO)userVO;
        return null;
    }

    /**
     * 根据ID查找网站营销人员
     *
     * @param SalesmanID
     * @return 符合ID的SalesmanVO
     */
    public SalesmanVO searchByID(String SalesmanID) {
        return null;
    }

    /**
     * 更新网站营销人员
     *
     * @param userVO
     * @return 是否更新成功
     */
    public ResultMessage update(UserVO userVO) {
        SalesmanVO salesmanVO = (SalesmanVO)userVO;
        return null;
    }

    /**
     * 删除网站营销人员
     *
     * @param salesmanID
     * @return 是否删除成功
     */
    public ResultMessage delete(String salesmanID) {
        return null;
    }

    /**
     * 根据关键词搜索网站营销人员
     *
     * @param keyword
     * @return 符合关键词的所有网站营销人员
     */
    public ArrayList<SalesmanVO> search(String keyword) {
        return null;
    }

}
