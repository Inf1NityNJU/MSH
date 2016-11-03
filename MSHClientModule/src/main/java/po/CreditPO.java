package po;

import util.CreditAction;

import java.util.Date;

/**
 * Created by SilverNarcissus on 16/10/11.
 */
public class CreditPO {

    private String orderID;
    private Date date;
    private int deltaCredit;
    private int resultCredit;
    private CreditAction creditAction;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    public CreditPO(String orderID, Date date, int deltaCredit, int resultCredit, CreditAction creditAction, String clientID) {
        this.orderID = orderID;
        this.date = date;
        this.deltaCredit = deltaCredit;
        this.resultCredit = resultCredit;
        this.creditAction = creditAction;
        this.clientID = clientID;
    }

    public CreditPO(CreditAction creditAction, String clientID) {
        this.orderID = "-1";
        this.date = new Date();
        this.deltaCredit = 0;
        this.resultCredit = 500;
        this.creditAction = CreditAction.INIT_CREDIT;
        this.clientID = clientID;
    }
}
