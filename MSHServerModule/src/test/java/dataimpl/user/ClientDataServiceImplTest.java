package dataimpl.user;

import dataimpl.userdataimpl.UserDataServiceFactory;
import dataservice.userdataservice.UserDataService;
import org.junit.Test;
import po.ClientPO;
import util.DateUtil;
import util.LoginState;
import util.ResultMessage;

import static org.junit.Assert.assertEquals;

/**
 * Created by Kray on 2016/11/18.
 */
public class ClientDataServiceImplTest {


    private UserDataService userDataService;

    public ClientDataServiceImplTest() {
        userDataService = UserDataServiceFactory.getClientDataService();
    }

    @Test
    public void login() throws Exception {

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
    public void addClient() throws Exception {
//        ResultMessage resultMessage = userDataService.addClient(new ClientPO("000000007", "KrayC", 500, 1, new DateUtil(2015, 10, 10),
//                "18795963603", "no_enterprise", "songkuixi", "123456"));
//        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void searchClientByID() throws Exception {

    }

    @Test
    public void updateClient() throws Exception {

    }

    @Test
    public void deleteClient() throws Exception {

    }

    @Test
    public void searchClient() throws Exception {

    }

    @Test
    public void addCreditRecord() throws Exception {

    }

    @Test
    public void searchCreditByID() throws Exception {

    }

}