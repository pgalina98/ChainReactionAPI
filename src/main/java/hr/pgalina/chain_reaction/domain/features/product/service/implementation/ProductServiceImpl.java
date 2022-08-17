package hr.pgalina.chain_reaction.domain.features.product.service.implementation;

import hr.pgalina.chain_reaction.domain.entity.Product;
import hr.pgalina.chain_reaction.domain.exception.BadRequestException;
import hr.pgalina.chain_reaction.domain.exception.constant.ErrorTypeConstants;
import hr.pgalina.chain_reaction.domain.features.product.dto.ProductDto;
import hr.pgalina.chain_reaction.domain.features.product.dto.ProductFilter;
import hr.pgalina.chain_reaction.domain.features.product.dto.ProductPage;
import hr.pgalina.chain_reaction.domain.features.product.enumeration.ProductType;
import hr.pgalina.chain_reaction.domain.features.product.service.ProductService;
import hr.pgalina.chain_reaction.domain.mapper.ProductMapper;
import hr.pgalina.chain_reaction.domain.repository.ProductCustomRepository;
import hr.pgalina.chain_reaction.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static hr.pgalina.chain_reaction.domain.exception.constant.ExceptionMessages.PRODUCT_DOES_NOT_EXIST;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private static final String PRIMARY_SORT_COLUMN = "name";
    private static final String SECONDARY_SORT_COLUMN = "model";

    private final ProductRepository productRepository;
    private final ProductCustomRepository productCustomRepository;

    private final ProductMapper productMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> getProductsByProductTypeAndProductName(ProductType productType, String productName) {
        log.info("Entered getProductsByProductType in ProductServiceImpl with productType {} and productName {}.", productType.getValue(), productName);

        return productMapper
            .mapToDtos(productCustomRepository.findAllByProductTypeAndProductName(productType.getIdProductType(), productName));
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

    @Override
    public ProductPage getProductsByFilter(Integer page, Integer size, ArrayList<Short> productTypes, ProductFilter filter) {
        log.info("Entered getProductsByFilter in ProductServiceImpl with page {}, size {}, productTypes {} and filter {}.", page, size, productTypes, filter);

        PageRequest pageable = PageRequest.of(
            page - 1,
            size,
            Sort.by(Sort.Direction.ASC, PRIMARY_SORT_COLUMN, SECONDARY_SORT_COLUMN)
        );

        return productCustomRepository.findAllByPageable(pageable, productTypes, filter);
    }
}
