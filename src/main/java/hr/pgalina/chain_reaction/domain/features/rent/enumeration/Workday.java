package hr.pgalina.chain_reaction.domain.features.rent.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
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

    private Short idWorkday;
    private String dayName;
    private LocalTime startTime;
    private LocalTime endTime;

    public static Workday findByCurrentDay() {
        String currentDayName = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(new Date());

        return Arrays.stream(Workday.values())
                .filter(workday -> workday.getDayName().equals(currentDayName))
                .findFirst()
                .orElse(null);
    }
}
