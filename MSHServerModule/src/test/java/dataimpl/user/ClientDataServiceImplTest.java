package dataimpl.user;

import dataimpl.userdataimpl.UserDataServiceFactory;
import dataservice.userdataservice.UserDataService;
import org.junit.Test;
import po.ClientPO;
import po.CreditPO;
import util.CreditAction;
import util.LoginState;
import util.ResultMessage;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        ResultMessage resultMessage = userDataService.addClient(new ClientPO("000000003", "songkuixi", 500, 0,
                "2016-02-02", "18795963603", "NO", "adminClient", "12345678"), new CreditPO("000000003"));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void searchClientByID() throws Exception {
        ClientPO examplePO = new ClientPO("000000003", "songkuixi", 500, 0,
                "2016-02-02", "18795963603", "NO", "adminClient", "12345678");
        ClientPO clientPO = userDataService.searchClientByID("000000003");
        assertTrue(clientPO.equals(examplePO));
    }

    @Test
    public void updateClient() throws Exception {
        ResultMessage resultMessage = userDataService.updateClient("000000003", new ClientPO("000000003", "songkuixi 2", 500, 0,
                "2016-02-02", "18795963603", "NO", "adminClient", "12345678"));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
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
        ResultMessage resultMessage = userDataService.addCreditRecord("000000003", new CreditPO("20161013000012345678", "2016-10-12", 200, 600, CreditAction.ADD_CREDIT, "000000003"));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void searchCreditByID() throws Exception {
        ArrayList<CreditPO> creditPOs = userDataService.searchCreditByID("000000003");
        ArrayList<CreditPO> exampleCreditPOs = new ArrayList<CreditPO>();
        exampleCreditPOs.add(new CreditPO("20161013000012345678", "2016-10-12", 200, 600, CreditAction.ADD_CREDIT, "000000003"));
        assertEquals(exampleCreditPOs, creditPOs);
    }

}