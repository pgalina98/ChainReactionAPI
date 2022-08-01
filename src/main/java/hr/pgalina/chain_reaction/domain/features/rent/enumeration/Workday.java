package hr.pgalina.chain_reaction.domain.features.rent.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

@Getter
@AllArgsConstructor
public enum Workday {

    MONDAY((short) 1, "Monday", LocalTime.of(9, 0,0), LocalTime.of(17, 0,0)),
    TUESDAY((short) 2, "Tuesday", LocalTime.of(13, 0,0), LocalTime.of(21, 0,0)),
    WEDNESDAY((short) 3, "Wednesday", LocalTime.of(13, 0,0), LocalTime.of(21, 0,0)),
    THURSDAY((short) 4, "Thursday", LocalTime.of(13, 0,0), LocalTime.of(21, 0,0)),
    FRIDAY((short) 5, "Friday", LocalTime.of(9, 0,0), LocalTime.of(17, 0,0)),
    SATURDAY((short) 6, "Saturday", LocalTime.of(13, 0,0), LocalTime.of(21, 0,0)),
    SUNDAY((short) 7, "Sunday", LocalTime.of(15, 0,0), LocalTime.of(21, 0,0));

    private final Short idWorkday;
    private final String dayName;
    private final LocalTime startTime;
    private final LocalTime endTime;

    public static Workday findByCurrentDay() {
        String currentDayName = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(new Date());

        return Arrays.stream(Workday.values())
                .filter(workday -> workday.getDayName().equals(currentDayName))
                .findFirst()
                .orElse(null);
    }

    public static Workday findByDate(LocalDate date) {
        String dayName = new SimpleDateFormat("EEEE", Locale.ENGLISH)
            .format(java.util.Date
                .from(date.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant())
            );

        return Arrays.stream(Workday.values())
                .filter(workday -> workday.getDayName().equals(dayName))
                .findFirst()
                .orElse(null);
    }
}
