package hr.pgalina.chain_reaction.domain.features.product.service;

import hr.pgalina.chain_reaction.domain.features.product.dto.ProductDto;
import hr.pgalina.chain_reaction.domain.features.product.enumeration.ProductType;

import java.util.List;

public interface ProductService {

    List<ProductDto> getProductsByProductType(ProductType productType);
}
