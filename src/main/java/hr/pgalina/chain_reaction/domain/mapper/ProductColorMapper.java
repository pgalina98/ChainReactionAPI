package hr.pgalina.chain_reaction.domain.mapper;

import hr.pgalina.chain_reaction.domain.feature.product.dto.ProductColorDto;
import hr.pgalina.chain_reaction.domain.feature.product.enumeration.ProductColor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductColorMapper {

    public ProductColorDto mapToDto(ProductColor productColor) {
        ProductColorDto productColorDto = new ProductColorDto();

        productColorDto.setIdProductColor(productColor.getIdProductColor());
        productColorDto.setValue(productColor.getValue());

        return productColorDto;
    }
}
