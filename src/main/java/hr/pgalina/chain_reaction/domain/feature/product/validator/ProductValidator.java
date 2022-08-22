package hr.pgalina.chain_reaction.domain.feature.product.validator;

public interface ProductValidator {

    void validateProductType(String productType);

    void validateProduct(Long idProduct);
}
