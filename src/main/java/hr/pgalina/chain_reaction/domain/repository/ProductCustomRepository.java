package hr.pgalina.chain_reaction.domain.repository;

import com.querydsl.core.BooleanBuilder;
import hr.pgalina.chain_reaction.domain.entity.Product;
import hr.pgalina.chain_reaction.domain.features.order.dto.ProductFilter;
import hr.pgalina.chain_reaction.domain.features.order.dto.ProductPage;
import hr.pgalina.chain_reaction.domain.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static hr.pgalina.chain_reaction.domain.entity.QProduct.product;

@Component
@RequiredArgsConstructor
public class ProductCustomRepository {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public ProductPage findAllByPageable(
        PageRequest pageable,
        ProductFilter filter
    ) {
        BooleanBuilder where = createFilterPredicate(filter);

        Page<Product> productsPage =  productRepository.findAll(where, pageable);

        return ProductPage
            .builder()
            .totalElements(productsPage.getTotalElements())
            .products(productMapper.mapToDtos(productsPage.getContent()))
            .build();
    }

    private BooleanBuilder createFilterPredicate(ProductFilter filter) {
        BooleanBuilder where = new BooleanBuilder();

        if (Objects.nonNull(filter.getKeyword()) && Strings.isNotBlank(filter.getKeyword())) {
            where
                .and(product.name.likeIgnoreCase(filter.getKeyword()))
                .or(product.model.likeIgnoreCase(filter.getKeyword()));
        }

        if (Objects.nonNull(filter.getBrands()) && filter.getBrands().size() > 0) {
            where
                .and(product.name.in(filter.getBrands()));
        }

        if (Objects.nonNull(filter.getTypes()) && filter.getTypes().size() > 0) {
            where
                .and(product.type.in(filter.getTypes()));
        }

        if (Objects.nonNull(filter.getColors()) && filter.getColors().size() > 0) {
            where
                .and(product.color.in(filter.getColors()));
        }
        
        if (Objects.nonNull(filter.getMaxPrize())) {
            where
                .and(product.price.lt(filter.getMaxPrize()))
                .or(product.price.eq(filter.getMaxPrize()));
        }

        return where;
    }
}
