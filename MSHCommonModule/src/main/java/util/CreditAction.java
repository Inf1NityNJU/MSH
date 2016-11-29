package util;

/**
 * Created by Kray on 2016/10/12.
 */
public enum CreditAction {
    ADD_CREDIT,     //完成订单
    DEDUCT_CREDIT,  //撤销订单
    REVOKE_CREDIT,  //申诉成功
    INIT_CREDIT,    //初始信用
    RECHARGE_CREDIT,//充值信用
    OVERDUE_CREDIT  //超时扣除信用
}
