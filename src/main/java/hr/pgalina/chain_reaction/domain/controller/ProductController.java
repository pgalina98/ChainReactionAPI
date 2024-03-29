package hr.pgalina.chain_reaction.domain.controller;

import hr.pgalina.chain_reaction.domain.feature.product.dto.ProductDto;
import hr.pgalina.chain_reaction.domain.feature.product.dto.ProductFilter;
import hr.pgalina.chain_reaction.domain.feature.product.dto.ProductPage;
import hr.pgalina.chain_reaction.domain.feature.product.enumeration.ProductType;
import hr.pgalina.chain_reaction.domain.feature.product.service.ProductService;
import hr.pgalina.chain_reaction.domain.feature.product.validator.ProductValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductValidator productValidator;

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> fetchProductsByProductTypeAndProductName(
        @RequestParam String productType,
        @RequestParam(required = false) String productName
    ) {
        log.info("Entered '/api/products' with product type {} and product name {} [GET].", productType, productName);

        productValidator.validateProductType(productType);

        return new ResponseEntity<>(productService.getProductsByProductTypeAndProductName(ProductType.findByValue(productType), productName), HttpStatus.OK);
    }

    @GetMapping("/{idProduct}")
    public ResponseEntity<ProductDto> fetchProductById(@PathVariable Long idProduct) {
        log.info("Entered '/api/products/{idProduct}' with product ID {} [GET].", idProduct);

        return new ResponseEntity<>(productService.getProductById(idProduct), HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<ProductPage> fetchProductsByFilter (
        @RequestParam(required = false, defaultValue = "1") Integer page,
        @RequestParam(required = false, defaultValue = "9999") Integer size,
        @RequestParam(required = false) ArrayList<Short> productTypes,
        @RequestBody ProductFilter filter
    ) {
        log.info("Entered '/api/products/filter' with page {}, size {}, productTypes {} and filter {} [POST].", page, size, productTypes, filter);

        return new ResponseEntity<>(productService.getProductsByFilter(page, size, productTypes, filter), HttpStatus.OK);
    }
}
