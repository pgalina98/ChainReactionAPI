package hr.pgalina.chain_reaction.domain.feature.order.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class CreditCardDetailsDto implements Serializable {

    private String cardNumber;

    private String expirationDate;

    private String cardholder;

    private String cvv;
}
