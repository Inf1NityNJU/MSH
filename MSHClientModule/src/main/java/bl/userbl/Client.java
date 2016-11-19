package bl.userbl;

import dataimpl.userdataimpl.UserDataServiceFactory;
import dataservice.userdataservice.UserDataService;
import po.ClientPO;
import po.CreditPO;
import util.CreditAction;
import util.DateUtil;
import util.LoginState;
import util.ResultMessage;
import vo.ClientVO;
import vo.CreditVO;
import vo.UserVO;

import java.util.ArrayList;

/**
 * Created by Kray on 2016/10/30.
 */
public class Client extends User {

    private UserDataService userDataService;
    private String account;
    private String password;

    public Client() {
        this.userDataService = UserDataServiceFactory.getClientDataService();
    }

    /**
     * 登录方法
     *
     * @param account
     * @param password
     * @return 当前登录状态
     */
    public LoginState login(String account, String password) {
        LoginState loginState = userDataService.login(account, password);
        if (loginState == LoginState.LOGIN_SUCCESS_Client) {
            System.out.println("LOGIN Client");
            super.setCurrentID("STRING FROM DB");
            this.account = account;
            this.password = password;
        }
        return loginState;
    }

    /**
     * 添加客户
     *
     * @param userVO
     * @return 是否添加成功
     */
    public ResultMessage add(UserVO userVO) {
        ClientVO clientVO = (ClientVO) userVO;
        ClientPO clientPO = new ClientPO(clientVO.clientID, clientVO.clientName, clientVO.credit, clientVO.level,
                clientVO.birthday.toString(), "", "", account, password);
        return userDataService.addClient(clientPO, new CreditPO(clientVO.clientID));
    }

    /**
     * 根据ID查找客户
     *
     * @param clientID
     * @return 查询到的ClientVO
     */
    public ClientVO searchByID(String clientID) {
        /*
        ClientPO clientPO = userDataService.searchClientByID(clientID);
        if(clientPO == null){
            return null;
        }else {
            String [] strs = clientPO.getBirthday().split("-");
            ClientVO clientVO = new ClientVO(clientPO.getClientID(), clientPO.getClientName(), clientPO.getLevel(),
                    new DateUtil(strs[0], strs[1], strs[2]), clientPO.getCredit(), clientPO.);
            return clientVO;
        }
        */
        return null;
    }

    /**
     * 更新用户信息
     *
     * @param userVO
     * @return 是否更新成功
     */
    public ResultMessage update(UserVO userVO) {
        ClientVO clientVO = (ClientVO) userVO;
        return null;
    }

    /**
     * 删除客户
     *
     * @param clientID
     * @return 是否删除成功
     */
    public ResultMessage delete(String clientID) {
        return userDataService.deleteClient(clientID);
    }

    /**
     * 根据关键词搜索客户
     *
     * @param keyword
     * @return 符合关键词的所有客户
     */
    public ArrayList<ClientVO> search(String keyword) {
        return null;
    }

    /**
     * 给对应ID的客户增加信用记录
     *
     * @param clientID
     * @param creditVO
     * @return 是否增加成功
     */
    public ResultMessage addCreditByID(String clientID, CreditVO creditVO) {
        return null;
    }

    /**
     * 根据客户ID查找信用记录
     *
     * @param clientID
     * @return 该客户的所有信用记录
     */
    public ArrayList<CreditVO> searchCreditByID(String clientID) {
        return null;
    }

}
