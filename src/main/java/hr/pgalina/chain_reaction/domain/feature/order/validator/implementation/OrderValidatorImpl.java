package hr.pgalina.chain_reaction.domain.feature.order.validator.implementation;

import hr.pgalina.chain_reaction.domain.exception.BadRequestException;
import hr.pgalina.chain_reaction.domain.exception.constant.ErrorTypeConstants;
import hr.pgalina.chain_reaction.domain.feature.order.form.OrderForm;
import hr.pgalina.chain_reaction.domain.feature.order.validator.OrderValidator;
import hr.pgalina.chain_reaction.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static hr.pgalina.chain_reaction.domain.exception.constant.ExceptionMessages.PRODUCT_IS_NOT_AVAILABLE_IN_SPECIFIED_QUANTITY;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderValidatorImpl implements OrderValidator {

    private final ProductRepository productRepository;

    @Override
    public void validateOrderForm(OrderForm orderForm) {
        log.info("Entered validateOrderForm in OrderValidatorImpl.");

        orderForm
            .getProducts()
            .stream()
            .forEach(cartItem -> {
                if (!productRepository.isQuantityOfProductAvailable(cartItem.getIdProduct(), cartItem.getQuantity())) {
                    throw new BadRequestException(ErrorTypeConstants.ERROR, HttpStatus.BAD_REQUEST, PRODUCT_IS_NOT_AVAILABLE_IN_SPECIFIED_QUANTITY);
                }
            });
    }
}
