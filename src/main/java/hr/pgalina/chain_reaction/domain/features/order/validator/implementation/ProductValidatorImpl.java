package hr.pgalina.chain_reaction.domain.features.order.validator.implementation;

import hr.pgalina.chain_reaction.domain.exception.BadRequestException;
import hr.pgalina.chain_reaction.domain.exception.contants.ErrorTypeConstants;
import hr.pgalina.chain_reaction.domain.features.order.enumeration.ProductType;
import hr.pgalina.chain_reaction.domain.features.order.validator.ProductValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static hr.pgalina.chain_reaction.domain.exception.contants.ExceptionMessages.PRODUCT_TYPE_DOES_NOT_EXIST;

@Slf4j
@Component
public class ProductValidatorImpl implements ProductValidator {

    @Override
    public void validateProductType(String value) {
        log.info("Entered validateProductType in ProductValidatorImpl with value {}.", value);

        ProductType productType = ProductType.findByValue(value);

        if(Objects.isNull(productType)) {
            throw new BadRequestException(ErrorTypeConstants.ERROR, HttpStatus.NOT_FOUND, PRODUCT_TYPE_DOES_NOT_EXIST);
        }
    }
}
