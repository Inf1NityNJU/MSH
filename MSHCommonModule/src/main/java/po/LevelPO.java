package po;

import java.io.Serializable;

import static util.EqualJudgeHelper.judgeEqual;

/**
 * Created by Kray on 2016/11/11.
 */
public class LevelPO implements Serializable {
    /*
     * ID (level = id)
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

    public LevelPO(int level, int credit) {
        this.level = level;
        this.credit = credit;
    }

    public LevelPO(String ID, int level, int credit) {
        this(level, credit);
        this.ID = ID;
    }

    public LevelPO() {

    }

    /**
     * 比较两个PO
     *
     * @param o
     * @return 比较结果
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof LevelPO) {
            LevelPO levelPO = (LevelPO) o;
            return compareData(levelPO);
        }
        return false;
    }

    /**
     * 生成对象的hashcode
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return ID.hashCode();
    }

    /**
     * 分别比较每个数据
     *
     * @param levelPO
     * @return 比较结果
     */
    private boolean compareData(LevelPO levelPO) {
        return judgeEqual(credit, levelPO.credit)
                && judgeEqual(level, levelPO.level);
    }
}
