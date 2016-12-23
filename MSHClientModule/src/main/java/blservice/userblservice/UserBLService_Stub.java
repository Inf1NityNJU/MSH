package blservice.userblservice;

import po.LevelPO;
import util.*;
import vo.*;

import java.util.ArrayList;
import java.util.jar.Pack200;

/**
 * Created by Kray on 2016/10/12.
 */
public class UserBLService_Stub implements UserBLService, UserBLInfo {

    private ClientVO exampleClientVO = new ClientVO("000000007", "KrayC", 0, new DateUtil(1996, 4, 25), 500, 0, "18795963603", "", "adminClient");
    private StaffVO exampleStaffVO = new StaffVO("300001", "testStaff", "25010001", "adminStaff");
    private SalesmanVO exampleSalesmanVO = new SalesmanVO("100001", "testSalesman", "adminSalesman");

    public LoginState login(String account, String password) {
        if (account.equals("adminClient") && password.equals("12345678")) {
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
        if (keyword.equals("000000007")) {
            clientVOs.add(exampleClientVO);
            return clientVOs;
        } else if (keyword.equals("000")) {
            clientVOs.add(new ClientVO("000000001", "Sorumi", 0, new DateUtil(2016, 1, 1), 500, 0,
                    "18795963603", "NJU", "tiejiayun"));
            clientVOs.add(new ClientVO("000000002", "Silver Narcissus", 0, new DateUtil(2016, 1, 1), 500, 0,
                    "18795963603", "", "xuekaifang"));
            clientVOs.add(new ClientVO("000000003", "Vivian SJ", 0, new DateUtil(2016, 1, 1), 500, 0,
                    "18795963603", "NJU", "yangsijia"));
            clientVOs.add(exampleClientVO);
            return clientVOs;
        } else {
            return clientVOs;
        }
    }

    public ResultMessage addCreditRecord(String clientID, CreditChangeInfoVO creditChangeInfoVO) {
        if (clientID.equals("000000007") && creditChangeInfoVO.deltaCredit == 200 && creditChangeInfoVO.creditAction == CreditAction.ADD_CREDIT) {
            System.out.println("Add successfully");
            return ResultMessage.SUCCESS;
        } else if (clientID.equals("000000007") && creditChangeInfoVO.deltaCredit == 200 && creditChangeInfoVO.creditAction == CreditAction.DEDUCT_CREDIT) {
            System.out.println("Deduct successfully");
            return ResultMessage.SUCCESS;
        } else if (clientID.equals("000000007") && creditChangeInfoVO.deltaCredit == 200 && creditChangeInfoVO.creditAction == CreditAction.REVOKE_CREDIT) {
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

    public ArrayList<LevelVO> getAllLevel() {
        ArrayList<LevelVO> levelVOs = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            levelVOs.add(new LevelVO(i + "", i * 100 + ""));
        }
        return levelVOs;
    }

    public ClientVO getClientByID(String ID) {
        if (ID.equals("000000001")) {
            return exampleClientVO;
        } else {
            return null;
        }
    }

    public StaffVO getStaffByID(String ID) {
        if (ID.equals("300001")) {
            return exampleStaffVO;
        } else {
            return null;
        }
    }

    public SalesmanVO getSalesmanByID(String ID) {
        if (ID.equals("100001")) {
            return exampleSalesmanVO;
        } else {
            return null;
        }
    }

    public ResultMessage updateLevel(LevelVO levelVO) {
        if (levelVO.level.equals("1")) {
            return ResultMessage.SUCCESS;
        } else {
            return ResultMessage.FAILED;
        }
    }

    public ResultMessage addLevel(LevelVO levelVO) {
        if ((Integer.parseInt(levelVO.level)) >= 1 && (Integer.parseInt(levelVO.level)) <= 10) {
            return ResultMessage.EXIST;
        } else {
            return ResultMessage.SUCCESS;
        }
    }

    public ResultMessage deleteLevel(String ID) {
        if (Integer.parseInt(ID) >= 1 && Integer.parseInt(ID) <= 10) {
            return ResultMessage.SUCCESS;
        } else {
            return ResultMessage.FAILED;
        }
    }

    public StaffVO getStaffByHotelID(String hotelID) {
        if (hotelID.equals("25010001")) {
            return exampleStaffVO;
        } else {
            return null;
        }
    }

    public LevelVO getLevel(String ID) {
        if (Integer.parseInt(ID) >= 1 && Integer.parseInt(ID) <= 10) {
            return new LevelVO(ID, Integer.parseInt(ID) * 100 + "");
        } else {
            return null;
        }
    }

    public int getCreditOfID(String id) {
        return 200;
    }

    public String getCurrentClientID() {
        return "000000007";
    }

    public String getCurrentStaffID() {
        return "300001";
    }

    public String getCurrentSalesmanID() {
        return "100001";
    }

    public String getHotelIDByStaffID(String ID) {
        if (ID.equals("300001")) {
            return "25010001";
        } else {
            return "";
        }
    }

}
