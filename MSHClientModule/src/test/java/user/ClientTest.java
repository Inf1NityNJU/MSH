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
    public void testAddClient() throws Exception {
        ResultMessage rm = client.addClient(new ClientVO("000000008","老宋头",0, new DateUtil(2016,1,1),500,0));
        assertEquals(ResultMessage.SUCCESS,rm);
        rm = client.addClient(new ClientVO("000000007","老宋",0, new DateUtil(2016,1,1),500,0));
        assertEquals(ResultMessage.FAILED,rm);
    }

    @Test
    public void testSearchClientByID() throws Exception {
        ClientVO cvo = client.searchClientByID("000000007");
        assertEquals(new ClientVO("000000007","老宋",0, new DateUtil(2016,1,1),500,0), cvo);
        cvo = client.searchClientByID("000000009");
        assertEquals(null, cvo);
    }

    @Test
    public void testUpdateClient() throws Exception {
        ResultMessage rm = client.updateClient(new ClientVO("000000007","老宋", 0, new DateUtil(2016,1,1), 1500,0));
        assertEquals(ResultMessage.SUCCESS,rm);
        rm = client.updateClient(new ClientVO("000000009","老宋头",0, new DateUtil(2016,1,1),500,0));
        assertEquals(ResultMessage.FAILED,rm);
    }

    @Test
    public void testDeleteClient() throws Exception {
        ResultMessage rm = client.deleteClient("000000007");
        assertEquals(ResultMessage.SUCCESS,rm);
        rm = client.deleteClient("000000009");
        assertEquals(ResultMessage.FAILED,rm);
    }

    @Test
    public void testSearchClient() throws Exception {
        ArrayList<ClientVO> acvo = new ArrayList<ClientVO>();
        acvo.add(new ClientVO("000000002","老二",0, new DateUtil(2016,2,2),500,0));
        assertEquals(acvo,client.searchClient("老二"));
        acvo.add(new ClientVO("000000001","老大",0, new DateUtil(2016,1,1),500,0));
        acvo.add(new ClientVO("000000003","老三",0, new DateUtil(2016,3,3),500,0));
        acvo.add(new ClientVO("000000004","老四",0, new DateUtil(2016,4,4),500,0));
        assertEquals(acvo,client.searchClient("老"));
    }

    @Test
    public void testAddCreditByClientID() throws Exception {
        ResultMessage rm = client.addCreditByClientID("000000007", new CreditVO(200, 500, CreditAction.ADD_CREDIT, null, new DateUtil(2016,11,1)));
        assertEquals(ResultMessage.SUCCESS, rm);
        rm = client.addCreditByClientID("000000009", new CreditVO(200, 500, CreditAction.ADD_CREDIT, null, new DateUtil(2016,11,1)));
        assertEquals(ResultMessage.FAILED, rm);
    }

    @Test
    public void testSearchCreditByClientID() throws Exception {
        ArrayList<OrderRoomVO> rooms = new ArrayList<OrderRoomVO>();
        OrderRoomVO room1 = new OrderRoomVO(RoomType.DoubleRoom, 300, 1);
        rooms.add(room1);
        ArrayList<CreditVO> acvo = new ArrayList<CreditVO>();
        acvo.add(new CreditVO(200, 700, CreditAction.ADD_CREDIT, new OrderVO("20161012010112340000", "01011234", "000000001", rooms,
                new DateUtil(2016, 10, 12), new DateUtil(2016, 10, 13), null, null,
                null, null, new TimeUtil(2016, 10, 11, 14, 0, 0), 2, false, OrderState.Unexecuted, new BillVO(null, null, 300, 280), null), new DateUtil(2016,10,12)));
        assertEquals(acvo, client.searchCreditByClientID("000000001"));
        acvo.clear();
        acvo.add(new CreditVO(200, 700, CreditAction.ADD_CREDIT, new OrderVO("20161012010112340003", "01011234", "000000004", rooms,
                new DateUtil(2016, 10, 12), new DateUtil(2016, 10, 13), null, null,
                new TimeUtil(2016, 10, 10, 14, 0, 0), new TimeUtil(2016, 10, 11, 14, 0, 0), null, 2, false, OrderState.Cancelled, new BillVO(null, null, 300, 280), null), new DateUtil(2016,10,12)));
        assertEquals(acvo, client.searchCreditByClientID("000000004"));
    }
}
