package util;

/**
 * Created by Kray on 2016/10/12.
 */
public enum CreditAction {
    ADD_CREDIT("执行订单", "12B7F3"),     //完成订单
    DEDUCT_CREDIT("撤销订单", "FC537D"),  //撤销订单
    REVOKE_CREDIT("申诉成功", "F8E81C"),  //申诉成功
    INIT_CREDIT("初始信用", "00CCCC"),    //初始信用
    RECHARGE_CREDIT("信用充值", "BC52FD"),//充值信用
    OVERDUE_CREDIT("订单超时", "FD9C4B");  //超时扣除信用

    private final String name;
    private final String color;

    private CreditAction(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return this.name;
    }

    public String getColor() {
        return this.color;
    }
}
