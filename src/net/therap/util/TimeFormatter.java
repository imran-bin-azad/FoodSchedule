package net.therap.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created with IntelliJ IDEA.
 * User: imran.azad
 * Date: 5/22/14
 * Time: 11:04 AM
 */
public class TimeFormatter {
    private static final int SECONDS_IN_HOUR = 3600;
    private static final int MILLIS_IN_SECOND = 1000;

    public static String getTimeWithGMTOffsetAndDST(int gmtOffset, int dst) {
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        timeZone.setRawOffset( (gmtOffset+dst) * (SECONDS_IN_HOUR * MILLIS_IN_SECOND) );

        String format = "yyyy-MM-dd hh:mm:ss:SSSS a";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        simpleDateFormat.setTimeZone(timeZone);
        return (simpleDateFormat.format(new Date()));
    }

    public static String getDayOfClientZone(String clientZone) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
        long diffWithClientInMillis = getDiffWithClientZoneInMillis(clientZone);

        Date todayDate = new Date(System.currentTimeMillis() + diffWithClientInMillis);
        return simpleDateFormat.format(todayDate);
    }

    private static long getDiffWithClientZoneInMillis(String clientZone) {
        //TODO: make it generic

        long diffInMillis = 0;
        if (clientZone.equals("BDT")) {
            diffInMillis = 0;
        }
        return diffInMillis;
    }
}
