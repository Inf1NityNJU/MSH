package vo;

/**
 * Created by Kray on 2016/11/11.
 */
public class LevelVO {
    /*
     * 当前信用值
     */
    public int currentCredit;
    /*
     * 距离下一等级还有的信用值
     */
    public int nextCredit;
    /*
     * 当前等级
     */
    public int currentLevel;

    public LevelVO(int currentCredit, int nextCredit, int currentLevel) {
        this.currentCredit = currentCredit;
        this.nextCredit = nextCredit;
        this.currentLevel = currentLevel;
    }
}
