package util;

/**
 * Created by Sorumi on 16/10/12.
 */
public enum OrderState {
    Unexecuted("未执行", "12B7F3", 0),
    Executed("已执行", "00CCCC", 1),
    Cancelled("已撤销", "BC52FD", 2),
    Abnormal("异常", "FC537D", 3);


    private final String name;
    private final String color;
    private final int num;

    private OrderState(String name, String color, int num) {

        this.name = name;
        this.color = color;
        this.num = num;
    }

    public String getName() {
        return this.name;
    }

    public String getColor() {
        return this.color;
    }

    public int getNum() {
        return this.num;
    }

}
