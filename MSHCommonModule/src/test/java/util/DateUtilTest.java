package util;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Created by SilverNarcissus on 2016/11/18.
 */
public class DateUtilTest {
    @Test
    public void increase() throws Exception {
        DateUtil dateUtil = new DateUtil(2016, 11, 29);
        dateUtil.increase();
        System.out.println(dateUtil);
    }

    @Test
    public void createByString() {
        DateUtil dateUtil = new DateUtil(2010, 5, 27);
        assertEquals(dateUtil, new DateUtil("2010-5-27"));
    }

    @Test
    public void testCalendar() {
        Calendar c = Calendar.getInstance();
        System.out.println(new DateUtil());
    }

    @Test
    public void testIsInRange() {
        DateUtil dateUtil1 = new DateUtil(2016, 10, 10);
        DateUtil start = new DateUtil(2014, 12, 20);
        DateUtil end = new DateUtil(2016, 10, 12);
        assertEquals(true, dateUtil1.isInRange(start, end));
    }
}