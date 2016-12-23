package dataimpl.user;

import dataimpl.userdataimpl.UserDataServiceFactory;
import dataservice.userdataservice.UserDataService;
import org.junit.Test;
import po.StaffPO;
import util.LoginState;
import util.ResultMessage;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Kray on 2016/11/17.
 */
public class StaffDataServiceImplTest {

    private UserDataService userDataService;

    public StaffDataServiceImplTest() {
        userDataService = UserDataServiceFactory.getStaffDataService();
    }

    @Test
    public void login() throws Exception {
        LoginState loginState = userDataService.login("accountStaff", "password");
        assertEquals(LoginState.LOGIN_SUCCESS_Staff, loginState);
        loginState = userDataService.login("accountStaff", "password2");
        assertEquals(LoginState.LOGIN_FAIL, loginState);
    }

    @Test
    public void logout() throws Exception {
        LoginState loginState = userDataService.logout();
        assertEquals(LoginState.LOGOUT, loginState);
    }

    @Test
    public void resetPassword() throws Exception {
        ResultMessage resultMessage = userDataService.resetPassword("accountStaff", "password", "12345678");
        assertEquals(ResultMessage.SUCCESS, resultMessage);
        resultMessage = userDataService.resetPassword("accountStaff", "password2", "password");
        assertEquals(ResultMessage.FAILED, resultMessage);
    }

    @Test
    public void addStaff() throws Exception {
        ResultMessage resultMessage = userDataService.addStaff(new StaffPO("300111", "testStaff", "25010001", "accountStaff", "password"));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
        resultMessage = userDataService.addStaff(new StaffPO("300111", "testStaff2", "25010001", "adminStaff", "password"));
        assertEquals(ResultMessage.EXIST, resultMessage);
    }

    @Test
    public void searchStaffByID() throws Exception {
        StaffPO examplePO = new StaffPO("300111", "testStaff", "25010001", "accountStaff", "password");
        StaffPO staffPO = userDataService.searchStaffByID("300111");
        assertTrue(staffPO.equals(examplePO));
    }

    @Test
    public void updateStaff() throws Exception {
        ResultMessage resultMessage = userDataService.updateStaff("300111",
                new StaffPO("300111", "testStaff2", "25010002", "adminStaff", "password"));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
        resultMessage = userDataService.updateStaff("300111",
                new StaffPO("300111", "testStaff", "25010001", "adminStaff", "password"));
        assertEquals(ResultMessage.FAILED, resultMessage);
    }

    @Test
    public void deleteStaff() throws Exception {
        ResultMessage resultMessage = userDataService.deleteStaff("300111");
        assertEquals(ResultMessage.SUCCESS, resultMessage);
        resultMessage = userDataService.deleteStaff("300110");
        assertEquals(ResultMessage.FAILED, resultMessage);
    }

    @Test
    public void searchStaff() throws Exception {
        ArrayList<StaffPO> staffPOs = userDataService.searchStaff("3000");
        ArrayList<StaffPO> exampleStaffPOs = new ArrayList<StaffPO>();
        exampleStaffPOs.add(new StaffPO("300111", "testStaff2", "25010002", "adminStaff", "password"));
        assertEquals(exampleStaffPOs, staffPOs);
    }

    @Test
    public void getStaffByHotelID() throws Exception{
        StaffPO staffPO = userDataService.getStaffByHotelID("25010001");
        assertEquals(new StaffPO("300111", "testStaff", "25010001", "adminStaff", "password"), staffPO);
    }

}