package bl.userbl;

import util.LoginState;
import util.ResultMessage;
import vo.StaffVO;

import java.util.ArrayList;

/**
 * Created by Kray on 2016/10/30.
 */
public class Staff extends User {

    /**
     * 登录
     *
     * @param account
     * @param password
     * @return 当前登录状态
     */
    public LoginState login(String account, String password) {
        if (true) {
            //去找
            super.setCurrentID("STRING FROM DB");
            return LoginState.LOGIN_SUCCESS_Salesman;
        } else {
            return LoginState.LOGIN_FAIL;
        }
    }

    /**
     * 增加酒店工作人员
     *
     * @param StaffVO
     * @return 是否增加成功
     */
    public ResultMessage add(StaffVO StaffVO) {
        return null;
    }

    /**
     * 根据ID查找酒店工作人员
     *
     * @param StaffID
     * @return 符合ID的StaffVO
     */
    public StaffVO searchByID(String StaffID) {
        return null;
    }

    /**
     * 更新酒店工作人员
     *
     * @param StaffVO
     * @return 是否更新成功
     */
    public ResultMessage update(StaffVO StaffVO) {
        return null;
    }

    /**
     * 删除酒店工作人员
     *
     * @param StaffID
     * @return 是否删除成功
     */
    public ResultMessage delete(String StaffID) {
        return null;
    }

    /**
     * 根据关键词,搜索酒店工作人员
     *
     * @param keyword
     * @return 符合关键词的所有酒店工作人员
     */
    public ArrayList<StaffVO> search(String keyword) {
        return null;
    }
}
