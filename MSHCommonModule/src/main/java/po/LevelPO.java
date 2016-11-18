package po;

/**
 * Created by Kray on 2016/11/11.
 */
public class LevelPO {
    /*
     * ID
     */
    private String ID;
    /*
     * 信用值
     */
    private int credit;
    /*
     * 等级
     */
    private int level;

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public LevelPO(int credit, int level) {
        this.credit = credit;
        this.level = level;
    }


    public LevelPO(String ID, int credit, int level) {
        this(credit, level);
        this.ID = ID;
    }

    public LevelPO() {

    }
}
