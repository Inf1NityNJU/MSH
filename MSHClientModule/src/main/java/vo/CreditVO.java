package vo;

import util.CreditAction;
import util.DateUtil;

import static util.EqualJudgeHelper.judgeEqual;

/**
 * Created by Kray on 2016/10/12.
 */
public class CreditVO {
    /**
     * 信用记录对应订单ID
     */
    public String orderID;
    /**
     * 信用记录日期
     */
    public DateUtil date;
    /**
     * 信用记录变化数值
     */
    public int deltaCredit;
    /**
     * 信用记录结果数值
     */
    public int resultCredit;
    /**
     * 信用记录动作
     */
    public CreditAction creditAction;

    /**
     * 信用记录构造方法,包括变化数值、结果数值、操作类型、订单编号
     *
     * @param deltaCredit
     * @param resultCredit
     * @param creditAction
     * @param orderID
     */
    public CreditVO(int deltaCredit, int resultCredit, CreditAction creditAction, String orderID, DateUtil date) {
        this.date = date;
        this.deltaCredit = deltaCredit;
        this.resultCredit = resultCredit;
        this.creditAction = creditAction;
        this.orderID = orderID;
    }

    /**
     * 比较两个VO
     *
     * @param o
     * @return 比较结果
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof CreditVO) {
            CreditVO creditVO = (CreditVO) o;
            return compareData(creditVO);
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
        return resultCredit;
    }

    /**
     * 分别比较每个数据
     *
     * @param creditVO
     * @return 比较结果
     */
    private boolean compareData(CreditVO creditVO) {
        return judgeEqual(creditAction, creditVO.creditAction)
                && judgeEqual(deltaCredit, creditVO.deltaCredit)
                && judgeEqual(resultCredit, creditVO.resultCredit)
                && judgeEqual(orderID, creditVO.orderID)
                && judgeEqual(date, creditVO.date);
    }
}
