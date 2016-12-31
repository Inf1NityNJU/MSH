package dataimpl.user;

import dataimpl.userdataimpl.UserDataServiceFactory;
import dataservice.userdataservice.UserDataService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import po.ClientPO;
import po.CreditPO;
import po.LevelPO;
import util.CreditAction;
import util.DateUtil;
import util.LoginState;
import util.ResultMessage;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Kray on 2016/11/18.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClientDataServiceImplTest {

    private UserDataService userDataService = UserDataServiceFactory.getClientDataService();;

    public ClientDataServiceImplTest() {
        userDataService = UserDataServiceFactory.getClientDataService();
    }

    @Test
    public void a_addClient() throws Exception {
        ResultMessage resultMessage = userDataService.addClient(new ClientPO("000000001", "testClient", 500,
                "1996-04-25", "18795963603", "Sina", "testClient", "password"));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
        resultMessage = userDataService.addClient(new ClientPO("000000001", "testClient2", 500,
                "1996-04-25", "18795963603", "Sina", "testClient", "password"));
        assertEquals(ResultMessage.EXIST, resultMessage);
    }

    @Test
    public void b_login() throws Exception {
        LoginState loginState = userDataService.login("testClient", "password");
        assertEquals(LoginState.LOGIN_SUCCESS_Client, loginState);
        loginState = userDataService.login("testClient", "password2");
        assertEquals(LoginState.LOGIN_FAIL, loginState);
    }

    @Test
    public void c_resetPassword() throws Exception {
        ResultMessage resultMessage = userDataService.resetPassword("testClient", "password", "00000000");
        assertEquals(ResultMessage.SUCCESS, resultMessage);
        resultMessage = userDataService.resetPassword("testClient", "password", "password");
        assertEquals(ResultMessage.FAILED, resultMessage);
    }

    @Test
    public void d_searchClientByID() throws Exception {
        ClientPO examplePO = new ClientPO("000000001", "testClient", 500,
                "1996-04-25", "18795963603", "Sina", "testClient", "00000000");
        ClientPO clientPO = userDataService.searchClientByID("000000001");
        assertTrue(clientPO.equals(examplePO));
    }

    @Test
    public void e_updateClient() throws Exception {
        ResultMessage resultMessage = userDataService.updateClient("000000001", new ClientPO("000000001", "testClient2", 500,
                "1996-04-25", "18795963603", "Sina", "testClient", "00000000"));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
        resultMessage = userDataService.updateClient("000000002", new ClientPO("000000002", "testClient", 500,
                "1996-04-25", "18795963603", "Sina", "testClient", "00000000"));
        assertEquals(ResultMessage.NOT_EXIST, resultMessage);
    }

    @Test
    public void f_searchClient() throws Exception {
        ArrayList<ClientPO> clientPOs = userDataService.searchClient("000");
        ArrayList<ClientPO> exampleClientPOs = new ArrayList<ClientPO>();
        exampleClientPOs.add(new ClientPO("000000001", "testClient2", 500,
                "1996-04-25", "18795963603", "Sina", "testClient", "00000000"));
        assertEquals(exampleClientPOs, clientPOs);
    }

    @Test
    public void g_addCreditRecord() throws Exception {
        LevelPO levelPO = new LevelPO("1",1,500);
        ResultMessage resultMessage = userDataService.addLevel(levelPO);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
        resultMessage = userDataService.addCreditRecord("000000001", new CreditPO("20161013000012344618",
                "2016-10-12", 200, 700, CreditAction.ADD_CREDIT, "000000001"));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void h_searchCreditByID() throws Exception {
        ArrayList<CreditPO> creditPOs = userDataService.searchCreditByID("000000001");
        ArrayList<CreditPO> exampleCreditPOs = new ArrayList<CreditPO>();
        exampleCreditPOs.add(new CreditPO("-000000001", new DateUtil().toString(), 0, 500, CreditAction.INIT_CREDIT, "000000001"));
        exampleCreditPOs.add(new CreditPO("20161013000012344618",
                "2016-10-12", 200, 700, CreditAction.ADD_CREDIT, "000000001"));
        assertEquals(exampleCreditPOs, creditPOs);
    }

    @Test
    public void i_getLevelByCredit() throws Exception{

        int level = userDataService.getLevelByCredit(600);
        assertEquals(1, level);

        ResultMessage rm = userDataService.deleteLevel("1");
        assertEquals(ResultMessage.SUCCESS, rm);
    }

    @Test
    public void j_deleteClient() throws Exception {
        ResultMessage resultMessage = userDataService.deleteClient("000000001");
        assertEquals(ResultMessage.SUCCESS, resultMessage);
//        resultMessage = userDataService.deleteClient("000000110");
//        assertEquals(ResultMessage.FAILED, resultMessage);
    }

    @Test
    public void k_logout() throws Exception {
        LoginState loginState = userDataService.logout();
        assertEquals(LoginState.LOGOUT, loginState);
    }
}