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
        LoginState loginState = userDataService.login("accountClient", "password");
        assertEquals(LoginState.LOGIN_SUCCESS_Client, loginState);
        loginState = userDataService.login("accountClient", "password2");
        assertEquals(LoginState.LOGIN_FAIL, loginState);
    }

    @Test
    public void logout() throws Exception {
        LoginState loginState = userDataService.logout();
        assertEquals(LoginState.LOGOUT, loginState);
    }

    @Test
    public void resetPassword() throws Exception {
        ResultMessage resultMessage = userDataService.resetPassword("accountClient", "password", "00000000");
        assertEquals(ResultMessage.SUCCESS, resultMessage);
        resultMessage = userDataService.resetPassword("accountClient", "password", "password");
        assertEquals(ResultMessage.FAILED, resultMessage);
    }

    @Test
    public void addClient() throws Exception {
        ResultMessage resultMessage = userDataService.addClient(new ClientPO("000000111", "testClient", 500,
                "1996-04-25", "18795963603", "Sina", "testClient", "password"));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
        resultMessage = userDataService.addClient(new ClientPO("000000111", "testClient2", 500,
                "1996-04-25", "18795963603", "Sina", "testClient", "password"));
        assertEquals(ResultMessage.EXIST, resultMessage);
    }

    @Test
    public void searchClientByID() throws Exception {
        ClientPO examplePO = new ClientPO("000000111", "testClient", 500,
                "1996-04-25", "18795963603", "Sina", "testClient", "password");
        ClientPO clientPO = userDataService.searchClientByID("000000111");
        assertTrue(clientPO.equals(examplePO));
    }

    @Test
    public void updateClient() throws Exception {
        ResultMessage resultMessage = userDataService.updateClient("000000111", new ClientPO("000000111", "testClient2", 500,
                "1996-04-25", "18795963603", "Sina", "testClient", "password"));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
        resultMessage = userDataService.updateClient("000000110", new ClientPO("000000111", "testClient", 500,
                "1996-04-25", "18795963603", "Sina", "testClient", "password"));
        assertEquals(ResultMessage.FAILED, resultMessage);
    }

    @Test
    public void deleteClient() throws Exception {
        ResultMessage resultMessage = userDataService.deleteClient("000000002");
        assertEquals(ResultMessage.SUCCESS, resultMessage);
//        resultMessage = userDataService.deleteClient("000000110");
//        assertEquals(ResultMessage.FAILED, resultMessage);
    }

    @Test
    public void searchClient() throws Exception {
        ArrayList<ClientPO> clientPOs = userDataService.searchClient("000");
        ArrayList<ClientPO> exampleClientPOs = new ArrayList<ClientPO>();
        exampleClientPOs.add(new ClientPO("000000111", "testClient2", 500,
                "1996-04-25", "18795963603", "Sina", "testClient", "password"));
        assertEquals(exampleClientPOs, clientPOs);
    }

    @Test
    public void addCreditRecord() throws Exception {
//        ResultMessage resultMessage = userDataService.addCreditRecord("000000111", new CreditPO("20161013000012345678",
//                "2016-10-12", 200, 700, CreditAction.ADD_CREDIT, "000000111"));
//        assertEquals(ResultMessage.SUCCESS, resultMessage);
        ResultMessage resultMessage = userDataService.addCreditRecord("000000111", new CreditPO("20161013000012344618",
                "2016-10-12", -400, 700, CreditAction.ADD_CREDIT, "000000111"));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
//        resultMessage = userDataService.addCreditRecord("000000110", new CreditPO("20161013000012345679",
//                "2016-10-13", 200, 700, CreditAction.ADD_CREDIT, "000000111"));
//        assertEquals(ResultMessage.FAILED, resultMessage);
    }

    @Test
    public void searchCreditByID() throws Exception {
        ArrayList<CreditPO> creditPOs = userDataService.searchCreditByID("000000111");
        ArrayList<CreditPO> exampleCreditPOs = new ArrayList<CreditPO>();
        exampleCreditPOs.add(new CreditPO("20161013000012345678",
                "2016-10-12", 200, 700, CreditAction.ADD_CREDIT, "000000003"));
        assertEquals(exampleCreditPOs, creditPOs);
    }

    @Test
    public void getLevelByCredit() throws Exception{
        int level = userDataService.getLevelByCredit(600);
        assertEquals(1, level);
    }

}