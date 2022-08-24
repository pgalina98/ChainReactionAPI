package hr.pgalina.chain_reaction.domain.feature.order.validator.implementation;

import hr.pgalina.chain_reaction.domain.entity.Product;
import hr.pgalina.chain_reaction.domain.exception.BadRequestException;
import hr.pgalina.chain_reaction.domain.exception.constant.ErrorTypeConstants;
import hr.pgalina.chain_reaction.domain.feature.order.enumeration.DeliveryType;
import hr.pgalina.chain_reaction.domain.feature.order.enumeration.PaymentMethod;
import hr.pgalina.chain_reaction.domain.feature.order.form.OrderForm;
import hr.pgalina.chain_reaction.domain.feature.order.validator.OrderValidator;
import hr.pgalina.chain_reaction.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static hr.pgalina.chain_reaction.domain.exception.constant.ExceptionMessages.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderValidatorImpl implements OrderValidator {

    private final ProductRepository productRepository;

    @Override
    public void validateOrderForm(OrderForm orderForm) {
        log.info("Entered validateOrderForm in OrderValidatorImpl.");

        DeliveryType deliveryType = DeliveryType.findByIdDeliveryType(orderForm.getDeliveryType().getIdDeliveryType());

        if (Objects.isNull(deliveryType)) {
            throw new BadRequestException(ErrorTypeConstants.ERROR, HttpStatus.NOT_FOUND, DELIVERY_TYPE_DOES_NOT_EXIST);
        }

        PaymentMethod paymentMethod = PaymentMethod.findByIdPaymentMethod(orderForm.getPaymentMethod().getIdPaymentMethod());

        if (Objects.isNull(paymentMethod)) {
            throw new BadRequestException(ErrorTypeConstants.ERROR, HttpStatus.NOT_FOUND, PAYMENT_METHOD_DOES_NOT_EXIST);
        }

        orderForm
            .getProducts()
            .stream()
            .forEach(cartItem -> {
                Product product = productRepository
                    .findById(cartItem.getIdProduct())
                    .orElseThrow(() -> new BadRequestException(ErrorTypeConstants.ERROR, HttpStatus.NOT_FOUND, PRODUCT_DOES_NOT_EXIST));

                if (!productRepository.isProductQuantityAvailable(cartItem.getIdProduct(), cartItem.getQuantity())) {
                    throw new BadRequestException(ErrorTypeConstants.ERROR, HttpStatus.BAD_REQUEST, String.format(PRODUCT_IS_NOT_AVAILABLE_IN_SPECIFIED_QUANTITY, product.getName(), product.getModel()));
                }
            });
    }
}
