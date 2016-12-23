package blimpl.userblimpl.mock;

import blimpl.userblimpl.Client;
import util.*;
import vo.*;

import java.util.ArrayList;

/**
 * Created by Kray on 2016/10/30.
 */
public class MockClient extends Client {

    private ClientVO exampleClientVO;

    public MockClient(){
        this.exampleClientVO = new ClientVO("000000001", "KrayC", 1, new DateUtil(1996, 4, 25),
                500, 0, "18795963603", "", "adminClient");
    }

    /**
     * 登录方法
     *
     * @param account
     * @param password
     * @return
     */
    public LoginState login(String account, String password) {
        if (account.equals("adminClient") && password.equals("12345678")) {
            super.setCurrentID(exampleClientVO.clientID);
            return LoginState.LOGIN_SUCCESS_Client;
        } else {
            return LoginState.LOGIN_FAIL;
        }
    }

    /**
     * 添加客户成功
     *
     * @param clientVO
     * @return
     */
    public ResultMessage add(ClientVO clientVO) {
        if (!clientVO.clientID.equals("000000001")) {
            return ResultMessage.SUCCESS;
        } else {
            System.out.println("Client Exist");
            return ResultMessage.FAILED;
        }
    }

    /**
     * 根据ID查找客户
     *
     * @param clientID
     * @return
     */
    public ClientVO searchByID(String clientID) {
        if (clientID.equals("000000001")) {
            return exampleClientVO;
        } else {
            return null;
        }
    }

    /**
     * 更新用户
     *
     * @param clientVO
     * @return
     */
    public ResultMessage update(ClientVO clientVO) {
        if (clientVO.clientID.equals("000000001")) {
            return ResultMessage.SUCCESS;
        } else {
            return ResultMessage.FAILED;
        }
    }

    /**
     * 删除客户
     *
     * @param clientID
     * @return
     */
    public ResultMessage delete(String clientID) {
        if (clientID.equals("000000001")) {
            return ResultMessage.SUCCESS;
        } else {
            return ResultMessage.FAILED;
        }
    }

    /**
     * 根据关键词搜索客户
     *
     * @param keyword
     * @return
     */
    public ArrayList<ClientVO> search(String keyword) {
        ClientVO exampleClientVO1 = new ClientVO("000000001", "老大", 0, new DateUtil(2016, 1, 1), 500, 0, "18795963603", "", "songkuixi");
        ClientVO exampleClientVO2 = new ClientVO("000000002", "老二", 0, new DateUtil(2016, 2, 2), 500, 0, "18795963603", "", "songkuixi");
        ClientVO exampleClientVO3 = new ClientVO("000000003", "老三", 0, new DateUtil(2016, 3, 3), 500, 0, "18795963603", "", "songkuixi");
        ClientVO exampleClientVO4 = new ClientVO("000000004", "老四", 0, new DateUtil(2016, 4, 4), 500, 0, "18795963603", "", "songkuixi");
        ArrayList<ClientVO> clientVOs = new ArrayList<ClientVO>();
        if (keyword.equals("000")) {
            clientVOs.add(exampleClientVO1);
            clientVOs.add(exampleClientVO2);
            clientVOs.add(exampleClientVO3);
            clientVOs.add(exampleClientVO4);
        } else if (keyword.equals("000000003")) {
            clientVOs.add(exampleClientVO3);
        }
        return clientVOs;
    }

    /**
     * 给对应ID的客户增加信用记录
     *
     * @param clientID
     * @param creditVO
     * @return
     */
    public ResultMessage addCreditByID(String clientID, CreditVO creditVO) {
        if (clientID.equals("000000001")) {
            return ResultMessage.SUCCESS;
        } else {
            return ResultMessage.FAILED;
        }
    }

    /**
     * 根据客户ID查找信用记录
     *
     * @param clientID
     * @return
     */
    public ArrayList<CreditVO> searchCreditByID(String clientID) {
        ArrayList<OrderRoomVO> rooms = new ArrayList<OrderRoomVO>();
        OrderRoomVO room1 = new OrderRoomVO(RoomType.DoubleRoom, 300, 1);
        rooms.add(room1);
        CreditVO exampleCreditVO1 = new CreditVO(200, 700, CreditAction.ADD_CREDIT, "20161012010112340001", new DateUtil(2016, 10, 12));
        CreditVO exampleCreditVO2 = new CreditVO(200, 700, CreditAction.ADD_CREDIT, "20161012010112340002", new DateUtil(2016, 10, 12));
        CreditVO exampleCreditVO3 = new CreditVO(200, 700, CreditAction.ADD_CREDIT, "20161012010112340003", new DateUtil(2016, 10, 12));
        CreditVO exampleCreditVO4 = new CreditVO(200, 700, CreditAction.ADD_CREDIT, "20161012010112340004", new DateUtil(2016, 10, 12));
        ArrayList<CreditVO> creditVOs = new ArrayList<CreditVO>();
        if (clientID.equals("000000001")) {
            creditVOs.add(exampleCreditVO1);
        } else if (clientID.equals("000000002")) {
            creditVOs.add(exampleCreditVO2);
        } else if (clientID.equals("000000003")) {
            creditVOs.add(exampleCreditVO3);
        } else if (clientID.equals("000000004")) {
            creditVOs.add(exampleCreditVO4);
        } else {

        }
        return creditVOs;
    }
}
