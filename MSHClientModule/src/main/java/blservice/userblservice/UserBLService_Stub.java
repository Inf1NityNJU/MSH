package blservice.userblservice;

import util.*;
import vo.*;

import java.util.ArrayList;

/**
 * Created by Kray on 2016/10/12.
 */
public class UserBLService_Stub implements UserBLService {

    private ClientVO exampleClientVO = new ClientVO("000000007", "老宋", 0, new DateUtil(2016, 1, 1), 500, 0);

    public LoginState login(String account, String password) {
        if (account.equals("admin") && password.equals("12345678")) {
            System.out.println("Login successfully");
            return LoginState.LOGIN_SUCCESS_Client;
        } else {
            System.out.println("Login failed");
            return LoginState.LOGIN_FAIL;
        }
    }

    public LoginState logout() {
        System.out.println("Logout");
        return LoginState.LOGOUT;
    }

    public ResetState reset(String account, String oldPassword, String newPassword) {
        if (oldPassword.equals("12345678") && newPassword.equals("1234567890")) {
            return ResetState.RESET_SUCCESS;
        } else {
            return ResetState.RESET_FAIL;
        }
    }

    public ResultMessage add(UserVO userVO) {
        ClientVO clientVO = (ClientVO) userVO;
        if (!clientVO.clientID.equals("000000007")) {
            return ResultMessage.SUCCESS;
        } else {
            System.out.println("Client Exist");
            return ResultMessage.FAILED;
        }
    }

    /**
     * 根据ID查找用户
     *
     * @param userID
     * @return 查到的用户
     */
    public UserVO searchByID(String userID) {
        if (userID.equals("000000007")) {
            return exampleClientVO;
        } else {
            return null;
        }
    }

    /**
     * 更新用户信息
     *
     * @param userVO
     * @return 是否更新成功
     */
    public ResultMessage update(UserVO userVO) {
        ClientVO clientVO = (ClientVO) userVO;
        if (clientVO.clientID.equals("000000007")) {
            return ResultMessage.SUCCESS;
        } else {
            System.out.println("Client Exist");
            return ResultMessage.FAILED;
        }
    }

    /**
     * 删除用户
     *
     * @param userID
     * @return 是否删除成功
     */
    public ResultMessage delete(String userID) {
        if (userID.equals("000000007")) {
            return ResultMessage.SUCCESS;
        } else {
            System.out.println("Client Exist");
            return ResultMessage.FAILED;
        }
    }

    /**
     * 搜索符合关键词的用户列表
     *
     * @param keyword
     * @return 返回的用户列表
     */
    public ArrayList search(String keyword) {
        ArrayList<ClientVO> clientVOs = new ArrayList<ClientVO>();
        if(keyword.equals("000000007")){
            clientVOs.add(exampleClientVO);
            return clientVOs;
        }else{
            return clientVOs;
        }
    }

    public ResultMessage addCreditRecord(String clientID, CreditVO creditVO) {
        if (clientID.equals("000000007") && creditVO.deltaCredit == 200 && creditVO.creditAction == CreditAction.ADD_CREDIT) {
            System.out.println("Add successfully");
            return ResultMessage.SUCCESS;
        } else if (clientID.equals("000000007") && creditVO.deltaCredit == 200 && creditVO.creditAction == CreditAction.DEDUCT_CREDIT) {
            System.out.println("Deduct successfully");
            return ResultMessage.SUCCESS;
        } else if (clientID.equals("000000007") && creditVO.deltaCredit == 200 && creditVO.creditAction == CreditAction.REVOKE_CREDIT) {
            System.out.println("Deduct successfully");
            return ResultMessage.SUCCESS;
        } else {
            return ResultMessage.FAILED;
        }
    }

    /**
     * 根据客户ID查找信用记录
     *
     * @param clientID
     * @return 该客户的信用记录列表
     */
    public ArrayList<CreditVO> searchCreditByID(String clientID) {
        ArrayList<OrderRoomVO> rooms = new ArrayList<OrderRoomVO>();
        OrderRoomVO room1 = new OrderRoomVO(RoomType.DoubleRoom, 300, 1);
        rooms.add(room1);
        CreditVO exampleCreditVO1 = new CreditVO(200, 700, CreditAction.ADD_CREDIT, new OrderVO("20161012010112340000", "01011234", "000000001", rooms,
                new DateUtil(2016, 10, 12), new DateUtil(2016, 10, 13), null, null,
                null, null, new TimeUtil(2016, 10, 11, 14, 0, 0), 2, false, OrderState.Unexecuted, new BillVO(null, null, 300, 280), null), new DateUtil(2016, 10, 12));
        CreditVO exampleCreditVO2 = new CreditVO(200, 700, CreditAction.ADD_CREDIT, new OrderVO("20161012010112340001", "01011234", "000000002", rooms,
                new DateUtil(2016, 10, 12), new DateUtil(2016, 10, 13), new TimeUtil(2016, 10, 12, 14, 0, 0), null,
                null, null, new TimeUtil(2016, 10, 11, 14, 0, 0), 2, false, OrderState.Executed, new BillVO(null, null, 300, 280), new AssessmentVO(5, 5, 5, 5, "很好很舒适")), new DateUtil(2016, 10, 12));
        CreditVO exampleCreditVO3 = new CreditVO(200, 700, CreditAction.ADD_CREDIT, new OrderVO("20161012010112340002", "01011234", "000000003", rooms,
                new DateUtil(2016, 10, 12), new DateUtil(2016, 10, 13), null, null,
                null, null, new TimeUtil(2016, 10, 11, 14, 0, 0), 2, false, OrderState.Abnormal, new BillVO(null, null, 300, 280), null), new DateUtil(2016, 10, 12));
        CreditVO exampleCreditVO4 = new CreditVO(200, 700, CreditAction.ADD_CREDIT, new OrderVO("20161012010112340003", "01011234", "000000004", rooms,
                new DateUtil(2016, 10, 12), new DateUtil(2016, 10, 13), null, null,
                new TimeUtil(2016, 10, 10, 14, 0, 0), new TimeUtil(2016, 10, 11, 14, 0, 0), null, 2, false, OrderState.Cancelled, new BillVO(null, null, 300, 280), null), new DateUtil(2016, 10, 12));
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
