package hr.pgalina.chain_reaction.domain.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeUtils {

    public static String formatDate(LocalDate date, String format) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(format);

        return dateFormatter.format(date);
    }

    public static String formatDateWithLocale(LocalDate date, String format, Locale locale) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(format).withLocale(locale);

        return dateFormatter.format(date);
    }

    public static String formatTime(LocalTime time, String format) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(format);

        return timeFormatter.format(time);
    }

    public static boolean isExpired(LocalDate date) {
        LocalDate now = LocalDate.now();

        return date.isBefore(now);
    }
}
