package vo;

import bl.userbl.Salesman;

import static util.EqualJudgeHelper.judgeEqual;

/**
 * Created by Kray on 2016/10/12.
 */
public class SalesmanVO extends UserVO {
    /**
     * 网站营销人员ID
     */
    public String salesmanID;
    /**
     * 网站营销人员姓名
     */
    public String salesmanName;

    /**
     * 网站营销人员的构造方法,包括人员ID和姓名
     *
     * @param id
     * @param name
     */
    public SalesmanVO(String id, String name) {
        this.salesmanID = id;
        this.salesmanName = name;
    }

    /**
     * 比较两个VO
     *
     * @param o
     * @return 比较结果
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof SalesmanVO) {
            SalesmanVO salesmanVO = (SalesmanVO) o;
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
    private boolean compareData(SalesmanVO salesmanVO) {
        return judgeEqual(salesmanID, salesmanVO.salesmanID)
                && judgeEqual(salesmanName, salesmanVO.salesmanName);
    }
}
