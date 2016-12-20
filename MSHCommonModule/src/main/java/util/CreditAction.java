package util;

/**
 * Created by Kray on 2016/10/12.
 */
public enum CreditAction {
    ADD_CREDIT("执行订单", "12B7F3", 1),     //完成订单
    DEDUCT_CREDIT("撤销订单", "FC537D", 2),  //撤销订单
    REVOKE_CREDIT("申诉成功", "F8E81C", 3),  //申诉成功
    INIT_CREDIT("初始信用", "00CCCC", 4),    //初始信用
    RECHARGE_CREDIT("信用充值", "BC52FD", 5),//充值信用
    OVERDUE_CREDIT("订单超时", "FD9C4B", 6);  //超时扣除信用

    private final String name;
    private final String color;
    private final int num;

    private CreditAction(String name, String color, int n) {
        this.name = name;
        this.color = color;


        this.num = 0;
    }

    public String getName() {
        return this.name;
    }

    public String getColor() {
        return this.color;
    }

    public int getNum() {
        return num;
    }
}
