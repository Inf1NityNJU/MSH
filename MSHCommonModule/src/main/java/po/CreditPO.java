package po;

import util.CreditAction;

import util.DateUtil;

import java.io.Serializable;
import java.util.Date;

import static util.EqualJudgeHelper.judgeEqual;

/**
 * Created by SilverNarcissus on 16/10/11.
 */
public class CreditPO implements Serializable {

    /**
     * 信用记录对应订单ID
     */
    private String orderID;
    /**
     * 信用记录日期
     */
    private String date;
    /**
     * 信用记录变化数值
     */
    private int deltaCredit;
    /**
     * 信用记录结果数值
     */
    private int resultCredit;
    /**
     * 信用记录动作
     */
    private CreditAction creditAction;
    /**
     * 信用记录所属客户ID
     */
    private String clientID;

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDeltaCredit() {
        return deltaCredit;
    }

    public void setDeltaCredit(int deltaCredit) {
        this.deltaCredit = deltaCredit;
    }

    public int getResultCredit() {
        return resultCredit;
    }

    public void setResultCredit(int resultCredit) {
        this.resultCredit = resultCredit;
    }

    public CreditAction getCreditAction() {
        return creditAction;
    }

    public void setCreditAction(CreditAction creditAction) {
        this.creditAction = creditAction;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public CreditPO() {

    }

    /**
     * 信用记录构造方法
     *
     * @param orderID
     * @param date
     * @param deltaCredit
     * @param resultCredit
     * @param creditAction
     * @param clientID
     */
    public CreditPO(String orderID, String date, int deltaCredit, int resultCredit, CreditAction creditAction, String clientID) {
        this.orderID = orderID;
        this.date = date;
        this.deltaCredit = deltaCredit;
        this.resultCredit = resultCredit;
        this.creditAction = creditAction;
        this.clientID = clientID;
    }

    /**
     * 初始的信用记录构造方法
     *
     * @param clientID
     */
    public CreditPO(String clientID) {
        this.orderID = "-" + clientID;
        DateUtil dateUtil = new DateUtil();
        this.date = dateUtil.toString();
        this.deltaCredit = 0;
        this.resultCredit = 500;
        this.creditAction = CreditAction.INIT_CREDIT;
        this.clientID = clientID;
    }

    /**
     * 比较两个PO
     *
     * @param o
     * @return 比较结果
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof CreditPO) {
            CreditPO creditPO = (CreditPO) o;
            return compareData(creditPO);
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
        return orderID.hashCode();
    }

    /**
     * 分别比较每个数据
     *
     * @param creditPO
     * @return 比较结果
     */
    private boolean compareData(CreditPO creditPO) {
        return judgeEqual(clientID, creditPO.clientID)
                && judgeEqual(orderID, creditPO.orderID)
                && judgeEqual(deltaCredit, creditPO.deltaCredit)
                && judgeEqual(resultCredit, creditPO.resultCredit)
                && judgeEqual(creditAction, creditPO.creditAction)
                && judgeEqual(date, creditPO.date);
    }
}
