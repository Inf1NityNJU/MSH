package vo;

import util.CreditAction;
import util.DateUtil;

/**
 * Created by Kray on 2016/10/12.
 */
public class CreditVO {

    public String orderID;
    public DateUtil date;
    public int deltaCredit;
    public int resultCredit;
    public CreditAction creditAction;

    /**
     * 信用记录构造方法,包括变化数值、结果数值、操作类型、订单编号
     *
     * @param deltaCredit
     * @param resultCredit
     * @param creditAction
     * @param orderVO
     */
    public CreditVO(int deltaCredit, int resultCredit, CreditAction creditAction, OrderVO orderVO, DateUtil date){
        this.date = date;
        this.deltaCredit = deltaCredit;
        this.resultCredit = resultCredit;
        this.creditAction = creditAction;
        this.orderID = orderVO.orderID;
    }
}
