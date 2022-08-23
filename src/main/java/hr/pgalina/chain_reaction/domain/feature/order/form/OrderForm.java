package hr.pgalina.chain_reaction.domain.feature.order.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hr.pgalina.chain_reaction.domain.feature.cart.dto.CartItemDto;
import hr.pgalina.chain_reaction.domain.feature.discount_code.dto.DiscountCodeDto;
import hr.pgalina.chain_reaction.domain.feature.order.dto.AddressDto;
import hr.pgalina.chain_reaction.domain.feature.order.dto.CreditCardDetailsDto;
import hr.pgalina.chain_reaction.domain.feature.order.dto.DeliveryTypeDto;
import hr.pgalina.chain_reaction.domain.feature.order.dto.PaymentMethodDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderForm implements Serializable {

    private Long idUser;

    private List<CartItemDto> products;

    private String buyer;

    private String phoneNumber;

    private DeliveryTypeDto deliveryType;

    private AddressDto deliveryAddress;

    private PaymentMethodDto paymentMethod;
    
    private CreditCardDetailsDto creditCardDetails;
    
    private DiscountCodeDto discountCode;
}
