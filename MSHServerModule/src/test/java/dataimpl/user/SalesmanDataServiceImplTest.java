package dataimpl.user;

import dataimpl.userdataimpl.UserDataServiceFactory;
import dataservice.userdataservice.UserDataService;
import org.junit.Test;
import po.LevelPO;
import po.SalesmanPO;
import util.LoginState;
import util.ResultMessage;

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
        LoginState loginState = userDataService.login("adminSalesman", "password");
        assertEquals(LoginState.LOGIN_SUCCESS_Salesman, loginState);
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
    public void addSalesman() throws Exception {
        ResultMessage resultMessage = userDataService.addSalesman(new SalesmanPO("100001", "KrayC", "adminSalesman", "password"));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void searchSalesmanByID() throws Exception {
        SalesmanPO examplePO = new SalesmanPO("100001", "KrayC", "adminSalesman", "password");
        SalesmanPO salesmanPO = userDataService.searchSalesmanByID("100001");
        assertTrue(salesmanPO.equals(examplePO));
//        salesmanPO = userDataService.searchSalesmanByID("100002");
//        assertFalse(salesmanPO.equals(examplePO));
    }

    @Test
    public void updateSalesman() throws Exception {
        ResultMessage resultMessage = userDataService.updateSalesman("100001", new SalesmanPO("100001", "wang ziqin", "adminSalesman", "password"));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void deleteSalesman() throws Exception {
        ResultMessage resultMessage = userDataService.deleteSalesman("100001");
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void searchSalesman() throws Exception {

    }

}