package util;

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
    @Override
    public boolean equals(Object o){
        DateUtil dateUtil=(DateUtil) o;
        return this.year==dateUtil.year&&this.month==dateUtil.month&&this.day==dateUtil.day;
    }
}
