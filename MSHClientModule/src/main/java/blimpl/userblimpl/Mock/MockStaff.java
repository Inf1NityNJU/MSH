package blimpl.userblimpl.mock;

import blimpl.userblimpl.Staff;
import util.LoginState;
import util.ResultMessage;
import vo.StaffVO;

import java.util.ArrayList;

/**
 * Created by Kray on 2016/10/30.
 */
public class MockStaff extends Staff {

    private StaffVO exampleStaffVO;

    public MockStaff(){
        this.exampleStaffVO = new StaffVO("300001", "staff1", "25010001", "adminStaff");
    }

    /**
     * 登录
     *
     * @param account
     * @param password
     * @return
     */
    public LoginState login(String account, String password) {
        if (account.equals("adminStaff") && password.equals("12345678")) {
            super.setCurrentID(exampleStaffVO.staffID);
            return LoginState.LOGIN_SUCCESS_Staff;
        } else {
            return LoginState.LOGIN_FAIL;
        }
    }

    /**
     * 增加酒店工作人员
     *
     * @param StaffVO
     * @return
     */
    public ResultMessage add(StaffVO StaffVO) {
        if (!StaffVO.staffID.equals("300001")) {
            return ResultMessage.SUCCESS;
        } else {
            System.out.println("Staff exist");
            return ResultMessage.FAILED;
        }
    }

    /**
     * 根据ID查找酒店工作人员
     *
     * @param StaffID
     * @return
     */
    public StaffVO searchByID(String StaffID) {
        if (StaffID.equals("300001")) {
            return exampleStaffVO;
        } else {
            return null;
        }
    }

    /**
     * 更新酒店工作人员
     *
     * @param StaffVO
     * @return
     */
    public ResultMessage update(StaffVO StaffVO) {
        if (StaffVO.staffID.equals("300001")) {
            return ResultMessage.SUCCESS;
        } else {
            return ResultMessage.FAILED;
        }
    }

    /**
     * 删除酒店工作人员
     *
     * @param StaffID
     * @return
     */
    public ResultMessage delete(String StaffID) {
        if (StaffID.equals("300001")) {
            return ResultMessage.SUCCESS;
        } else {
            return ResultMessage.FAILED;
        }
    }

    /**
     * 根据关键词,搜索酒店工作人员
     *
     * @param keyword
     * @return
     */
    public ArrayList<StaffVO> search(String keyword) {
        StaffVO exampleStaffVO1 = new StaffVO("300011", "老大", "25010001", "adminStaff1");
        StaffVO exampleStaffVO2 = new StaffVO("300012", "老二", "25010002", "adminStaff2");
        StaffVO exampleStaffVO3 = new StaffVO("300013", "老三", "25010001", "adminStaff3");
        StaffVO exampleStaffVO4 = new StaffVO("300014", "老四", "25010003", "adminStaff4");
        ArrayList<StaffVO> StaffVOs = new ArrayList<StaffVO>();
        if (keyword.equals("3000")) {
            StaffVOs.add(exampleStaffVO1);
            StaffVOs.add(exampleStaffVO2);
            StaffVOs.add(exampleStaffVO3);
            StaffVOs.add(exampleStaffVO4);
        } else if (keyword.equals("老二")) {
            StaffVOs.add(exampleStaffVO2);
        } else if (keyword.equals("25010001")) {
            StaffVOs.add(exampleStaffVO1);
            StaffVOs.add(exampleStaffVO3);
        }
        return StaffVOs;
    }
}
