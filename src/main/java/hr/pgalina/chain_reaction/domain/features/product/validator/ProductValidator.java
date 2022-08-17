package hr.pgalina.chain_reaction.domain.features.product.validator;

public interface ProductValidator {

    void validateProductType(String productType);

    void validateProduct(Long idProduct);
}
