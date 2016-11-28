package bl.userbl;

import dataimpl.userdataimpl.UserDataServiceFactory;
import dataservice.userdataservice.UserDataService;
import po.StaffPO;
import util.LoginState;
import util.ResetState;
import util.ResultMessage;
import vo.StaffVO;
import vo.StaffVO_Register;
import vo.UserVO;

import java.util.ArrayList;

/**
 * Created by Kray on 2016/10/30.
 */
public class Staff extends User {

    private UserDataService userDataService;
    private String account;
    private String password;

    public Staff() {
        this.userDataService = UserDataServiceFactory.getStaffDataService();
    }

    /**
     * 登录
     *
     * @param account
     * @param password
     * @return 当前登录状态
     */
    public LoginState login(String account, String password) {
        LoginState loginState = userDataService.login(account, password);
        if (loginState == LoginState.LOGIN_SUCCESS_Staff) {
            System.out.println("Login Staff");
            StaffPO staffPO = userDataService.searchStaff(account).get(0);
            super.setCurrentID(staffPO.getStaffID());
            this.account = account;
            this.password = password;
        }
        return loginState;
    }

    /**
     * 修改密码
     *
     * @param account
     * @param oldPassword
     * @param newPassword
     * @return
     */
    public ResetState resetPassword(String account, String oldPassword, String newPassword) {
        if (userDataService.resetPassword(account, oldPassword, newPassword) == ResultMessage.SUCCESS) {
            return ResetState.RESET_SUCCESS;
        } else {
            return ResetState.RESET_FAIL;
        }
    }

    /**
     * 增加酒店工作人员
     *
     * @param userVO
     * @return 是否增加成功
     */
    public ResultMessage add(UserVO userVO) {
        StaffVO_Register staffVO = (StaffVO_Register) userVO;
        StaffPO staffPO = new StaffPO(staffVO.staffID, staffVO.staffName, staffVO.hotelID, staffVO.account, staffVO.password);
        return userDataService.addStaff(staffPO);
    }

    /**
     * 根据ID查找酒店工作人员
     *
     * @param staffID
     * @return 符合ID的StaffVO
     */
    public StaffVO searchByID(String staffID) {
        StaffPO staffPO = userDataService.searchStaffByID(staffID);
        if (staffPO == null) {
            return null;
        } else {
            StaffVO staffVO = new StaffVO(staffPO.getStaffID(), staffPO.getStaffName(), staffPO.getHotelID() ,staffPO.getAccount());
            return staffVO;
        }
    }

    /**
     * 更新酒店工作人员
     *
     * @param userVO
     * @return 是否更新成功
     */
    public ResultMessage update(UserVO userVO) {
        StaffVO staffVO = (StaffVO) userVO;
        StaffPO tmpPO = userDataService.searchStaffByID(staffVO.staffID);
        StaffPO staffPO = new StaffPO(staffVO.staffID, staffVO.staffName, staffVO.hotelID, tmpPO.getAccount(), tmpPO.getPassword());
        return userDataService.updateStaff(staffVO.staffID, staffPO);
    }

    /**
     * 删除酒店工作人员
     *
     * @param staffID
     * @return 是否删除成功
     */
    public ResultMessage delete(String staffID) {
        return userDataService.deleteStaff(staffID);
    }

    /**
     * 根据关键词,搜索酒店工作人员
     *
     * @param keyword
     * @return 符合关键词的所有酒店工作人员
     */
    public ArrayList<StaffVO> search(String keyword) {
        ArrayList<StaffPO> staffPOs = userDataService.searchStaff(keyword);
        ArrayList<StaffVO> staffVOs = new ArrayList<StaffVO>();
        for (StaffPO staffPO : staffPOs) {
            staffVOs.add(new StaffVO(staffPO.getStaffID(), staffPO.getStaffName(), staffPO.getHotelID(), staffPO.getAccount()));
        }
        return staffVOs;
    }
}
