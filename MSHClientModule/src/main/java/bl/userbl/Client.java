package bl.userbl;

import dataimpl.userdataimpl.UserDataServiceFactory;
import dataservice.userdataservice.UserDataService;
import network.UserClientNetworkImpl;
import network.UserClientNetworkService;
import po.ClientPO;
import po.CreditPO;
import po.LevelPO;
import util.DateUtil;
import util.LoginState;
import util.ResetState;
import util.ResultMessage;
import vo.*;

import java.util.ArrayList;

/**
 * Created by Kray on 2016/10/30.
 */
public class Client extends User {

    //这里进行Dateutil和String的转换

//    private UserDataService userDataService;
//    private UserClientNetworkService userClientNetwork;

//    private String account;
//    private String password;

    public Client() {
        super();
//        this.userDataService = UserDataServiceFactory.getClientDataService();
//        this.userClientNetwork = new UserClientNetworkImpl();
    }

    /**
     * 修改密码
     *
     * @param account
     * @param oldPassword
     * @param newPassword
     * @return
     */
    public ResetState resetPassword(String account, String oldPassword, String newPassword) {
        if (userClientNetwork.resetPassword(account, oldPassword, newPassword) == ResultMessage.SUCCESS) {
            return ResetState.RESET_SUCCESS;
        } else {
            return ResetState.RESET_FAIL;
        }
    }

    /**
     * 添加客户
     *
     * @param userVO
     * @return 是否添加成功
     */
    public ResultMessage add(UserVO userVO) {
        ClientVO_Register clientVO = (ClientVO_Register) userVO;
        ClientPO clientPO = new ClientPO(clientVO.clientID, clientVO.clientName, clientVO.credit, clientVO.level,
                clientVO.birthday.toString(), clientVO.contactInfo, clientVO.enterprise, clientVO.account, clientVO.password);
//        return userDataService.addClient(clientPO);
        return userClientNetwork.addClient(clientPO);
    }

    /**
     * 根据ID查找客户
     *
     * @param clientID
     * @return 查询到的ClientVO
     */
    public ClientVO searchByID(String clientID) {
        ClientPO clientPO = userClientNetwork.searchClientByID(clientID);
//        ClientPO clientPO = userDataService.searchClientByID(clientID);

        if (clientPO == null) {
            return null;
        } else {
            ClientVO clientVO = new ClientVO(clientPO.getClientID(), clientPO.getClientName(), clientPO.getLevel(),
                    new DateUtil(clientPO.getBirthday()), clientPO.getCredit(), clientPO.getEnterprise().equals("") ? 0 : 1,
                    clientPO.getContactInfo(), clientPO.getEnterprise(), clientPO.getAccount());
            return clientVO;
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
        ClientPO tmpPO = userClientNetwork.searchClientByID(clientVO.clientID);
        ClientPO clientPO = new ClientPO(clientVO.clientID, clientVO.clientName, clientVO.credit, clientVO.level,
                clientVO.birthday.toString(), clientVO.contactInfo, clientVO.enterprise, clientVO.account, tmpPO.getPassword());
//        return userDataService.updateClient(clientVO.clientID, clientPO);
        return userClientNetwork.updateClient(clientVO.clientID, clientPO);
    }

    /**
     * 删除客户
     *
     * @param clientID
     * @return 是否删除成功
     */
    public ResultMessage delete(String clientID) {
//        return userDataService.deleteClient(clientID);
        return userClientNetwork.deleteClient(clientID);
    }

    /**
     * 根据关键词搜索客户
     *
     * @param keyword
     * @return 符合关键词的所有客户
     */
    public ArrayList<ClientVO> search(String keyword) {
        ArrayList<ClientPO> clientPOs = userClientNetwork.searchClient(keyword);
//        ArrayList<ClientPO> clientPOs = userDataService.searchClient(keyword);
        ArrayList<ClientVO> clientVOs = new ArrayList<ClientVO>();

        for (ClientPO clientPO : clientPOs) {
            clientVOs.add(new ClientVO(clientPO.getClientID(), clientPO.getClientName(), clientPO.getLevel(),
                    new DateUtil(clientPO.getBirthday()), clientPO.getCredit(), clientPO.getEnterprise().equals("") ? 0 : 1,
                    clientPO.getContactInfo(), clientPO.getEnterprise(), clientPO.getAccount()));
        }
        return clientVOs;
    }

    /**
     * 给对应ID的客户增加信用记录
     *
     * @param clientID
     * @param creditVO
     * @return 是否增加成功
     */
    public ResultMessage addCreditByID(String clientID, CreditVO creditVO) {
//        return userDataService.addCreditRecord(clientID, new CreditPO(creditVO.orderID, creditVO.date.toString(),
//                creditVO.deltaCredit, creditVO.resultCredit, creditVO.creditAction, clientID));
        return userClientNetwork.addCreditRecord(clientID, new CreditPO(creditVO.orderID, creditVO.date.toString(),
                creditVO.deltaCredit, creditVO.resultCredit, creditVO.creditAction, clientID));
    }

    /**
     * 根据客户ID查找信用记录
     *
     * @param clientID
     * @return 该客户的所有信用记录
     */
    public ArrayList<CreditVO> searchCreditByID(String clientID) {
//        ArrayList<CreditPO> creditPOs = userDataService.searchCreditByID(clientID);
        ArrayList<CreditPO> creditPOs = userClientNetwork.searchCreditByID(clientID);
        ArrayList<CreditVO> creditVOs = new ArrayList<CreditVO>();
        for (CreditPO creditPO : creditPOs) {
            CreditVO creditVO = new CreditVO(creditPO.getDeltaCredit(), creditPO.getResultCredit(),
                    creditPO.getCreditAction(), creditPO.getOrderID(), new DateUtil(creditPO.getDate()));
            creditVOs.add(creditVO);
        }
        return creditVOs;
    }

    public LevelVO getLevel(String level) {
//        LevelPO levelPO = userDataService.getLevel(level);
        LevelPO levelPO = userClientNetwork.getLevel(level);
        return new LevelVO(levelPO.getLevel() + "", levelPO.getCredit() + "");
    }

}
