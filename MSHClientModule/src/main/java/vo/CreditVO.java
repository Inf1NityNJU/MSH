package vo;

import util.CreditAction;
import util.DateUtil;

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
     * @param orderVO
     */
    public CreditVO(int deltaCredit, int resultCredit, CreditAction creditAction, OrderVO orderVO, DateUtil date) {
        this.date = date;
        this.deltaCredit = deltaCredit;
        this.resultCredit = resultCredit;
        this.creditAction = creditAction;
        this.orderID = orderVO.orderID;
    }
}
