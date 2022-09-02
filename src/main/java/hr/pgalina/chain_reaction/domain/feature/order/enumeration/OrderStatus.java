package hr.pgalina.chain_reaction.domain.feature.order.enumeration;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import hr.pgalina.chain_reaction.domain.exception.BadRequestException;
import hr.pgalina.chain_reaction.domain.exception.constant.ErrorTypeConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

import static hr.pgalina.chain_reaction.domain.exception.constant.ExceptionMessages.ORDER_STATUS_DOES_NOT_EXIST;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum OrderStatus {

    CREATED((short) 1, "CREATED"),
    IN_TRANSIT((short) 2, "IN TRANSIT"),
    DELIVERED((short) 3, "DELIVERED");

    private final Short idOrderStatus;
    private final String value;

    public static OrderStatus findByIdOrderStatus(Short idOrderStatus) {
        return Arrays.stream(OrderStatus.values())
            .filter(orderStatus -> orderStatus.getIdOrderStatus().equals(idOrderStatus))
            .findFirst()
            .orElse(null);
    }

    public static OrderStatus findByValue(String value) {
        return Arrays.stream(OrderStatus.values())
            .filter(orderStatus -> orderStatus.getValue().equals(value))
            .findFirst()
            .orElse(null);
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static OrderStatus fromIdOrderStatis(Short idOrderStatus) {
        if (idOrderStatus != null) {
            for (OrderStatus orderStatus : OrderStatus.values()) {
                if (orderStatus.getIdOrderStatus().equals(idOrderStatus)) {
                    return orderStatus;
                }
            }
        }

        throw new BadRequestException(ErrorTypeConstants.ERROR, HttpStatus.NOT_FOUND, ORDER_STATUS_DOES_NOT_EXIST);
    }
}
