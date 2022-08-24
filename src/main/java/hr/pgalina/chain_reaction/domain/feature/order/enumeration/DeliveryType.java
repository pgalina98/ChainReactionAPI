package hr.pgalina.chain_reaction.domain.feature.order.enumeration;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import hr.pgalina.chain_reaction.domain.exception.BadRequestException;
import hr.pgalina.chain_reaction.domain.exception.constant.ErrorTypeConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

import static hr.pgalina.chain_reaction.domain.exception.constant.ExceptionMessages.DELIVERY_TYPE_DOES_NOT_EXIST;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
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

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static DeliveryType fromIdDeliveryType(Short idDeliveryType) {
        if (idDeliveryType != null) {
            for (DeliveryType deliveryType : DeliveryType.values()) {
                if (deliveryType.getIdDeliveryType().equals(idDeliveryType)) {
                    return deliveryType;
                }
            }
        }

        throw new BadRequestException(ErrorTypeConstants.ERROR, HttpStatus.NOT_FOUND, DELIVERY_TYPE_DOES_NOT_EXIST);
    }
}
