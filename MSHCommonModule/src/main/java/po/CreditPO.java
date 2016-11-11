package po;

import util.CreditAction;

import util.DateUtil;

/**
 * Created by SilverNarcissus on 16/10/11.
 */
public class CreditPO {

    /**
     * 信用记录对应订单ID
     */
    private String orderID;
    /**
     * 信用记录日期
     */
    private DateUtil date;
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

    /**
     *
     * @return
     */
    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public DateUtil getDate() {
        return date;
    }

    public void setDate(DateUtil date) {
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

    public CreditPO(String orderID, DateUtil date, int deltaCredit, int resultCredit, CreditAction creditAction, String clientID) {
        this.orderID = orderID;
        this.date = date;
        this.deltaCredit = deltaCredit;
        this.resultCredit = resultCredit;
        this.creditAction = creditAction;
        this.clientID = clientID;
    }

    public CreditPO(CreditAction creditAction, String clientID) {
        this.orderID = "-1";
        this.date = new DateUtil(2015,10,10);
        this.deltaCredit = 0;
        this.resultCredit = 500;
        this.creditAction = CreditAction.INIT_CREDIT;
        this.clientID = clientID;
    }
}