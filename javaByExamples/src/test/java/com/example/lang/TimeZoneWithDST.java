package com.example.lang;

import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;

/**
 * Created by owu on 2017/7/24.
 */
public class TimeZoneWithDST {

    @Test
    public void testDst_DaylightSavingTime() throws Exception {
        // https://www.timeanddate.com/time/change/finland/mariehamn?year=2016
        // http://www.zeitverschiebung.net/en/timezone/europe--mariehamn
        TimeZone tz = TimeZone.getTimeZone("Europe/Mariehamn");
        FastDateFormat dateFormat = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss", tz);
        FastDateFormat tzDateFormat = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ssZZ", tz);

        testDate(dateFormat, tzDateFormat, "2016-03-26T12:00:00", "2016-03-26T12:00:00+02:00");
        testDate(dateFormat, tzDateFormat, "2016-03-27T12:00:00", "2016-03-27T12:00:00+03:00");

        testDate(dateFormat, tzDateFormat, "2016-10-29T12:00:00", "2016-10-29T12:00:00+03:00");
        testDate(dateFormat, tzDateFormat, "2016-10-30T12:00:00", "2016-10-30T12:00:00+02:00");

        testDate(dateFormat, tzDateFormat, "2016-11-11T12:00:00", "2016-11-11T12:00:00+02:00");

        Date d1 = tzDateFormat.parse("2016-03-26T12:00:00+02:00");
        Date d2 = tzDateFormat.parse("2016-03-27T12:00:00+03:00");
        assertEquals((24 - 1) * 60 * 60 * 1000, (d2.getTime() - d1.getTime()));

        d1 = tzDateFormat.parse("2016-03-26T12:00:00+02:00");
        d2 = tzDateFormat.parse("2016-03-27T12:00:00+02:00");
        assertEquals(24 * 60 * 60 * 1000, (d2.getTime() - d1.getTime()));
    }

    private void testDate(FastDateFormat dateFormat, FastDateFormat tzDateFormat, String source, String expected)
            throws ParseException {
        Date date = dateFormat.parse(source);
        assertEquals(expected, tzDateFormat.format(date));
    }
}
