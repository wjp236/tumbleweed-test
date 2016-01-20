package wang.tumbleweed.test.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by mylover on 10/30/15.
 */
public class DateTest {

    public Logger log = LogManager.getLogger(DateTest.class);

    @Test
    public void test11() {
        Calendar curr = Calendar.getInstance();
        curr.set(Calendar.YEAR,curr.get(Calendar.YEAR)-1);
        Date date=curr.getTime();
        SimpleDateFormat dateformat=new SimpleDateFormat("yyyyMMdd");
        String lastToday = dateformat.format(date);
        int date3 = Integer.parseInt(lastToday);
        log.info(date3);
    }

}
