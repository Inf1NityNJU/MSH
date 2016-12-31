package user;

import blimpl.blfactory.BLFactoryImpl;
import blimpl.userblimpl.Client;
import blimpl.userblimpl.Salesman;
import blservice.userblservice.UserBLInfo;
import org.junit.*;
import org.junit.runners.MethodSorters;
import util.*;
import vo.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Kray on 2016/11/6.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClientTest {
    private Client client;
    private static Salesman salesman;

    public ClientTest() {
        client = new Client();
    }

    @BeforeClass
    public static void setUp(){
        salesman = new Salesman();
        salesman.addLevel(new LevelVO("1", "500"));
    }

    @Test
    public void a_testAddClient() throws Exception {
        ResultMessage rm = client.add(new ClientVO_Register(
                new DateUtil(2016, 1, 1), 0, "", "adminClient", "123456789"));
        assertEquals(ResultMessage.SUCCESS, rm);
    }

    @Test
    public void b_testLogin() throws Exception {
        LoginState ls = client.login("adminClient", "123456789");
        assertEquals(LoginState.LOGIN_SUCCESS_Client, ls);
        ls = client.login("adminClient", "00000000");
        assertEquals(LoginState.LOGIN_FAIL, ls);
    }

    @Test
    public void c_testUpdate() throws Exception {
        ResultMessage rm = client.update(new ClientVO("000000001", "songkuixi2", 0, new DateUtil(2016, 1, 1),
                500, 0, "18795963603", "", "adminClient"));
        assertEquals(ResultMessage.SUCCESS, rm);
    }

    @Test
    public void d_testSearchByID() throws Exception {
        ClientVO cvo = client.searchByID("000000001");
        assertEquals(new ClientVO("000000001", "songkuixi2", 0, new DateUtil(2016, 1, 1), 500, 0, "18795963603", "", "adminClient"), cvo);
        cvo = client.searchByID("000000009");
        assertEquals(null, cvo);
    }

    @Test
    public void e_testSearchClient() throws Exception {
        ArrayList<ClientVO> acvo = new ArrayList<ClientVO>();
        acvo.add(new ClientVO("000000001", "songkuixi2", 0, new DateUtil(2016, 1, 1), 500, 0, "18795963603", "", "adminClient"));

        ArrayList<ClientVO> tmpAcvoS = client.search("00");
        for (int i = 0; i < acvo.size(); i++) {
            ClientVO tmpAcvo = acvo.get(i);
            assertEquals(tmpAcvo, tmpAcvoS.get(i));
        }
    }

    @Test
    public void f_testAddCreditByID() throws Exception {
        ResultMessage rm = client.addCreditByID("000000001", new CreditChangeInfoVO(1000, CreditAction.ADD_CREDIT, "20161012010112341635", new DateUtil(2016, 11, 1)));
        assertEquals(ResultMessage.SUCCESS, rm);
//        rm = client.addCreditByID("000000009", new CreditVO(200, 500, CreditAction.ADD_CREDIT, "20161012010112340000", new DateUtil(2016, 11, 1)));
//        assertEquals(ResultMessage.FAILED, rm);
    }

    @Test
    public void g_testSearchCreditByID() throws Exception {
//        ArrayList<CreditVO> acvo = new ArrayList<CreditVO>();
//        acvo.add(new CreditVO(1000, 1500, CreditAction.ADD_CREDIT, "201610120101123416350", new DateUtil(2016, 11, 1)));
//        acvo.add(new CreditVO(0, 500, CreditAction.INIT_CREDIT, "-000000001", new DateUtil()));
//        ArrayList<CreditVO> tmpAcvoS = client.searchCreditByID("000000001");
//        assertEquals(acvo, tmpAcvoS);
    }

    @Test
    public void h_testGetClientByID() throws Exception {
        UserBLInfo userBLInfo = new BLFactoryImpl().getUserBLInfo_Client();
        int credit = userBLInfo.getCreditOfID("000000001");
        assertEquals(1500, credit);
    }

    @Test
    public void i_testGetLevelByCredit() {
        assertEquals(new LevelVO("1", "500"), client.getLevel("1"));
    }

    @Test
    public void j_testResetPassword() throws Exception {
        ResetState resetState = salesman.resetPassword("adminClient", "123456789", "00000000");
        assertEquals(ResetState.RESET_SUCCESS, resetState);
    }


    @Test
    public void k_testDelete() throws Exception {
        ResultMessage rm = client.delete("000000001");
        assertEquals(ResultMessage.SUCCESS, rm);
        rm = client.delete("000000009");
        assertEquals(ResultMessage.FAILED, rm);
    }

    @AfterClass
    public static void tearDown(){
        salesman.deleteLevel("1");
    }

}
