package po;

import util.DateUtil;

import java.util.Date;

/**
 * Created by SilverNarcissus on 16/10/11.
 */
public class ClientPO{
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
    private DateUtil birthday;
    /**
     * 客户联系方式
     */
    private String contactInfo;
    /**
     * 客户所属企业,若是普通用户则为空
     */
    private String Enterprise;
    /**
     * 客户账号
     */
    private String account;
    /**
     * 客户密码
     */
    private String password;

    public ClientPO(String clientID, String clientName, int credit, int level, DateUtil birthday, String contactInfo, String enterprise, String account, String password) {
        this.clientID = clientID;
        this.clientName = clientName;
        this.credit = credit;
        this.level = level;
        this.birthday = birthday;
        this.contactInfo = contactInfo;
        Enterprise = enterprise;
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

    public DateUtil getBirthday() {
        return birthday;
    }

    public void setBirthday(DateUtil birthday) {
        this.birthday = birthday;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getEnterprise() {
        return Enterprise;
    }

    public void setEnterprise(String enterprise) {
        Enterprise = enterprise;
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
}
