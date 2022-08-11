package hr.pgalina.chain_reaction.domain.controller;

import hr.pgalina.chain_reaction.domain.features.order.dto.ProductDto;
import hr.pgalina.chain_reaction.domain.features.order.dto.ProductFilter;
import hr.pgalina.chain_reaction.domain.features.order.dto.ProductPage;
import hr.pgalina.chain_reaction.domain.features.order.enumeration.ProductType;
import hr.pgalina.chain_reaction.domain.features.order.service.ProductService;
import hr.pgalina.chain_reaction.domain.features.order.validator.ProductValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductValidator productValidator;

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> fetchProductsByProductType(@RequestParam String productType) {
        log.info("Entered '/api/products' with product type {} [GET].", productType);

        productValidator.validateProductType(productType);

        return new ResponseEntity<>(productService.getProductsByProductType(ProductType.findByValue(productType)), HttpStatus.OK);
    }

    @GetMapping("/{idProduct}")
    public ResponseEntity<ProductDto> fetchProductById(@PathVariable Long idProduct) {
        log.info("Entered '/api/products/{idProduct}' with product ID {} [GET].", idProduct);

        return new ResponseEntity<>(productService.getProductById(idProduct), HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<ProductPage> fetchProductsByFilter (
        @RequestParam(required = false, defaultValue = "1") int page,
        @RequestParam(required = false, defaultValue = "9999") int size,
        @RequestBody ProductFilter filter
    ) {
        log.info("Entered '/api/products' with page {}, size {} and filter [POST].", page, size, filter);

        return new ResponseEntity<>(productService.getProductsByFilter(page, size, filter), HttpStatus.OK);
    }
}
