package dataimpl.User;

import dataservice.userdataservice.UserDataService;
import org.junit.Test;
import po.StaffPO;
import util.ResultMessage;

import static org.junit.Assert.*;

/**
 * Created by Kray on 2016/11/17.
 */
public class UserDataServiceImplTest {

    private UserDataService userDataService;

    public UserDataServiceImplTest() {
        userDataService = UserDataServiceFactory.getUserDataService();
    }

    @Test
    public void addStaff() throws Exception {
        ResultMessage resultMessage = userDataService.addStaff(new StaffPO("100001", "KrayC", "25010001", "songkuixi", "123456"));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

}