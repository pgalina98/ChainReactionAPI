package hr.pgalina.chain_reaction.domain.features.order.service;

import hr.pgalina.chain_reaction.domain.features.order.dto.ProductDto;
import hr.pgalina.chain_reaction.domain.features.order.enumeration.ProductType;

import java.util.List;

public interface ProductService {

    List<ProductDto> getProductsByProductType(ProductType productType);

    ProductDto getProductById(Long idProduct);
}
