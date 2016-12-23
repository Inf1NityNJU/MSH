package dataimpl.user;

import dataimpl.userdataimpl.UserDataServiceFactory;
import dataservice.userdataservice.UserDataService;
import org.junit.Test;
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
public class SalesmanDataServiceImplTest {

    private UserDataService userDataService;

    public SalesmanDataServiceImplTest() {
        userDataService = UserDataServiceFactory.getSalesmanDataService();
    }

    @Test
    public void login() throws Exception {
        LoginState loginState = userDataService.login("accountSalesman", "password");
        assertEquals(LoginState.LOGIN_SUCCESS_Salesman, loginState);
        loginState = userDataService.login("accountSalesman", "password");
        assertEquals(LoginState.LOGIN_FAIL, loginState);
    }

    @Test
    public void logout() throws Exception {
        LoginState loginState = userDataService.logout();
        assertEquals(LoginState.LOGOUT, loginState);
    }

    @Test
    public void resetPassword() throws Exception {
        ResultMessage resultMessage = userDataService.resetPassword("adminSalesman", "password", "00000000");
        assertEquals(ResultMessage.SUCCESS, resultMessage);
        resultMessage = userDataService.resetPassword("adminSalesman", "password", "password");
        assertEquals(ResultMessage.EXIST, resultMessage);
    }

    @Test
    public void addSalesman() throws Exception {
        ResultMessage resultMessage = userDataService.addSalesman(new SalesmanPO("100111", "testSalesman",
                "accountSalesman", "password"));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
        resultMessage = userDataService.addSalesman(new SalesmanPO("100111", "testSalesman2",
                "accountSalesman", "password"));
        assertEquals(ResultMessage.EXIST, resultMessage);
        resultMessage = userDataService.addSalesman(new SalesmanPO("100110", "",
                "accountSalesman", "password"));
        assertEquals(ResultMessage.FAILED, resultMessage);
    }

    @Test
    public void searchSalesmanByID() throws Exception {
        SalesmanPO examplePO = new SalesmanPO("100111", "testSalesman",
                "accountSalesman", "password");
        SalesmanPO salesmanPO = userDataService.searchSalesmanByID("100111");
        assertTrue(salesmanPO.equals(examplePO));
    }

    @Test
    public void updateSalesman() throws Exception {
        ResultMessage resultMessage = userDataService.updateSalesman("100111",
                new SalesmanPO("100111", "testSalesman2", "accountSalesman", "password"));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
        resultMessage = userDataService.updateSalesman("100111",
                new SalesmanPO("100111", "testSalesman", "", "password"));
        assertEquals(ResultMessage.FAILED, resultMessage);
    }

    @Test
    public void deleteSalesman() throws Exception {
        ResultMessage resultMessage = userDataService.deleteSalesman("100111");
        assertEquals(ResultMessage.SUCCESS, resultMessage);
        resultMessage = userDataService.deleteSalesman("100110");
        assertEquals(ResultMessage.FAILED, resultMessage);
    }

    @Test
    public void searchSalesman() throws Exception {
        ArrayList<SalesmanPO> salesmanPOs = userDataService.searchSalesman("100");
        ArrayList<SalesmanPO> exampleSalesmanPOs = new ArrayList<SalesmanPO>();
        exampleSalesmanPOs.add(new SalesmanPO("100111", "testSalesman2", "accountSalesman", "password"));
        assertEquals(exampleSalesmanPOs, salesmanPOs);
    }

    @Test
    public void addLevel() throws Exception {
        LevelPO levelPO = new LevelPO("1",1,500);
        ResultMessage resultMessage = userDataService.addLevel(levelPO);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
        levelPO = new LevelPO("1",1,600);
        resultMessage = userDataService.addLevel(levelPO);
        assertEquals(ResultMessage.EXIST, resultMessage);
    }

    @Test
    public void updateLevel() throws Exception {
        LevelPO levelPO = new LevelPO("1",1,700);
        ResultMessage resultMessage = userDataService.updateLevel(levelPO.getID(), levelPO);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void deleteLevel() throws Exception {
        ResultMessage rm = userDataService.deleteLevel("1");
        assertEquals(ResultMessage.SUCCESS, rm);
        rm = userDataService.deleteLevel("2");
        assertEquals(ResultMessage.FAILED, rm);
    }

    @Test
    public void getLevel() throws Exception{
        LevelPO levelPO = userDataService.getLevel("1");
        assertEquals(new LevelPO("1",1,500), levelPO);
    }

}
