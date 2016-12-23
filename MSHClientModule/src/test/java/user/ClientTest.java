package user;

import blimpl.blfactory.BLFactoryImpl;
import blimpl.userblimpl.Client;
import blservice.userblservice.UserBLInfo;
import org.junit.Test;
import util.*;
import vo.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Kray on 2016/11/6.
 */
public class ClientTest {
    private Client client;

    public ClientTest() {
        client = new Client();
    }

    @Test
    public void testLogin() throws Exception {
        LoginState ls = client.login("adminClient", "12345678");
        assertEquals(LoginState.LOGIN_SUCCESS_Client, ls);
        ls = client.login("adminClient", "00000000");
        assertEquals(LoginState.LOGIN_FAIL, ls);
    }

    @Test
    public void testAdd() throws Exception {
        ResultMessage rm = client.add(new ClientVO_Register(new DateUtil(2016, 1, 1), 0,
                "", "adminClient", "123456789"));
        assertEquals(ResultMessage.SUCCESS, rm);
    }

    @Test
    public void testSearchByID() throws Exception {
        ClientVO cvo = client.searchByID("000000007");
        assertEquals(new ClientVO("000000007", "songkuixi", 0, new DateUtil(2016, 1, 1), 500, 0, "18795963603", "", "songkuixi"), cvo);
        cvo = client.searchByID("000000009");
        assertEquals(null, cvo);
    }

    @Test
    public void testUpdate() throws Exception {
        ResultMessage rm = client.update(new ClientVO("000000007", "songkuixi", 0, new DateUtil(2016, 1, 1), 1500, 0, "18795963603", "", "songkuixi"));
        assertEquals(ResultMessage.SUCCESS, rm);
        rm = client.update(new ClientVO("000000007", "songkuixi2", 0, new DateUtil(2016, 1, 1), 500, 0, "18795963603", "", "songkuixi"));
        assertEquals(ResultMessage.FAILED, rm);
    }

    @Test
    public void testDelete() throws Exception {
        ResultMessage rm = client.delete("000000007");
        assertEquals(ResultMessage.SUCCESS, rm);
        rm = client.delete("000000009");
        assertEquals(ResultMessage.FAILED, rm);
    }

    @Test
    public void testSearchClient() throws Exception {
        ArrayList<ClientVO> acvo = new ArrayList<ClientVO>();
        acvo.add(new ClientVO("000000002", "老二", 0, new DateUtil(2016, 2, 2), 500, 0, "18795963603", "", "songkuixi"));

        ArrayList<ClientVO> tmpAcvoS = client.search("老二");
        for (int i = 0; i < acvo.size(); i++) {
            ClientVO tmpAcvo = acvo.get(i);
            assertEquals(tmpAcvo, tmpAcvoS.get(i));
        }

        acvo.clear();
        acvo.add(new ClientVO("000000001", "老大", 0, new DateUtil(2016, 1, 1), 500, 0, "18795963603", "", "songkuixi"));
        acvo.add(new ClientVO("000000002", "老二", 0, new DateUtil(2016, 2, 2), 500, 0, "18795963603", "", "songkuixi"));
        acvo.add(new ClientVO("000000003", "老三", 0, new DateUtil(2016, 3, 3), 500, 0, "18795963603", "", "songkuixi"));
        acvo.add(new ClientVO("000000004", "老四", 0, new DateUtil(2016, 4, 4), 500, 0, "18795963603", "", "songkuixi"));

        tmpAcvoS = client.search("老");
        for (int i = 0; i < acvo.size(); i++) {
            ClientVO tmpAcvo = acvo.get(i);
            assertEquals(tmpAcvo, tmpAcvoS.get(i));
        }
    }

    @Test
    public void testAddCreditByID() throws Exception {
        ResultMessage rm = client.addCreditByID("000000003", new CreditChangeInfoVO(2000, CreditAction.ADD_CREDIT, "20161012010112341635", new DateUtil(2016, 11, 1)));
        assertEquals(ResultMessage.SUCCESS, rm);
//        rm = client.addCreditByID("000000009", new CreditVO(200, 500, CreditAction.ADD_CREDIT, "20161012010112340000", new DateUtil(2016, 11, 1)));
//        assertEquals(ResultMessage.FAILED, rm);
    }

    @Test
    public void testSearchCreditByID() throws Exception {
//        System.out.println(client.searchCreditByID("000000002"));
//
        ArrayList<CreditVO> acvo = new ArrayList<CreditVO>();
        acvo.add(new CreditVO(200, 700, CreditAction.ADD_CREDIT, "20161012010112340002", new DateUtil(2016, 10, 12)));
        for (int i = 0; i < acvo.size(); i++) {
            CreditVO tmpAcvo = acvo.get(i);
            ArrayList<CreditVO> tmpAcvoS = client.searchCreditByID("000000001");
            for (int j = 0; j < tmpAcvoS.size(); j++) {
                assertEquals(tmpAcvo, tmpAcvoS.get(j));
            }
        }

        acvo.clear();
        acvo.add(new CreditVO(200, 700, CreditAction.ADD_CREDIT, "20161012010112340003", new DateUtil(2016, 10, 12)));
        for (int i = 0; i < acvo.size(); i++) {
            CreditVO tmpAcvo = acvo.get(i);
            ArrayList<CreditVO> tmpAcvoS = client.searchCreditByID("000000004");
            for (int j = 0; j < tmpAcvoS.size(); j++) {
                assertEquals(tmpAcvo, tmpAcvoS.get(j));
            }
        }
    }

    @Test
    public void testGetClientByID() throws Exception {
        UserBLInfo userBLInfo = new BLFactoryImpl().getUserBLInfo_Client();
        int credit = userBLInfo.getCreditOfID("000000005");
        assertEquals(21099, credit);
    }

}
