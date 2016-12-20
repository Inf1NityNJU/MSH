package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import static util.EqualJudgeHelper.judgeEqual;

/**
 * Created by Sorumi on 16/10/12.
 */
public class TimeUtil {

    public DateUtil date;

    public int hour;
    public int min;
    public int sec;

    public TimeUtil(String timeUtil) {
        //第一次分割
        String[] dateAndTime = timeUtil.split(" ");
        date = new DateUtil(dateAndTime[0]);
        //
        String[] time = dateAndTime[1].split(":");
        hour = Integer.parseInt(time[0]);
        min = Integer.parseInt(time[1]);
        sec = Integer.parseInt(time[2]);
    }

    public TimeUtil(int year, int month, int day, int hour, int min, int sec) {
        date = new DateUtil(year, month, day);
        this.hour = hour;
        this.min = min;
        this.sec = sec;
    }

    public TimeUtil (LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = dateTime.format(formatter);

        String[] dateAndTime = formattedDateTime.split(" ");
        date = new DateUtil(dateAndTime[0]);
        //
        String[] time = dateAndTime[1].split(":");
        hour = Integer.parseInt(time[0]);
        min = Integer.parseInt(time[1]);
        sec = Integer.parseInt(time[2]);

    }

    public int compareTime(TimeUtil time) {
        if (!date.equals(time.date)) {
            return date.compareDate(time.date);
        } else {
            if (hour != time.hour) {
                return hour - time.hour;
            } else {
                if (min != time.min) {
                    return min - time.min;
                } else {
                    return sec - time.sec;
                }
            }
        }
    }

    /**
     * 得到两个时间的间隔
     *
     * @param timeUtil 另一个时间
     * @return 时间间隔(毫秒)
     */
    public long getIntervalTime(TimeUtil timeUtil) {
        Calendar calendar = Calendar.getInstance();
        //第一个日期的秒值
        long firstSecond = 0;
        calendar.set(date.year
                , date.month - 1
                , date.day
                , hour
                , min
                , sec);
        firstSecond = calendar.getTimeInMillis();
        //第二个日期的秒值
        long secondSecond = 0;
        calendar.set(timeUtil.date.year
                , timeUtil.date.month - 1
                , timeUtil.date.day
                , timeUtil.hour
                , timeUtil.min
                , timeUtil.sec);
        secondSecond = calendar.getTimeInMillis();
        //
        return secondSecond - firstSecond;
    }


    /**
     * 比较两个time
     *
     * @param o
     * @return 比较结果
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof TimeUtil) {
            TimeUtil timeUtil = (TimeUtil) o;
            return equalsTime(timeUtil);
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
        return sec;
    }

    /**
     * 分别比较每个数据
     *
     * @param timeUtil
     * @return 比较结果
     */
    private boolean equalsTime(TimeUtil timeUtil) {
        return judgeEqual(date, timeUtil.date)
                && judgeEqual(hour, timeUtil.hour)
                && judgeEqual(min, timeUtil.min)
                && judgeEqual(sec, timeUtil.sec);
    }

    @Override
    public String toString() {
        return date.toString() + " " + timeString();
    }

    public String timeString() {
        String hour = String.valueOf(this.hour);
        String min = String.valueOf(this.min);
        String sec = String.valueOf(this.sec);
        if (hour.length() == 1) {
            hour = "0" + hour;
        }
        if (min.length() == 1) {
            min = "0" + min;
        }
        if (sec.length() == 1) {
            sec = "0" + sec;
        }
        return hour + ":" + min + ":" + sec;
    }

}
