package hr.pgalina.chain_reaction.domain.features.product.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ProductColor {

    WHITE((short) 1, "WHITE"),
    GRAY_DARK((short) 2, "GRAY-DARK"),
    BLACK((short) 3, "BLACK"),
    BLUE((short) 4, "BLUE"),
    ORANGE((short) 5, "ORANGE"),
    PINK((short) 6, "PINK"),
    YELLOW((short) 7, "YELLOW");

    private Short idProductColor;
    private String value;

    public static ProductColor findByIdProductColor(Short idProductColor) {
        return Arrays.stream(ProductColor.values())
                .filter(productColor -> productColor.getIdProductColor().equals(idProductColor))
                .findFirst()
                .orElse(null);
    }
}
