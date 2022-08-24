package hr.pgalina.chain_reaction.domain.feature.product.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ProductType {

    E_BIKE((short) 1, "E-BIKE"),
    BIKE((short) 2, "BIKE"),
    HELMET((short) 3, "HELMET");

    private final Short idProductType;
    private final String value;

    public static ProductType findByIdProductType(Short idProductType) {
        return Arrays.stream(ProductType.values())
            .filter(productType -> productType.getIdProductType().equals(idProductType))
            .findFirst()
            .orElse(null);
    }

    public static ProductType findByValue(String value) {
        return Arrays.stream(ProductType.values())
            .filter(productType -> productType.getValue().equals(value))
            .findFirst()
            .orElse(null);
    }
}