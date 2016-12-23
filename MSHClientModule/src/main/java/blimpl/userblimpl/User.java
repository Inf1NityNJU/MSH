package blimpl.userblimpl;

import network.UserClientNetworkImpl;
import network.UserClientNetworkService;
import po.ClientPO;
import po.SalesmanPO;
import po.StaffPO;
import util.LoginState;
import util.ManagerInfo;
import util.ResetState;
import util.ResultMessage;
import vo.*;

import java.util.ArrayList;

/**
 * Created by Sorumi on 16/10/30.
 */
public class User {

    /**
     * UserBL
     */
    protected UserClientNetworkService userClientNetwork;

    public User(){
        this.userClientNetwork = new UserClientNetworkImpl();
    }

    /**
     * 登录
     *
     * @param account
     * @param password
     * @return 当前登录状态
     */
    public LoginState login(String account, String password) {
        if (account.equals(ManagerInfo.manager_account) && password.equals(ManagerInfo.manager_password)) {
            return LoginState.LOGIN_SUCCESS_Manager;
        }
        LoginState loginState = userClientNetwork.login(account, password);
        if (loginState == LoginState.LOGIN_SUCCESS_Client) {
            System.out.println("Login Client");
            ArrayList<ClientPO> clientPOs = userClientNetwork.searchClient(account);
            for (ClientPO clientPO : clientPOs) {
                if (clientPO.getPassword().equals(password) && clientPO.getAccount().equals(account)) {
                    this.setCurrentID(clientPO.getClientID());
                }
            }
        } else if (loginState == LoginState.LOGIN_SUCCESS_Salesman) {
            System.out.println("Login Salesman");
            ArrayList<SalesmanPO> salesmanPOs = userClientNetwork.searchSalesman(account);
            for (SalesmanPO salesmanPO : salesmanPOs) {
                if (salesmanPO.getPassword().equals(password) && salesmanPO.getAccount().equals(account)) {
                    this.setCurrentID(salesmanPO.getSalesmanID());
                }
            }
        } else if (loginState == LoginState.LOGIN_SUCCESS_Staff) {
            System.out.println("Login Staff");
            ArrayList<StaffPO> staffPOs = userClientNetwork.searchStaff(account);
            for (StaffPO staffPO : staffPOs) {
                if (staffPO.getPassword().equals(password) && staffPO.getAccount().equals(account)) {
                    this.setCurrentID(staffPO.getStaffID());
                }
            }
        }
        return loginState;
    }

    /**
     * 登出
     *
     * @return 当前登录状态
     */
    public LoginState logout() {
        setCurrentID("");
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
        return null;
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
     * 设置当前用户ID
     *
     * @param currentID
     */
    public void setCurrentID(String currentID) {
        if (!currentID.equals("")) {
            switch (currentID.charAt(0)) {
                case '1':
                    UserInfoManager.getUserInfoManager().setCurrentSalesmanID(currentID);
                    break;
                case '3':
                    UserInfoManager.getUserInfoManager().setCurrentStaffID(currentID);
                    break;
                case '0':
                    UserInfoManager.getUserInfoManager().setCurrentClientID(currentID);
                    break;
            }
        }
        System.out.println("Set ID: " + currentID);
    }

    /**
     * 返回当前客户ID
     *
     * @return 当前客户ID
     */
    public String getCurrentClientID() {
        System.out.println("Get ID: " + UserInfoManager.getUserInfoManager().getCurrentClientID());
        return UserInfoManager.getUserInfoManager().getCurrentClientID();
    }

    /**
     * 返回当前酒店工作人员ID
     *
     * @return 当前酒店工作人员ID
     */
    public String getCurrentStaffID() {
        System.out.println("Get ID: " + UserInfoManager.getUserInfoManager().getCurrentStaffID());
        return UserInfoManager.getUserInfoManager().getCurrentStaffID();
    }

    /**
     * 返回当前网站营销人员ID
     *
     * @return 当前网站营销人员ID
     */
    public String getCurrentSalesmanID() {
        System.out.println("Get ID: " + UserInfoManager.getUserInfoManager().getCurrentSalesmanID());
        return UserInfoManager.getUserInfoManager().getCurrentSalesmanID();
    }

}
