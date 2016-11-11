package util;

import static util.EqualJudgeHelper.judgeEqual;

/**
 * Created by Sorumi on 16/10/12.
 */
public class DateUtil {

    public int year;
    public int month;
    public int day;

    public DateUtil(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }


    /**
     * 比较两个data
     *
     * @param o
     * @return 比较结果
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof DateUtil) {
            DateUtil dateUtil = (DateUtil) o;
            return compareData(dateUtil);
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
        return day;
    }

    /**
     * 分别比较每个数据
     *
     * @param dateUtil
     * @return 比较结果
     */
    private boolean compareData(DateUtil dateUtil) {
        return judgeEqual(year, dateUtil.year) && judgeEqual(month, dateUtil.month) && judgeEqual(day, dateUtil.day);
    }
}
