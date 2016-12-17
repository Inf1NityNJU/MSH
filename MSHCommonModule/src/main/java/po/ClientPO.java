package po;

import util.DateUtil;

import java.io.Serializable;
import java.util.Date;

import static util.EqualJudgeHelper.judgeEqual;

/**
 * Created by SilverNarcissus on 16/10/11.
 */
public class ClientPO extends UserPO implements Serializable {
    /**
     * 客户ID
     */
    private String clientID;
    /**
     * 客户姓名
     */
    private String clientName;
    /**
     * 客户信用值
     */
    private int credit;
    /**
     * 客户等级
     */
    private int level;
    /**
     * 客户生日
     */
    private String birthday;
    /**
     * 客户联系方式
     */
    private String contactInfo;
    /**
     * 客户所属企业,若是普通用户则为空
     */
    private String enterprise;
    /**
     * 客户账号
     */
    private String account;
    /**
     * 客户密码
     */
    private String password;

    public ClientPO() {

    }

    public ClientPO(String clientID, String clientName, int credit, String birthday, String contactInfo, String enterprise, String account, String password) {
        this.clientID = clientID;
        this.clientName = clientName;
        this.credit = credit;
        this.level = 0;
        this.birthday = birthday;
        this.contactInfo = contactInfo;
        this.enterprise = enterprise;
        this.account = account;
        this.password = password;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 比较两个PO
     *
     * @param o
     * @return 比较结果
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof ClientPO) {
            ClientPO clientPO = (ClientPO) o;
            return compareData(clientPO);
        }
        return false;
    }

    /**
     * 生成对象的hashcode
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return clientID.hashCode();
    }

    /**
     * 分别比较每个数据
     *
     * @param clientPO
     * @return 比较结果
     */
    private boolean compareData(ClientPO clientPO) {
        return judgeEqual(clientID, clientPO.clientID)
                && judgeEqual(clientName, clientPO.clientName)
                && judgeEqual(credit, clientPO.credit)
                && judgeEqual(contactInfo, clientPO.contactInfo)
                && judgeEqual(account, clientPO.account)
                && judgeEqual(password, clientPO.password)
                && judgeEqual(level, clientPO.level)
                && judgeEqual(birthday, clientPO.birthday)
                && judgeEqual(enterprise, clientPO.enterprise);
    }
}
