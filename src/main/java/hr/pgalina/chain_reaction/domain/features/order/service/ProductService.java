package hr.pgalina.chain_reaction.domain.features.order.service;

import hr.pgalina.chain_reaction.domain.features.order.dto.ProductDto;
import hr.pgalina.chain_reaction.domain.features.order.dto.ProductFilter;
import hr.pgalina.chain_reaction.domain.features.order.dto.ProductPage;
import hr.pgalina.chain_reaction.domain.features.order.enumeration.ProductType;

import java.util.ArrayList;
import java.util.List;

public interface ProductService {

    List<ProductDto> getProductsByProductType(ProductType productType);

    ProductDto getProductById(Long idProduct);

    ProductPage getProductsByFilter(Integer page, Integer size, ArrayList<Short> productTypes, ProductFilter filter);
}
