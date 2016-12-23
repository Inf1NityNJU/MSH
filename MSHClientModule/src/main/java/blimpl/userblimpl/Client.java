package blimpl.userblimpl;


import network.UserClientNetworkImpl;
import network.UserClientNetworkService;
import po.ClientPO;
import po.CreditPO;
import po.LevelPO;
import util.DateUtil;
import util.ResetState;
import util.ResultMessage;
import vo.*;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Kray on 2016/10/30.
 */
public class Client extends User {

    //这里进行Dateutil和String的转换

//    private UserClientNetworkService userClientNetwork;

    public Client() {
        super();
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
        ClientPO clientPO = new ClientPO(
                clientVO.clientID,
                clientVO.clientName,
                clientVO.credit,
                clientVO.birthday.toString(),
                clientVO.contactInfo,
                clientVO.enterprise,
                clientVO.account,
                clientVO.password);
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
        if (clientPO == null) {
            return null;
        } else {
            return new ClientVO(
                    clientPO.getClientID(),
                    clientPO.getClientName(),
                    getLevelByCredit(clientPO.getCredit()),
                    new DateUtil(clientPO.getBirthday()),
                    clientPO.getCredit(),
                    clientPO.getEnterprise().equals("") ? 0 : 1,
                    clientPO.getContactInfo(),
                    clientPO.getEnterprise(),
                    clientPO.getAccount());
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
        ClientPO clientPO = new ClientPO(
                clientVO.clientID,
                clientVO.clientName,
                clientVO.credit,
                clientVO.birthday.toString(),
                clientVO.contactInfo,
                clientVO.enterprise,
                clientVO.account,
                tmpPO.getPassword());
        return userClientNetwork.updateClient(clientVO.clientID, clientPO);
    }

    /**
     * 删除客户
     *
     * @param clientID
     * @return 是否删除成功
     */
    public ResultMessage delete(String clientID) {
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
        ArrayList<ClientVO> clientVOs = new ArrayList<ClientVO>();
        clientPOs.forEach(clientPO -> clientVOs.add(new ClientVO(
                clientPO.getClientID(),
                clientPO.getClientName(),
                getLevelByCredit(clientPO.getCredit()),
                new DateUtil(clientPO.getBirthday()),
                clientPO.getCredit(),
                clientPO.getEnterprise().equals("") ? 0 : 1,
                clientPO.getContactInfo(),
                clientPO.getEnterprise(),
                clientPO.getAccount()))
        );
        return clientVOs;
    }

    /**
     * 给对应ID的客户增加信用记录
     *
     * @param clientID
     * @param creditChangeInfoVO
     * @return 是否增加成功
     */
    public ResultMessage addCreditByID(String clientID, CreditChangeInfoVO creditChangeInfoVO) {
        ClientVO clientVO = searchByID(clientID);
        int credit = clientVO.credit;
        return userClientNetwork.addCreditRecord(clientID, new CreditPO(
                creditChangeInfoVO.orderID + creditChangeInfoVO.creditAction.getNum(), //增加一位标示位
                creditChangeInfoVO.date.toString(),
                creditChangeInfoVO.deltaCredit,
                creditChangeInfoVO.deltaCredit + credit,
                creditChangeInfoVO.creditAction,
                clientID));
    }

    /**
     * 根据客户ID查找信用记录
     *
     * @param clientID
     * @return 该客户的所有信用记录
     */
    public ArrayList<CreditVO> searchCreditByID(String clientID) {
        ArrayList<CreditPO> creditPOs = userClientNetwork.searchCreditByID(clientID);
        ArrayList<CreditVO> creditVOs = new ArrayList<CreditVO>();
        creditPOs.forEach(creditPO -> {
            creditVOs.add(new CreditVO(
                    creditPO.getDeltaCredit(),
                    creditPO.getResultCredit(),
                    creditPO.getCreditAction(),
                    creditPO.getOrderID().substring(0, creditPO.getOrderID().length()-1),
                    new DateUtil(creditPO.getDate())));
        });
        creditVOs.sort(new CreditComparator());
        return creditVOs;
    }

    public LevelVO getLevel(String level) {
        LevelPO levelPO = userClientNetwork.getLevel(level);
        return new LevelVO(levelPO.getLevel() + "", levelPO.getCredit() + "");
    }

    /**
     * 得到某客户的总信用值
     *
     * @param clientID
     * @return 客户总信用值
     */
    public int getCreditOfID(String clientID) {
        return searchByID(clientID).credit;
    }

    /**
     * 根据信用值得到等级
     * @param credit
     * @return
     */
    private int getLevelByCredit(int credit){
        return userClientNetwork.getLevelByCredit(credit);
    }

    private class CreditComparator implements Comparator<CreditVO> {

        public int compare(CreditVO o1, CreditVO o2) {
            if (o1.date.compareDate(o2.date) != 0) {
                return o1.date.compareDate(o2.date);
            } else {
                if (o1.orderID.charAt(0) == '-' && o2.orderID.charAt(0) != '-') {
                    return 1;
                } else if (o1.orderID.charAt(0) != '-' && o2.orderID.charAt(0) == '-') {
                    return -1;
                } else if (o1.orderID.charAt(0) == '-' && o2.orderID.charAt(0) == '-') {
                    return 1;
                } else {
                    return Integer.parseInt(o1.orderID.substring(o1.orderID.length() - 6, o1.orderID.length() - 1))
                            - Integer.parseInt(o2.orderID.substring(o1.orderID.length() - 6, o1.orderID.length() - 1));
                }
            }
        }

    }
}
