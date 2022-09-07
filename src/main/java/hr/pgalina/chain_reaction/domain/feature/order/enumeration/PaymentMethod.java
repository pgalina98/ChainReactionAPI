package hr.pgalina.chain_reaction.domain.feature.order.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import hr.pgalina.chain_reaction.domain.exception.BadRequestException;
import hr.pgalina.chain_reaction.domain.exception.constant.ErrorTypeConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

import static hr.pgalina.chain_reaction.domain.exception.constant.ExceptionMessages.PAYMENT_METHOD_DOES_NOT_EXIST;

@Getter
@AllArgsConstructor
public enum PaymentMethod {

    CASH((short) 1, "CASH"),
    CREDIT_CART((short) 2, "CREDIT CART"),
    PAY_PAL((short) 3, "PAY PAL"),
    APPLE_PAY((short) 4, "APPLE PAY");

    private final Short idPaymentMethod;
    private final String value;

    public static PaymentMethod findByIdPaymentMethod(Short idPaymentMethod) {
        return Arrays.stream(PaymentMethod.values())
            .filter(paymentMethod -> paymentMethod.getIdPaymentMethod().equals(idPaymentMethod))
            .findFirst()
            .orElse(null);
    }

    public static PaymentMethod findByValue(String value) {
        return Arrays.stream(PaymentMethod.values())
            .filter(paymentMethod -> paymentMethod.getValue().equals(value))
            .findFirst()
            .orElse(null);
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static PaymentMethod fromIdPaymentMethod(Short idPaymentMethod) {
        if (idPaymentMethod != null) {
            for (PaymentMethod paymentMethod : PaymentMethod.values()) {
                if (paymentMethod.getIdPaymentMethod().equals(idPaymentMethod)) {
                    return paymentMethod;
                }
            }
        }

        throw new BadRequestException(ErrorTypeConstants.ERROR, HttpStatus.NOT_FOUND, PAYMENT_METHOD_DOES_NOT_EXIST);
    }
}
