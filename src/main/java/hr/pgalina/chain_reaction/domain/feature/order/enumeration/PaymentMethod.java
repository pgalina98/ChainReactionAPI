package hr.pgalina.chain_reaction.domain.feature.order.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum PaymentMethod {

    CASH((short) 1, "CASH"),
    CREDIT_CART((short) 2, "CREDIT_CART"),
    PAY_PAL((short) 3, "PAY_PAL"),
    APPLE_PAY((short) 4, "APPLE_PAY");

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
}
