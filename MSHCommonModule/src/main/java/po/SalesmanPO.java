package po;

/**
 * Created by SilverNarcissus on 16/10/11.
 */
public class SalesmanPO {
    /**
     * 网站营销人员ID
     */
    private String salesmanID;
    /**
     * 网站营销人员姓名
     */
    private String salesmanName;
    /**
     * 网站营销人员账号
     */
    private String account;
    /**
     * 网站营销人员密码
     */
    private String password;

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccount(String account) {
        this.account = account;

    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    public String getSalesmanID() {
        return salesmanID;
    }

    public void setSalesmanID(String salesmanID) {
        this.salesmanID = salesmanID;
    }

    public SalesmanPO(String salesmanID, String salesmanName, String account, String password) {
        this.salesmanID = salesmanID;
        this.salesmanName = salesmanName;
        this.account = account;
        this.password = password;
    }
}