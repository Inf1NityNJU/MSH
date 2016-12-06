package util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by SilverNarcissus on 2016/12/5.
 */
public class TimeUtilTest {
    @Test
    public void useStringToBuildTimeUtil() {
        TimeUtil timeUtil = new TimeUtil("2016-12-05 13:04:30");
        System.out.println(timeUtil);
    }

    @Test
    public void getIntervalTime() throws Exception {
        TimeUtil timeUtil1 = new TimeUtil("2016-12-05 13:04:30");
        TimeUtil timeUtil2 = new TimeUtil("2016-12-05 19:04:30");
        System.out.println(timeUtil1.getIntervalTime(timeUtil2));
    }

    @Test
    public void equals() throws Exception {

    }

}