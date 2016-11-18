package dataimpl.User;

import com.sun.org.apache.regexp.internal.RE;
import dataservice.userdataservice.UserDataService;
import org.junit.Test;
import po.StaffPO;
import util.ResultMessage;

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
    public void addStaff() throws Exception {
        ResultMessage resultMessage = userDataService.addStaff(new StaffPO("100001", "KrayC", "25010001", "songkuixi", "123456"));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void searchStaffByID() throws Exception {
    }

    @Test
    public void updateStaff() throws Exception {
        ResultMessage resultMessage = userDataService.updateStaff("100001", new StaffPO("100001", "KrayC2", "25010002", "songkuixi", "123456"));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void deleteStaff() throws Exception {
        ResultMessage resultMessage = userDataService.deleteStaff("100001");
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void searchStaff() throws Exception {

    }

    @Test
    public void login() throws Exception {

    }

    @Test
    public void logout() throws Exception {

    }

    @Test
    public void resetPassword() throws Exception {

    }

}