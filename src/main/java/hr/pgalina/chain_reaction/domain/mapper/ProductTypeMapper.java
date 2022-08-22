package hr.pgalina.chain_reaction.domain.mapper;

import hr.pgalina.chain_reaction.domain.feature.product.dto.ProductTypeDto;
import hr.pgalina.chain_reaction.domain.feature.product.enumeration.ProductType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductTypeMapper {

    public ProductTypeDto mapToDto(ProductType productType) {
        ProductTypeDto productTypeDto = new ProductTypeDto();

        productTypeDto.setIdProductType(productType.getIdProductType());
        productTypeDto.setValue(productType.getValue());

        return productTypeDto;
    }
}
