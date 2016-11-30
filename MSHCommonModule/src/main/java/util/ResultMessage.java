package util;

/**
 * Created by Sorumi on 16/10/12.
 */
public enum ResultMessage {
    SUCCESS, // 成功
    FAILED, // 失败
    WRONG, // 错误
    EXIST, // 已存在
    NOT_EXIST, // 不存在
    TOO_LONG, // 输入过长
    TOO_SHORT, // 输入过短
    INVALID, // 含有非法字符
    NULL, // 输入为空
    INSUFFICIENT, //数量不足
    SUFFICIENT,//数量充足
    TRUE,//判断为真
    FALSE;//判断为假


    public String toString() {
        switch (this) {
            case SUCCESS:
                return "成功";
            case FAILED:
                return "失败";
            case WRONG:
                return "错误";
            case EXIST:
                return "已存在";
            case NOT_EXIST:
                return "不存在";
            case TOO_LONG:
                return "输入过长";
            case TOO_SHORT:
                return "输入过短";
            case INVALID:
                return "含有非法字符";
            case NULL:
                return "输入为空";
            case SUFFICIENT:
                return "房间数量充足";
            case INSUFFICIENT:
                return "房间数量不足";
            case TRUE:
                return "真";
            case FALSE:
                return "假";
            default:
                return null;
        }
    }

}
