package vo;

import static util.EqualJudgeHelper.judgeEqual;

/**
 * Created by Kray on 2016/11/23.
 */
public class SalesmanVO_Register extends SalesmanVO {
    /**
     * 网站营销人员姓名
     */
    public String salesmanName;
    /**
     * 账号
     */
    public String account;
    /**
     * 密码
     */
    public String password;

    /**
     * 注册网站营销人员构造方法
     *
     * @param name
     * @param account
     * @param password
     */
    public SalesmanVO_Register(String name, String account, String password) {
        this.salesmanName = name;
        this.account = account;
        this.password = password;
    }

    /**
     * 比较两个VO
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof SalesmanVO_Register) {
            SalesmanVO_Register salesmanVO = (SalesmanVO_Register) o;
            return compareData(salesmanVO);
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
     * @param salesmanVO
     * @return 比较结果
     */
    private boolean compareData(SalesmanVO_Register salesmanVO) {
        return judgeEqual(salesmanName, salesmanVO.salesmanName)
                && judgeEqual(account, salesmanVO.account)
                && judgeEqual(password, salesmanVO.password);
    }
}
