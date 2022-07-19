package hr.pgalina.chain_reaction.domain.features.product.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ProductColor {

    WHITE((short) 1, "WHITE"),
    DARK_GRAY((short) 2, "DARK-GRAY"),
    BLACK((short) 3, "BLACK");

    private Short idProductColor;
    private String value;

    public static ProductColor findByIdProductColor(Short idProductColor) {
        return Arrays.stream(ProductColor.values())
                .filter(productColor -> productColor.getIdProductColor().equals(idProductColor))
                .findFirst()
                .orElse(null);
    }
}
