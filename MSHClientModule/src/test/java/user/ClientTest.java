package user;

import bl.userbl.Client;
import bl.userbl.MockClient;
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

    public ClientTest(){
        client = new MockClient();
    }

    @Test
    public void testLogin() throws Exception {
        LoginState ls = client.login("adminClient","12345678");
        assertEquals(LoginState.LOGIN_SUCCESS_Client,ls);
        ls = client.login("adminClient","00000000");
        assertEquals(LoginState.LOGIN_FAIL,ls);
    }

    @Test
    public void testAdd() throws Exception {
        ResultMessage rm = client.add(new ClientVO("000000008","老宋头",0, new DateUtil(2016,1,1),500,0));
        assertEquals(ResultMessage.SUCCESS,rm);
        rm = client.add(new ClientVO("000000007","老宋",0, new DateUtil(2016,1,1),500,0));
        assertEquals(ResultMessage.FAILED,rm);
    }

    @Test
    public void testSearchByID() throws Exception {
        ClientVO cvo = client.searchByID("000000007");
        assertEquals(new ClientVO("000000007","老宋",0, new DateUtil(2016,1,1),500,0).birthday.day, cvo.birthday.day);
        assertEquals(new ClientVO("000000007","老宋",0, new DateUtil(2016,1,1),500,0).birthday.month, cvo.birthday.month);
        assertEquals(new ClientVO("000000007","老宋",0, new DateUtil(2016,1,1),500,0).birthday.year, cvo.birthday.year);
        assertEquals(new ClientVO("000000007","老宋",0, new DateUtil(2016,1,1),500,0).clientID, cvo.clientID);
        assertEquals(new ClientVO("000000007","老宋",0, new DateUtil(2016,1,1),500,0).clientName, cvo.clientName);
        assertEquals(new ClientVO("000000007","老宋",0, new DateUtil(2016,1,1),500,0).credit, cvo.credit);
        assertEquals(new ClientVO("000000007","老宋",0, new DateUtil(2016,1,1),500,0).type, cvo.type);
        assertEquals(new ClientVO("000000007","老宋",0, new DateUtil(2016,1,1),500,0).level, cvo.level);
        cvo = client.searchByID("000000009");
        assertEquals(null, cvo);
    }

    @Test
    public void testUpdate() throws Exception {
        ResultMessage rm = client.update(new ClientVO("000000007","老宋", 0, new DateUtil(2016,1,1), 1500,0));
        assertEquals(ResultMessage.SUCCESS,rm);
        rm = client.update(new ClientVO("000000009","老宋头",0, new DateUtil(2016,1,1),500,0));
        assertEquals(ResultMessage.FAILED,rm);
    }

    @Test
    public void testDelete() throws Exception {
        ResultMessage rm = client.delete("000000007");
        assertEquals(ResultMessage.SUCCESS,rm);
        rm = client.delete("000000009");
        assertEquals(ResultMessage.FAILED,rm);
    }

    @Test
    public void testSearchClient() throws Exception {
        ArrayList<ClientVO> acvo = new ArrayList<ClientVO>();
        acvo.add(new ClientVO("000000002","老二",0, new DateUtil(2016,2,2),500,0));


        ArrayList<ClientVO> tmpAcvoS = client.searchClient("老二");
        for(int i = 0; i < acvo.size(); i++) {
            ClientVO tmpAcvo = acvo.get(i);
            assertEquals(tmpAcvo.level, tmpAcvoS.get(i).level);
            assertEquals(tmpAcvo.type, tmpAcvoS.get(i).type);
            assertEquals(tmpAcvo.credit, tmpAcvoS.get(i).credit);
            assertEquals(tmpAcvo.clientName, tmpAcvoS.get(i).clientName);
            assertEquals(tmpAcvo.clientID, tmpAcvoS.get(i).clientID);
            assertEquals(tmpAcvo.birthday.year, tmpAcvoS.get(i).birthday.year);
            assertEquals(tmpAcvo.birthday.month, tmpAcvoS.get(i).birthday.month);
            assertEquals(tmpAcvo.birthday.day, tmpAcvoS.get(i).birthday.day);
        }

        acvo.clear();
        acvo.add(new ClientVO("000000001","老大",0, new DateUtil(2016,1,1),500,0));
        acvo.add(new ClientVO("000000002","老二",0, new DateUtil(2016,2,2),500,0));
        acvo.add(new ClientVO("000000003","老三",0, new DateUtil(2016,3,3),500,0));
        acvo.add(new ClientVO("000000004","老四",0, new DateUtil(2016,4,4),500,0));

        tmpAcvoS = client.searchClient("老");
        for(int i = 0; i < acvo.size(); i++) {
            ClientVO tmpAcvo = acvo.get(i);
            assertEquals(tmpAcvo.level, tmpAcvoS.get(i).level);
            assertEquals(tmpAcvo.type, tmpAcvoS.get(i).type);
            assertEquals(tmpAcvo.credit, tmpAcvoS.get(i).credit);
            assertEquals(tmpAcvo.clientName, tmpAcvoS.get(i).clientName);
            assertEquals(tmpAcvo.clientID, tmpAcvoS.get(i).clientID);
            assertEquals(tmpAcvo.birthday.year, tmpAcvoS.get(i).birthday.year);
            assertEquals(tmpAcvo.birthday.month, tmpAcvoS.get(i).birthday.month);
            assertEquals(tmpAcvo.birthday.day, tmpAcvoS.get(i).birthday.day);
        }
    }

    @Test
    public void testAddCreditByID() throws Exception {
        ArrayList<OrderRoomVO> rooms = new ArrayList<OrderRoomVO>();
        OrderRoomVO room1 = new OrderRoomVO(RoomType.DoubleRoom, 300, 1);
        rooms.add(room1);
        ResultMessage rm = client.addCreditByID("000000007", new CreditVO(200, 500, CreditAction.ADD_CREDIT, new OrderVO("20161012010112340000", "01011234", "000000001", rooms,
                new DateUtil(2016, 10, 12), new DateUtil(2016, 10, 13), null, null,
                null, null, new TimeUtil(2016, 10, 11, 14, 0, 0), 2, false, OrderState.Unexecuted, new BillVO(null, null, 300, 280), null), new DateUtil(2016,11,1)));
        assertEquals(ResultMessage.SUCCESS, rm);
        rm = client.addCreditByID("000000009", new CreditVO(200, 500, CreditAction.ADD_CREDIT, new OrderVO("20161012010112340000", "01011234", "000000001", rooms,
                new DateUtil(2016, 10, 12), new DateUtil(2016, 10, 13), null, null,
                null, null, new TimeUtil(2016, 10, 11, 14, 0, 0), 2, false, OrderState.Unexecuted, new BillVO(null, null, 300, 280), null), new DateUtil(2016,11,1)));
        assertEquals(ResultMessage.FAILED, rm);
    }

    @Test
    public void testSearchCreditByID() throws Exception {
        ArrayList<OrderRoomVO> rooms = new ArrayList<OrderRoomVO>();
        OrderRoomVO room1 = new OrderRoomVO(RoomType.DoubleRoom, 300, 1);
        rooms.add(room1);
        ArrayList<CreditVO> acvo = new ArrayList<CreditVO>();
        acvo.add(new CreditVO(200, 700, CreditAction.ADD_CREDIT, new OrderVO("20161012010112340000", "01011234", "000000001", rooms,
                new DateUtil(2016, 10, 12), new DateUtil(2016, 10, 13), null, null,
                null, null, new TimeUtil(2016, 10, 11, 14, 0, 0), 2, false, OrderState.Unexecuted, new BillVO(null, null, 300, 280), null), new DateUtil(2016,10,12)));
        for(int i = 0; i < acvo.size(); i++) {
            CreditVO tmpAcvo = acvo.get(i);
            ArrayList<CreditVO> tmpAcvoS = client.searchCreditByID("000000001");
            for(int j = 0; j < tmpAcvoS.size(); j++) {
                assertEquals(tmpAcvo.creditAction, tmpAcvoS.get(j).creditAction);
                assertEquals(tmpAcvo.deltaCredit, tmpAcvoS.get(j).deltaCredit);
                assertEquals(tmpAcvo.resultCredit, tmpAcvoS.get(j).resultCredit);
                assertEquals(tmpAcvo.orderID, tmpAcvoS.get(j).orderID);
                assertEquals(tmpAcvo.date.year, tmpAcvoS.get(j).date.year);
                assertEquals(tmpAcvo.date.month, tmpAcvoS.get(j).date.month);
                assertEquals(tmpAcvo.date.day, tmpAcvoS.get(j).date.day);
            }
        }

        acvo.clear();
        acvo.add(new CreditVO(200, 700, CreditAction.ADD_CREDIT, new OrderVO("20161012010112340003", "01011234", "000000004", rooms,
                new DateUtil(2016, 10, 12), new DateUtil(2016, 10, 13), null, null,
                new TimeUtil(2016, 10, 10, 14, 0, 0), new TimeUtil(2016, 10, 11, 14, 0, 0), null, 2, false, OrderState.Cancelled, new BillVO(null, null, 300, 280), null), new DateUtil(2016,10,12)));
        for(int i = 0; i < acvo.size(); i++) {
            CreditVO tmpAcvo = acvo.get(i);
            ArrayList<CreditVO> tmpAcvoS = client.searchCreditByID("000000004");
            for(int j = 0; j < tmpAcvoS.size(); j++) {
                assertEquals(tmpAcvo.creditAction, tmpAcvoS.get(j).creditAction);
                assertEquals(tmpAcvo.deltaCredit, tmpAcvoS.get(j).deltaCredit);
                assertEquals(tmpAcvo.resultCredit, tmpAcvoS.get(j).resultCredit);
                assertEquals(tmpAcvo.orderID, tmpAcvoS.get(j).orderID);
                assertEquals(tmpAcvo.date.year, tmpAcvoS.get(j).date.year);
                assertEquals(tmpAcvo.date.month, tmpAcvoS.get(j).date.month);
                assertEquals(tmpAcvo.date.day, tmpAcvoS.get(j).date.day);
            }
        }
    }
}
