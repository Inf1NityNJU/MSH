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
        LoginState loginState = userDataService.login("adminStaff", "password");
        assertEquals(LoginState.LOGIN_SUCCESS_Staff, loginState);
    }

    @Test
    public void logout() throws Exception {
        LoginState loginState = userDataService.logout();
        assertEquals(LoginState.LOGOUT, loginState);
    }

    @Test
    public void resetPassword() throws Exception {

    }

    @Test
    public void addStaff() throws Exception {
        ResultMessage resultMessage = userDataService.addStaff(new StaffPO("300001", "songkuixi", "25010001", "adminStaff", "password"));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
//        resultMessage = userDataService.addStaff(new StaffPO("300002", "songkuixi", "25010001", "adminStaff", "password"));
//        assertEquals(ResultMessage.SUCCESS, resultMessage);
//        resultMessage = userDataService.addStaff(new StaffPO("300003", "songkuixi", "25010001", "adminStaff", "password"));
//        assertEquals(ResultMessage.SUCCESS, resultMessage);
//        resultMessage = userDataService.addStaff(new StaffPO("300004", "songkuixi", "25010001", "adminStaff", "password"));
//        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void searchStaffByID() throws Exception {
        StaffPO examplePO = new StaffPO("300001", "songkuixi", "25010001", "adminStaff", "password");
        StaffPO staffPO = userDataService.searchStaffByID("300001");
        assertTrue(staffPO.equals(examplePO));
        staffPO = userDataService.searchStaffByID("300002");
        assertFalse(staffPO.equals(examplePO));
    }

    @Test
    public void updateStaff() throws Exception {
        ResultMessage resultMessage = userDataService.updateStaff("300001", new StaffPO("300001", "Kray2", "25010002", "adminStaff", "password"));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void deleteStaff() throws Exception {
        ResultMessage resultMessage = userDataService.deleteStaff("300001");
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void searchStaff() throws Exception {
        ArrayList<StaffPO> staffPOs = userDataService.searchStaff("1000");
        ArrayList<StaffPO> exampleStaffPOs = new ArrayList<StaffPO>();
        exampleStaffPOs.add(new StaffPO("300001", "songkuixi", "25010001", "adminStaff", "password"));
        exampleStaffPOs.add(new StaffPO("300002", "songkuixi", "25010001", "adminStaff", "password"));
        exampleStaffPOs.add(new StaffPO("300003", "songkuixi", "25010001", "adminStaff", "password"));
        exampleStaffPOs.add(new StaffPO("300004", "songkuixi", "25010001", "adminStaff", "password"));
        assertEquals(exampleStaffPOs, staffPOs);
    }

}