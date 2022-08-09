package hr.pgalina.chain_reaction.domain.features.order.service.implementation;

import hr.pgalina.chain_reaction.domain.entity.Product;
import hr.pgalina.chain_reaction.domain.exception.BadRequestException;
import hr.pgalina.chain_reaction.domain.exception.constant.ErrorTypeConstants;
import hr.pgalina.chain_reaction.domain.features.order.dto.ProductDto;
import hr.pgalina.chain_reaction.domain.features.order.enumeration.ProductType;
import hr.pgalina.chain_reaction.domain.features.order.service.ProductService;
import hr.pgalina.chain_reaction.domain.mapper.ProductMapper;
import hr.pgalina.chain_reaction.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static hr.pgalina.chain_reaction.domain.exception.constant.ExceptionMessages.PRODUCT_DOES_NOT_EXIST;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> getProductsByProductType(ProductType productType) {
        log.info("Entered getProductsByProductType in ProductServiceImpl with productType {}.", productType.getValue());

        return productMapper
            .mapToDtos(productRepository.findProductsByType(productType.getIdProductType()));
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDto getProductById(Long idProduct) {
        log.info("Entered getProductById in ProductServiceImpl with idProduct {}.", idProduct);

        Product product = productRepository
            .findById(idProduct)
            .orElseThrow(() -> new BadRequestException(ErrorTypeConstants.ERROR, HttpStatus.NOT_FOUND, PRODUCT_DOES_NOT_EXIST));

        return productMapper.mapToDto(product);
    }
}
