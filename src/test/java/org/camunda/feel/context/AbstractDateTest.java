package org.camunda.feel.context;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.camunda.feel.context.AbstractDate.format;
import static org.junit.Assert.assertEquals;

public class AbstractDateTest {
    public static final String DATE_TIME = "2019-07-30T15:48:00Z";

    @Test
    public void sqlMilliFormat(){
        Map<String,Object> map = new HashMap<>();
        map.put("format","sql_milli");
        map.put("timeZone","Europe/Berlin");
        String result = format(DATE_TIME,map);
        assertEquals("2019-07-30 17:48:00.000", result);
    }

    @Test
    public void isoMilliFormat(){
        Map<String,Object> map = new HashMap<>();
        map.put("format","iso_milli");
        map.put("timeZone","UTC");
        String result = format(DATE_TIME,map);
        assertEquals("2019-07-30T15:48:00.000Z", result);
    }

    @Test
    public void existingFormat(){
        Map<String,Object> map = new HashMap<>();
        map.put("format","rfc_1123_date_time");
        map.put("timeZone","UTC");
        map.put("existingFormat","yyyy-MM-dd'T'HH:mm:ssXXX");
        String result = format(DATE_TIME,map);
        assertEquals("Tue, 30 Jul 2019 15:48:00 GMT", result);
    }

    @Test
    public void inputFormat(){
        Map<String,Object> map = new HashMap<>();
        map.put("format","dd.MM.yyyy");
        map.put("timeZone","UTC");
        String result = format(DATE_TIME,map);
        assertEquals("30.07.2019", result);
    }

    @Test
    public void timestampFormat(){
        Map<String,Object> map = new HashMap<>();
        map.put("format","iso_sec");
        map.put("timeZone","UTC");
        String result = format(1378653552L,map);
        assertEquals("2013-09-08T15:19:12Z", result);
    }

    @Test
    public void timestampMilliFormat(){
        Map<String,Object> map = new HashMap<>();
        map.put("format","iso_milli");
        map.put("timeZone","UTC");
        String result = format(1378653552123L,map);
        assertEquals("2013-09-08T15:19:12.123Z", result);
    }


    @Test
    public void timestampNanoFormat(){
        Map<String,Object> map = new HashMap<>();
        map.put("format","iso");
        map.put("timeZone","UTC");
        String result = format(1378653552123456000L,map);
        assertEquals("2013-09-08T15:19:12.123456Z", result);
    }

    @Test
    public void timestampMicroFormat(){
        Map<String,Object> map = new HashMap<>();
        map.put("format","iso");
        map.put("timeZone","UTC");
        String result = format(1378653552000123456L,map);
        assertEquals("2013-09-08T15:19:12.123456Z", result);
    }
}
