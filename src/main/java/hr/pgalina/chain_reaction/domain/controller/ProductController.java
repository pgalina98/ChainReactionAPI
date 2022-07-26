package hr.pgalina.chain_reaction.domain.controller;

import hr.pgalina.chain_reaction.domain.features.product.dto.ProductDto;
import hr.pgalina.chain_reaction.domain.features.product.enumeration.ProductType;
import hr.pgalina.chain_reaction.domain.features.product.service.ProductService;
import hr.pgalina.chain_reaction.domain.features.product.validator.ProductValidator;
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
}
