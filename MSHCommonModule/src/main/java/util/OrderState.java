package util;

/**
 * Created by Sorumi on 16/10/12.
 */
public enum OrderState {
    Unexecuted("未执行", "12B7F3"),
    Executed("已执行", "00CCCC"),
    Abnormal("异常", "BC52FD"),
    Cancelled("已撤销", "FC537D");

    private final String name;
    private final String color;

    private OrderState(String name, String color) {

        this.name = name;
        this.color = color;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getColor() {
        return this.color;
    }
}
