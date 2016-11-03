package po;

import java.util.Date;

/**
 * Created by SilverNarcissus on 16/10/11.
 */
public class ClientPO{
    private String clientID;
    private String clientName;
    private int credit;
    private int level;
    private Date birthday;
    private String contactInfo;
    private String Enterprise;
    private String account;
    private String password;

    public ClientPO(String clientID, String clientName, int credit, int level, Date birthday, String contactInfo, String enterprise, String account, String password) {
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
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
