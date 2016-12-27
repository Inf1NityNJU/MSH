package dataimpl.user;

import dataimpl.userdataimpl.UserDataServiceFactory;
import dataservice.userdataservice.UserDataService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import po.StaffPO;
import util.LoginState;
import util.ResultMessage;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Kray on 2016/11/17.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StaffDataServiceImplTest {

    private UserDataService userDataService = UserDataServiceFactory.getStaffDataService();

    public StaffDataServiceImplTest() {
        userDataService = UserDataServiceFactory.getStaffDataService();
    }

    @Test
    public void a_addStaff() throws Exception {
        userDataService = UserDataServiceFactory.getStaffDataService();
        ResultMessage resultMessage = userDataService.addStaff(new StaffPO("300001", "testStaff", "25010001", "accountStaff", "password"));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
        resultMessage = userDataService.addStaff(new StaffPO("300001", "testStaff2", "25010001", "accountStaff", "password"));
        assertEquals(ResultMessage.FAILED, resultMessage);
    }

    @Test
    public void b_login() throws Exception {
        LoginState loginState = userDataService.login("accountStaff", "password");
        assertEquals(LoginState.LOGIN_SUCCESS_Staff, loginState);
        loginState = userDataService.login("accountStaff", "password2");
        assertEquals(LoginState.LOGIN_FAIL, loginState);
    }

    @Test
    public void c_resetPassword() throws Exception {
        ResultMessage resultMessage = userDataService.resetPassword("accountStaff", "password", "12345678");
        assertEquals(ResultMessage.SUCCESS, resultMessage);
        resultMessage = userDataService.resetPassword("accountStaff", "password2", "password");
        assertEquals(ResultMessage.FAILED, resultMessage);
    }

    @Test
    public void d_searchStaffByID() throws Exception {
        StaffPO examplePO = new StaffPO("300001", "testStaff", "25010001", "accountStaff", "12345678");
        StaffPO staffPO = userDataService.searchStaffByID("300001");
        assertTrue(staffPO.equals(examplePO));
    }

    @Test
    public void e_updateStaff() throws Exception {
        ResultMessage resultMessage = userDataService.updateStaff("300001",
                new StaffPO("300001", "testStaff2", "25010002", "adminStaff", "12345678"));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
        resultMessage = userDataService.updateStaff("300111",
                new StaffPO("300111", "testStaff", "25010001", "adminStaff2", "12345678"));
        assertEquals(ResultMessage.NOT_EXIST, resultMessage);
    }

    @Test
    public void f_searchStaff() throws Exception {
        ArrayList<StaffPO> staffPOs = userDataService.searchStaff("3000");
        ArrayList<StaffPO> exampleStaffPOs = new ArrayList<StaffPO>();
        exampleStaffPOs.add(new StaffPO("300001", "testStaff2", "25010002", "adminStaff", "12345678"));
        assertEquals(exampleStaffPOs, staffPOs);
    }

    @Test
    public void g_getStaffByHotelID() throws Exception{
        StaffPO staffPO = userDataService.getStaffByHotelID("25010002");
        assertEquals(new StaffPO("300001", "testStaff2", "25010002", "adminStaff", "12345678"), staffPO);
    }

    @Test
    public void h_deleteStaff() throws Exception {
        ResultMessage resultMessage = userDataService.deleteStaff("300001");
        assertEquals(ResultMessage.SUCCESS, resultMessage);
        resultMessage = userDataService.deleteStaff("300110");
        assertEquals(ResultMessage.NOT_EXIST, resultMessage);
    }


    @Test
    public void i_logout() throws Exception {
        LoginState loginState = userDataService.logout();
        assertEquals(LoginState.LOGOUT, loginState);
    }
}