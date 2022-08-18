package hr.pgalina.chain_reaction.domain.util;

import java.util.Objects;

public class StringUtils {

    public static boolean isNullOrUndefined (String value) {
        if (Objects.isNull(value)) {
            return true;
        }

        if (value.equals("null")) {
            return true;
        }

        if (value.equals("undefined")) {
            return true;
        }

        return false;
    }
}
