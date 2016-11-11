package util;

import static util.EqualJudgeHelper.judgeEqual;

/**
 * Created by Sorumi on 16/10/12.
 */
public class TimeUtil {

    public DateUtil date;

    public int hour;
    public int min;
    public int sec;

    public TimeUtil(int year, int month, int day, int hour, int min, int sec) {
        date=new DateUtil(year,month,day);
        this.hour = hour;
        this.min = min;
        this.sec = sec;
    }
    private boolean compareData(TimeUtil timeUtil){
        return judgeEqual(date,timeUtil.date)&&judgeEqual(hour,timeUtil.hour)&&judgeEqual(min,timeUtil.min)&&judgeEqual(sec,timeUtil.sec);
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof TimeUtil) {
            TimeUtil timeUtil = (TimeUtil) o;
            return compareData(timeUtil);
        }
        return false;
    }

    @Override
    public int hashCode(){
        return sec;
    }
}
