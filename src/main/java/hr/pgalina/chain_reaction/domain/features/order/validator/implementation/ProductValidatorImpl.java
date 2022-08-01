package hr.pgalina.chain_reaction.domain.features.order.validator.implementation;

import hr.pgalina.chain_reaction.domain.entity.Product;
import hr.pgalina.chain_reaction.domain.exception.BadRequestException;
import hr.pgalina.chain_reaction.domain.exception.contants.ErrorTypeConstants;
import hr.pgalina.chain_reaction.domain.features.order.enumeration.ProductType;
import hr.pgalina.chain_reaction.domain.features.order.validator.ProductValidator;
import hr.pgalina.chain_reaction.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static hr.pgalina.chain_reaction.domain.exception.contants.ExceptionMessages.PRODUCT_TYPE_DOES_NOT_EXIST;
import static hr.pgalina.chain_reaction.domain.exception.contants.ExceptionMessages.PRODUCT_DOES_NOT_EXIST;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductValidatorImpl implements ProductValidator {

    private final ProductRepository productRepository;

    @Override
    public void validateProductType(String value) {
        log.info("Entered validateProductType in ProductValidatorImpl with value {}.", value);

        ProductType productType = ProductType.findByValue(value);

        if(Objects.isNull(productType)) {
            throw new BadRequestException(ErrorTypeConstants.ERROR, HttpStatus.NOT_FOUND, PRODUCT_TYPE_DOES_NOT_EXIST);
        }
    }

    @Override
    public void validateProduct(Long idProduct) {
        log.info("Entered validateProduct in ProductValidatorImpl with idProduct {}.", idProduct);

        productRepository
            .existsByIdProduct(idProduct)
            .orElseThrow(() -> new BadRequestException(ErrorTypeConstants.ERROR, HttpStatus.NOT_FOUND, PRODUCT_DOES_NOT_EXIST));
    }
}
