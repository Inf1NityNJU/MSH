package dataimpl.user;

import dataimpl.userdataimpl.UserDataServiceFactory;
import dataservice.userdataservice.UserDataService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import po.LevelPO;
import po.SalesmanPO;
import util.LoginState;
import util.ResultMessage;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Kray on 2016/11/18.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SalesmanDataServiceImplTest {

    private UserDataService userDataService;

    public SalesmanDataServiceImplTest() {
        userDataService = UserDataServiceFactory.getSalesmanDataService();

    }

    @Test
    public void a_addSalesman() throws Exception {
        ResultMessage resultMessage = userDataService.addSalesman(new SalesmanPO("100001", "testSalesman",
                "accountSalesman", "password"));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
        resultMessage = userDataService.addSalesman(new SalesmanPO("100001", "testSalesman2",
                "accountSalesman", "password"));
        assertEquals(ResultMessage.FAILED, resultMessage);
        resultMessage = userDataService.addSalesman(new SalesmanPO("100000", "",
                "accountSalesman", "password"));
        assertEquals(ResultMessage.FAILED, resultMessage);
    }

    @Test
    public void b_login() throws Exception {
        LoginState loginState = userDataService.login("accountSalesman", "password");
        assertEquals(LoginState.LOGIN_SUCCESS_Salesman, loginState);
        loginState = userDataService.login("accountSalesman2", "password");
        assertEquals(LoginState.LOGIN_FAIL, loginState);
    }

    @Test
    public void c_resetPassword() throws Exception {
        ResultMessage resultMessage = userDataService.resetPassword("accountSalesman", "password", "00000000");
        assertEquals(ResultMessage.SUCCESS, resultMessage);
        resultMessage = userDataService.resetPassword("accountSalesman", "password", "password");
        assertEquals(ResultMessage.FAILED, resultMessage);
    }

    @Test
    public void d_searchSalesmanByID() throws Exception {
        SalesmanPO examplePO = new SalesmanPO("100001", "testSalesman",
                "accountSalesman", "00000000");
        SalesmanPO salesmanPO = userDataService.searchSalesmanByID("100001");
        assertTrue(salesmanPO.equals(examplePO));
    }

    @Test
    public void e_updateSalesman() throws Exception {
        ResultMessage resultMessage = userDataService.updateSalesman("100001",
                new SalesmanPO("100001", "testSalesman2", "accountSalesman", "password"));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
        resultMessage = userDataService.updateSalesman("100002",
                new SalesmanPO("100002", "testSalesman", "", "password"));
        assertEquals(ResultMessage.NOT_EXIST, resultMessage);
    }

    @Test
    public void f_searchSalesman() throws Exception {
        ArrayList<SalesmanPO> salesmanPOs = userDataService.searchSalesman("100");
        ArrayList<SalesmanPO> exampleSalesmanPOs = new ArrayList<SalesmanPO>();
        exampleSalesmanPOs.add(new SalesmanPO("100001", "testSalesman2", "accountSalesman", "password"));
        assertEquals(exampleSalesmanPOs, salesmanPOs);
    }

    @Test
    public void g_addLevel() throws Exception {
        LevelPO levelPO = new LevelPO("1",1,500);
        ResultMessage resultMessage = userDataService.addLevel(levelPO);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
        levelPO = new LevelPO("1",1,600);
        resultMessage = userDataService.addLevel(levelPO);
        assertEquals(ResultMessage.EXIST, resultMessage);
    }

    @Test
    public void h_updateLevel() throws Exception {
        LevelPO levelPO = new LevelPO("1",1,700);
        ResultMessage resultMessage = userDataService.updateLevel(levelPO.getID(), levelPO);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }


    @Test
    public void i_getLevel() throws Exception{
        LevelPO levelPO = userDataService.getLevel("1");
        assertEquals(new LevelPO("1",1,700), levelPO);
    }

    @Test
    public void j_deleteLevel() throws Exception {
        ResultMessage rm = userDataService.deleteLevel("1");
        assertEquals(ResultMessage.SUCCESS, rm);
        rm = userDataService.deleteLevel("2");
        assertEquals(ResultMessage.NOT_EXIST, rm);
    }

    @Test
    public void k_deleteSalesman() throws Exception {
        ResultMessage resultMessage = userDataService.deleteSalesman("100001");
        assertEquals(ResultMessage.SUCCESS, resultMessage);
        resultMessage = userDataService.deleteSalesman("100110");
        assertEquals(ResultMessage.NOT_EXIST, resultMessage);
    }

    @Test
    public void l_logout() throws Exception {
        LoginState loginState = userDataService.logout();
        assertEquals(LoginState.LOGOUT, loginState);
    }
}
