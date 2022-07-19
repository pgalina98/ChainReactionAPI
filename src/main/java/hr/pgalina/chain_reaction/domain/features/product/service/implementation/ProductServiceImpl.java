package hr.pgalina.chain_reaction.domain.features.product.service.implementation;

import hr.pgalina.chain_reaction.domain.features.product.dto.ProductDto;
import hr.pgalina.chain_reaction.domain.features.product.enumeration.ProductType;
import hr.pgalina.chain_reaction.domain.features.product.service.ProductService;
import hr.pgalina.chain_reaction.domain.mapper.ProductMapper;
import hr.pgalina.chain_reaction.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    @Override
    public List<ProductDto> getProductsByProductType(ProductType productType) {
        log.info("Entered getProductsByProductType in ProductServiceImpl with productType {}.", productType.getValue());

        return productMapper
            .mapToDtos(productRepository.findProductsByType(productType.getIdProductType()));
    }
}
