package po;

import java.io.Serializable;

import static util.EqualJudgeHelper.judgeEqual;

/**
 * Created by SilverNarcissus on 16/10/11.
 */
public class SalesmanPO extends UserPO implements Serializable {
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

    public SalesmanPO() {

    }

    public SalesmanPO(String salesmanID, String salesmanName, String account, String password) {
        this.salesmanID = salesmanID;
        this.salesmanName = salesmanName;
        this.account = account;
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
        if (o instanceof SalesmanPO) {
            SalesmanPO salesmanPO = (SalesmanPO) o;
            return compareData(salesmanPO);
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
        return salesmanID.hashCode();
    }

    /**
     * 分别比较每个数据
     *
     * @param salesmanPO
     * @return 比较结果
     */
    private boolean compareData(SalesmanPO salesmanPO) {
        return judgeEqual(salesmanID, salesmanPO.salesmanID)
                && judgeEqual(salesmanName, salesmanPO.salesmanName)
                && judgeEqual(account, salesmanPO.account)
                && judgeEqual(password, salesmanPO.password);
    }
}