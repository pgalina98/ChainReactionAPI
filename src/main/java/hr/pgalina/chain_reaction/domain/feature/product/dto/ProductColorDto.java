package hr.pgalina.chain_reaction.domain.feature.product.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class ProductColorDto implements Serializable {

    private Short idProductColor;
    
    private String value;
}
