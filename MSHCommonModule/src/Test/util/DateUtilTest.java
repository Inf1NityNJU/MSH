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

    }
    @Test
    public void createByString(){
        DateUtil dateUtil=new DateUtil(2010,5,27);
        assertEquals(dateUtil,new DateUtil("2010-5-27"));
    }
    @Test
    public void testCalendar(){
        Calendar c=Calendar.getInstance();
        System.out.println(new DateUtil());
    }
}