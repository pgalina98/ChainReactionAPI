package hr.pgalina.chain_reaction.domain.mapper;

import hr.pgalina.chain_reaction.domain.features.product.dto.ProductTypeDto;
import hr.pgalina.chain_reaction.domain.features.product.enumeration.ProductType;
import org.springframework.stereotype.Component;

@Component
public class ProductTypeMapper {

    public ProductTypeDto mapToDto(ProductType productType) {
        ProductTypeDto productTypeDto = new ProductTypeDto();

        productTypeDto.setIdProductType(productType.getIdProductType());
        productTypeDto.setValue(productType.getValue());

        return productTypeDto;
    }
}
