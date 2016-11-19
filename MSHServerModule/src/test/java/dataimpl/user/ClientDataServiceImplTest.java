package dataimpl.user;

import dataimpl.userdataimpl.UserDataServiceFactory;
import dataservice.userdataservice.UserDataService;
import org.junit.Test;
import po.ClientPO;
import po.CreditPO;
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
        LoginState loginState = userDataService.login("adminClient", "12345678");
        assertEquals(LoginState.LOGIN_SUCCESS_Client, loginState);
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
<<<<<<< HEAD
        ResultMessage resultMessage = userDataService.addClient(new ClientPO("000000007", "KrayC", 500, 1, new DateUtil(2015, 10, 10),
                "18795963603", "no_enterprise", "songkuixi", "123456"));
=======
        ResultMessage resultMessage = userDataService.addClient(new ClientPO("000000003", "songkuixi", 500, 0,
                "2016-02-02", "18795963603", "NO", "adminClient", "12345678"), new CreditPO("000000003"));
>>>>>>> 9ce127d88bc54ad432688c8a90de443810c1e426
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void searchClientByID() throws Exception {

    }

    @Test
    public void updateClient() throws Exception {

    }

    @Test
    public void deleteClient() throws Exception {
        ResultMessage resultMessage = userDataService.deleteClient("000000003");
        assertEquals(ResultMessage.SUCCESS, resultMessage);
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