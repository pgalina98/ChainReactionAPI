package hr.pgalina.chain_reaction.domain.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class DateTimeUtils {

    public static String formatDate(LocalDate date, String format) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(format);

        return dateFormatter.format(date);
    }
}
