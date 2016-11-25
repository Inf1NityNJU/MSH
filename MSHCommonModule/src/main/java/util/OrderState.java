package util;

/**
 * Created by Sorumi on 16/10/12.
 */
public enum OrderState {
    Unexecuted("未执行", "12B7F3"),
    Executed("已执行", "00CCCC"),
    Abnormal("异常", "FC537D"),
    Cancelled("已撤销", "BC52FD");

    private final String name;
    private final String color;

    private OrderState(String name, String color) {

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
