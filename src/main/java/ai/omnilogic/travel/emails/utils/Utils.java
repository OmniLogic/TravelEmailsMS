package ai.omnilogic.travel.emails.utils;

import com.microtripit.mandrillapp.lutung.logging.Logger;
import com.microtripit.mandrillapp.lutung.logging.LoggerFactory;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {
    private static final Logger log = LoggerFactory.getLogger(Utils.class);

    public static String dateNow(){
        return Utils.dateNow("uuuu-MM-dd'T'HH:mm:ss.SSS");
    }

    public static String dateNow(String pattern){
        DateTimeFormatter dtfDateTime = DateTimeFormatter.ofPattern(pattern);
        Instant now = Instant.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(now, ZoneId.systemDefault());
        return dtfDateTime.format(zonedDateTime);
    }

    public static LocalDate stringToDate(String date, DateTimeFormatter formatter) {
        return LocalDate.parse(date, formatter);
    }

}
