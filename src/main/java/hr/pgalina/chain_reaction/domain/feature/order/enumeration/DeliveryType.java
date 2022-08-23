package hr.pgalina.chain_reaction.domain.feature.order.enumeration;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum DeliveryType {

    STORE((short) 1, "STORE"),
    DHL_DELIVERY((short) 2, "DHL_DELIVERY"),
    FED_EX_DELIVERY((short) 3, "FED_EX_DELIVERY");

    private final Short idDeliveryType;
    private final String value;

    public static DeliveryType findByIdDeliveryType(Short idDeliveryType) {
        return Arrays.stream(DeliveryType.values())
                .filter(deliveryType -> deliveryType.getIdDeliveryType().equals(idDeliveryType))
                .findFirst()
                .orElse(null);
    }

    public static DeliveryType findByValue(String value) {
        return Arrays.stream(DeliveryType.values())
                .filter(deliveryType -> deliveryType.getValue().equals(value))
                .findFirst()
                .orElse(null);
    }
}
