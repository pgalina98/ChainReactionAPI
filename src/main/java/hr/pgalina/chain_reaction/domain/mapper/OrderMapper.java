package hr.pgalina.chain_reaction.domain.mapper;

import hr.pgalina.chain_reaction.domain.entity.DiscountCode;
import hr.pgalina.chain_reaction.domain.entity.Order;
import hr.pgalina.chain_reaction.domain.exception.BadRequestException;
import hr.pgalina.chain_reaction.domain.exception.constant.ErrorTypeConstants;
import hr.pgalina.chain_reaction.domain.feature.order.form.OrderForm;
import hr.pgalina.chain_reaction.domain.repository.DiscountCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static hr.pgalina.chain_reaction.domain.exception.constant.ExceptionMessages.DISCOUNT_CODE_DOES_NOT_EXIST;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final DiscountCodeRepository discountCodeRepository;

    private final OrderProductMapper orderProductMapper;

    public Order mapToEntity(OrderForm orderForm) {
        Order order = new Order();

        order.setBuyer(orderForm.getBuyer());
        order.setPhoneNumber(orderForm.getPhoneNumber());
        order.setDeliveryType(orderForm.getDeliveryType().getIdDeliveryType());
        order.setCity(orderForm.getDeliveryAddress().getCity());
        order.setAddress(orderForm.getDeliveryAddress().getAddress());
        order.setZipCode(orderForm.getDeliveryAddress().getZipCode());
        order.setPaymentMethod(orderForm.getPaymentMethod().getIdPaymentMethod());
        order.setCardNumber(orderForm.getCreditCardDetails().getCardNumber());
        order.setExpirationDate(orderForm.getCreditCardDetails().getExpirationDate());
        order.setCardholder(orderForm.getCreditCardDetails().getCardholder());
        order.setCvv(orderForm.getCreditCardDetails().getCvv());

        if (orderForm.getUseDiscountCode()) {
            DiscountCode discountCode = discountCodeRepository
                .findById(orderForm.getDiscountCode().getIdDiscountCode())
                .orElseThrow(() -> new BadRequestException(ErrorTypeConstants.ERROR, HttpStatus.NOT_FOUND, DISCOUNT_CODE_DOES_NOT_EXIST));

            order.setDiscountCode(discountCode);
        }

        order.setProducts(orderProductMapper.mapToEntities(orderForm.getProducts(), order));
        order.setTotal(orderForm.getTotal());

        return order;
    }
}
