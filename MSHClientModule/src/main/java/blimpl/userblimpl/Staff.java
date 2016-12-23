package blimpl.userblimpl;

import po.StaffPO;
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

    public Staff() {
        super();
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
        if (userClientNetwork.resetPassword(account, oldPassword, newPassword) == ResultMessage.SUCCESS) {
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
        StaffPO staffPO = new StaffPO(
                null,
                staffVO.staffName,
                staffVO.hotelID,
                staffVO.account,
                staffVO.password);
        return userClientNetwork.addStaff(staffPO);
    }

    /**
     * 根据ID查找酒店工作人员
     *
     * @param staffID
     * @return 符合ID的StaffVO
     */
    public StaffVO searchByID(String staffID) {
        StaffPO staffPO = super.userClientNetwork.searchStaffByID(staffID);
        if (staffPO == null) {
            return null;
        } else {
            return new StaffVO(
                    staffPO.getStaffID(),
                    staffPO.getStaffName(),
                    staffPO.getHotelID(),
                    staffPO.getAccount());
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
        StaffPO tmpPO = userClientNetwork.searchStaffByID(staffVO.staffID);
        StaffPO staffPO = new StaffPO(
                staffVO.staffID,
                staffVO.staffName,
                staffVO.hotelID,
                tmpPO.getAccount(),
                tmpPO.getPassword());
        return userClientNetwork.updateStaff(staffVO.staffID, staffPO);
    }

    /**
     * 删除酒店工作人员
     *
     * @param staffID
     * @return 是否删除成功
     */
    public ResultMessage delete(String staffID) {
        return userClientNetwork.deleteStaff(staffID);
    }

    /**
     * 根据关键词,搜索酒店工作人员
     *
     * @param keyword
     * @return 符合关键词的所有酒店工作人员
     */
    public ArrayList<StaffVO> search(String keyword) {
        ArrayList<StaffPO> staffPOs = userClientNetwork.searchStaff(keyword);
        ArrayList<StaffVO> staffVOs = new ArrayList<StaffVO>();
        staffPOs.forEach(staffPO -> staffVOs.add(
                new StaffVO(
                        staffPO.getStaffID(),
                        staffPO.getStaffName(),
                        staffPO.getHotelID(),
                        staffPO.getAccount())));
        return staffVOs;
    }

    /**
     * 得到酒店工作人员对应的酒店ID
     *
     * @param ID
     * @return
     */
    public String getHotelIDByStaffID(String ID) {
        return userClientNetwork.searchStaffByID(ID).getHotelID();
    }

    /**
     * 通过酒店ID得到酒店工作人员
     *
     * @param hotelID
     * @return
     */
    public StaffVO getStaffByHotelID(String hotelID) {
        StaffPO staffPO = super.userClientNetwork.getStaffByHotelID(hotelID);
        if (staffPO == null) {
            return null;
        } else {
            return new StaffVO(
                    staffPO.getStaffID(),
                    staffPO.getStaffName(),
                    staffPO.getHotelID(),
                    staffPO.getAccount());
        }
    }
}
