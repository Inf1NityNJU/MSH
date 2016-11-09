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

    private boolean compareData(DateUtil date){
        return judgeEqual(year,date.year)&&judgeEqual(month,date.month)&&judgeEqual(day,date.day);
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof DateUtil) {
            DateUtil dateUtil = (DateUtil) o;
            return compareData(dateUtil);
        }
        return false;
    }
}
