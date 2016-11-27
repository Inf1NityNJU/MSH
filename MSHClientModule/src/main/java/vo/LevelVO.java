package vo;

/**
 * Created by Kray on 2016/11/11.
 */
public class LevelVO {
    /*
     * 到此等级需要信用值
     */
    public String credit;
    /*
     * 等级
     */
    public String level;

    public LevelVO(String level, String credit) {
        this.level = level;
        this.credit = credit;
    }
}
