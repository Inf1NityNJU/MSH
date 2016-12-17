package vo;

import util.CreditAction;
import util.DateUtil;

/**
 * Created by Sorumi on 16/12/17.
 */
public class CreditChangeInfoVO {

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
     * 信用记录动作
     */
    public CreditAction creditAction;

    /**
     * 信用记录构造方法,包括变化数值、结果数值、操作类型、订单编号
     *
     * @param deltaCredit
     * @param creditAction
     * @param orderID
     */
    public CreditChangeInfoVO(int deltaCredit, CreditAction creditAction, String orderID, DateUtil date) {
        this.date = date;
        this.deltaCredit = deltaCredit;
        this.creditAction = creditAction;
        this.orderID = orderID;
    }
}
