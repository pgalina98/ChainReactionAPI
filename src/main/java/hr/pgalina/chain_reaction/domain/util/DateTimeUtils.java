package hr.pgalina.chain_reaction.domain.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

    public static String formatDate(LocalDate date, String format) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(format);

        return dateFormatter.format(date);
    }
}
