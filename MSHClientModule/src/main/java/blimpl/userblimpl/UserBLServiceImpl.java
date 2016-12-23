package blimpl.userblimpl;

//import blservice.userblservice.LevelService;

import blservice.userblservice.UserBLInfo;
import blservice.userblservice.UserBLService;
import util.LoginState;
import util.ResetState;
import util.ResultMessage;
import vo.*;

import java.util.ArrayList;

/**
 * Created by Kray on 2016/11/6.
 */
public class UserBLServiceImpl implements UserBLService, UserBLInfo {

    private User user;
    private LoginState loginState;

    protected UserBLServiceImpl(User user, LoginState loginState) {
        this.user = user;
        this.loginState = loginState;
    }

    /**
     * 登录
     *
     * @param account
     * @param password
     * @return 当前登录状态
     */
    public LoginState login(String account, String password) {
        LoginState loginState = user.login(account, password);
        this.loginState = loginState;
        return loginState;
    }

    /**
     * 登出
     *
     * @return 当前登录状态
     */
    public LoginState logout() {
        if (loginState != LoginState.LOGOUT) {
            return user.logout();
        } else {
            System.out.println("Already Logout");
            return null;
        }
    }

    /**
     * 重置密码
     *
     * @param account
     * @param oldPassword
     * @param newPassword
     * @return 重置密码结果状态
     */
    public ResetState reset(String account, String oldPassword, String newPassword) {
        return user.resetPassword(account, oldPassword, newPassword);
    }

    /**
     * 增加用户
     *
     * @param userVO
     * @return 是否增加成功
     */
    public ResultMessage add(UserVO userVO) {
        return user.add(userVO);
    }

    /**
     * 根据ID查找用户
     *
     * @param userID
     * @return 查到的用户
     */
    public UserVO searchByID(String userID) {
        return user.searchByID(userID);
    }

    /**
     * 更新用户信息
     *
     * @param userVO
     * @return 是否更新成功
     */
    public ResultMessage update(UserVO userVO) {
        return user.update(userVO);
    }

    /**
     * 删除用户
     *
     * @param userID
     * @return 是否删除成功
     */
    public ResultMessage delete(String userID) {
        return user.delete(userID);
    }

    /**
     * 搜索符合关键词的用户列表
     *
     * @param keyword
     * @return 返回的用户列表
     */
    public ArrayList search(String keyword) {
        return user.search(keyword);
    }

    /**
     * 给客户增加信用记录
     *
     * @param clientID
     * @param creditChangeInfoVO
     * @return 增加是否成功
     */
    public ResultMessage addCreditRecord(String clientID, CreditChangeInfoVO creditChangeInfoVO) {
        if (user instanceof Client) {
            Client client = (Client) user;
            return client.addCreditByID(clientID, creditChangeInfoVO);
        } else {
            return null;
        }
    }

    /**
     * 根据客户ID查找信用记录
     *
     * @param clientID
     * @return 该客户的信用记录列表
     */
    public ArrayList<CreditVO> searchCreditByID(String clientID) {
        if (user instanceof Client) {
            Client client = (Client) user;
            return client.searchCreditByID(clientID);
        } else {
            return null;
        }
    }

    /**
     * 根据客户ID获得总信用值
     *
     * @param id
     * @return 该客户的总信用值
     */
    public int getCreditOfID(String id) {
        if (user instanceof Client) {
            Client client = (Client) user;
            return client.getCreditOfID(id);
        } else {
            return -1;
        }
    }

    /**
     * 得到当前登录状态下的客户ID
     *
     * @return 当前登录状态下的客户ID
     */
    public String getCurrentClientID() {
        if (user instanceof Client) {
            Client client = (Client) user;
            return client.getCurrentClientID();
        } else {
            return null;
        }
    }

    /**
     * 得到当前登录状态下的酒店工作人员ID
     *
     * @return 当前登录状态下的酒店工作人员ID
     */
    public String getCurrentStaffID() {
        if (user instanceof Staff) {
            Staff staff = (Staff) user;
            return staff.getCurrentStaffID();
        } else {
            return null;
        }
    }

    /**
     * 得到当前登录状态下的网站营销人员ID
     *
     * @return 当前登录状态下的网站营销人员ID
     */
    public String getCurrentSalesmanID() {
        if (user instanceof Salesman) {
            Salesman salesman = (Salesman) user;
            return salesman.getCurrentSalesmanID();
        } else {
            return null;
        }
    }

    /**
     * 得到酒店工作人员对应的酒店名字
     *
     * @param ID
     * @return
     */
    public String getHotelIDByStaffID(String ID) {
        if (user instanceof Staff) {
            Staff staff = (Staff) user;
            return staff.getHotelIDByStaffID(ID);
        } else {
            return null;
        }
    }

    /**
     * 增加一条等级信息
     *
     * @return
     */
    public ResultMessage addLevel(LevelVO levelVO) {
        if (user instanceof Salesman) {
            Salesman salesman = (Salesman) user;
            return salesman.addLevel(levelVO);
        } else {
            return null;
        }
    }

    /**
     * 更新一条等级信息
     *
     * @return
     */
    public ResultMessage updateLevel(LevelVO levelVO) {
        if (user instanceof Salesman) {
            Salesman salesman = (Salesman) user;
            return salesman.updateLevel(levelVO);
        } else {
            return null;
        }
    }

    /**
     * 删除一条等级信息
     *
     * @return
     */
    public ResultMessage deleteLevel(String ID) {
        if (user instanceof Salesman) {
            Salesman salesman = (Salesman) user;
            return salesman.deleteLevel(ID);
        } else {
            return null;
        }
    }

    /**
     * 得到一条等级信息
     *
     * @param level
     * @return
     */
    public LevelVO getLevel(String level) {
        if (user instanceof Client) {
            Client client = (Client) user;
            return client.getLevel(level);
        } else {
            return null;
        }
    }

    /**
     * 得到所有等级信息
     *
     * @return
     */
    public ArrayList<LevelVO> getAllLevel() {
        if (user instanceof Salesman) {
            Salesman salesman = (Salesman) user;
            return salesman.getAllLevel();
        } else {
            return null;
        }
    }

    /**
     * 得到客户
     *
     * @param ID
     * @return
     */
    public ClientVO getClientByID(String ID) {
        return (ClientVO) user.searchByID(ID);
    }

    /**
     * 得到酒店工作人员
     *
     * @param ID
     * @return
     */
    public StaffVO getStaffByID(String ID) {
        return (StaffVO) user.searchByID(ID);
    }

    /**
     * 得到网站营销人员
     *
     * @param ID
     * @return
     */
    public SalesmanVO getSalesmanByID(String ID) {
        return (SalesmanVO) user.searchByID(ID);
    }

    /**
     * 通过酒店ID得到酒店工作人员
     *
     * @param hotelID
     * @return
     */
    public StaffVO getStaffByHotelID(String hotelID) {
        if (user instanceof Staff) {
            Staff staff = (Staff) user;
            return staff.getStaffByHotelID(hotelID);
        } else {
            return null;
        }
    }

    /**
     * 得到当前用户
     *
     * @return
     */
    public User getUser() {
        return user;
    }

}
