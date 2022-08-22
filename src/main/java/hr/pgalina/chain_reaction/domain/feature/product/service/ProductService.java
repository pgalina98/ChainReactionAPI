package hr.pgalina.chain_reaction.domain.feature.product.service;

import hr.pgalina.chain_reaction.domain.feature.product.dto.ProductDto;
import hr.pgalina.chain_reaction.domain.feature.product.dto.ProductFilter;
import hr.pgalina.chain_reaction.domain.feature.product.dto.ProductPage;
import hr.pgalina.chain_reaction.domain.feature.product.enumeration.ProductType;

import java.util.ArrayList;
import java.util.List;

public interface ProductService {

    List<ProductDto> getProductsByProductTypeAndProductName(ProductType productType, String productName);

    ProductDto getProductById(Long idProduct);

    ProductPage getProductsByFilter(Integer page, Integer size, ArrayList<Short> productTypes, ProductFilter filter);
}
