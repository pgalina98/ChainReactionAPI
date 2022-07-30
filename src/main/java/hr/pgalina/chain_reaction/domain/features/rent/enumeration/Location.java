package hr.pgalina.chain_reaction.domain.features.rent.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Location {

    AUSTIN((short) 1, "Austin"),
    BOSTON((short) 2, "Boston"),
    NEW_YORK((short) 3, "New York"),
    TAOS((short) 4, "Taos"),
    MADISON((short) 5, "Madison"),
    SAVANNAH((short) 6, "Savannah"),
    NASHVILLE((short) 7, "Nashville");

    private Short idLocation;
    private String value;

    public static Location findByIdLocation(Short idLocation) {
        return Arrays.stream(Location.values())
                .filter(location -> location.getIdLocation().equals(idLocation))
                .findFirst()
                .orElse(null);
    }
}