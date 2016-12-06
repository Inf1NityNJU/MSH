package vo;

import static util.EqualJudgeHelper.judgeEqual;

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

    /**
     * 比较两个VO
     *
     * @param o
     * @return 比较结果
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof LevelVO) {
            LevelVO levelVO = (LevelVO) o;
            return compareData(levelVO);
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
        return level.hashCode();
    }

    /**
     * 分别比较每个数据
     *
     * @param levelVO
     * @return 比较结果
     */
    private boolean compareData(LevelVO levelVO) {
        return judgeEqual(level, levelVO.level)
                && judgeEqual(credit, levelVO.credit);

    }
}
