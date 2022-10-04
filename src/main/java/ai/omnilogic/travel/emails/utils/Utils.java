package ai.omnilogic.travel.emails.utils;

import ai.omnilogic.travel.emails.models.hotel.HotelType;
import com.azure.cosmos.implementation.guava25.base.Optional;
import com.microtripit.mandrillapp.lutung.logging.Logger;
import com.microtripit.mandrillapp.lutung.logging.LoggerFactory;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

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

    public static String formatDecimal( BigDecimal number ) {

        return NumberFormat.getCurrencyInstance( new Locale( "pt", "BR" ) ).format( number.doubleValue() );
    }

    public static String formatDateToBr( String date )  {
        Date date2 = null;
        try {
            date2 = new SimpleDateFormat( "yyyy-MM-dd" ).parse( date );
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.util.Locale locale = new java.util.Locale( "pt", "BR" );
        String asd = new SimpleDateFormat( "dd/MM/yyyy", locale ).format( date2 );
        return asd;

    }

    public static String imgHotel(HotelType hotel) {
        String uri = "";
        switch (hotel) {
            case CAETE:
                uri = "https://traveltaua.blob.core.windows.net/crmsydle/caete.jpg?sp=rl&st=2021-08-05T19:56:57Z&se=2080-08-06T19:56:00Z&sv=2020-08-04&sr=b&sig=qp7VepbmCls%2BUCo2kNTy6sFdq9RKd6yuWCWXSSug17Q%3D";
                break;
            case ATIBAIA:
                uri = "https://traveltaua.blob.core.windows.net/crmsydle/atibaia.jpg?sp=rl&st=2021-08-05T19:56:35Z&se=2080-08-06T19:56:00Z&sv=2020-08-04&sr=b&sig=lV%2BE8fvfWKUisIbMVhAUeRfymuvWq%2BcqKOtJIPM9wy8%3D";
                break;
            case ARAXA:
                uri = "https://traveltaua.blob.core.windows.net/crmsydle/araxa.jpg?sp=rl&st=2021-08-05T19:57:36Z&se=2080-08-06T19:57:00Z&sv=2020-08-04&sr=b&sig=QqrEJnpoFeWKe1lxnFl2v7ZDztynkDsg3s2zOQX934w%3D";
                break;
            case ALEXANIA:
                uri = "https://traveltaua.blob.core.windows.net/crmsydle/alexania.jpg?sp=rl&st=2021-08-05T19:57:16Z&se=2080-08-06T19:57:00Z&sv=2020-08-04&sr=b&sig=6ptLfOAJIjU0LXIh5TlnOy8lUE1blOq95d%2BTmNa2GzQ%3D";
                break;
            case ALEGRO:
                uri = "https://traveltaua.blob.core.windows.net/crmsydle/alegro.jpg?sp=rl&st=2021-08-05T19:56:05Z&se=2080-08-06T19:56:00Z&sv=2020-08-04&sr=b&sig=KXtCRt7EuLn1oag5WqBNmBGxkE6%2BxnEw5%2Ff4%2FsDB8SM%3D";
        }
        return uri;
    }

    public static Integer nightsStays(String stayDateStart, String stayDateEnd) {
        try {
            if (!Optional.fromNullable(stayDateStart).isPresent() ||
                    !Optional.fromNullable(stayDateEnd).isPresent())
                return 0;

            if (stayDateStart.length() < 10 || stayDateEnd.length() < 10)
                return 0;

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            LocalDate dateStart = LocalDate.parse(stayDateStart.substring(0, 10), formatter);

            LocalDate dateEnd = LocalDate.parse(stayDateEnd.substring(0, 10), formatter);

            return Long.valueOf(ChronoUnit.DAYS.between(dateStart, dateEnd)).intValue();
        } catch( Exception ex ) {
            ex.printStackTrace();
            return 0;
        }
    }

}
