package fr.koolicar.koolicarapi;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jean-philippedescamps on 20/12/2016.
 */
public class Utils {

    public static String now(String dateFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(new Date());
    }
}
