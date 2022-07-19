package hr.pgalina.chain_reaction.domain.mapper;

import hr.pgalina.chain_reaction.domain.features.product.dto.ProductColorDto;
import hr.pgalina.chain_reaction.domain.features.product.enumeration.ProductColor;
import org.springframework.stereotype.Component;

@Component
public class ProductColorMapper {

    public ProductColorDto mapToDto(ProductColor productColor) {
        ProductColorDto productColorDto = new ProductColorDto();

        productColorDto.setIdProductColor(productColorDto.getIdProductColor());
        productColorDto.setValue(productColorDto.getValue());

        return productColorDto;
    }
}
